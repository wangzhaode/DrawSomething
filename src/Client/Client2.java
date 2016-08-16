package Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.*;
import javax.swing.*;

import Net.Client;
import Start.StartFrame;

public class Client2 extends Thread {
	public JFrame f2;
	public Client3 c3;
	public String serveripstr;
	JPanel jp;
	JTextField jf;
	JLabel jl;
	JButton jb;
	public StartFrame sf;
    
   

	public Client2(StartFrame sf) {
		this.sf = sf;
		c3 = new Client3(sf,this);
	}

	public void run() {
		f2 = new JFrame();
		f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f2.setBounds(500, 300, 100, 50);
		jp = new JPanel();
		jf = new JTextField(15);
		jl = new JLabel("房间IP");
		jb = new JButton("确定");
		jf.addActionListener(new ClientListener());
		jb.addActionListener(new ClientListener());
		jp.add(jl);
		jp.add(jf);
		jp.add(jb);
		f2.getContentPane().add(jp);
		f2.pack();
		f2.setVisible(true);
	}

	public class ClientListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			serveripstr = jf.getText();
			f2.setVisible(false);
            c3.start();
            jf.setText("");
            	
		}
	}
}
