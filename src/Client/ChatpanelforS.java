package Client;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.*;

public class ChatpanelforS extends JPanel{
	public int mark;
JScrollPane jspanel;
JPanel jpanel;
JTextField tfTxt;
JTextArea taContent;
JButton jbutton;
Socket s = null;
DataOutputStream dos = null;
DataInputStream dis = null;
private boolean bConnected = false;
Thread tRecv = new Thread(new RecvThread()); 
private mainPanel mp;
	public ChatpanelforS(mainPanel mp)
	{    
		mark = 0;
		this.mp = mp;
		mark = 0;
		taContent = new JTextArea();
		taContent.setPreferredSize(new Dimension(200, 350));
		taContent.setOpaque(false);
		taContent.setBorder(BorderFactory.createLineBorder(Color.black));
		taContent.setFont(new Font("楷体",Font.ITALIC|Font.BOLD,20));
		jpanel = new JPanel();
		jpanel.setOpaque(false);
		tfTxt = new JTextField(10);
		tfTxt.setOpaque(false);
		tfTxt.setFont(new Font("楷体",Font.ITALIC|Font.BOLD,20));
		tfTxt.setBorder(BorderFactory.createLineBorder(Color.black));
		jbutton = new JButton("发送");
		jbutton.setContentAreaFilled(false);
		jbutton.setBorder(null);
		jbutton.setFont(new Font("楷体",Font.ITALIC|Font.BOLD,20));
		jbutton.setBorder(BorderFactory.createRaisedBevelBorder());
		jpanel.add(tfTxt);
		jpanel.add(jbutton);
		this.add(taContent);
		this.add(jpanel);
		this.setPreferredSize(new Dimension(200, 410));
		TFListener tf = new TFListener();
		jbutton.addActionListener(tf);
		tfTxt.addActionListener(tf);
		setVisible(true);
		connect();
		tRecv.start();
	}
	public void connect() {
		try {
			s = new Socket("127.0.0.1", 8889);
			dos = new DataOutputStream(s.getOutputStream());
			dis = new DataInputStream(s.getInputStream());
			bConnected = true;
		} catch (UnknownHostException e) {
			//e.printStackTrace();
		} catch (IOException e) {
			//e.printStackTrace();
		}
		
	}
	
	public void disconnect() {
		try {
			dos.close();
			dis.close();
			s.close();
		} catch (IOException e) {
			//e.printStackTrace();
		}
		
	}
	
	private class TFListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String str = mp.sf.startpanel3.name+":"+tfTxt.getText().trim();
			tfTxt.setText("");
			
			try {
				dos.writeUTF(str);
				dos.flush();
			} catch (IOException e1) {
				//e1.printStackTrace();
			}
			
		}
		
	}
	
	private class RecvThread implements Runnable {

		public void run() {
			try {
				while(bConnected) {
					String str = dis.readUTF();
					taContent.setText(taContent.getText() + str + '\n'+'\n');
					if(str.contains("答案就是"))
					{
						mark = mark+1;
						mp.headpanel.marklabel.setText("得分:"+mark);
						mp.uppanel1.timelabel.setText("答对了！");
						mp.paintpanel.List = new ArrayList<Polygon>();
						mp.paintpanel.colorList = new ArrayList<Color>();
						mp.paintpanel.thickList = new ArrayList<Float>();
						mp.paintpanel.repaint();
						mp.uppanel1.timelabel.timer.stop();
						mp.uppanel1.time = 20;
						mp.uppanel1.startbutton.setEnabled(true);
					}
				}
			} catch (SocketException e) {
				//System.out.println("退出了，bye!");
			} catch (EOFException e) {
				//System.out.println("推出了，bye - bye!");
			} catch (IOException e) {
				//e.printStackTrace();
			} 
			
		}
		
	}
}
