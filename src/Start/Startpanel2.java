package Start;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import FileManger.SwingResourceManager;

public class Startpanel2 extends JPanel {
	ImageIcon icon;
	ScaleIcon icon1, icon2, icon3, icon4, icon5, icon6;
	JButton iconbutton1, iconbutton2, iconbutton3, iconbutton4, iconbutton5,
			iconbutton6;
	public JLabel iconchooser = new JLabel();
	public JLabel chooselabel = new JLabel();
	public int headx = 1;

	public Startpanel2() {
		this.setLayout(new BorderLayout());
		JPanel iconPanel = new JPanel();
		iconPanel.setLayout(new GridLayout(2, 3));
		icon = new ImageIcon(SwingResourceManager.getImage("src/ImageIcon/01.jpg"));
		icon1 = new ScaleIcon(new ImageIcon(SwingResourceManager.getImage("src/ImageIcon/01.jpg")));
		icon2 = new ScaleIcon(new ImageIcon(SwingResourceManager.getImage("src/ImageIcon/02.jpg")));
		icon3 = new ScaleIcon(new ImageIcon(SwingResourceManager.getImage("src/ImageIcon/03.jpg")));
		icon4 = new ScaleIcon(new ImageIcon(SwingResourceManager.getImage("src/ImageIcon/04.jpg")));
		icon5 = new ScaleIcon(new ImageIcon(SwingResourceManager.getImage("src/ImageIcon/05.jpg")));
		icon6 = new ScaleIcon(new ImageIcon(SwingResourceManager.getImage("src/ImageIcon/06.jpg")));
		iconbutton1 = new JButton(icon1);
		iconbutton2 = new JButton(icon2);
		iconbutton3 = new JButton(icon3);
		iconbutton4 = new JButton(icon4);
		iconbutton5 = new JButton(icon5);
		iconbutton6 = new JButton(icon6);
		iconbutton1.setBorder(null);
		iconbutton2.setBorder(null);
		iconbutton3.setBorder(null);
		iconbutton4.setBorder(null);
		iconbutton5.setBorder(null);
		iconbutton6.setBorder(null);
		iconbutton1.setPreferredSize(new Dimension(icon.getIconWidth(), icon
				.getIconHeight()));
		iconbutton2.setPreferredSize(new Dimension(icon.getIconWidth(), icon
				.getIconHeight()));
		iconbutton3.setPreferredSize(new Dimension(icon.getIconWidth(), icon
				.getIconHeight()));
		iconbutton4.setPreferredSize(new Dimension(icon.getIconWidth(), icon
				.getIconHeight()));
		iconbutton5.setPreferredSize(new Dimension(icon.getIconWidth(), icon
				.getIconHeight()));
		iconbutton6.setPreferredSize(new Dimension(icon.getIconWidth(), icon
				.getIconHeight()));
		iconbutton1.addActionListener(new iconListener());
		iconbutton2.addActionListener(new iconListener());
		iconbutton3.addActionListener(new iconListener());
		iconbutton4.addActionListener(new iconListener());
		iconbutton5.addActionListener(new iconListener());
		iconbutton6.addActionListener(new iconListener());
		iconPanel.add(iconbutton1);
		iconPanel.add(iconbutton2);
		iconPanel.add(iconbutton3);
		iconPanel.add(iconbutton4);
		iconPanel.add(iconbutton5);
		iconPanel.add(iconbutton6);
		iconchooser.setText("在这里选择头像啦亲      ");
		iconchooser.setFont(new Font("楷体",Font.ITALIC|Font.BOLD,32));
		//iconchooser.setPreferredSize(new Dimension(156, 39));
		iconchooser.setBackground(Color.white);
		iconchooser.setOpaque(false);
		chooselabel.setIcon(icon1);
		JPanel choosepanel = new JPanel();
		choosepanel.add(iconchooser);
		choosepanel.add(chooselabel);
		choosepanel.setOpaque(false);
		this.add(choosepanel, BorderLayout.NORTH);
		this.add(iconPanel, BorderLayout.SOUTH);
	}

	public class iconListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == iconbutton1) {
				chooselabel.setIcon(icon1);
				headx = 1;
			}
			if (e.getSource() == iconbutton2) {
				chooselabel.setIcon(icon2);
				headx = 2;
			}
			if (e.getSource() == iconbutton3) {
				chooselabel.setIcon(icon3);
				headx = 3;
			}
			if (e.getSource() == iconbutton4) {
				chooselabel.setIcon(icon4);
				headx = 4;
			}
			if (e.getSource() == iconbutton5) {
				chooselabel.setIcon(icon5);
				headx = 5;
			}
			if (e.getSource() == iconbutton6) {
				chooselabel.setIcon(icon6);
				headx = 6;
			}
		}

	}
}
