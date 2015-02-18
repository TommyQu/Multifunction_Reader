package mainframeaction.bookshelfaction;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import domain.IconNode;
import frame.*;

public class DelBookshelf {
	private List<String> listBookcategory=new ArrayList<String>();
	public DelBookshelf(String nodeText) throws IOException
	{
		String str="";
		if(nodeText.equals("默认书架"))
			JOptionPane.showMessageDialog(null, "无法删除默认书架");
		else 
		{
			File root=new File("src/info/bookshelf/");
			File[] shelves=root.listFiles();
			for(File shelf:shelves)
			{
				if(shelf.getName().equals(nodeText))
				{
					File[] fs=shelf.listFiles();
					for(File f:fs)
					{
						f.delete();
					}
					if (shelf.delete()) 
					{
						JOptionPane.showMessageDialog(null,
								"删除成功");
						break;
					}
					else
					{
						JOptionPane.showMessageDialog(null, "无法删除");
					}
				}
			}
			for(int i=0;i<frame.MainFrame.getBookshelf().getChildCount();i++) //找到删除的节点，从MainFrame删除
			{
				if(nodeText.equals(((IconNode)frame.MainFrame.getBookshelf().getChildAt(i)).getText()))
				{
					frame.MainFrame.getBookshelf().remove(i);
					frame.MainFrame.getTreeModel().reload(frame.MainFrame.getBookshelf());
					frame.MainFrame.getjPanel_book().removeAll();
				}
			}

		}
	}
}
