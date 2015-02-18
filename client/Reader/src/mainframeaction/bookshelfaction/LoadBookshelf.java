package mainframeaction.bookshelfaction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class LoadBookshelf {
	private List<String> listBookcategory=new ArrayList<String>();
	public LoadBookshelf()
	{
		File root=new File("src/info/bookshelf");
		File[] shelves=root.listFiles();
		for(File file: shelves)
			listBookcategory.add(file.getName());
	}
	public List<String> getListBookcategory() {
		return listBookcategory;
	}
	public void setListBookcategory(List<String> listBookcategory) {
		this.listBookcategory = listBookcategory;
	}
}
