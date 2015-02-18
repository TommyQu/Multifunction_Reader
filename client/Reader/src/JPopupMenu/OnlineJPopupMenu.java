package JPopupMenu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

import mainframeaction.bookshelfaction.DelBookshelf;
import mainframeaction.onlineshelfaction.AddOnlineShelf;
import mainframeaction.onlineshelfaction.DelOnlineShelf;
import mainframeaction.onlineshelfaction.EditOnlineShelf;

public class OnlineJPopupMenu extends JPopupMenu{
	private JMenuItem addOnlineShelf = new JMenuItem("添加在线书架");
	private JMenuItem editOnlineShelf = new JMenuItem("编辑在线书架");
	private JMenuItem delOnlineShelf = new JMenuItem("删除在线书架");
	private String nodeText;
	public OnlineJPopupMenu(String nodeText)
	{
		this.nodeText=nodeText;
		addOnlineShelfAction();
		editOnlineShelfAction();
		delOnlineShelfAction();
		if(nodeText.equals("在线书架"))
			this.add(addOnlineShelf);
		else
		{
			this.add(addOnlineShelf);
			this.add(editOnlineShelf);
			this.add(delOnlineShelf);
		}
	}
	public void hid()
	{
		this.setVisible(false);
	}
	public void addOnlineShelfAction()
	{
		addOnlineShelf.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				hid();
				final JDialog jDialog=new JDialog();
				jDialog.setTitle("添加在线书架");
				jDialog.setLayout(null);
//				jDialog.setLocationRelativeTo(null);
				jDialog.setSize(300, 200);
				JLabel jLabel=new JLabel("书架名");
				jLabel.setBounds(50, 50, 80, 20);
				final JTextArea jTextArea=new JTextArea();
				jTextArea.setBounds(100, 50, 100, 20);
				jTextArea.setBorder(BorderFactory.createMatteBorder (1,1,1,1, Color.gray));
				JButton confirm=new JButton("确认");
				confirm.setBounds(50, 100, 80, 20);
				JButton cancel=new JButton("取消");
				cancel.setBounds(150, 100, 80, 20);
				jDialog.add(jTextArea);
				jDialog.add(jLabel);
				jDialog.add(confirm);
				jDialog.add(cancel);
				jDialog.setVisible(true);
				jDialog.setAlwaysOnTop(true);
				confirm.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						jDialog.dispose();
						AddOnlineShelf addOnlineShelf=new AddOnlineShelf(jTextArea.getText());
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
	public void editOnlineShelfAction()
	{
		editOnlineShelf.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				hid();
				final JDialog jDialog=new JDialog();
				jDialog.setTitle("修改选中书架");
				jDialog.setLayout(null);
				jDialog.setSize(300, 220);
				JLabel ojJLabel=new JLabel("原书架名      "+nodeText);
				ojJLabel.setBounds(50, 30, 200, 20);
				JLabel jLabel=new JLabel("新书架名");
				jLabel.setBounds(50, 70, 80, 20);
				final JTextArea jTextArea=new JTextArea();
				jTextArea.setBounds(120, 70, 100, 20);
				jTextArea.setBorder(BorderFactory.createMatteBorder (1,1,1,1, Color.gray));
				JButton confirm=new JButton("确认");
				confirm.setBounds(50, 120, 80, 20);
				JButton cancel=new JButton("取消");
				cancel.setBounds(150, 120, 80, 20);
				jDialog.add(jTextArea);
				jDialog.add(jLabel);
				jDialog.add(ojJLabel);
				jDialog.add(confirm);
				jDialog.add(cancel);
				jDialog.setVisible(true);
				jDialog.setAlwaysOnTop(true);
				confirm.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						jDialog.dispose();
						EditOnlineShelf editOnlineShelf=new EditOnlineShelf(nodeText,jTextArea.getText());
					}
				});
				cancel.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						jDialog.dispose();
					}
				});
			}
		});
	}
	public void delOnlineShelfAction() {
		// TODO Auto-generated method stub
		delOnlineShelf.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				hid();
				DelOnlineShelf del = null;
				int optionType = JOptionPane.YES_NO_OPTION; // ��ť����
				int messageType = JOptionPane.WARNING_MESSAGE; // ͼ������
				int result = JOptionPane.showConfirmDialog(null,"删除选中书架将清空书架内书籍，是否确认删除?", "消息", optionType, messageType);
				if(result==JOptionPane.YES_OPTION)
				{
					del=new DelOnlineShelf(nodeText);
				}
			}
		});
	}
}
