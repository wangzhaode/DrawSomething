package Client;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import FileManger.SwingResourceManager;
public class Toolchoosepanel extends JPanel implements ActionListener{
	JButton pen,eraser,delect;
	ImageIcon penicon,erasericon,delecticon;
	mainPanel mp;
public Toolchoosepanel(mainPanel mp)
{   
	try{
	this.mp = mp;
	penicon = new ImageIcon(SwingResourceManager.getImage("src/ImageIcon/pen.png"));
	erasericon = new ImageIcon(SwingResourceManager.getImage("src/ImageIcon/eraser.png"));
	delecticon = new ImageIcon(SwingResourceManager.getImage("src/ImageIcon/delect.png"));
	pen = new JButton(penicon);
	eraser = new JButton(erasericon);
	delect = new JButton(delecticon);
	pen.setBorder(BorderFactory.createRaisedBevelBorder());
	eraser.setBorder(BorderFactory.createRaisedBevelBorder());
	delect.setBorder(BorderFactory.createRaisedBevelBorder());
	pen.setPreferredSize(new Dimension(40,30));
	eraser.setPreferredSize(new Dimension(45,30));
	delect.setPreferredSize(new Dimension(90,30));
	pen.addActionListener(this);
	eraser.addActionListener(this);
	delect.addActionListener(this);
	this.setPreferredSize(new Dimension(100,70));
	pen.setContentAreaFilled(false);
	eraser.setContentAreaFilled(false);
	delect.setContentAreaFilled(false);
	this.add(pen);
	this.add(eraser);
	this.add(delect);
	}catch(Exception e){}
}

public void actionPerformed(ActionEvent e) {
	try{
	if(e.getSource()==pen)
	{
		mp.paintpanel.paintColor=mp.colorselectpanel.paintcolor;
		pen.setBorder(BorderFactory.createLoweredBevelBorder());
		eraser.setBorder(BorderFactory.createRaisedBevelBorder());
		delect.setBorder(BorderFactory.createRaisedBevelBorder());
		mp.paintpanel.img = mp.paintpanel.kit.getImage("src/ImageIcon/pen.png");
		mp.paintpanel.eraser = false;
	}
	if(e.getSource()==eraser)
	{
		mp.paintpanel.paintColor = Color.white;
		eraser.setBorder(BorderFactory.createLoweredBevelBorder());
		pen.setBorder(BorderFactory.createRaisedBevelBorder());
		delect.setBorder(BorderFactory.createRaisedBevelBorder());
		mp.paintpanel.img = mp.paintpanel.kit.getImage("src/ImageIcon/eraser.png");
		mp.paintpanel.eraser = true;
	}
	if(e.getSource()==delect)
	{	
		mp.paintpanel.List = new ArrayList<Polygon>();
		mp.paintpanel.colorList = new ArrayList<Color>();
		mp.paintpanel.thickList = new ArrayList<Float>();
	    mp.paintpanel.repaint();
	    if(mp.paintpanel.ps.c!=null)
	    	mp.paintpanel.ps.c.resend();
	    delect.setBorder(BorderFactory.createLoweredBevelBorder());
	    pen.setBorder(BorderFactory.createRaisedBevelBorder());
		eraser.setBorder(BorderFactory.createRaisedBevelBorder());
	}
	}catch(Exception event){}
}

}
