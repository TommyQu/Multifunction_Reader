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
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.JTree;

import mainframeaction.bookshelfaction.AddBookshelf;
import mainframeaction.bookshelfaction.DelBookshelf;
import mainframeaction.bookshelfaction.EditBookshelf;

public class ShelfJPopupMenu extends JPopupMenu{
	private JMenuItem addBookshelf = new JMenuItem("添加新的书架");
	private JMenuItem editBookshelf = new JMenuItem("修改选中书架");
	private JMenuItem delBookshelf = new JMenuItem("删除选中书架");
	private String nodeText;
	public ShelfJPopupMenu(String nodeText, JPanel jPanel)
	{
		this.nodeText=nodeText;
		addBookshelfAction();
		editBookshelfAction();
		delBookshelfAction();
		if(this.nodeText.equals("本地书架"))
			this.add(addBookshelf);
		else
		{
			this.add(addBookshelf);
			this.add(editBookshelf);
			this.add(delBookshelf);
		}
		jPanel.repaint();
	}
	public void addBookshelfAction()  //������������鼮
	{
		addBookshelf.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				hid();
				final JDialog jDialog=new JDialog();
				jDialog.setTitle("添加新的书架");
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
				confirm.addActionListener(new ActionListener() { //������ܷ���
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						try {
							AddBookshelf addBookshelf=new AddBookshelf(jTextArea.getText());
							jDialog.dispose();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
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
				hid();
			}
		});
	}
	public void editBookshelfAction()
	{
		editBookshelf.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				hid();
				final JDialog jDialog=new JDialog();
				jDialog.setTitle("修改选中书库");
				jDialog.setLayout(null);
				jDialog.setSize(300, 220);
				JLabel ojJLabel=new JLabel("原书库名      "+nodeText);
				ojJLabel.setBounds(50, 30, 200, 20);
				JLabel jLabel=new JLabel("新书库名");
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
				confirm.addActionListener(new ActionListener() { //ȷ���޸�������
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						try {
							EditBookshelf editBookshelf=new EditBookshelf(nodeText,jTextArea.getText());
							jDialog.dispose();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
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
				hid();
			}
		});
	}
	public void delBookshelfAction()
	{
		delBookshelf.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				hid();
				DelBookshelf del = null;
				int optionType = JOptionPane.YES_NO_OPTION; // ��ť����
				int messageType = JOptionPane.WARNING_MESSAGE; // ͼ������
				int result = JOptionPane.showConfirmDialog(null,"删除选中书架将清空书架内书籍，是否确认删除?", "消息", optionType, messageType);
				if(result==JOptionPane.YES_OPTION)
				{
					try {
						del=new DelBookshelf(nodeText);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
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
