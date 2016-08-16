package Net;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Data.Data;
import Net.ChatServer.Client;

public class DataServer extends Thread{
	boolean started;
	ServerSocket ss = null;
	List<Socket> clients = new ArrayList<Socket>();
	public ResultSet rs;
	public String str = "";
    public DataServer(){}
	public void run() {
		try {
			ss = new ServerSocket(8891);
			started = true;
		} catch (BindException e) {
						System.exit(0);
		} catch (IOException e) {
			//e.printStackTrace();
		}
        Data data = new Data();
        rs = data.getData();
        try {
			while (rs.next()) {
			  str += rs.getString(2);
			}
		} catch (Exception e) {
		}
		try {
			while (started) {
				Socket s = ss.accept();
				DataOutputStream output = new DataOutputStream(s.getOutputStream());
				output.writeUTF("1");
				output.writeUTF(str);
				clients.add(s);
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
public void send(String str){
	try{
	for(Socket s:clients )
	{
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		dos.writeUTF(str);
	}
	}catch(Exception e){}
}
}
