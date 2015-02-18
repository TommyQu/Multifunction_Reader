package bookframeoperation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class LoadBookmark {
	private List<String> listBookmark=new ArrayList<String>();
	private File getBookmarkFile=null;
	public LoadBookmark(String fileName) throws IOException 
	{
		final File root=new File("src/info/bookmark/");
		File[] files=root.listFiles();
		for(File file:files)
		{
			if(file.getName().equals(fileName+".txt"))
			{
				getBookmarkFile=file;
			}
		}
		if (getBookmarkFile != null) 
		{
			String str = "";
			BufferedReader br = new BufferedReader(new FileReader(getBookmarkFile.getPath()));
			while (br.ready()) 
			{
				str = br.readLine();
				listBookmark.add(str);
			}
			br.close();
		}
		else
			JOptionPane.showMessageDialog(null, "该书尚无书签");
	}
	public List<String> getListBookmark() {
		return listBookmark;
	}
	public void setListBookmark(List<String> listBookmark) {
		this.listBookmark = listBookmark;
	}
}
