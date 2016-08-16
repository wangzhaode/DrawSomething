package Start;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Client.*;

public class Startpanel3 extends JPanel implements ActionListener{
	public StartFrame sf;
	public Client1 c1;
	public Client2 c2;
	public  JButton choosebutton1;
	public  JButton choosebutton2;
	public JFrame jf;
	public String name;
	public Startpanel1 sp1;
public Startpanel3(StartFrame st,Startpanel1 sp1)
{
	this.sf = st;
	this.sp1 = sp1;
	this.jf = st.frame;
	c1 = new Client1(st);
	c2 = new Client2(st);
	choosebutton1 = new JButton("创建房间");
	choosebutton2 = new JButton("加入房间");
	choosebutton1.setPreferredSize(new Dimension(150,50));
	choosebutton2.setPreferredSize(new Dimension(150,50));
	choosebutton1.setBorder(BorderFactory.createRaisedBevelBorder());
	choosebutton2.setBorder(BorderFactory.createRaisedBevelBorder());
	choosebutton1.setFont(new Font("楷体",Font.ITALIC|Font.BOLD,22));
	choosebutton2.setFont(new Font("楷体",Font.ITALIC|Font.BOLD,22));
	choosebutton1.addActionListener(this);
	choosebutton2.addActionListener(this);
	choosebutton1.setContentAreaFilled(false);
	choosebutton2.setContentAreaFilled(false);
	//choosebutton1.setIcon(new ImageIcon());
    this.add(choosebutton1);
    this.add(choosebutton2);
}
  
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==choosebutton1)
	          c1.start();
		if(e.getSource()==choosebutton2)
			  c2.start();
		name = sp1.nameTextField.getText();
		jf.dispose();
	}
}
