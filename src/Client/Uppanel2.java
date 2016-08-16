package Client;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
public class Uppanel2 extends JPanel implements ActionListener{
public JButton startbutton,databutton;
public JLabel problemlabel;
public TimeLabel timelabel;
public JPanel p2,p3,p4;
private mainPanel mp;
public Timer timer;
public int time;
public wordtipFrame wordtipframe;
	public Uppanel2(mainPanel mp)
	{   
		this.mp = mp;
		p2 = new JPanel();
		p3 = new JPanel();
		p4 = new JPanel();	
		p2.setOpaque(false);
		p3.setOpaque(false);
		p4.setOpaque(false);
		databutton = new JButton("答题助手");
		databutton.addActionListener(this);
		databutton.setPreferredSize(new Dimension(120,30));
		databutton.setContentAreaFilled(false);
		databutton.setBorder(BorderFactory.createRaisedBevelBorder());
		databutton.setFont(new Font("楷体",Font.ITALIC|Font.BOLD,18));
		p4.add(databutton);
		problemlabel = new JLabel("提示区");
		problemlabel.setPreferredSize(new Dimension(120,30));
		problemlabel.setFont(new Font("楷体",Font.ITALIC|Font.BOLD,28));
		p2.add(problemlabel);
		timelabel = new TimeLabel();
		timelabel.setPreferredSize(new Dimension(120,30));
		timelabel.setFont(new Font("楷体",Font.ITALIC|Font.BOLD,28));
		p3.add(timelabel);
		this.setLayout(new GridLayout(1,3,100,0));
		this.add(p2);
		this.add(p3);
		this.add(p4);
		this.setPreferredSize(new Dimension(920,40));
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==databutton)
		{
			wordtipframe = new  wordtipFrame(mp);
			Thread th = new Thread(wordtipframe);
			th.start();
		}	
	}
	public class TimeLabel extends JLabel implements ActionListener {
		public TimeLabel() {
			this.setText("时间");
			timer = new Timer(1000, this);
			time = 20;
		}
		public void actionPerformed(ActionEvent e) {
			this.setText(Integer.toString(time));
			time--;
			updateUI();
			if(time==0)
			{
				this.setText("时间到");
				problemlabel.setText("提示区");
				mp.ppc.List = new ArrayList<Polygon>();
				mp.ppc.colorList = new ArrayList<Color>();
				mp.ppc.thickList = new ArrayList<Float>();
			    mp.ppc.repaint();
				timer.stop();
				time = 20;
			}
		}
	}
}
