package Client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import Net.Server.Clients;

public class Paintpanel extends JPanel {
	public ArrayList<Polygon> List;
	public ArrayList<Color> colorList;
	public ArrayList<Float> thickList;
	public Color paintColor = Color.black;
	public Polygon m_polygon, x, n, m;
	public int m_beginX, m_beginY;
	public float thick = 4f;
	public Toolkit kit;
	public Image img;
	public boolean eraser;
	public mainPanel mp;
	public paintserver ps;
	public ServerSocket ss;
	public ArrayList<Clients> clients = new ArrayList<Clients>();
	public Point p;

	public Paintpanel(mainPanel mp) {
		this.mp = mp;
		List = new ArrayList<Polygon>();
		colorList = new ArrayList<Color>();
		thickList = new ArrayList<Float>();
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(600, 410));
		this.addMouseListener(new paintListener());
		this.addMouseMotionListener(new paintListener());
		eraser = false;
		kit = Toolkit.getDefaultToolkit();
		img = kit.getImage("src/ImageIcon/pen.png");
		ps = new paintserver();
		ps.start();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		for (int i = 0; i < List.size(); i++) {
			g.setColor(colorList.get(i));
			g2d.setStroke(new BasicStroke(thickList.get(i),
					BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND));
			x = List.get(i);
			g2d.drawPolyline(x.xpoints, x.ypoints, x.npoints);
		}
		Cursor dynamiteCuror = kit.createCustomCursor(img, new Point(10, 10),
				"dynamite stick");
		setCursor(dynamiteCuror);
	}

	public class paintListener implements MouseListener, MouseMotionListener {

		public void mouseDragged(MouseEvent e) {

			int currentX = e.getX();
			int currentY = e.getY();
			m_polygon.addPoint(currentX, currentY);
			n.addPoint(currentX, currentY);
			p = new Point();
			p = e.getPoint();
			repaint();
			if(ps.c!=null)
			ps.c.resend();

		}

		public void mousePressed(MouseEvent e) {

			m_beginX = e.getX();
			m_beginY = e.getY();
			m_polygon = new Polygon();
			m_polygon.addPoint(m_beginX, m_beginY);
			n = new Polygon();
			n.addPoint(m_beginX, m_beginY);
			List.add(m_polygon);
			if (eraser == true)
				colorList.add(Color.white);
			else
				colorList.add(paintColor);
			thickList.add(thick);

		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

	public class paintserver extends Thread {
		public Client c;
		private ObjectOutputStream dos = null;
		List<Client> clients = new ArrayList<Client>();
		public paintserver() {
			try {
				ss = new ServerSocket(8890);
			} catch (BindException e) {
				JOptionPane.showConfirmDialog(null, "程序已被打开，请关闭重试！");
				System.exit(0);
			} catch (IOException e) {
				//e.printStackTrace();
			}
		}

		public void run() {
			try {
				while (true) {
					Socket s = ss.accept();
					//System.out.println("a client has connected");
					 c = new Client(s);
					new Thread(c).start();
					clients.add(c);
				}
			} catch (IOException e) {
			//	e.printStackTrace();
			}
		}

		class Client implements Runnable {
			private Socket s;
			private ObjectOutputStream dos = null;

			public Client(Socket s) {
				this.s = s;
				try {
				dos = new ObjectOutputStream(s.getOutputStream());
				} catch (IOException e) {
					//e.printStackTrace();
				}
			}

			public void resend() {
				for (int i = 0; i < clients.size(); i++) {
					Client cs2 = clients.get(i);

					try {
						dos = new ObjectOutputStream(cs2.s.getOutputStream());
						dos.writeObject(List);
						dos.writeObject(colorList);
						dos.writeObject(thickList);
						dos.flush();
					} catch (Exception e) {
					//	e.printStackTrace();
					}
				}
			}

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		}
	}
}
