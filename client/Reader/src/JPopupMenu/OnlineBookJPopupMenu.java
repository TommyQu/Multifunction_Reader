package JPopupMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import domain.IconNode;

public class OnlineBookJPopupMenu extends JPopupMenu{
	private JMenuItem dowload=new JMenuItem("下载到本地");
	private JMenuItem delete=new JMenuItem("从在线书架删除");
	public OnlineBookJPopupMenu(final String nodeText,final String bookName)
	{
		this.add(dowload);
		this.add(delete);
		dowload.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				hid();
				// TODO Auto-generated method stub
				JFileChooser fd=new JFileChooser();
				fd.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int button=fd.showSaveDialog(null);
				File f=null;
				if(button==fd.APPROVE_OPTION)
				{
					f=fd.getSelectedFile();
					String url="http://localhost/reader/DownloadBook.php";
					PostMethod postMethod=new PostMethod(url);
					postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
					postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
							new DefaultHttpMethodRetryHandler());
					NameValuePair[] editshelfInformation={ new NameValuePair("username", frame.MainFrame.getLoginName().getText()),
							new NameValuePair("nodetext", nodeText),
							new NameValuePair("bookname", bookName),
							new NameValuePair("savepath",f.getPath())};
					postMethod.setRequestBody(editshelfInformation);
					try {
						int statusCode = frame.MainFrame.getHttpClient().executeMethod(postMethod);
						if (statusCode == HttpStatus.SC_OK) 
						{
							byte[] responseBody=postMethod.getResponseBody();
							String str_response=new String(responseBody);
							if(str_response.equals("下载成功"))
								JOptionPane.showMessageDialog(null, "下载成功");
							else
								JOptionPane.showMessageDialog(null, "下载失败");
//							FileWriter fileWriter=new FileWriter(new File("F:/"+bookName));
//							fileWriter.write(str_response);
//							fileWriter.close();
//							System.out.println(str_response);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "无法连接到服务器");
						}
					} catch (HttpException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
	//			System.out.println(f.getPath());
			}
		});
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				hid();
				int optionType = JOptionPane.YES_NO_OPTION; // ��ť����
				int messageType = JOptionPane.WARNING_MESSAGE; // ͼ������
				int result = JOptionPane.showConfirmDialog(null,
						"是否确认从在线书架删除", "消息ʾ", optionType, messageType);
				if (result == JOptionPane.YES_OPTION) 
				{
					String url="http://localhost/reader/DelBook.php";
					PostMethod postMethod=new PostMethod(url);
					postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
					postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
							new DefaultHttpMethodRetryHandler());
					NameValuePair[] editshelfInformation={ new NameValuePair("username", frame.MainFrame.getLoginName().getText()),
							new NameValuePair("nodetext", nodeText),
							new NameValuePair("bookname", bookName)};
					postMethod.setRequestBody(editshelfInformation);
					try {
						int statusCode = frame.MainFrame.getHttpClient().executeMethod(postMethod);
						if (statusCode == HttpStatus.SC_OK) 
						{
							byte[] responseBody=postMethod.getResponseBody();
							String str_response=new String(responseBody);
							System.out.println(str_response);
							if(str_response.equals("删除成功"))
							{
								JOptionPane.showMessageDialog(null, "删除成功");
								for(int i=0;i<frame.MainFrame.getOnlineshelfJTextArea().size();i++) //找到删除的节点，从MainFrame删除
								{
									if(frame.MainFrame.getOnlineshelfJTextArea().get(i).getText().equals(bookName))
									{
//										System.out.println(frame.MainFrame.getOnlineshelfJTextArea().get(i));
										frame.MainFrame.getjPanel_book().remove(frame.MainFrame.getOnlineshelfJTextArea().get(i));
										frame.MainFrame.getjPanel_book().remove(frame.MainFrame.getOnlineshelfJLabel().get(i));
										frame.MainFrame.getjPanel_book().repaint();
										break;
									}
								}
							}
							else
								JOptionPane.showMessageDialog(null, "删除失败");
						}
						else
						{
							JOptionPane.showMessageDialog(null, "无法连接到服务器");
						}
					} catch (HttpException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
	}
	public void hid()
	{
		this.setVisible(false);
	}

}
