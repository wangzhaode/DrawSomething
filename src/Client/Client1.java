package Client;

import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import FileManger.SwingResourceManager;
import Net.Server;
import Start.ScaleIcon;
import Start.StartFrame;

public class Client1 extends Thread{
	public StartFrame sf;
	public JFrame f;
	public boolean server = true;
	public Client1(StartFrame sf)
	{
		this.sf = sf;
	}
public void run()
{
	f = new JFrame();
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	mainPanel mp = new mainPanel(sf,server,this);
	ScaleIcon icon7 = new ScaleIcon(new ImageIcon(SwingResourceManager.getImage("src/ImageIcon/08.jpg")));
    JLabel jl = new JLabel(icon7);
    f.getLayeredPane().add(jl,new Integer(Integer.MIN_VALUE)); 
    jl.setBounds(0,0,960,655);
    JPanel cp = (JPanel) f.getContentPane();
    cp.setOpaque(false);
	f.setBounds(200, 30, 120, 350 );
	
	mp.setOpaque(false);
	f.add(mp);	
	f.pack();
	f.setVisible(true);
}
}
