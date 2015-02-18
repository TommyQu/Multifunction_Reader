package bookframeoperation;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

public class ChangeFontstyle {
	public ChangeFontstyle(JMenuItem menu_settting_fontstyle) {
		// TODO Auto-generated constructor stub
		menu_settting_fontstyle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Font[] fonts=GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts(); 
				final List<String> list_font=new ArrayList<String>();
				for(int i=0;i<fonts.length;i++)
				{
					list_font.add(fonts[i].getFamily());
				}
				final JDialog jDialog=new JDialog();
				JLabel label_bookshelf=new JLabel();
				JPanel jPanel=new JPanel();
				final ButtonGroup buttonGroup=new ButtonGroup();
				final List<JRadioButton> listRatioButton=new ArrayList<JRadioButton>();
				for(int i=0;i<list_font.size();i++)
				{
					JRadioButton jRadioButton=new JRadioButton();
					jRadioButton.setText(list_font.get(i));
					jRadioButton.setVisible(true);
					jRadioButton.setBounds(0,30*i,130,20);
					listRatioButton.add(jRadioButton);
					buttonGroup.add(jRadioButton);
					jPanel.add(jRadioButton);
				}
//				System.out.println(jPanel.getComponentCount());
				JButton confirm=new JButton("确认");
				JButton cancel=new JButton("取消");
				jDialog.setTitle("设置字体样式");
				jDialog.setLayout(null);
				jDialog.setSize(350,450);
				label_bookshelf.setBounds(30, 20, 100, 30);
				label_bookshelf.setText("请选择字体样式");
//				jCheckBox.setBounds(0, 0, 120, 150);
//				jCheckBox.setOpaque(false);;
				jPanel.setBounds(0, 0, 250, list_font.size()*30);
				jPanel.setPreferredSize(new Dimension(250, list_font.size()*30));
				jPanel.setLayout(null);
				jPanel.setOpaque(false);
				jPanel.setVisible(true);
				JScrollPane jScrollPane=new JScrollPane(jPanel);
//				jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//				jScrollPane.setViewportView(jPanel);
				jScrollPane.setBounds(30, 60, 270, 270);
				jScrollPane.setOpaque(false);
//				jScrollPane.getViewport().setOpaque(false); 
				jScrollPane.setVisible(true);
//				jScrollPane.setLayout(null);
				confirm.setBounds(50, 350, 80, 20);
				cancel.setBounds(200, 350, 80, 20);
				jDialog.add(label_bookshelf);
				jDialog.add(jScrollPane);
				jDialog.add(confirm);
				jDialog.add(cancel);
				jDialog.setVisible(true);
				jDialog.setAlwaysOnTop(true);
				confirm.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						for(int i=0;i<listRatioButton.size();i++)
						{
							if(listRatioButton.get(i).isSelected())
							{
								frame.BookFrame.setFontstyle(listRatioButton.get(i).getText());
								for(int j=0;j<frame.BookFrame.getBook_area().size();j++)
								{
									frame.BookFrame.getBook_area().get(j).setFont(new Font(frame.BookFrame.getFontstyle(),
											frame.BookFrame.getFontthick(), 
											frame.BookFrame.getFontSize()));
									frame.BookFrame.getjPanel().repaint();
								}
								break;
							}
						}
//						list_font.clear();
						jDialog.dispose();
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
}
