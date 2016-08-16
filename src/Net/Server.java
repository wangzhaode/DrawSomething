package Net;

import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.JOptionPane;

import Client.mainPanel;

public class Server extends Thread {

	public mainPanel mp;
	public ArrayList<String> headxlist = new ArrayList<String>();
	public ArrayList<String> iplist = new ArrayList<String>();
	public ArrayList<String> namexlist = new ArrayList<String>();
	ServerSocket ss = null;
	ArrayList<Clients> clients = new ArrayList<Clients>();
	private int x = 0;

	public Server(mainPanel mp) {
		this.mp = mp;
	}

	public void run() {
		try {
			ss = new ServerSocket(8888);
		} catch (BindException e) {
			JOptionPane.showConfirmDialog(null, "程序已被打开，请关闭重试！");
			System.exit(0);
		} catch (IOException e) {
			// e.printStackTrace();
		}
		try {
			while (!mp.uppanel1.gamestart) {
				Socket s = ss.accept();
				Clients c = new Clients(s);
				clients.add(c);
				new Thread(c).start();
			}
		} catch (IOException e) {
			// e.printStackTrace();
		} finally {
			try {
				ss.close();
			} catch (IOException e) {
				// e.printStackTrace();
			}

		}

	}

	public class Clients implements Runnable {
		private Socket s;
		public ObjectInputStream dinput = null;
		public ObjectOutputStream dout = null;
		private DataInputStream dis = null;
		private DataOutputStream dos = null;
		private boolean bConnected = false;

		public Clients(Socket s) {
			this.s = s;
			try {
				dis = new DataInputStream(s.getInputStream());
				dos = new DataOutputStream(s.getOutputStream());
			} catch (IOException e) {
				// e.printStackTrace();
			}
		}

		public void send(String str) {
			try {
				dos.writeUTF(str);
			} catch (IOException e) {
				clients.remove(this);
				// System.out.println("Client closed! from Server send");
			}
		}

		public void output() {
			if (clients.size() == 1) {
				Clients cs2 = clients.get(0);
				cs2.send("0");
			} else {
				for (int i = 0; i < clients.size(); i++) {
					Clients cs2 = clients.get(i);
					cs2.send(Integer.toString(clients.size() - 1));
					for (int j = 0; j < clients.size(); j++) {
						if (j != i) {
							cs2.send(headxlist.get(j));
							cs2.send(iplist.get(j));
							cs2.send(namexlist.get(j));
						}
					}
				}
			}
		}

		public void run() {
			String str;
			try {
				while (!clients.isEmpty()) {
					str = dis.readUTF();
					if (str.equals("1")) {
						headxlist.add(dis.readUTF());
						iplist.add(dis.readUTF());
						namexlist.add(dis.readUTF());
					} else {
						headxlist.remove(dis.readUTF());
						iplist.remove(dis.readUTF());
						namexlist.remove(dis.readUTF());
						// System.out.println("delect the client");
						clients.remove(this);
					}
					output();
				}
			} catch (Exception e) {
				// e.printStackTrace();
				// System.out.println("Client closed! from Server run");
			}

		}
	}
}
