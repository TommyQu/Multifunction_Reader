package JPopupMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

public class ShelfBookJPopupMenu extends JPopupMenu{
	private JMenuItem delFromShelf=new JMenuItem("从书架中删除");
	private JMenuItem addToStore=new JMenuItem("添加到我的收藏");
	private int isExist=0;
	public ShelfBookJPopupMenu(final String nodeText,final String bookName)
	{
		this.add(delFromShelf);
		this.add(addToStore);
		delFromShelf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				hid();
				int optionType = JOptionPane.YES_NO_OPTION; // ��ť����
				int messageType = JOptionPane.WARNING_MESSAGE; // ͼ������
				int result = JOptionPane.showConfirmDialog(null,
						"是否确认从本地书架删除", "消息ʾ", optionType, messageType);
				if (result == JOptionPane.YES_OPTION) 
				{
					File root=new File("src/info/bookshelf/"+nodeText+"/");
					File[] files=root.listFiles();
					for(File f:files)
					{
						if(f.getName().equals(bookName))
						{
							if(f.delete())
							{
								JOptionPane.showMessageDialog(null, "从本地书架删除成功");
								for(int i=0;i<frame.MainFrame.getBookshelfJTextArea().size();i++)
								{
									if(frame.MainFrame.getBookshelfJTextArea().get(i).getText().equals(bookName))
									{
										frame.MainFrame.getjPanel_book().remove(frame.MainFrame.getBookshelfJTextArea().get(i));
										frame.MainFrame.getjPanel_book().remove(frame.MainFrame.getBookshelfJLabel().get(i));
										frame.MainFrame.getjPanel_book().repaint();
										break;
									}
								}
							}
							
						}
					}
				}
			}
		});
		addToStore.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				hid();
				File f=new File("src/info/store/"+nodeText+"/"+bookName);
				File root=new File("src/info/store");
				File[] files=root.listFiles();
				if(files!=null)
				{
					for(File ff:files)
					{
						if(ff.getName().equals(bookName))
						{
							JOptionPane.showMessageDialog(null, "同名书籍已存在于我的收藏中");
							isExist=1;
							break;
						}
					}
				}
				if(isExist==0)
				{
					try {
						FileInputStream inStream = new FileInputStream(
								"src/info/bookshelf/"+nodeText+"/"+bookName);
						FileOutputStream outStream = new FileOutputStream(
								"src/info/store/"+bookName);
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
						JOptionPane.showMessageDialog(null, "《"
								+ bookName + "》导入到 "
								+ "我的收藏中成功");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "找不到指定文件");
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
