package mainframeaction.bookshelfaction;

import java.awt.Component;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import domain.IconNode;

public class AddBookshelf {
	private List<String> listBookcategory=new ArrayList<String>();
	public AddBookshelf(String bookCategory) throws IOException
	{
		listBookcategory.clear();
		if(bookCategory.contains(" ")||
				bookCategory.contains("<")||
				bookCategory.contains(">")||
				bookCategory.contains("/")||
				bookCategory.contains("\\")||
				bookCategory.contains(":")||
				bookCategory.contains("*")||
				bookCategory.contains("?")||
				bookCategory.equals("\""))
			JOptionPane.showMessageDialog(null, "书架名为空或包含不合法字符");
		else
		{
			int isExist=0;
			File root=new File("src/info/bookshelf");
			File[] shelves=root.listFiles();
			for(File file: shelves)
			{
				if(file.getName().equals(bookCategory))
				{
					isExist=1;
					break;
				}
				else
					listBookcategory.add(file.getName());
			}
			if(isExist==0)
			{
				listBookcategory.add(bookCategory);
				File f=new File("src/info/bookshelf/"+bookCategory);
				f.mkdir();
				JOptionPane.showMessageDialog(null, "添加书架成功");
				IconNode newBookshelf=new IconNode(new ImageIcon("src/img/BookshelfFont.png"), bookCategory);
				newBookshelf.setFather("bookshelf");
				frame.MainFrame.getTreeModel().insertNodeInto(newBookshelf, frame.MainFrame.getBookshelf(), 1);
				frame.MainFrame.getTreeModel().reload(frame.MainFrame.getBookshelf());
				//				frame.MainFrame.getBookshelf().add(newBookshelf);
//				frame.MainFrame.getjTree().revalidate();
			}
			else
				JOptionPane.showMessageDialog(null, "ͬ同名书架已存在");
			listBookcategory.clear();
		}
	}

}
