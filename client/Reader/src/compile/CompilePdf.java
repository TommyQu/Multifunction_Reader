package compile;
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

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import frame.*;

public class CompilePdf {
	List<String> bookContent=new ArrayList<String>();
	int pageCount=0;
	int isExist=1;
	int count=0;
	public CompilePdf(String file,String method) throws IOException
	{
		String s="";
		String str="";
		String pdfFile=file;
		PDDocument pdfDocument=null;
		try
		{
			pdfDocument=PDDocument.load(pdfFile);
			PDFTextStripper stripper=new PDFTextStripper();
			s=stripper.getText(pdfDocument);
			for(int i=0;i<s.length();i+=500)
			{
				if(s.length()-i>500)
					str=s.substring(i, i+500);
				else
					str=s.substring(i, s.length()-1);
				bookContent.add(str);
				pageCount++;
			}
		}
		catch(IOException e)
		{
			isExist=0;
			e.printStackTrace();
		}
		finally
		{
			try
			{
				pdfDocument.close();
			}
			catch(IOException e)
			{
				JOptionPane.showMessageDialog(null, "系统错误");
//				isExist=0;
				e.printStackTrace();
			}
			finally
			{
				if (isExist == 0) 
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
								while (br.ready()) {
									getFile = br.readLine();
									recentfile.add(getFile);
									if (file.equals(getFile))
										recentfile.remove(count);
									count++;
								}
								System.out.println(count);
								br.close();
								FileWriter fw = new FileWriter(new File(
										"src/info/recent.txt"));
								fw.write("");
								fw.close();
								BufferedWriter bw = new BufferedWriter(
										new FileWriter("src/info/recent.txt",
												true));
								bw.write("");
								System.out.println(file);
								for (int i = 0; i < recentfile.size(); i++) {
									System.out.println(recentfile.size());
									System.out.println(recentfile.get(i));
									bw.write(recentfile.get(i));
									bw.newLine();
								}
								recentfile.clear();
								bw.close();
								JOptionPane.showMessageDialog(null,
										"从最近阅读中删除成功");
								for (int i = 0; i < frame.MainFrame  //从页面中删除该JTextArea
										.getRecentJTextArea().size(); i++) {
									System.out.println("bb");
									File delFile = new File(file);
									if (delFile.getName().equals(
											frame.MainFrame.getRecentJTextArea().get(i)
													.getText())) {
//										System.out.println("aa"
//												+ frame.MainFrame.getRecentJTextArea()
//														.get(i));
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
					//repaint
				}
			}
		}
	}
	public int getIsExist() {
		return isExist;
	}
	public void setIsExist(int isExist) {
		this.isExist = isExist;
	}
//	public static String getText(String file)
//	{
//		String s="";
//		String pdfFile=file;
//		PDDocument pdfDocument=null;
//		try
//		{
//			pdfDocument=PDDocument.load(pdfFile);
//			PDFTextStripper stripper=new PDFTextStripper();
//			s=stripper.getText(pdfDocument);
//		}
//		catch(IOException e)
//		{
//			e.printStackTrace();
//		}
//		finally
//		{
//			try
//			{
//				if(pdfDocument!=null)
//				{
//					pdfDocument.close();
//				}
//			}
//			catch(IOException e)
//			{
//				e.printStackTrace();
//			}
//		}
//		return s;
//	}
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
}
