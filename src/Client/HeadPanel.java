package Client;
import java.awt.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.*;

import Net.Client;
import Net.Server;
import Net.localClient;
import Start.Startpanel3;
import Start.Startpanel2;
public class HeadPanel extends JPanel{
public JLabel headlabel,namelabel,iplabel,portlabel,marklabel;
public Client c;
public int headx;
public String namex;
public int mark;
public mainPanel mp;
public localClient lc;
public HeadPanel(mainPanel mp) 
{   
	this.mp = mp;
	this.lc = mp.lc;
	mark = 0;
	this.headx = mp.sf.startpanel2.headx;
	namex = mp.sf.startpanel3.name;
	namelabel = new JLabel(mp.sf.startpanel3.name);
	headlabel = mp.sf.startpanel2.chooselabel;
	headlabel.setPreferredSize(new Dimension(100,90));
	iplabel = new JLabel("  "+lc.ipstr);
	if(mp.server)
	     portlabel = new JLabel("房主");	     
	else
		 portlabel = new JLabel("房客");
	portlabel.setFont(new Font("楷体",Font.ITALIC|Font.BOLD,16));
	iplabel.setPreferredSize(new Dimension(100,30));
	headlabel.setLayout(new GridLayout(5,1));
	marklabel = new JLabel("得分:"+mark);
	this.add(iplabel);
	this.add(portlabel);
	this.add(headlabel);
	this.add(namelabel);
	this.add(marklabel);
	this.setPreferredSize(new Dimension(100,180));
}
public HeadPanel(mainPanel mp,boolean server) 
{   
	this.mp = mp;
	this.c = mp.c;
	this.headx = mp.sf.startpanel2.headx;
	namex = mp.sf.startpanel3.name;
	namelabel = new JLabel(mp.sf.startpanel3.name);
	headlabel = mp.sf.startpanel2.chooselabel;
	headlabel.setPreferredSize(new Dimension(100,90));
	iplabel = new JLabel("  "+c.ipstr);
	if(mp.server)
	     portlabel = new JLabel("房主");
	else
		 portlabel = new JLabel("房客");
	iplabel.setPreferredSize(new Dimension(100,30));
	headlabel.setLayout(new GridLayout(3,1));
	marklabel = new JLabel("得分:"+mark);
	this.add(iplabel);
	this.add(portlabel);
	this.add(headlabel);
	this.add(namelabel);
	this.add(marklabel);
	this.setPreferredSize(new Dimension(100,180));
}
}
