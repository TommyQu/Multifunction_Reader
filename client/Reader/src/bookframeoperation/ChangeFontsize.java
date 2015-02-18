package bookframeoperation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ChangeFontsize {

	public ChangeFontsize(JMenuItem menu_settting_fontsize) {
		menu_settting_fontsize.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				final JDialog jDialog=new JDialog();
				jDialog.setTitle("设置字体大小");
				jDialog.setLayout(null);
				jDialog.setSize(300, 220);
				JLabel ojJLabel=new JLabel("原字体大小     "+frame.BookFrame.getFontSize());
				ojJLabel.setBounds(50, 30, 200, 20);
				JLabel jLabel=new JLabel("新字体大小");
				jLabel.setBounds(50, 70, 80, 20);
				JLabel jLabel2=new JLabel("(13~19)");
				jLabel2.setBounds(70, 90, 80, 20);
				final JTextArea jTextArea=new JTextArea();
				jTextArea.setBounds(130, 70, 100, 20);
				jTextArea.setBorder(BorderFactory.createMatteBorder (1,1,1,1, Color.gray));
				JButton confirm=new JButton("确认");
				confirm.setBounds(50, 120, 80, 20);
				JButton cancel=new JButton("取消");
				cancel.setBounds(150, 120, 80, 20);
				jDialog.add(jTextArea);
				jDialog.add(jLabel);
				jDialog.add(jLabel2);
				jDialog.add(ojJLabel);
				jDialog.add(confirm);
				jDialog.add(cancel);
				jDialog.setVisible(true);
				jDialog.setAlwaysOnTop(true);
				confirm.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						try
						{
							int newsize=Integer.parseInt(jTextArea.getText());
							if(newsize>=13&&newsize<=19)
							{
								frame.BookFrame.setFontSize(newsize);
								for(int j=0;j<frame.BookFrame.getBook_area().size();j++)
								{
									frame.BookFrame.getBook_area().get(j).setFont(new Font(frame.BookFrame.getFontstyle(),
											frame.BookFrame.getFontthick(), 
											frame.BookFrame.getFontSize()));
									frame.BookFrame.getjPanel().repaint();
								}
								jDialog.dispose();
							}
							else
							{
								JOptionPane.showMessageDialog(jDialog, "请输入13-19之间的整数");
							}
						}
						catch(Exception e1)
						{
							JOptionPane.showMessageDialog(jDialog, "请输入13-19之间的整数");
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
		});
			// TODO Auto-generated method stub
	}

}
