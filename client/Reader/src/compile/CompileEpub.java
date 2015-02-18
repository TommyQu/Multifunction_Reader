package compile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.stream.FileImageOutputStream;
import javax.swing.JOptionPane;

import domain.HtmlToText;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubReader;

public class CompileEpub {
	List<String> bookContent=new ArrayList<String>();
	int pageCount=0;
	int isExisted=1;
	int count=0;
	public CompileEpub(String path,String method)
	{
		EpubReader epubReader=new EpubReader();
		Book book;
		try {
			book=epubReader.readEpub(new FileInputStream(path));
			Resource imgresource=book.getCoverImage();
			Resource contentresource=null;
			HtmlToText htmlToText=new HtmlToText();
			String s="";
			String str="";
			String contentStr="";
			byte[] p=imgresource.getData();
			String newFilename="src/bookcover/"+book.getTitle()+".jpg"; 
			FileImageOutputStream imgout=new FileImageOutputStream(new File(newFilename)); 
			imgout.write(p,0,p.length); 
			imgout.close(); 
			List<Resource> list=book.getContents();
			for(int i=0;i<list.size();i++)
			{
				contentresource=list.get(i);
				byte[] resdata = contentresource.getData();
				s=new String(resdata,"utf-8");
				contentStr+=htmlToText.HtmlToTextAction(s);
			}
			for(int i=0;i<contentStr.length();i+=500)
			{
				if(contentStr.length()-i>500)
					str=contentStr.substring(i, i+500);
				else
					str=contentStr.substring(i, contentStr.length()-1);
				bookContent.add(str);
				pageCount++;
			}
			imgresource.close();
			contentresource.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			isExisted=0;
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			isExisted=0;
			e.printStackTrace();
		} 
		finally
		{
			if(isExisted==0)
			{
			if (method.equals("recent")) // 最近阅读中无法打开
			{
				int optionType = JOptionPane.YES_NO_OPTION; //
				int messageType = JOptionPane.WARNING_MESSAGE; //
				int result = JOptionPane.showConfirmDialog(null,
						"所选书籍不存在，是否从最近阅读中删除?", "消息", optionType,
						messageType);
				if (result == JOptionPane.YES_OPTION) {
					try {
						List<String> recentfile = new ArrayList<String>();
						BufferedReader br = new BufferedReader(
								new FileReader("src/info/recent.txt"));
						String getFile;
						System.out.println("初始recentfile长度"+recentfile.size());
						while (br.ready()) {
							getFile = br.readLine();
							recentfile.add(getFile);
							if (path.equals(getFile))
								recentfile.remove(count);
							count++;
						}
						br.close();
						System.out.println(recentfile.size());
						for(int i=0;i<recentfile.size();i++)
							System.out.println(recentfile.get(i));
						FileWriter fw = new FileWriter(new File("src/info/recent.txt"));
						fw.write("");
						fw.close();
						BufferedWriter bw = new BufferedWriter(
								new FileWriter("src/info/recent.txt",
										true));
						bw.write("");
						for (int i = 0; i < recentfile.size(); i++) {
//							System.out.println(recentfile.size());
							System.out.println("a"+recentfile.get(i));
							bw.write(recentfile.get(i));
							bw.newLine();
						}
						recentfile.clear();
						bw.close();
						JOptionPane.showMessageDialog(null,
								"从最近阅读中删除成功");
						for (int i = 0; i < frame.MainFrame  //从页面中删除该JTextArea
								.getRecentJTextArea().size(); i++) {
//							System.out.println("bb");
							File delFile = new File(path);
							if (delFile.getName().equals(
									frame.MainFrame.getRecentJTextArea().get(i)
											.getText())) {
//								System.out.println("aa"
//										+ frame.MainFrame.getRecentJTextArea()
//												.get(i));
								frame.MainFrame.getjPanel_book().remove(
										frame.MainFrame.getRecentJTextArea()
												.get(i));
								frame.MainFrame.getjPanel_book().remove(
										frame.MainFrame.getRecentJLabel()
												.get(i));
								frame.MainFrame.getjPanel_book().repaint();
								break;
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			}
		}
	}
	public List<String> getBookContent() {
		return bookContent;
	}
	public void setBookContent(List<String> bookContent) {
		this.bookContent = bookContent;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getIsExisted() {
		return isExisted;
	}
	public void setIsExisted(int isExisted) {
		this.isExisted = isExisted;
	}

}
