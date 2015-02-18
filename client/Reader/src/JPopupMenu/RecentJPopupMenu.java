package JPopupMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

public class RecentJPopupMenu extends JPopupMenu{
	private JMenuItem DelFromRecent= new JMenuItem("将该文件从最近阅读清除");
	private File file=null;
	private List<String> recent=new ArrayList<String>();
	private File chooseFile=null;
	public RecentJPopupMenu(final String path) 
	{
		this.add(DelFromRecent);
		DelFromRecent.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				// TODO Auto-generated method stub
				hid();
				int optionType = JOptionPane.YES_NO_OPTION; // ��ť����
				int messageType = JOptionPane.WARNING_MESSAGE; // ͼ������
				int result = JOptionPane.showConfirmDialog(null,
						"是否确认从最近阅读删除", "消息ʾ", optionType, messageType);
				if (result == JOptionPane.YES_OPTION) 
				{
					try 
					{
						String str;
						BufferedReader br = new BufferedReader(new FileReader("src/info/recent.txt"));
						while(br.ready())
						{
							str=br.readLine();
							File f=new File(str);
							if(f.getName().equals(path))
							{
								chooseFile=f;
								continue;
							}
							else
								recent.add(str);
						}
						br.close();
						System.out.println(recent.size());
//						recent.remove(path);
						System.out.println(recent.size());
						FileWriter fw = new FileWriter(new File("src/info/recent.txt"));
						fw.write("");
						fw.close();
						BufferedWriter bw = new BufferedWriter(
								new FileWriter("src/info/recent.txt", true));
						bw.write("");
						for (int i = 0; i < recent.size(); i++) {
//							System.out.println("a:"
//									+ List_RecentFile.get(i));
							bw.write(recent.get(i));
							bw.newLine();
						}
						recent.clear();
						bw.close();
						for (int i = 0; i < frame.MainFrame  //从页面中删除该JTextArea
								.getRecentJTextArea().size(); i++) {
				//			System.out.println("bb");
							if (chooseFile.getName().equals(
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
						JOptionPane.showMessageDialog(null, "从最近阅读删除成功");
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
					}
				}
				else
				{
					
				}
				hid();
			}
		});
	}

	public void hid() 
	{
		this.setVisible(false);
	}
}
