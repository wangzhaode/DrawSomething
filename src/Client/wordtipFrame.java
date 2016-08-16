package Client;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.*;

import Start.ScaleIcon;
import Client.wordFrame.wordPanel;
import FileManger.SwingResourceManager;

public class wordtipFrame extends JFrame implements Runnable{
	public mainPanel mp;
	public wordtipPanel wp;
public wordtipFrame(mainPanel mp){
	this.mp = mp;
	ScaleIcon icon7 = new ScaleIcon(new ImageIcon(SwingResourceManager.getImage("src/ImageIcon/08.jpg")));
	JLabel jl = new JLabel(icon7);
	this.getLayeredPane().add(jl, new Integer(Integer.MIN_VALUE));
	jl.setBounds(0, 0, 600, 300);
	JPanel cp = (JPanel) this.getContentPane();
	cp.setOpaque(false);
	this.addWindowListener(new WindowListener(){
		public void windowActivated(WindowEvent arg0) {
		}
		public void windowClosed(WindowEvent arg0) {
		}
		public void windowClosing(WindowEvent arg0) {
			wordtipFrame.this.dispose();
		}
		public void windowDeactivated(WindowEvent arg0) {
		}
		public void windowDeiconified(WindowEvent arg0) {
		}
		public void windowIconified(WindowEvent arg0) {
		}
		public void windowOpened(WindowEvent arg0) {
		}});
}
public void run(){
	this.setBounds(500, 100, 310, 500);
	this.setPreferredSize(new Dimension(600,300));
    wp= new wordtipPanel(mp);
    wp.setOpaque(false);
	this.add(wp);
	this.pack();
	this.setVisible(true);
	}
public class wordtipPanel extends JPanel implements ActionListener {
	public String wordstr = "";
	ArrayList<JButton>buttonList;
	public wordtipPanel(mainPanel mp) { 
		this.setOpaque(false);
		if(mp.chatpanel.wordstr==null)
		    wordstr = mp.dataclient.wordstr;
		else
			wordstr = mp.chatpanel.wordstr;
		replay();
		this.setLayout(new GridLayout(5,10));
		this.setPreferredSize(new Dimension(600,300));
	}
	public void replay()
	{   
		this.removeAll();
		this.updateUI();	
		ArrayList<String> strlist = new ArrayList<String>();
		buttonList = new ArrayList<JButton>();
		for (int i = 0; i < wordstr.length(); i++) {
			strlist.add(wordstr.substring(i, i+1));
		}
		for (int i = 0; i <wordstr.length(); i++) {
			int x = (int) (Math.random() * strlist.size());
			JButton button = new JButton(strlist.get(x));
			button.setContentAreaFilled(false);
			button.setFont(new Font("¿¬Ìå",Font.ITALIC|Font.BOLD,16));
			button.addActionListener(this);
			button.setPreferredSize(new Dimension(80,30));
			buttonList.add(button);
			strlist.remove(x);
			this.add(button);
		}
		this.updateUI();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String word;
		for(int i=0;i<buttonList.size();i++)
		{
			if(e.getSource()==buttonList.get(i))
			{
				word = buttonList.get(i).getText();
				String st = mp.chatpanel.tfTxt.getText();
				st += word;
				mp.chatpanel.tfTxt.setText(st);
			}
		}
	}
}
}
