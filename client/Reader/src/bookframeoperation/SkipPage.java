package bookframeoperation;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class SkipPage 
{
	public SkipPage()
	{
		final JDialog jDialog=new JDialog();
		jDialog.setTitle("跳转页面");
		jDialog.setLayout(null);
//		jDialog.setLocationRelativeTo(null);
		jDialog.setSize(300, 200);
		JLabel jLabel=new JLabel("目标页数：");
		jLabel.setBounds(50, 50, 80, 20);
		final JTextArea jTextArea=new JTextArea();
		jTextArea.setBounds(120, 50, 100, 20);
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
		jTextArea.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					try
					{
						if (Integer.parseInt(jTextArea.getText()) >= 1
								&& Integer.parseInt(jTextArea.getText()) <= frame.BookFrame
										.getPageCount()) 
						{
							if ((Integer.parseInt(jTextArea.getText())) % 2 == 1) 
							{
								int newLeft = Integer.parseInt(jTextArea.getText());
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
								int newRight = Integer.parseInt(jTextArea.getText());
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
							JOptionPane.showMessageDialog(jDialog, "目标页数不在范围内");
					} 
					catch(Exception e1)
					{
						JOptionPane.showMessageDialog(jDialog, "目标页数必须为数字");
					}
				}
			}
		});
		confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try
				{
					if (Integer.parseInt(jTextArea.getText()) >= 1
							&& Integer.parseInt(jTextArea.getText()) <= frame.BookFrame
									.getPageCount()) 
					{
						if ((Integer.parseInt(jTextArea.getText())) % 2 == 1) 
						{
							int newLeft = Integer.parseInt(jTextArea.getText());
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
							int newRight = Integer.parseInt(jTextArea.getText());
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
						JOptionPane.showMessageDialog(jDialog, "目标页数不在范围内");
				} 
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(jDialog, "目标页数必须为数字");
				}
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

}
