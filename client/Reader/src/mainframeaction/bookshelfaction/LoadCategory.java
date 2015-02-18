package mainframeaction.bookshelfaction;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import JPopupMenu.RecentJPopupMenu;
import JPopupMenu.ShelfBookJPopupMenu;
import mainframeaction.OpenSelectedBook;

public class LoadCategory {
	List<JTextArea> BookshelfJTextArea=new ArrayList<JTextArea>();
	List<JLabel> BookshelfJLabel=new ArrayList<JLabel>();
	ImageIcon imageIcon = new ImageIcon("src/img/book.png");
	File getCategory=null;
	ShelfBookJPopupMenu jPopupMenu=null;
	public LoadCategory(final String text){
		// TODO Auto-generated constructor stub
		final File root=new File("src/info/bookshelf/");
		File[] files=root.listFiles();
		for(File file:files)
		{
			if(file.getName().equals(text))
			{
				getCategory=file;
			}
		}
		if(getCategory!=null)
		{
			int count=0;
			File[] fs=getCategory.listFiles();
			for(File f:fs)
			{
				if((count%20)>=0&&(count%20)<=4)
				{
					JLabel jLabel=new JLabel(imageIcon);
					jLabel.setBounds(200*((count%20)+1), 35, 100, 120);
					jLabel.setVisible(true);
					BookshelfJLabel.add(jLabel);
					JTextArea jTextArea=new JTextArea(f.getName());
					jTextArea.setBounds(200*((count%20)+1), 55, 100, 120);
					jTextArea.setOpaque(false);
					jTextArea.setFocusable(false);
					jTextArea.setEditable(false);
					jTextArea.setVisible(true);
					BookshelfJTextArea.add(jTextArea);
				}
				else if((count%20)>=5&&(count%20)<=9)
				{
					JLabel jLabel=new JLabel(imageIcon);
					jLabel.setBounds(200*((count%20)-4), 205, 100, 120);
					jLabel.setVisible(true);
					BookshelfJLabel.add(jLabel);
					JTextArea jTextArea=new JTextArea(f.getName());
					jTextArea.setBounds(200*((count%20)-4), 225, 100, 120);
					jTextArea.setOpaque(false);
					jTextArea.setFocusable(false);
					jTextArea.setEditable(false);
					jTextArea.setVisible(true);
					BookshelfJTextArea.add(jTextArea);
				}
				else if((count%20)>=10&&(count%20)<=14)
				{
					JLabel jLabel=new JLabel(imageIcon);
					jLabel.setBounds(200*((count%20)-9), 375, 100, 120);
					jLabel.setVisible(true);
					BookshelfJLabel.add(jLabel);
					JTextArea jTextArea=new JTextArea(f.getName());
					jTextArea.setBounds(200*((count%20)-9), 395, 100, 120);
					jTextArea.setOpaque(false);
					jTextArea.setFocusable(false);
					jTextArea.setEditable(false);
					jTextArea.setVisible(true);
					BookshelfJTextArea.add(jTextArea);
				}
				else
				{
					JLabel jLabel=new JLabel(imageIcon);
					jLabel.setBounds(200*((count%20)-14), 545, 100, 120);
					jLabel.setVisible(true);
					BookshelfJLabel.add(jLabel);
					JTextArea jTextArea=new JTextArea(f.getName());
					jTextArea.setBounds(200*((count%20)-14), 565, 100, 120);
					jTextArea.setOpaque(false);
					jTextArea.setVisible(true);
					BookshelfJTextArea.add(jTextArea);
				}
				count++;
			}
			frame.MainFrame.setJpanelbookMax((count/20));
			for(int i=0;i<BookshelfJTextArea.size();i++)
			{
				BookshelfJTextArea.get(i).setCursor(new Cursor(Cursor.HAND_CURSOR));  
				BookshelfJTextArea.get(i).addMouseListener(new MouseAdapter() 
				{
					public void mousePressed(MouseEvent e) 
					{
						if(jPopupMenu!=null)
							jPopupMenu.setVisible(false);
						if (e.getButton() == MouseEvent.BUTTON1) // ���������
						{
							try {
								OpenSelectedBook openShelfBook = new OpenSelectedBook(getCategory.getPath(),
										((JTextArea) e.getSource()).getText(),"bookshelf");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						else if(e.getButton()==MouseEvent.BUTTON3)
						{
							jPopupMenu=new ShelfBookJPopupMenu(text,((JTextArea) e.getSource()).getText());
//							System.out.println(((JTextArea) e.getSource()).getText());
							jPopupMenu.setLocation(e.getXOnScreen(), e.getYOnScreen());
							jPopupMenu.setVisible(true);
						}
					}
				});
			}
		}
	}
	public File getGetCategory() {
		return getCategory;
	}
	public void setGetCategory(File getCategory) {
		this.getCategory = getCategory;
	}
	public List<JTextArea> getBookshelfJTextArea() {
		return BookshelfJTextArea;
	}
	public void setBookshelfJTextArea(List<JTextArea> bookshelfJTextArea) {
		BookshelfJTextArea = bookshelfJTextArea;
	}
	public List<JLabel> getBookshelfJLabel() {
		return BookshelfJLabel;
	}
	public void setBookshelfJLabel(List<JLabel> bookshelfJLabel) {
		BookshelfJLabel = bookshelfJLabel;
	}
}
