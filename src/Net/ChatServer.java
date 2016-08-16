package Net;

import java.io.*;
import java.net.*;
import java.util.*;

import Client.mainPanel;

public class ChatServer extends Thread{
	boolean started = false;
	ServerSocket ss = null;
	List<Client> clients = new ArrayList<Client>();
	mainPanel mp;
    public ChatServer(mainPanel mp){this.mp = mp;}
	public void run() {
		try {
			ss = new ServerSocket(8889);
			started = true;
		} catch (BindException e) {
			//System.out.println("端口使用中....");
			//System.out.println("请关掉相关程序并重新运行服务器！");
			System.exit(0);
		} catch (IOException e) {
			//e.printStackTrace();
		}

		try {

			while (started) {
				Socket s = ss.accept();
				Client c = new Client(s);
				new Thread(c).start();
				clients.add(c);
			}
		} catch (IOException e) {
			//e.printStackTrace();
		} finally {
			try {
				ss.close();
			} catch (IOException e) {
				//e.printStackTrace();
			}
		}
	}

	class Client implements Runnable {
		private Socket s;
		private DataInputStream dis = null;
		private DataOutputStream dos = null;
		private boolean bConnected = false;

		public Client(Socket s) {
			this.s = s;
			try {
				dis = new DataInputStream(s.getInputStream());
				dos = new DataOutputStream(s.getOutputStream());
				bConnected = true;
			} catch (IOException e) {
				//e.printStackTrace();
			}
		}

		public void send(String str) {
			try {
				dos.writeUTF(str);
			} catch (IOException e) {
				clients.remove(this);
			}
		}

		public void run() {
			try {
				while (bConnected) {
					String str = dis.readUTF();
					//System.out.println(str);
					if(str.contains("correct"))
					{   
						String nam = str.substring(7,str.length());
						for (int i = 0; i < clients.size(); i++) {
							Client c = clients.get(i);
							c.send("恭喜 \""+nam+"\"答对了，"+"\n"+"答案就是"+"—"+mp.uppanel1.word+"\n"+"房主与"+nam+"各得一分。");
						}
					}
					else{
					for (int i = 0; i < clients.size(); i++) {
						Client c = clients.get(i);
						c.send(str);
					}
					}
				}
			} catch (Exception e) {
				///System.out.println("Client closed! from ChatServer run2");
			} finally {
				try {
					if (dis != null)
						dis.close();
					if (dos != null)
						dos.close();
					if (s != null) {
						s.close();
					}

				} catch (IOException e1) {
					//e1.printStackTrace();
				}

			}
		}

	}
}
