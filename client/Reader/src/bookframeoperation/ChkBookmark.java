package bookframeoperation;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;


public class ChkBookmark {
	List<String> listBookmarks=new ArrayList<String>();
	int leftPageNum=0;
	public ChkBookmark(final File chooseFile) throws IOException
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
		final ButtonGroup buttonGroup=new ButtonGroup();
		final List<JRadioButton> listRatioButton=new ArrayList<JRadioButton>();
		LoadBookmark loadBookmark=new LoadBookmark(chooseFile.getName());
		listBookmarks=loadBookmark.getListBookmark();
		for(int i=0;i<listBookmarks.size();i++)
		{
			JRadioButton jRadioButton=new JRadioButton();
			jRadioButton.setText(listBookmarks.get(i));
			jRadioButton.setVisible(true);
			jRadioButton.setBounds(0,30*i,130,20);
			listRatioButton.add(jRadioButton);
			buttonGroup.add(jRadioButton);
			jPanel.add(jRadioButton);
		}
//		System.out.println(jPanel.getComponentCount());
		JButton confirm=new JButton("确认");
		JButton cancel=new JButton("取消");
		jDialog.setTitle("查看书签");
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
		label_filePath2.setBounds(250, 120, 150, 20);
		label_fileLength.setText("文件长度：");
		label_fileLength.setBounds(230, 150, 100, 20);
		label_fileLength2.setText(Long.toString(chooseFile.length()));
		label_fileLength2.setBounds(250, 180, 100, 20);
//		jCheckBox.setBounds(0, 0, 120, 150);
//		jCheckBox.setOpaque(false);;
		jPanel.setBounds(0, 0, 120, listBookmarks.size()*30);
		jPanel.setPreferredSize(new Dimension(120, listBookmarks.size()*30));
		jPanel.setLayout(null);
		jPanel.setOpaque(false);
		jPanel.setVisible(true);
		JScrollPane jScrollPane=new JScrollPane(jPanel);
//		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//		jScrollPane.setViewportView(jPanel);
		jScrollPane.setBounds(30, 60, 150, 150);
		jScrollPane.setOpaque(false);
//		jScrollPane.getViewport().setOpaque(false); 
		jScrollPane.setVisible(true);
//		jScrollPane.setLayout(null);
		confirm.setBounds(100, 250, 80, 20);
		cancel.setBounds(250, 250, 80, 20);
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
			public void actionPerformed(ActionEvent arg0) 
			{
				// TODO Auto-generated method stub
				String markText=null;
				for(int i=0;i<listRatioButton.size();i++)
				{
					if(listRatioButton.get(i).isSelected())
					{
						markText=listRatioButton.get(i).getText();
						break;
					}
				}
				if(markText!=null)
				{
					leftPageNum=Integer.parseInt(markText.substring(markText.indexOf("页数")+3, markText.length()));
//					System.out.println(leftPageNum);
					if (leftPageNum % 2 == 1) 
					{
						int newLeft = leftPageNum;
						int newRight = newLeft + 1;
						frame.BookFrame.getjPanel().removeAll();
						frame.BookFrame.getjPanel()
								.add(frame.BookFrame.getBook_area().get(
										newLeft-1));
						frame.BookFrame.getjPanel().add(
								frame.BookFrame.getBook_area()
										.get(newRight-1));
						frame.BookFrame.getLeftPageNum().setText(
								Integer.toString(newLeft));
						frame.BookFrame.getRightPageNum().setText(
								Integer.toString(newRight));
						frame.BookFrame.getjPanel().add(frame.BookFrame.getLeftPageNum());
						frame.BookFrame.getjPanel().add(frame.BookFrame.getRightPageNum());
					}
					else
					{
						int newRight = leftPageNum;
						int newLeft = newRight - 1;
						frame.BookFrame.getjPanel().removeAll();
						frame.BookFrame.getjPanel().add(frame.BookFrame.getBook_area().get(
										newLeft-1));
						frame.BookFrame.getjPanel().add(
								frame.BookFrame.getBook_area()
										.get(newRight-1));
						frame.BookFrame.getLeftPageNum().setText(
								Integer.toString(newLeft));
						frame.BookFrame.getRightPageNum().setText(
								Integer.toString(newRight));
						frame.BookFrame.getjPanel().add(frame.BookFrame.getLeftPageNum());
						frame.BookFrame.getjPanel().add(frame.BookFrame.getRightPageNum());
					}
					frame.BookFrame.getjPanel().repaint();
					jDialog.dispose();
				}
				else
					JOptionPane.showMessageDialog(jDialog, "请选择一个书签");
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
