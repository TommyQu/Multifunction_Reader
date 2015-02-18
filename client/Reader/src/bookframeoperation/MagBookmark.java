package bookframeoperation;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class MagBookmark {
	List<String> listBookmarks=new ArrayList<String>();
	int leftPageNum=0;
	public MagBookmark(final File chooseFile) throws IOException
	{
		final JDialog jDialog=new JDialog();
		JLabel label_bookshelf=new JLabel();
		JLabel label_fileName=new JLabel();
		JLabel label_fileName2=new JLabel();
		JLabel label_filePath=new JLabel();
		JLabel label_filePath2=new JLabel();
		JLabel label_fileLength=new JLabel();
		JLabel label_fileLength2=new JLabel();
		JPanel jPanel=new JPanel();
		JScrollPane jScrollPane=null;
		final List<JCheckBox> lJCheckBoxs=new ArrayList<JCheckBox>();
		LoadBookmark loadBookmark=new LoadBookmark(chooseFile.getName());
		for(int i=0;i<loadBookmark.getListBookmark().size();i++)
		{
			JCheckBox jCheckBox=new JCheckBox();
			jCheckBox.setText(loadBookmark.getListBookmark().get(i));
			jCheckBox.setVisible(true);
			jCheckBox.setBounds(0,30*i,130,20);
			lJCheckBoxs.add(jCheckBox);
			jPanel.add(jCheckBox);
//			list_boxMenuItems.add(boxMenuItem);
		}
//		System.out.println(lJCheckBoxs.size());
		JButton confirm=new JButton("删除选中书签");
		JButton cancel=new JButton("取消");
		jDialog.setTitle("管理书签");
		jDialog.setLayout(null);
		jDialog.setSize(450,350);
		label_bookshelf.setText("请选择书签");
		label_bookshelf.setBounds(30, 30, 100, 20);
		label_fileName.setText("文件名：");
		label_fileName.setBounds(230, 30, 100, 20);
		label_fileName2.setText(chooseFile.getName());
		label_fileName2.setBounds(250, 60, 100, 20);
		label_filePath.setText("文件路径：");
		label_filePath.setBounds(230, 90, 100, 20);
		label_filePath2.setText(chooseFile.getPath());
		label_filePath2.setBounds(250, 120, 100, 20);
		label_fileLength.setText("文件长度：");
		label_fileLength.setBounds(230, 150, 100, 20);
		label_fileLength2.setText(Long.toString(chooseFile.length()));
		label_fileLength2.setBounds(250, 180, 100, 20);
//		jCheckBox.setBounds(0, 0, 120, 150);
//		jCheckBox.setOpaque(false);;
		jPanel.setBounds(0, 0, 120, loadBookmark.getListBookmark().size()*30);
		jPanel.setPreferredSize(new Dimension(120, loadBookmark.getListBookmark().size()*30));
		jPanel.setLayout(null);
		jPanel.setVisible(true);
		jScrollPane=new JScrollPane(jPanel);
		jScrollPane.setBounds(30, 60, 150, 150);
		jScrollPane.setOpaque(false);
//		jScrollPane.setLayout(null);
		confirm.setBounds(70, 250, 120, 20);
		cancel.setBounds(220, 250, 120, 20);
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
		confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int flag=0;
				for(int i=0;i<lJCheckBoxs.size();i++)
				{
					if(lJCheckBoxs.get(i).isSelected())
					{
						flag=1;
						String str;
						try {
							BufferedReader br = new BufferedReader(new FileReader("src/info/bookmark/"+chooseFile.getName()+".txt"));
							while (br.ready()) 
							{
								str = br.readLine();
								if(str.equals(lJCheckBoxs.get(i).getText()))
								{
//									System.out.println(str);
									continue;
								}
								else
									listBookmarks.add(str);
							}
							br.close();
							listBookmarks.clear();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}
				if(flag==1)
				{
					int optionType = JOptionPane.YES_NO_OPTION; // 
					int messageType = JOptionPane.WARNING_MESSAGE; //
					int result = JOptionPane.showConfirmDialog(jDialog,
							"是否确认删除选中书签", "消息", optionType, messageType);
					if (result == JOptionPane.YES_OPTION) 
					{
						try {
							FileWriter fw = new FileWriter(new File("src/info/bookmark/"+chooseFile.getName()+".txt"));
							fw.write("");
							fw.close();
							BufferedWriter bw = new BufferedWriter(new FileWriter(
									"src/info/bookmark/"+chooseFile.getName()+".txt", true));
							bw.write("");
							for (int j = 0; j < listBookmarks.size(); j++) {
								bw.write(listBookmarks.get(j));
								bw.newLine();
							}
							bw.close();
							JOptionPane.showMessageDialog(jDialog, "删除选中书签成功");
							jDialog.dispose();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				else
					JOptionPane.showMessageDialog(jDialog, "请选择书签操作");
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
