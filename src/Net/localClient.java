package Net;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Client.Client2;
import Client.Client3;
import Client.mainPanel;

public class localClient extends Thread {
	public String ipstr;
	public InetAddress ip;
	Socket s = null;
	DataOutputStream dos = null;
	DataInputStream dis = null;
	mainPanel mp;
	int headx;
	String namex;
	private boolean bConnected = false;
	public ArrayList<String> headxlist;
	public ArrayList<String> iplist;
	public ArrayList<String> namexlist;
    private int n = 0;
	public localClient(mainPanel mp) {
		this.mp = mp;
		try {
			s = new Socket("127.0.0.1",8888);
		} catch (IOException e1) {
			//e1.printStackTrace();
		}
		headx = mp.sf.startpanel2.headx;
		namex = mp.sf.startpanel3.name;
		try {
			ip = InetAddress.getLocalHost();
			ipstr = ip.getHostAddress();
			dos = new DataOutputStream(s.getOutputStream());
			dis = new DataInputStream(s.getInputStream());
			bConnected = true;
		} catch (Exception e) {
		}
	}
	public void run() {
	
		try {
			 dos.writeUTF("1");
			 dos.writeUTF(Integer.toString(headx));
			 dos.writeUTF(ipstr);
			 dos.writeUTF(namex);
		} catch (IOException e) {
			//e.printStackTrace();
		}
		 while(true)
		 {
			 try {
					receive();
			} catch (Exception e) {
				//e.printStackTrace();
			} 
		 }
	}
public void receive()
{
	try {
		headxlist = new ArrayList<String>();
		iplist = new ArrayList<String>();
		namexlist = new ArrayList<String>();
		int x = Integer.parseInt(dis.readUTF());
		if(x==0){
			mp.downpanel.sreplay();
		}
		else{
		for (int i = 0; i < x; i++) {
			headxlist.add(dis.readUTF());
			iplist.add(dis.readUTF());
			namexlist.add(dis.readUTF());
		 }
		 mp.downpanel.sreplay();
		 }
	} catch (NumberFormatException | IOException e) {
		//e.printStackTrace();
	}
}
}

