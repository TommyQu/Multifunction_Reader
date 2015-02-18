package compile;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.google.common.io.CharStreams;

public class CompileTxt {
	List<String> bookContent=new ArrayList<String>();
	int pageCount=0;
	int isExist=1;
	int count=0;
	public CompileTxt(String path,String method) throws IOException
	{
		try {
			FileInputStream fis = new FileInputStream(path);
			Charset charset = Charset.forName("GBK");// 创建GBK字符集
			FileChannel fc = fis.getChannel();// 得到文件通道
			ByteBuffer bf = ByteBuffer.allocate((int) fc.size());// 分配与文件尺寸等大的缓冲区
			fc.read(bf);// 整个文件内容全读入缓冲区，即是内存映射文件
			bf.rewind();// 把缓冲中当前位置回复为零
			String s = charset.decode(bf).toString();// 输出缓冲区中的内容
			String str;
			for (int i = 0; i < s.length(); i += 500) {
				if (s.length() - i > 500)
					str = s.substring(i, i + 500);
				else
					str = s.substring(i, s.length() - 1);
				bookContent.add(str);
				pageCount++;
			}
		}
		catch(Exception e)
		{
			isExist=0;
		}
		finally
		{
			if(isExist==0)
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
							if (path.equals(getFile))
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
						System.out.println(path);
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
	public int getIsExist() {
		return isExist;
	}
	public void setIsExist(int isExist) {
		this.isExist = isExist;
	}
	
}
