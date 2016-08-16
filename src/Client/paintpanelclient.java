package Client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.*;

import FileManger.SwingResourceManager;

public class paintpanelclient extends JPanel {
	public mainPanel mp;
	public ArrayList<Polygon> List = new ArrayList<Polygon>();
	public ArrayList<Color> colorList;
	public ArrayList<Float> thickList;
	public Color paintColor = Color.black;
	public Polygon m_polygon, x, n;
	public int m_beginX, m_beginY;
	public float thick = 4f;
	public Toolkit kit;
	public Image img;
	public boolean eraser;
	public Point p;
	public paintpanelclient(mainPanel mp) {
		this.mp = mp;
		List = new ArrayList<Polygon>();
		colorList = new ArrayList<Color>();
		thickList = new ArrayList<Float>();
		n = new Polygon();
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(600, 410));
		// this.addMouseListener(new paintListener());
		// this.addMouseMotionListener(new paintListener());
		eraser = false;
		kit = Toolkit.getDefaultToolkit();
		img = kit.getImage("src/ImageIcon/pen.png");
		ppclient ppc = new ppclient();
		ppc.start();
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
			repaint();
		}

		public void mousePressed(MouseEvent e) {

			m_beginX = e.getX();
			m_beginY = e.getY();
			m_polygon = new Polygon();
			m_polygon.addPoint(m_beginX, m_beginY);
			if (eraser == true)
				colorList.add(Color.white);
			else
				colorList.add(paintColor);
			thickList.add(thick);
		}

		@Override
		public void mouseClicked(MouseEvent arg0) { // TODO

		}

		@Override
		public void mouseEntered(MouseEvent arg0) { // TODO

		}

		@Override
		public void mouseExited(MouseEvent arg0) { // TODO

		}

		public void mouseReleased(MouseEvent e) { // TODO Auto-generated method
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO
		}

	}

	public class ppclient extends Thread {
		public Socket s;
		private ObjectInputStream dis = null;
		private int i = 0;

		public void run() {
			try {
				s = new Socket(mp.c3.serveripstr, 8890);
			} catch (IOException e1) {
				//e1.printStackTrace();
			}
			try {
				dis = new ObjectInputStream(s.getInputStream());
			} catch (IOException e) {
				//e.printStackTrace();
			}
			while (true) {
				try {
					dis = new ObjectInputStream(s.getInputStream());
					List = (ArrayList<Polygon>)dis.readObject();
					colorList = (ArrayList<Color>) dis.readObject();
					thickList = (ArrayList<Float>) dis.readObject();
					repaint();
				
				} catch (ClassNotFoundException | IOException e) {
					int again = JOptionPane.showConfirmDialog(null, "房主已关闭房间，请退出！");
					System.exit(0);
				}
			}
		}
	}
}
