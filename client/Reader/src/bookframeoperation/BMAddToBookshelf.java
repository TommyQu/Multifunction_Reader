package bookframeoperation;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import mainframeaction.bookshelfaction.LoadBookshelf;
import mainframeaction.bookshelfaction.LoadCategory;

public class BMAddToBookshelf {
	List<String> listSingleCategory=new ArrayList<String>();
	public BMAddToBookshelf(final File chooseFile) throws IOException {
		// TODO Auto-generated constructor stub
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
		LoadBookshelf loadBookshelf=new LoadBookshelf();
		for(int i=0;i<loadBookshelf.getListBookcategory().size();i++)
		{
			JCheckBox jCheckBox=new JCheckBox();
			jCheckBox.setText(loadBookshelf.getListBookcategory().get(i));
			jCheckBox.setVisible(true);
			jCheckBox.setBounds(0,30*i,100,20);
			lJCheckBoxs.add(jCheckBox);
			jPanel.add(jCheckBox);
//			list_boxMenuItems.add(boxMenuItem);
		}
//		System.out.println(lJCheckBoxs.size());
		JButton confirm=new JButton("确认");
		JButton cancel=new JButton("取消");
		jDialog.setTitle("添加至书库");
		jDialog.setLayout(null);
		jDialog.setSize(350,350);
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
//		jCheckBox.setBounds(0, 0, 120, 150);
//		jCheckBox.setOpaque(false);;
		jPanel.setBounds(0, 0, 300, 300);
		jPanel.setPreferredSize(new Dimension(110, loadBookshelf.getListBookcategory().size()*30));
		jPanel.setLayout(null);
		jPanel.setVisible(true);
		jScrollPane=new JScrollPane(jPanel);
		jScrollPane.setBounds(30, 60, 120, 150);
		jScrollPane.setOpaque(false);
//		jScrollPane.setLayout(null);
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
				int flag=0;
				for(int i=0;i<lJCheckBoxs.size();i++)
				{
					if (lJCheckBoxs.get(i).isSelected()) {
						flag = 1;
						// System.out.println(lJCheckBoxs.get(i).getText());
						String str;
						int isExist = 0;
						File root = new File("src/info/bookshelf/"
								+ lJCheckBoxs.get(i).getText());
						File[] shelves = root.listFiles();
						for (File file : shelves) {
							if (file.getName().equals(chooseFile.getName())) {
								isExist = 1;
								break;
							}
						}
						if (isExist == 1)
							JOptionPane.showMessageDialog(jDialog, "同名文件已存在于 "
									+ lJCheckBoxs.get(i).getText() + " 书架下");
						else {
							try {
								FileInputStream inStream = new FileInputStream(
										chooseFile.getPath());
								FileOutputStream outStream = new FileOutputStream(
										"src/info/bookshelf/"
												+ lJCheckBoxs.get(i).getText()
												+ "/" + chooseFile.getName());
								FileChannel inChannel = inStream.getChannel();
								FileChannel outChannel = outStream.getChannel();
								ByteBuffer buffer = ByteBuffer.allocate(1024);
								while ((inChannel.read(buffer) != -1)) {
									buffer.flip();
									outChannel.write(buffer);
									buffer.clear();
								}
								inStream.close();
								outStream.close();
								inChannel.close();
								outChannel.close();
								JOptionPane.showMessageDialog(jDialog, "《"
										+ chooseFile.getName() + "》导入到 "
										+ lJCheckBoxs.get(i).getText() + "下成功");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				}
				if(flag==0)  //未选中任一书签
				{
					String str;
					int isExist = 0;
					try 
					{
						BufferedReader br = new BufferedReader(
								new FileReader(
										"src/info/bookshelf/默认书架.txt"));
						while (br.ready()) {
							str = br.readLine();
							if (str.equals(chooseFile.getPath()))
								isExist = 1;
							listSingleCategory.add(str);
						}
						br.close();
						if (isExist == 0) {
							listSingleCategory.add(0, chooseFile.getPath());
							FileWriter fw = new FileWriter(new File("src/info/bookshelf/默认书架.txt"));
							fw.write("");
							fw.close();
							BufferedWriter bw = new BufferedWriter(new FileWriter("src/info/bookshelf/默认书架.txt", true));
							bw.write("");
							for (int j = 0; j < listSingleCategory.size(); j++) {
								bw.write(listSingleCategory.get(j));
								bw.newLine();
							}
							bw.close();
							JOptionPane.showMessageDialog(null,
									"《"+chooseFile.getName()+"》" + "自动添加至 默认书架 ");
							jDialog.dispose();
						} 
						else
							JOptionPane.showMessageDialog(null, "ͬ该书已存在于 默认书架 中");
					}
					catch (IOException e1) 
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
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
