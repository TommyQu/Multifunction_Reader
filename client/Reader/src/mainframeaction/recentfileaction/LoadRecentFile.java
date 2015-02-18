package mainframeaction.recentfileaction;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import mainframeaction.OpenSelectedBook;
import JPopupMenu.RecentJPopupMenu;
import sun.security.krb5.internal.tools.Ktab;
import domain.IconNode;

public class LoadRecentFile {
	List<JTextArea> RecentJTextArea=new ArrayList<JTextArea>();
	List<JLabel> RecentJLabel=new ArrayList<JLabel>();
	ImageIcon imageIcon = new ImageIcon("src/img/book.png");
	RecentJPopupMenu jPopupMenu=null;
	int isExist=1;
	public LoadRecentFile() throws IOException 
	{
//		System.out.print("aaaaaaaa");
		String str;
		File rf=null;
		BufferedReader br = new BufferedReader(new FileReader(
				"src/info/recent.txt"));
		int count = 0;
		while (br.ready()) {
			str = br.readLine();
			rf = new File(str);
//			recent.add(new IconNode(new ImageIcon("src/img/bookfont.png"), rf.getName().substring(0, 4)));
			count++;
			if(count>=1&&count<=5)
			{
				JLabel jLabel=new JLabel(imageIcon);
				jLabel.setBounds(200*count, 35, 100, 120);
				jLabel.setVisible(true);
				RecentJLabel.add(jLabel);
				JTextArea jTextArea=new JTextArea(rf.getName());
				jTextArea.setBounds(200*count, 55, 100, 120);
				jTextArea.setOpaque(false);
				jTextArea.setFocusable(false);
				jTextArea.setEditable(false);
				jTextArea.setVisible(true);
				RecentJTextArea.add(jTextArea);
			}
			else if(count>=6&&count<=10)
			{
				JLabel jLabel=new JLabel(imageIcon);
				jLabel.setBounds(200*(count-5), 205, 100, 120);
				jLabel.setVisible(true);
				RecentJLabel.add(jLabel);
				JTextArea jTextArea=new JTextArea(rf.getName());
				jTextArea.setBounds(200*(count-5), 225, 100, 120);
				jTextArea.setOpaque(false);
				jTextArea.setFocusable(false);
				jTextArea.setEditable(false);
				jTextArea.setVisible(true);
				RecentJTextArea.add(jTextArea);
			}
			else if(count>=11&&count<=15)
			{
				JLabel jLabel=new JLabel(imageIcon);
				jLabel.setBounds(200*(count-10), 375, 100, 120);
				jLabel.setVisible(true);
				RecentJLabel.add(jLabel);
				JTextArea jTextArea=new JTextArea(rf.getName());
				jTextArea.setBounds(200*(count-10), 395, 100, 120);
				jTextArea.setOpaque(false);
				jTextArea.setFocusable(false);
				jTextArea.setEditable(false);
				jTextArea.setVisible(true);
				RecentJTextArea.add(jTextArea);
			}
			else
			{
				JLabel jLabel=new JLabel(imageIcon);
				jLabel.setBounds(200*(count-15), 545, 100, 120);
				jLabel.setVisible(true);
				RecentJLabel.add(jLabel);
				JTextArea jTextArea=new JTextArea(rf.getName());
				jTextArea.setBounds(200*(count-15), 565, 100, 120);
				jTextArea.setOpaque(false);
				jTextArea.setVisible(true);
				RecentJTextArea.add(jTextArea);
			}
		} 
		br.close();
		for(int i=0;i<RecentJTextArea.size();i++)
		{
			RecentJTextArea.get(i).setCursor(new Cursor(Cursor.HAND_CURSOR));  
			RecentJTextArea.get(i).addMouseListener(new MouseAdapter() 
			{
				public void mousePressed(MouseEvent e) 
				{
					if(jPopupMenu!=null)
						jPopupMenu.setVisible(false);
					if (e.getButton() == MouseEvent.BUTTON1) //左键点击打开
					{
						if(jPopupMenu!=null)
							jPopupMenu.setVisible(false);
						try 
						{
							OpenSelectedBook openSelectedBook = new OpenSelectedBook(
									"src/info/recent.txt", ((JTextArea) e
											.getSource()).getText(),"recent");
							if(openSelectedBook.getIsExist()==0)
								isExist=0;
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else if(e.getButton()==MouseEvent.BUTTON3)
					{
						jPopupMenu=new RecentJPopupMenu(((JTextArea) e.getSource()).getText());
//						System.out.println(((JTextArea) e.getSource()).getText());
						jPopupMenu.setLocation(e.getXOnScreen(), e.getYOnScreen());
						jPopupMenu.setVisible(true);
					}
				}
//				public void mouseEntered(MouseEvent e)
//				{
//					RecentJTextArea.get(i).setCursor(new Cursor(Cursor.HAND_CURSOR));   
//				}
//	            public   void   mouseExited(MouseEvent   e)   {   
//	                setCursor(new   Cursor(Cursor.DEFAULT_CURSOR));   
//	            }  
			});
		}
	}
	public List<JLabel> getRecentJLabel() {
		return RecentJLabel;
	}
	public void setRecentJLabel(List<JLabel> recentJLabel) {
		RecentJLabel = recentJLabel;
	}
	public List<JTextArea> getRecentJTextArea() {
		return RecentJTextArea;
	}
	public void setRecentJTextArea(List<JTextArea> recentJTextArea) {
		RecentJTextArea = recentJTextArea;
	}
	public int getIsExist() {
		return isExist;
	}
	public void setIsExist(int isExist) {
		this.isExist = isExist;
	}
}
