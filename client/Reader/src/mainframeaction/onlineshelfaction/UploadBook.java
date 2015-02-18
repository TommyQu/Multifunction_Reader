package mainframeaction.onlineshelfaction;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.EncodingUtil;

import sun.awt.OSInfo.OSType;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import domain.IconNode;
import mainframeaction.bookshelfaction.LoadBookshelf;

public class UploadBook {

	public UploadBook(JMenuItem online_UploadBook) {
		// TODO Auto-generated constructor stub
		online_UploadBook.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (frame.MainFrame.getLoginName().getText().equals("无"))
					JOptionPane.showMessageDialog(null, "请先登录");
				else {
					JFileChooser fileChooser = new JFileChooser();
					final File chooseFile;
					fileChooser.setCurrentDirectory(new File("F:\\"));
					fileChooser.setAcceptAllFileFilterUsed(false);
					fileChooser
							.addChoosableFileFilter(new javax.swing.filechooser.FileFilter() {
								@Override
								public boolean accept(File file) {
									if (file.getName().endsWith(".txt")
											|| file.getName().endsWith(".pdf")
											|| file.getName().endsWith(".epub")
											|| file.getName().endsWith(".mobi")
											|| file.isDirectory()) {
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
					final JDialog jDialog = new JDialog();
					JLabel label_bookshelf = new JLabel();
					JLabel label_fileName = new JLabel();
					JLabel label_fileName2 = new JLabel();
					JLabel label_filePath = new JLabel();
					JLabel label_filePath2 = new JLabel();
					JLabel label_fileLength = new JLabel();
					JLabel label_fileLength2 = new JLabel();
					JPanel jPanel = new JPanel();
					JScrollPane jScrollPane = null;
					final List<JCheckBox> lJCheckBoxs = new ArrayList<JCheckBox>();
					LoadOnlineShelf loadOnlineShelf = new LoadOnlineShelf(
							frame.MainFrame.getLoginName().getText());
//					System.out.println(loadOnlineShelf.getLlist().size());
					for (int i = 0; i < loadOnlineShelf.getLlist().size(); i++) {
						JCheckBox jCheckBox = new JCheckBox();
						jCheckBox.setText(loadOnlineShelf.getLlist().get(i));
						jCheckBox.setVisible(true);
						jCheckBox.setBounds(0, 30 * i, 100, 20);
						lJCheckBoxs.add(jCheckBox);
						jPanel.add(jCheckBox);
						// list_boxMenuItems.add(boxMenuItem);
					}
					// System.out.println(lJCheckBoxs.size());
					JButton confirm = new JButton("确认");
					JButton cancel = new JButton("取消");
					jDialog.setTitle("添加至书库");
					jDialog.setLayout(null);
					jDialog.setSize(350, 350);
					label_bookshelf.setText("请选择书库");
					label_bookshelf.setBounds(30, 30, 100, 20);
					label_fileName.setText("文件名：");
					label_fileName.setBounds(200, 30, 100, 20);
					label_fileName2.setText(chooseFile.getName());
					label_fileName2.setBounds(220, 60, 100, 20);
					label_filePath.setText("文件路径：");
					label_filePath.setBounds(200, 90, 100, 20);
					label_filePath2.setText(chooseFile.getPath());
					label_filePath2.setBounds(220, 120, 100, 20);
					label_fileLength.setText("文件长度：");
					label_fileLength.setBounds(200, 150, 100, 20);
					label_fileLength2.setText(Long.toString(chooseFile.length()));
					label_fileLength2.setBounds(220, 180, 100, 20);
					// jCheckBox.setBounds(0, 0, 120, 150);
					// jCheckBox.setOpaque(false);;
					jPanel.setBounds(0, 0, 300, 300);
					jPanel.setPreferredSize(new Dimension(110, loadOnlineShelf
							.getLlist().size() * 30));
					jPanel.setLayout(null);
					jPanel.setVisible(true);
					jScrollPane = new JScrollPane(jPanel);
					jScrollPane.setBounds(30, 60, 120, 150);
					jScrollPane.setOpaque(false);
					// jScrollPane.setLayout(null);
					confirm.setBounds(50, 250, 80, 20);
					cancel.setBounds(200, 250, 80, 20);
					jDialog.add(label_bookshelf);
					jDialog.add(label_fileName);
					jDialog.add(label_fileName2);
					jDialog.add(label_filePath);
					jDialog.add(label_filePath2);
					jDialog.add(label_fileLength);
					jDialog.add(label_fileLength2);
					jDialog.add(jScrollPane);
					jDialog.add(confirm);
					jDialog.add(cancel);
					jDialog.setVisible(true);
					jDialog.setAlwaysOnTop(true);
					confirm.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							// postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
							// new DefaultHttpMethodRetryHandler());
							for (int i = 0; i < lJCheckBoxs.size(); i++) 
							{
								if (lJCheckBoxs.get(i).isSelected()) 
								{
									try 
									{
										String url = "http://localhost/reader/UploadBook.php";
										PostMethod postMethod = new PostMethod(url);
										Part[] parts = { new FilePart("file",chooseFile),
												new StringPart("username", frame.MainFrame.getLoginName().getText(),"utf-8"),
												new StringPart("shelfname",lJCheckBoxs.get(i).getText(),"utf-8")};
										postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
										postMethod.setRequestEntity(new MultipartRequestEntity(parts, postMethod.getParams()));
										int statusCode = frame.MainFrame.getHttpClient().executeMethod(postMethod);
//										System.out.println(lJCheckBoxs.get(i).getText());
										if (statusCode == HttpStatus.SC_OK)
										{
											byte[] responseBody = postMethod
													.getResponseBody();
											String str_response = new String(
													responseBody);
//											System.out.println(str_response);
											if (str_response.equals("﻿上传成功"))
											{
												JOptionPane.showMessageDialog(jDialog, "上传成功");
						
												frame.MainFrame.getjPanel_book().revalidate();
											}
											else if(str_response.equals("﻿同名书籍已存在"))
												JOptionPane.showMessageDialog(jDialog, "同名书籍已存在");
											else if(str_response.equals("大小"))
												JOptionPane.showMessageDialog(jDialog, "请上传小于10M的文件");
											else
												JOptionPane.showMessageDialog(jDialog, "服务器错误");
										} 
										else
											JOptionPane.showMessageDialog(jDialog, "无法连接到服务器");
									} 
									catch (HttpException e1) 
									{
										// TODO Auto-generated catch block
										e1.printStackTrace();
									} 
									catch (IOException e1) 
									{
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								} 
//								else if (i == (lJCheckBoxs.size() - 1)) 
//								{
//									JOptionPane.showMessageDialog(jDialog,"请选择书架");
//								}
							}
							jDialog.dispose();
						}
					});
					cancel.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							// TODO Auto-generated method stub
							jDialog.dispose();
						}
					});
				}
			}
		});
	}
}
