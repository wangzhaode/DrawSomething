package Net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import Client.mainPanel;

public class DataClient extends Thread {
	public Socket s;
	public String tip,word;
	public String wordstr;
	DataInputStream dis;
	private mainPanel mp;
	public DataClient(mainPanel mp){
		this.mp = mp;
	}
	public void run() {
		try {
			s = new Socket(mp.c3.serveripstr, 8891);
			dis = new DataInputStream(s.getInputStream());
			while (true) {
				String ss = dis.readUTF();
				if(ss.equals("1")){
					wordstr = dis.readUTF();
				}
				else{
					word = dis.readUTF();
					tip = dis.readUTF();
					mp.uppanel2.problemlabel.setText(tip);
					mp.uppanel2.timer.start();
				}
			
			}
		} catch (UnknownHostException e) {
			//e.printStackTrace();
		} catch (IOException e) {
		//	e.printStackTrace();
		}

	}
}
