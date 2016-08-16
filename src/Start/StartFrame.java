package Start;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Client.Client1;
import Client.Client2;
import FileManger.SwingResourceManager;

public class StartFrame extends JFrame{
	  public  Startpanel1 startpanel1;
	  public Startpanel2 startpanel2;
	  public  Startpanel3 startpanel3;
	  public  JFrame frame;
	public StartFrame(){
	 
	  frame = new JFrame("Äã»­ÎÒ²Â");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JPanel startpanel = new JPanel(); 
      ImageIcon imag = new ImageIcon(SwingResourceManager.getImage("src/ImageIcon/08.jpg"));
      ScaleIcon icon7 = new ScaleIcon(imag);
      JLabel jl = new JLabel(icon7);
      frame.getLayeredPane().add(jl,new Integer(Integer.MIN_VALUE)); 
      jl.setBounds(0,0,960,655);
      JPanel cp = (JPanel) frame.getContentPane();
      cp.setOpaque(false);
     
      
      startpanel1 = new Startpanel1();
      startpanel2 = new Startpanel2();
      startpanel3 = new Startpanel3(this,startpanel1);
      
      
      startpanel1.setOpaque(false);
      startpanel2.setOpaque(false);
      startpanel3.setOpaque(false); 
      
      
      startpanel.setLayout(new BorderLayout());
      startpanel.add(startpanel2,BorderLayout.WEST);
      JPanel jp = new JPanel();
      
      jp.setLayout(new GridLayout(2,1));
      jp.add(startpanel3);
      jp.add(startpanel1);
      jp.setOpaque(false);
      startpanel.add(jp,BorderLayout.EAST);
      startpanel.setOpaque(false);
      frame.add(startpanel);
      frame.pack();
          }
  public JFrame getFrame(){
	  return frame;
  }
public void launchFrame() {
	  frame.setBounds(100, 40,960,655);
	     frame.setVisible(true);
}
     
}
