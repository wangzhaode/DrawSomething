package Client;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import FileManger.SwingResourceManager;
import Net.Server;
import Start.ScaleIcon;
import Start.StartFrame;

public class Client3 extends Thread {
	public StartFrame sf;
	public Socket s;
	public Client2 c2;
	public String serveripstr;
	public JFrame f;
	public mainPanel mp;

	public Client3(StartFrame sf, Client2 c2) {
		this.sf = sf;
		this.c2 = c2;
	}

	public void run() {
		serveripstr = c2.serveripstr;
		try {
			s = new Socket(serveripstr, 8888);
		} catch (Exception e) {

			int again = JOptionPane.showConfirmDialog(null, "找不到IP，请重新进入!");
			System.exit(0);
		}
		f = new JFrame();
		ScaleIcon icon7 = new ScaleIcon(new ImageIcon(SwingResourceManager.getImage("src/ImageIcon/08.jpg")));
		JLabel jl = new JLabel(icon7);
		f.getLayeredPane().add(jl, new Integer(Integer.MIN_VALUE));
		jl.setBounds(0, 0, 960, 655);
		JPanel cp = (JPanel) f.getContentPane();
		cp.setOpaque(false);
		f.addWindowListener(new thisWindowLisener());
		f.setBounds(200, 30, 120, 350);
		mp = new mainPanel(sf, this, false);
		mp.setOpaque(false);
		f.add(mp);
		f.pack();
		f.setVisible(true);
	}

	private class thisWindowLisener implements WindowListener {
		@Override
		public void windowActivated(WindowEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosed(WindowEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosing(WindowEvent arg0) {
			// TODO Auto-generated method stub
			// System.out.println("推出了哦");
			mp.c.exitsend();
			System.exit(0);
		}

		@Override
		public void windowDeactivated(WindowEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowDeiconified(WindowEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowIconified(WindowEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowOpened(WindowEvent arg0) {
			// TODO Auto-generated method stub

		}
	}
}
