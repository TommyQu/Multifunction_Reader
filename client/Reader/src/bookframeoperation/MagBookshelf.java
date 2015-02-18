package bookframeoperation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import domain.IconNode;
import mainframeaction.bookshelfaction.DelBookshelf;
import mainframeaction.bookshelfaction.LoadBookshelf;
import mainframeaction.bookshelfaction.LoadCategory;

public class MagBookshelf {
	public MagBookshelf(JMenuItem operation_magbookshelf){
		// TODO Auto-generated constructor stub
		operation_magbookshelf.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				List<String> bookshelf=new ArrayList<String>();
				final List<JCheckBox> lJCheckBoxs=new ArrayList<JCheckBox>();
				final JDialog jDialog=new JDialog();
				jDialog.setTitle("管理本地书架");
				jDialog.setSize(400,500);
				jDialog.setLayout(null);
				jDialog.setVisible(true);
				jDialog.setAlwaysOnTop(true);
				final JComboBox<String> jComboBox=new JComboBox<String>();
				jComboBox.setBounds(50, 30, 150, 30);
				jComboBox.setBackground(Color.WHITE);
				jComboBox.setFocusable(false);  //选中背景为无色
				jComboBox.addItem("");
				JButton delbookshelf=new JButton();
				delbookshelf.setText("删除选中书架");
				delbookshelf.setBounds(220, 30, 120, 30);
				JButton confirm=new JButton();
				confirm.setText("确认删除选中书籍");
				confirm.setBounds(70, 400, 140, 20);
				JButton cancel=new JButton();
				cancel.setText("取消");
				cancel.setBounds(240, 400, 80, 20);
				final JPanel jPanel=new JPanel();
				jPanel.setBackground(Color.WHITE);
				jPanel.setLayout(null);
				JScrollPane jScrollPane=new JScrollPane(jPanel);
				jScrollPane.setBounds(50, 80, 300, 300);
				LoadBookshelf loadBookshelf=new LoadBookshelf();
				bookshelf=loadBookshelf.getListBookcategory();
				for(int i=0;i<bookshelf.size();i++)
				{
					jComboBox.addItem(bookshelf.get(i));
				}
				jDialog.add(cancel);
				jDialog.add(confirm);
				jDialog.add(jScrollPane);
				jDialog.add(delbookshelf);
				jDialog.add(jComboBox);
				jComboBox.addItemListener(new ItemListener() {
					
					@Override
					public void itemStateChanged(ItemEvent e) 
					{
						// TODO Auto-generated method stub
						if(e.getStateChange() == ItemEvent.SELECTED)
						{
							lJCheckBoxs.clear();
							jPanel.removeAll();
							String bookshelf=jComboBox.getSelectedItem().toString();
							LoadCategory loadCategory=new LoadCategory(bookshelf);
							for(int i=0;i<loadCategory.getBookshelfJTextArea().size();i++)
							{
								JCheckBox jCheckBox=new JCheckBox();
								jCheckBox.setText(loadCategory.getBookshelfJTextArea().get(i).getText());
								jCheckBox.setBounds(0, 30*i, 300, 20);
								jCheckBox.setBackground(Color.WHITE);
								lJCheckBoxs.add(jCheckBox);
								jPanel.add(jCheckBox);
							}
							jPanel.setBounds(0, 0, 300, lJCheckBoxs.size()*30);
							jPanel.setPreferredSize(new Dimension(280, lJCheckBoxs.size()*30));
							jPanel.repaint();
						}
					}
				});
				delbookshelf.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						int optionType = JOptionPane.YES_NO_OPTION; // 
						int messageType = JOptionPane.WARNING_MESSAGE; //
						int result = JOptionPane.showConfirmDialog(null,
								"删除选中书架将清空书架内书籍，是否确认删除?", "消息", optionType, messageType);
						if (result == JOptionPane.YES_OPTION) 
						{
							String bookshelf=jComboBox.getSelectedItem().toString();
							String str="";
							if(bookshelf.equals("默认书架"))
								JOptionPane.showMessageDialog(null, "无法删除默认书架");
							else 
							{
								File root=new File("src/info/bookshelf/");
								File[] shelves=root.listFiles();
								for(File shelf:shelves)
								{
									if(shelf.getName().equals(bookshelf))
									{
										File[] fs=shelf.listFiles();
										for(File f:fs)
										{
											f.delete();
										}
										if (shelf.delete()) 
										{
											JOptionPane.showMessageDialog(null,
													"删除成功");
											for(int i=0;i<jComboBox.getItemCount();i++)
											{
												if(jComboBox.getItemAt(i).toString().equals(bookshelf))
												{
													jComboBox.removeItemAt(i);
													jComboBox.revalidate();
												}
											}
											jPanel.removeAll();
											jPanel.repaint();
											for(int i=0;i<frame.MainFrame.getBookshelf().getChildCount();i++) //找到删除的节点，从MainFrame删除
											{
												if(jComboBox.getSelectedItem().toString().equals(((IconNode)frame.MainFrame.getBookshelf().getChildAt(i)).getText()))
												{
													frame.MainFrame.getBookshelf().remove(i);
													frame.MainFrame.getTreeModel().reload(frame.MainFrame.getBookshelf());
													frame.MainFrame.getjPanel_book().removeAll();
												}
											}
											break;
										}
										else
										{
											JOptionPane.showMessageDialog(null, "无法删除");
										}
									}

								}
							}
						}
						
					}
				});
				confirm.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						int optionType = JOptionPane.YES_NO_OPTION; // 
						int messageType = JOptionPane.WARNING_MESSAGE; //
						int result = JOptionPane.showConfirmDialog(null,
								"是否确认从书架中删除选中书籍?", "消息", optionType, messageType);
						if (result == JOptionPane.YES_OPTION) 
						{
						for(int i=0;i<lJCheckBoxs.size();i++)
						{
							if(lJCheckBoxs.get(i).isSelected())
							{
							File root=new File("src/info/bookshelf/");
							File[] shelves=root.listFiles();
							for(File shelf:shelves)
							{
								if(shelf.getName().equals(jComboBox.getSelectedItem().toString()))
								{
									File[] fs=shelf.listFiles();
									for(File f:fs)
									{
										if(f.getName().equals(lJCheckBoxs.get(i).getText()))
										{
											if(f.delete())
											{
												JOptionPane.showMessageDialog(null,
														"删除《"+lJCheckBoxs.get(i).getText()+"》成功");
												//jPanel.getComponents()
												break;
											}
											else
												JOptionPane.showMessageDialog(null, "无法删除");
										}
									}
								}
							}
							}
						}
						//从jpanel中删除选中书籍
						jPanel.revalidate();
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
