package Client;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.*;

import Data.Data;
import FileManger.SwingResourceManager;
import Start.ScaleIcon;

public class wordFrame extends JFrame implements Runnable {
	public wordFrame(){
		this.addWindowListener(new WindowListener(){
			public void windowActivated(WindowEvent arg0) {
			}
			public void windowClosed(WindowEvent arg0) {
			}
			public void windowClosing(WindowEvent arg0) {
				wordFrame.this.dispose();
			}
			public void windowDeactivated(WindowEvent arg0) {
			}
			public void windowDeiconified(WindowEvent arg0) {
			}
			public void windowIconified(WindowEvent arg0) {
			}
			public void windowOpened(WindowEvent arg0) {
			}});
	}
	public void run() {
		ScaleIcon icon7 = new ScaleIcon(new ImageIcon(SwingResourceManager.getImage("src/ImageIcon/08.jpg")));
		JLabel jl = new JLabel(icon7);
		this.getLayeredPane().add(jl, new Integer(Integer.MIN_VALUE));
		jl.setBounds(0, 0, 310, 500);
		JPanel cp = (JPanel) this.getContentPane();
		cp.setOpaque(false);
		this.setBounds(500, 100, 315, 500);
		this.setPreferredSize(new Dimension(315,500));
		wordPanel np = new wordPanel();		
		JScrollPane jsp = new JScrollPane(np);
		jsp.setOpaque(false);  
		jsp.getViewport().setOpaque(false); 
		this.add(jsp);
		np.replay();
		this.pack();
		this.setVisible(true);
	}

	public class wordPanel extends JPanel {
		ArrayList<String> codeList;
		ArrayList<String> wordList;
		ArrayList<String> charList;
	 public wordPanel(){
		 this.setOpaque(false);
	 }
		public void replay(){
		    this.removeAll();
			codeList = new ArrayList<String>();
			wordList = new ArrayList<String>();
			charList = new ArrayList<String>();
			Data data = new Data();
			ResultSet rs = data.getData();
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			try {
				String z,x,c;
				while (rs.next()) {
					z = rs.getString(1);
					codeList.add(z);
					x = rs.getString(2);
					wordList.add(x);
					c = rs.getString(3);
					charList.add(c);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// 数据库为空的情况
				if (codeList.size()==0) {
					JLabel label1 = new JLabel("你的词库里啥都没有啊");
					JLabel label2 = new JLabel("快点添加几个词汇吧！");
					label1.setFont(new Font("楷体",Font.ITALIC|Font.BOLD,16));
					label2.setFont(new Font("楷体",Font.ITALIC|Font.BOLD,16));
					label1.setOpaque(false);
					label2.setOpaque(false);
					this.add(label1);
					this.add(label2);
					this.add(new addPanel(data));
				}
				// 数据库不空的情况
				else {
					JLabel label = new JLabel("词语      提示    ");
					label.setOpaque(false);
					label.setFont(new Font("楷体",Font.ITALIC|Font.BOLD,16));
					this.add(label);
					for (int i = 0; i < codeList.size(); i++) {
						deletPanel dp = new deletPanel(i,data);
						this.add(dp);
					}
					this.add(new addPanel(data));
					updateUI();
				}
			}
		}
		private class deletPanel extends JPanel implements ActionListener {
			JLabel j2;
			JLabel j3;
			JButton jb;
			int i;

			public deletPanel(int i,Data data) {
				this.i = i;
				this.setOpaque(false);
				j2 = new JLabel(wordList.get(i));
				j2.setPreferredSize(new Dimension(60, 60));
				j2.setFont(new Font("楷体",Font.ITALIC|Font.BOLD,13));
				j3 = new JLabel(charList.get(i));
				j3.setPreferredSize(new Dimension(60, 60));
				j3.setFont(new Font("楷体",Font.ITALIC|Font.BOLD,13));
				j2.setOpaque(false);
				j3.setOpaque(false);
				this.add(j2);
				this.add(j3);
				jb = new JButton("删除");
				jb.addActionListener(this);
				jb.setContentAreaFilled(false);
				jb.setFont(new Font("楷体",Font.ITALIC|Font.BOLD,13));
				jb.setBorder(BorderFactory.createRaisedBevelBorder());
				this.add(jb);
				this.setPreferredSize(new Dimension(240, 50));
			}

			public void actionPerformed(ActionEvent e) {
				Data data = new Data();
				data.deletData(wordList.get(i));
				data.DataClose();
				replay();
			}
		}

		private class addPanel extends JPanel implements ActionListener {
			JTextField jt2;
			JLabel jl;
			JButton b;

			public addPanel(Data data) {
				jt2 = new JTextField(6);
				jt2.addActionListener(this);
				jt2.setOpaque(false);
				jl = new JLabel();
				jl.setPreferredSize(new Dimension(60, 60));
				jt2.setFont(new Font("楷体",Font.ITALIC|Font.BOLD,13));
				b = new JButton("添加");
				b.addActionListener(this);
				b.setFont(new Font("楷体",Font.ITALIC|Font.BOLD,13));
				b.setContentAreaFilled(false);
				b.setBorder(BorderFactory.createRaisedBevelBorder());
				this.setOpaque(false);
				this.add(jt2);
				this.add(jl);
				this.add(b);
			}

			public void actionPerformed(ActionEvent e) {
				Data data = new Data();
				int a;
				String str = "";
				if (jt2.getText().length() > 4 || jt2.getText().length() < 1) {
					a = JOptionPane.showConfirmDialog(null, "请保证添加的词语字数在一到四之间！");
					jt2.setText("");
				}
				switch (jt2.getText().length()) {
				case 1:
					str = "一字词语";
					data.addData(jt2.getText(), str);
					break;
				case 2:
					str = "二字词语";
					data.addData(jt2.getText(), str);
					break;
				case 3:
					str = "三字词语";
					data.addData(jt2.getText(), str);
					break;
				case 4:
					str = "四字成语";
					data.addData(jt2.getText(), str);
					break;
				}
				jt2.setText("");
				data.DataClose();
				replay();
			}
		}
	}
}
