package mainframeaction.storeaction;

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
import JPopupMenu.ShelfBookJPopupMenu;
import JPopupMenu.StoreJPopupMenu;
import sun.security.krb5.internal.tools.Ktab;
import domain.IconNode;

public class LoadStore {
	List<JTextArea> StoreJTextArea=new ArrayList<JTextArea>();
	List<JLabel> StoreJLabel=new ArrayList<JLabel>();
	ImageIcon imageIcon = new ImageIcon("src/img/book.png");
	StoreJPopupMenu jPopupMenu=null;
	public LoadStore(){
		// TODO Auto-generated constructor stub
		final File root=new File("src/info/store/");
		File[] files=root.listFiles();
		if(files!=null)
		{
			int count=0;
			for(File f:files)
			{
				count++;
				if(count>=1&&count<=5)
				{
					JLabel jLabel=new JLabel(imageIcon);
					jLabel.setBounds(200*count, 35, 100, 120);
					jLabel.setVisible(true);
					StoreJLabel.add(jLabel);
					JTextArea jTextArea=new JTextArea(f.getName());
					jTextArea.setBounds(200*count, 55, 100, 120);
					jTextArea.setOpaque(false);
					jTextArea.setFocusable(false);
					jTextArea.setEditable(false);
					jTextArea.setVisible(true);
					StoreJTextArea.add(jTextArea);
				}
				else if(count>=6&&count<=10)
				{
					JLabel jLabel=new JLabel(imageIcon);
					jLabel.setBounds(200*(count-5), 205, 100, 120);
					jLabel.setVisible(true);
					StoreJLabel.add(jLabel);
					JTextArea jTextArea=new JTextArea(f.getName());
					jTextArea.setBounds(200*(count-5), 225, 100, 120);
					jTextArea.setOpaque(false);
					jTextArea.setFocusable(false);
					jTextArea.setEditable(false);
					jTextArea.setVisible(true);
					StoreJTextArea.add(jTextArea);
				}
				else if(count>=11&&count<=15)
				{
					JLabel jLabel=new JLabel(imageIcon);
					jLabel.setBounds(200*(count-10), 375, 100, 120);
					jLabel.setVisible(true);
					StoreJLabel.add(jLabel);
					JTextArea jTextArea=new JTextArea(f.getName());
					jTextArea.setBounds(200*(count-10), 395, 100, 120);
					jTextArea.setOpaque(false);
					jTextArea.setFocusable(false);
					jTextArea.setEditable(false);
					jTextArea.setVisible(true);
					StoreJTextArea.add(jTextArea);
				}
				else
				{
					JLabel jLabel=new JLabel(imageIcon);
					jLabel.setBounds(200*(count-15), 545, 100, 120);
					jLabel.setVisible(true);
					StoreJLabel.add(jLabel);
					JTextArea jTextArea=new JTextArea(f.getName());
					jTextArea.setBounds(200*(count-15), 565, 100, 120);
					jTextArea.setOpaque(false);
					jTextArea.setVisible(true);
					StoreJTextArea.add(jTextArea);
				}
			}
			for(int i=0;i<StoreJTextArea.size();i++)
			{
				StoreJTextArea.get(i).setCursor(new Cursor(Cursor.HAND_CURSOR));  
				StoreJTextArea.get(i).addMouseListener(new MouseAdapter() 
				{
					public void mousePressed(MouseEvent e) 
					{
						if(jPopupMenu!=null)
							jPopupMenu.setVisible(false);
						if (e.getButton() == MouseEvent.BUTTON1) // ���������
						{
							try {
								OpenSelectedBook openShelfBook = new OpenSelectedBook("",
										((JTextArea) e.getSource()).getText(),"store");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						else if(e.getButton()==MouseEvent.BUTTON3)
						{
							jPopupMenu=new StoreJPopupMenu(((JTextArea) e.getSource()).getText());
//							System.out.println(((JTextArea) e.getSource()).getText());
							jPopupMenu.setLocation(e.getXOnScreen(), e.getYOnScreen());
							jPopupMenu.setVisible(true);
						}
					}
				});
			}
		}
	}
	public List<JTextArea> getStoreJTextArea() {
		return StoreJTextArea;
	}
	public void setStoreJTextArea(List<JTextArea> StoreJTextArea) {
		StoreJTextArea = StoreJTextArea;
	}
	public List<JLabel> getStoreJLabel() {
		return StoreJLabel;
	}
	public void setStoreJLabel(List<JLabel> StoreJLabel) {
		StoreJLabel = StoreJLabel;
	}
}
