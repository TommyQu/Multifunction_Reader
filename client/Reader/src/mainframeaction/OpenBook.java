package mainframeaction;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import compile.CompileEpub;
import compile.CompilePdf;
import compile.CompileTxt;
import frame.BookFrame;

public class OpenBook {
	JFileChooser fileChooser = new JFileChooser();
	File chooseFile;
	List<String> book_content=new ArrayList<String>();
	int pageCount;
	int count=0;
	List<String> List_RecentFile = new ArrayList<String>();
	String str;
	public OpenBook() throws IOException
	{
		fileChooser.setCurrentDirectory(new File("F:\\"));
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser
				.addChoosableFileFilter(new javax.swing.filechooser.FileFilter() {
					@Override
					public boolean accept(File file) {
						if (file.getName().endsWith(".txt")
								|| file.getName().endsWith(
										".pdf")
								|| file.getName().endsWith(
										".epub")
								|| file.getName().endsWith(
										".mobi")||file.isDirectory()) {
							return true;
						} else
							return false;
					}

					@Override
					public String getDescription() {
						// TODO Auto-generated method stub
						return "请选择txt.pdf.epub.mobi格式的文件";
					}
				});
		fileChooser.showOpenDialog(fileChooser);
		chooseFile = fileChooser.getSelectedFile();
		if (chooseFile != null) {
			if (chooseFile.getName().endsWith(".pdf")) {
				CompilePdf compilePdf = new CompilePdf(
						chooseFile.getPath(),null);
				book_content = compilePdf.getBookContent();
				pageCount = compilePdf.getPageCount();
				// System.out.print(content);
			} else if (chooseFile.getName().endsWith(".epub")) {
				CompileEpub compileEpub = new CompileEpub(
						chooseFile.getPath(),null);
				book_content = compileEpub.getBookContent();
				pageCount = compileEpub.getPageCount();
			} else if(chooseFile.getName().endsWith(".txt")){
				CompileTxt compileTxt=new CompileTxt(chooseFile.getPath(),null);
				book_content=compileTxt.getBookContent();
				pageCount=compileTxt.getPageCount();

			}
		}
		try {
			List_RecentFile.add(chooseFile.getPath());
			BufferedReader br = new BufferedReader(new FileReader("src/info/recent.txt"));
			while (br.ready()) {
				str = br.readLine();
				List_RecentFile.add(str);
				count++;
				// System.out.println("aaa"+str);
				if(str.equals(chooseFile.getPath()))
					List_RecentFile.remove(count);
			}
			br.close();
			if(List_RecentFile.size()==21)
				List_RecentFile.remove(20);
			FileWriter fw = new FileWriter(new File(
					"src/info/recent.txt"));
			fw.write("");
			fw.close();
			BufferedWriter bw = new BufferedWriter(
					new FileWriter("src/info/recent.txt", true));
			bw.write("");
			for (int i = 0; i < List_RecentFile.size(); i++) {
//				System.out.println("a:"
//						+ List_RecentFile.get(i));
				bw.write(List_RecentFile.get(i));
				bw.newLine();
			}
			List_RecentFile.clear();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		System.out.println("a"+book_content.size());
		BookFrame bookFrame = new BookFrame(chooseFile, book_content,pageCount);
		book_content.clear();
	}
}
