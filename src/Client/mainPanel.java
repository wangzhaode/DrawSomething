package Client;

import java.awt.*;
import java.net.UnknownHostException;

import javax.swing.*;

import Net.*;
import Start.StartFrame;

public class mainPanel extends JPanel {
	public JPanel epanel, wpanel, npanel, spanel;
	public Paintpanel paintpanel;
	public paintpanelclient ppc;
	public Thickpanel thickpanel;
	public Toolchoosepanel toolchoosepanel;
	public Colorselectpanel colorselectpanel;
	public HeadPanel headpanel;
	public Chatpanel chatpanel;
	public ChatpanelforS chatpanels;
	public Uppanel1 uppanel1;
	public Uppanel2 uppanel2;
	public Downpanel downpanel;
	public StartFrame sf;
	public Server s;
	public Client c;
	public Client3 c3;
	public int headx;
	public boolean server;
	public Thread th;
    public Client1 c1;
    public localClient lc;
    public ChatServer cs;
    public DataServer dataserver;
    public DataClient dataclient;
	public mainPanel(StartFrame sf,boolean server,Client1 c1) {
		this.sf = sf;
		this.server = server;
		this.c1 = c1;
		dataserver = new DataServer();
		dataserver.start();
		this.setLayout(new BorderLayout());
		epanel = new JPanel();
		wpanel = new JPanel();
		npanel = new JPanel();
		spanel = new JPanel();
		paintpanel = new Paintpanel(this);
		uppanel1 = new Uppanel1(this);
		colorselectpanel = new Colorselectpanel(this);
		thickpanel = new Thickpanel(this);
		toolchoosepanel = new Toolchoosepanel(this);
		
		epanel.setOpaque(false);
		wpanel.setOpaque(false);
		npanel.setOpaque(false);
		spanel.setOpaque(false);
		paintpanel.setOpaque(false);
		paintpanel.setBorder(BorderFactory.createLineBorder(Color.black));
		uppanel1.setOpaque(false);
		colorselectpanel.setOpaque(false);
		thickpanel.setOpaque(false);
		toolchoosepanel.setOpaque(false);
		
     	s = new Server(this);
		s.start();
		cs = new ChatServer(this);
		cs.start();
		lc = new localClient(this);
		lc.start();
		headpanel = new HeadPanel(this);
		chatpanels = new ChatpanelforS(this);
		downpanel = new Downpanel(this);
		
		headpanel.setOpaque(false);
		chatpanels.setOpaque(false);
		downpanel.setOpaque(false);
		
		epanel.setPreferredSize(new Dimension(120, 350));
		epanel.add(colorselectpanel);
		epanel.add(thickpanel);
		epanel.add(toolchoosepanel);
		epanel.add(headpanel);
		this.add(epanel, BorderLayout.EAST);
		this.add(paintpanel, BorderLayout.CENTER);
		this.add(chatpanels, BorderLayout.WEST);
		this.add(uppanel1, BorderLayout.NORTH);
		this.add(downpanel, BorderLayout.SOUTH);
	}

	public mainPanel(StartFrame sf, Client3 c3,boolean server) {
		this.sf = sf;
		this.c3 = c3;
		this.server = server;
		this.setLayout(new BorderLayout());
		downpanel = new Downpanel(this,server);
		c = new Client(this, c3);
		c.start();
		downpanel.setOpaque(false);
		
		epanel = new JPanel();
		wpanel = new JPanel();
		npanel = new JPanel();
		spanel = new JPanel();
		ppc = new paintpanelclient(this);
		colorselectpanel = new Colorselectpanel(this);
		thickpanel = new Thickpanel(this);
		toolchoosepanel = new Toolchoosepanel(this);
		headpanel = new HeadPanel(this,server);
		uppanel2 = new Uppanel2(this);
		dataclient = new DataClient(this);
		dataclient.start();
		chatpanel = new Chatpanel(this);
		
		epanel.setOpaque(false);
		wpanel.setOpaque(false);
		npanel.setOpaque(false);
		spanel.setOpaque(false);
		ppc.setOpaque(false);
		ppc.setBorder(BorderFactory.createLineBorder(Color.black));
		headpanel.setOpaque(false);
		uppanel2.setOpaque(false);
		colorselectpanel.setOpaque(false);
		thickpanel.setOpaque(false);
		toolchoosepanel.setOpaque(false);
		chatpanel.setOpaque(false);
		
		epanel.setPreferredSize(new Dimension(120, 350));
		epanel.add(colorselectpanel);
		epanel.add(thickpanel);
		epanel.add(toolchoosepanel);
		epanel.add(headpanel);
		this.add(epanel, BorderLayout.EAST);
		this.add(ppc, BorderLayout.CENTER);
		this.add(chatpanel, BorderLayout.WEST);
		this.add(uppanel2, BorderLayout.NORTH);
		this.add(downpanel, BorderLayout.SOUTH);
	}
}
