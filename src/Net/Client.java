package Net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Client.Client2;
import Client.Client3;
import Client.mainPanel;

public class Client extends Thread {
	public String ipstr;
	public InetAddress ip;
	Socket s = null;
	DataOutputStream dos = null;
	DataInputStream dis = null;
	mainPanel mp;
	int headx;
	String namex;
	public ArrayList<String> headxlist;
	public ArrayList<String> iplist;
	public ArrayList<String> namexlist;
	public Client3 c3;
	private int n = 0;

	public Client(mainPanel mp, Client3 c3) {
		this.mp = mp;
		this.c3 = c3;
		headx = mp.sf.startpanel2.headx;
		namex = mp.sf.startpanel3.name;
		try {
			ip = InetAddress.getLocalHost();
			ipstr = ip.getHostAddress();
			s = c3.s;
			dos = new DataOutputStream(s.getOutputStream());
			dis = new DataInputStream(s.getInputStream());
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
		while (true) {
			try {
				receive();
			} catch (Exception e) {
			}
		}
	}

	public void receive() {
		try {
			headxlist = new ArrayList<String>();
			iplist = new ArrayList<String>();
			namexlist = new ArrayList<String>();
			int x = Integer.parseInt(dis.readUTF());
			for (int i = 0; i < x; i++) {
				headxlist.add(dis.readUTF());
				iplist.add(dis.readUTF());
				namexlist.add(dis.readUTF());
			}
			mp.downpanel.creplay();
			mp.downpanel.updateUI();
		} catch (NumberFormatException | IOException e) {
			//e.printStackTrace();
		}
	}
	public void exitsend(){
		try {
			dos.writeUTF("0");
			dos.writeUTF(Integer.toString(headx));
			dos.writeUTF(ipstr);
			dos.writeUTF(namex);
		} catch (IOException e) {
			//e.printStackTrace();
		}
	}
}
