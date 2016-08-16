package Client;

import java.awt.*;

import javax.swing.*;

import FileManger.SwingResourceManager;

public class Downpanel extends JPanel{
	public JLabel headlabel, namelabel, iplabel, portlabel;
	HeadPanel2 hp;
	public mainPanel mp;

	public Downpanel(mainPanel mp) {
		this.mp = mp;
		this.setPreferredSize(new Dimension(920, 180));
		//this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		//this.setLayout(new GridLayout(1,5));
	}

	public Downpanel(mainPanel mp,boolean server) {
		this.mp = mp;
		this.setPreferredSize(new Dimension(920, 180));
		//this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		//this.setLayout(new GridLayout(1,5));
	}
	public void sreplay()
	{   
		this.removeAll();
		if(mp.server)
		{  
		   
			for(int i=0;i<mp.lc.headxlist.size();i++)
			{
				hp = new HeadPanel2(Integer.parseInt(mp.lc.headxlist.get(i)),mp.lc.iplist.get(i),mp.lc.namexlist.get(i),false);
				this.add(hp);
			}
		}
		else
		{
			for(int i=0;i<mp.lc.headxlist.size();i++)
			{   
				if(i==0)
				{  hp = new HeadPanel2(Integer.parseInt(mp.lc.headxlist.get(i)),mp.lc.iplist.get(i),mp.lc.namexlist.get(i),true);
				   this.add(hp);}
				else
				{
					hp = new HeadPanel2(Integer.parseInt(mp.lc.headxlist.get(i)),mp.lc.iplist.get(i),mp.lc.namexlist.get(i),false);
					this.add(hp);
				}
			}
		}this.updateUI();
	}
	public void creplay()
	{  
		this.removeAll();
		if(mp.server)
		{   
			for(int i=0;i<mp.c.headxlist.size();i++)
			{
				hp = new HeadPanel2(Integer.parseInt(mp.c.headxlist.get(i)),mp.c.iplist.get(i),mp.c.namexlist.get(i),false);
				this.add(hp);
			}
		}
		else
		{
			for(int i=0;i<mp.c.headxlist.size();i++)
			{   
				if(i==0)
				{  
					hp = new HeadPanel2(Integer.parseInt(mp.c.headxlist.get(i)),mp.c.iplist.get(i),mp.c.namexlist.get(i),true);
				   this.add(hp);}
				else
				{
					hp = new HeadPanel2(Integer.parseInt(mp.c.headxlist.get(i)),mp.c.iplist.get(i),mp.c.namexlist.get(i),false);
					this.add(hp);
				}
			}
		}this.updateUI();
	}
	public class HeadPanel2 extends JPanel {
		private boolean server;
		public int headx;
		public String ipstr;
		public String namex;
		public HeadPanel2(int headx, String ipstr, String namex,boolean server) {
			this.headx = headx;
			this.ipstr = ipstr;
			this.namex = namex;
			this.server= server;
			this.setOpaque(false);
			namelabel = new JLabel(namex);
			switch (headx) {
			case 1:
				headlabel = new JLabel(new ImageIcon(SwingResourceManager.getImage("src/ImageIcon/01.jpg")));
				break;
			case 2:
				headlabel = new JLabel(new ImageIcon(SwingResourceManager.getImage("src/ImageIcon/02.jpg")));
				break;
			case 3:
				headlabel = new JLabel(new ImageIcon(SwingResourceManager.getImage("src/ImageIcon/03.jpg")));
				break;
			case 4:
				headlabel = new JLabel(new ImageIcon(SwingResourceManager.getImage("src/ImageIcon/04.jpg")));
				break;
			case 5:
				headlabel = new JLabel(new ImageIcon(SwingResourceManager.getImage("src/ImageIcon/05.jpg")));
				break;
			case 6:
				headlabel = new JLabel(new ImageIcon(SwingResourceManager.getImage("src/ImageIcon/06.jpg")));
				break;
			}

			headlabel.setPreferredSize(new Dimension(100, 90));
			iplabel = new JLabel("  "+ipstr);
			iplabel.setPreferredSize(new Dimension(100, 30));
			if (server)
				portlabel = new JLabel("房主");
			else
				portlabel = new JLabel("房客");
			portlabel.setFont(new Font("楷体",Font.ITALIC|Font.BOLD,16));
			iplabel.setLayout(new GridLayout(3, 1));
			portlabel.setOpaque(false);
			headlabel.setOpaque(false);
			namelabel.setOpaque(false);
			this.add(iplabel);
			this.add(portlabel);
			this.add(headlabel);
			this.add(namelabel);
			this.setPreferredSize(new Dimension(100,180));
		}
	}
}
