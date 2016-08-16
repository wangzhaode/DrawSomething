package Client;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Thickpanel extends JPanel implements ActionListener{
 Thickbutton thickbutton1,thickbutton2,thickbutton3,thickbutton4;
 mainPanel mp;
	public Thickpanel(mainPanel mp)
	{    
		try{
		 this.mp = mp;
		 thickbutton1 = new Thickbutton(4);
		 thickbutton2 = new Thickbutton(8);
		 thickbutton3 = new Thickbutton(12);
		 thickbutton4 = new Thickbutton(16);
		 thickbutton1.addActionListener(this);
		 thickbutton2.addActionListener(this);
		 thickbutton3.addActionListener(this);
		 thickbutton4.addActionListener(this);
		 thickbutton1.setPreferredSize(new Dimension(45,30));
		 thickbutton2.setPreferredSize(new Dimension(45,30));
		 thickbutton3.setPreferredSize(new Dimension(45,30));
		 thickbutton4.setPreferredSize(new Dimension(45,30));
		 this.setPreferredSize(new Dimension(100,70));
		 thickbutton1.setContentAreaFilled(false);
		 thickbutton2.setContentAreaFilled(false);
		 thickbutton3.setContentAreaFilled(false);
		 thickbutton4.setContentAreaFilled(false);
		 this.add(thickbutton1);
		 this.add(thickbutton2);
		 this.add(thickbutton3);
		 this.add(thickbutton4);
		}catch(Exception e){}
	}

	public void actionPerformed(ActionEvent e) {
		try{
	if(e.getSource()==thickbutton1)
	{	
		mp.paintpanel.thick = 4f;       
	    thickbutton1.setBorder(BorderFactory.createLoweredBevelBorder());
	    thickbutton2.setBorder(BorderFactory.createRaisedBevelBorder());
	    thickbutton3.setBorder(BorderFactory.createRaisedBevelBorder());
	    thickbutton4.setBorder(BorderFactory.createRaisedBevelBorder());
	}
	if(e.getSource()==thickbutton2)
		{
		  mp.paintpanel.thick = 8f;
		  thickbutton2.setBorder(BorderFactory.createLoweredBevelBorder());
		  thickbutton1.setBorder(BorderFactory.createRaisedBevelBorder());
		  thickbutton3.setBorder(BorderFactory.createRaisedBevelBorder());
		  thickbutton4.setBorder(BorderFactory.createRaisedBevelBorder());
		}
	if(e.getSource()==thickbutton3)
		{
		   mp.paintpanel.thick = 12f;
		   thickbutton3.setBorder(BorderFactory.createLoweredBevelBorder());
		   thickbutton1.setBorder(BorderFactory.createRaisedBevelBorder());
		   thickbutton2.setBorder(BorderFactory.createRaisedBevelBorder());
		   thickbutton4.setBorder(BorderFactory.createRaisedBevelBorder());
		}
	if(e.getSource()==thickbutton4)
		{
		   mp.paintpanel.thick = 16f;
		   thickbutton4.setBorder(BorderFactory.createLoweredBevelBorder());
		   thickbutton1.setBorder(BorderFactory.createRaisedBevelBorder());
		   thickbutton2.setBorder(BorderFactory.createRaisedBevelBorder());
		   thickbutton3.setBorder(BorderFactory.createRaisedBevelBorder());
		}
		}catch(Exception event){}
	}
 public class Thickbutton extends JButton
  {   
	  private int width;
	  public Thickbutton(int width)
	  {   
		  this.setBorder(BorderFactory.createRaisedBevelBorder());
		  this.width = width;
	  }
	  public void paintComponent(Graphics g)
	  {
		  super.paintComponent(g);
		  g.setColor(Color.black);
		  g.fillOval(23-width/2,15-width/2, width, width);
	  }
  }

}
