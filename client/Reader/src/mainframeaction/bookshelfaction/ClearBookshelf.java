package mainframeaction.bookshelfaction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import domain.IconNode;

public class ClearBookshelf {

	public ClearBookshelf(JMenuItem operation_clearbookshelf) {
		// TODO Auto-generated constructor stub
		operation_clearbookshelf.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int optionType = JOptionPane.YES_NO_OPTION; //
				int messageType = JOptionPane.WARNING_MESSAGE; // 
				int result = JOptionPane.showConfirmDialog(null,
						"是否确认清空本地书架", "消息", optionType, messageType);
				if (result == JOptionPane.YES_OPTION) 
				{
					try
					{
						File root = new File("src/info/bookshelf/");
						File[] shelves = root.listFiles();
						for (File shelve : shelves) 
						{
							if (shelve.getName().equals("默认书架") == false) 
							{
								File[] fs = shelve.listFiles();
								for (File f : fs)
									f.delete();
								shelve.delete();
							}
							else
							{
								File[] fs = shelve.listFiles();
								for (File f : fs)
									f.delete();
							}
						}
						JOptionPane.showMessageDialog(null, "清空本地书架成功");
						frame.MainFrame.getBookshelf().removeAllChildren();
						IconNode iconNode=new IconNode(new ImageIcon("src/img/BookshelfFont.png"), "默认书架");
						iconNode.setFather("bookshelf");
						frame.MainFrame.getBookshelf().add(iconNode);
						frame.MainFrame.getTreeModel().reload();
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		});
	}

}
