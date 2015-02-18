package mainframeaction.bookshelfaction;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import domain.IconNode;

public class EditBookshelf {
	private List<String> listBookcategory=new ArrayList<String>();
	public EditBookshelf(String nodeText,String newName) throws IOException {
		if(newName.contains(" ")||
				newName.contains("<")||
				newName.contains(">")||
				newName.contains("/")||
				newName.contains("\\")||
				newName.contains(":")||
				newName.contains("*")||
				newName.contains("?")||
				newName.equals("\""))
			JOptionPane.showMessageDialog(null, "书架名为空或包含不合法字符");
		else if(nodeText.equals("默认书架"))
			JOptionPane.showMessageDialog(null, "不能编辑默认书架");
		else
		{
			File root = new File("src/info/bookshelf/");
			File[] shelves = root.listFiles();
			for (File shelf : shelves) {
				if (shelf.getName().equals(nodeText)) {
					File newDir = new File("src/info/bookshelf/" + newName);
					newDir.mkdir();
					File[] fs = shelf.listFiles();
					for (File f : fs)
						f.renameTo(new File(newDir.getPath() + "/"
								+ f.getName()));
					if (shelf.delete()) {
						JOptionPane.showMessageDialog(null, "修改书架成功");
						for (int i = 0; i < frame.MainFrame.getBookshelf()
								.getChildCount(); i++) // 找到删除的节点，从MainFrame删除
						{
							if (nodeText.equals(((IconNode) frame.MainFrame
									.getBookshelf().getChildAt(i)).getText())) {
								((IconNode) frame.MainFrame.getBookshelf()
										.getChildAt(i)).setText(newName);
								frame.MainFrame.getTreeModel().reload(frame.MainFrame.getBookshelf());
							}
						}
					} else
						JOptionPane.showMessageDialog(null, "修改书架失败");
					break;
				}
			}
		}
	}

}
