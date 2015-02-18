package mainframeaction;

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
import javax.swing.JPanel;

import sun.security.jca.GetInstance.Instance;
import compile.CompileEpub;
import compile.CompilePdf;
import compile.CompileTxt;
import frame.BookFrame;

public class OpenSelectedBook {
	File chooseFile;
	List<String> book_content=new ArrayList<String>();
	int count=0;
	List<String> List_RecentFile = new ArrayList<String>();
	int pageCount;
	int isExist=1;
	public OpenSelectedBook(String category_name,String book_name,String method) throws IOException
	{
		String str;
		if(method.equals("online"))
		{
//			chooseFile=new File("http:/"+"/"+"/localhost/"+frame.MainFrame.getLoginName().getText()+"/"+category_name+"/"+book_name);
//			System.out.println(chooseFile.getPath());
		}
		else if(method.equals("store"))
		{
			chooseFile=new File("src/info/store/"+book_name);
		}
		else if(method.equals("bookshelf"))
		{
			File rf=null;
			File root = new File(category_name);
			File[] shelves = root.listFiles();
			for (File file : shelves) {
				if (file.getName().equals(book_name)) {
					chooseFile=file;
					isExist = 1;
					break;
				}
			}
		}
		else if(method.equals("recent"))
		{
			File rf;
			BufferedReader br = new BufferedReader(new FileReader(category_name));
			while(br.ready())
			{
				str=br.readLine();
				rf=new File(str);
				if(book_name.equals(rf.getName()))
				{
					isExist=1;
					chooseFile=rf;
				}
			}
			br.close();
		}
//		System.out.println(chooseFile.getName());
		if (chooseFile.getName().endsWith(".pdf")) {
			CompilePdf compilePdf = new CompilePdf(chooseFile.getPath(), method);
			if (compilePdf.getIsExist() == 0)
				isExist = 0;
			else {
				book_content = compilePdf.getBookContent();
				pageCount = compilePdf.getPageCount();
			}
		} else if (chooseFile.getName().endsWith(".epub")) {
			CompileEpub compileEpub = new CompileEpub(chooseFile.getPath(),
					method);
			if (compileEpub.getIsExisted() == 0) {
				isExist = 0;
			} else {
				book_content = compileEpub.getBookContent();
				pageCount = compileEpub.getPageCount();
			}
		} else if (chooseFile.getName().endsWith(".txt")) {
			CompileTxt compileTxt = new CompileTxt(chooseFile.getPath(), method);
			if (compileTxt.getIsExist() == 0)
				isExist = 0;
			else {
				book_content = compileTxt.getBookContent();
				pageCount = compileTxt.getPageCount();
			}
		}
		if(isExist==1)
		{
		try {
			List_RecentFile.add(chooseFile.getPath());
			BufferedReader br1 = new BufferedReader(new FileReader(
					"src/info/recent.txt"));
			while (br1.ready()) {
				str = br1.readLine();
				List_RecentFile.add(str);
				count++;
				// System.out.println("aaa"+str);
				if (str.equals(chooseFile.getPath()))
					List_RecentFile.remove(count);
			}
			br1.close();
			if (List_RecentFile.size() == 21)
				List_RecentFile.remove(20);
			FileWriter fw = new FileWriter(new File("src/info/recent.txt"));
			fw.write("");
			fw.close();
			BufferedWriter bw = new BufferedWriter(new FileWriter(
					"src/info/recent.txt", true));
			bw.write("");
			for (int i = 0; i < List_RecentFile.size(); i++) {
				// System.out.println("a:"
				// + List_RecentFile.get(i));
				bw.write(List_RecentFile.get(i));
				bw.newLine();
			}
			List_RecentFile.clear();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		BookFrame bookFrame = new BookFrame(chooseFile, book_content, pageCount);
		}
	}

	public int getIsExist() {
		return isExist;
	}
	public void setIsExist(int isExist) {
		this.isExist = isExist;
	}

}
