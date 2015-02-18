package mainframeaction.storeaction;

import java.awt.Dimension;
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

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import bookframeoperation.LoadBookmark;

public class MagStore {

	public MagStore(JMenuItem operation_magstore) {
		// TODO Auto-generated constructor stub
		operation_magstore.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
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
				LoadStore loadStore=new LoadStore();
				for(int i=0;i<loadStore.getStoreJTextArea().size();i++)
				{
					JCheckBox jCheckBox=new JCheckBox();
					jCheckBox.setText(loadStore.getStoreJTextArea().get(i).getText());
					jCheckBox.setVisible(true);
					jCheckBox.setBounds(0,30*i,130,20);
					lJCheckBoxs.add(jCheckBox);
					jPanel.add(jCheckBox);
//					list_boxMenuItems.add(boxMenuItem);
				}
//				System.out.println(lJCheckBoxs.size());
				JButton confirm=new JButton("删除");
				JButton cancel=new JButton("取消");
				jDialog.setTitle("管理我的收藏");
				jDialog.setLayout(null);
				jDialog.setSize(280,350);
				label_bookshelf.setText("请选择书籍");
				label_bookshelf.setBounds(30, 30, 100, 20);
//				jCheckBox.setBounds(0, 0, 120, 150);
//				jCheckBox.setOpaque(false);;
				jPanel.setBounds(0, 0, 180, loadStore.getStoreJTextArea().size()*30);
				jPanel.setPreferredSize(new Dimension(120, loadStore.getStoreJTextArea().size()*30));
				jPanel.setLayout(null);
				jPanel.setVisible(true);
				jScrollPane=new JScrollPane(jPanel);
				jScrollPane.setBounds(30, 60, 180, 150);
				jScrollPane.setOpaque(false);
//				jScrollPane.setLayout(null);
				confirm.setBounds(40, 250, 80, 20);
				cancel.setBounds(140, 250, 80, 20);
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
						int optionType = JOptionPane.YES_NO_OPTION; // 
						int messageType = JOptionPane.WARNING_MESSAGE; //
						int result = JOptionPane.showConfirmDialog(jDialog,
								"是否确认删除选中书籍", "消息", optionType, messageType);
						if (result == JOptionPane.YES_OPTION) 
						{
							for(int i=0;i<lJCheckBoxs.size();i++)
							{
								if(lJCheckBoxs.get(i).isSelected())
								{
									flag=1;
									String str;
									File f=new File("src/info/store/"+lJCheckBoxs.get(i).getText());
									try
									{
										f.delete();
									}
									catch(Exception e)
									{
										JOptionPane.showMessageDialog(jDialog, f.getName()+"无法删除");
									}
								}
							}
							JOptionPane.showMessageDialog(jDialog, "删除选中书籍成功");
							jDialog.dispose();
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
		});
	}

}
