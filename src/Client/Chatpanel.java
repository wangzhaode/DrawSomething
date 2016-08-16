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

public class Chatpanel extends JPanel {
	public int mark;
	public String wordstr;
	ArrayList<String> strlist;
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

	public Chatpanel(mainPanel mp) {
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
			s = new Socket(mp.c3.serveripstr, 8889);
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
			try {
				String str = mp.sf.startpanel3.name + ":"
						+ tfTxt.getText().trim();
				dos.writeUTF(str);
				dos.flush();
				if(mp.dataclient.word!=null){
				if (tfTxt.getText().contains(mp.dataclient.word)) {
					mark = mark + 1;
					mp.headpanel.marklabel.setText("得分:" + mark);
					mp.headpanel.updateUI();
					if (wordstr == null) {
						wordstr = mp.dataclient.wordstr;
						strlist = new ArrayList<String>();
						for (int i = 0; i < wordstr.length(); i++) {
							strlist.add(wordstr.substring(i, i + 1));
						}
					}
					ArrayList<String> newlist = new ArrayList<String>();
					for (int i = 0; i < mp.dataclient.word.length(); i++) 
					{
						newlist.add(mp.dataclient.word.substring(i, i + 1));
					}
					for (int i = 0; i < strlist.size(); i++) {
						
						for(int j = 0;j<newlist.size();j++)
						{   
							if (strlist.get(i).equals(newlist.get(j)))
							{  
								strlist.remove(i);
								i = i-1;
								newlist.remove(j);
								
							}
						}
					}
					wordstr = "";
					for (int i = 0; i < strlist.size(); i++) {
						wordstr += strlist.get(i);
					}
					if (mp.uppanel2.wordtipframe!= null) {
						mp.uppanel2.wordtipframe.wp.wordstr = wordstr;
						mp.uppanel2.wordtipframe.wp.replay();
					}
					try {
						dos.writeUTF("correct" + mp.sf.startpanel3.name);
						dos.flush();
					} catch (IOException e1) {
						//e1.printStackTrace();
					}
					mp.uppanel2.timelabel.setText("答对了！");
					mp.uppanel2.problemlabel.setText("提示区");
					mp.uppanel2.timer.stop();
					mp.uppanel2.time = 20;
				}
				}
			} catch (IOException e1) {
				//e1.printStackTrace();
			} finally {
				tfTxt.setText("");
			}

		}

	}

	private class RecvThread implements Runnable {

		public void run() {
			try {
				while (bConnected) {
					String str = dis.readUTF();
					taContent.setText(taContent.getText() + str + '\n' + '\n');
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
