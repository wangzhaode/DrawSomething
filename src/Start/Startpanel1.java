package Start;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Startpanel1 extends JPanel{
	public JTextField nameTextField;
	JLabel usernamelabel;
public Startpanel1()
{   
	this.setPreferredSize(new Dimension(300,50));
	nameTextField = new JTextField();
	usernamelabel = new JLabel("你的大名");
	 usernamelabel.setFont(new Font("楷体",Font.ITALIC|Font.BOLD,32));
   // usernamelabel.setBackground(Color.white);
    nameTextField.addActionListener(new TextListener());
    nameTextField.setColumns(10);
    nameTextField.setOpaque(false);
    nameTextField.setFont(new Font("楷体",Font.ITALIC|Font.BOLD,18));
    nameTextField.setPreferredSize(new Dimension(150,30));
    nameTextField.setBounds(284, 349, 156, 39);
    this.add(usernamelabel);
    this.add(nameTextField);
}


public   class TextListener implements ActionListener{
	
	public void actionPerformed(ActionEvent event) {
	}
  }
}
