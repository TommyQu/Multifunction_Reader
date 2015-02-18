package JPopupMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

public class StoreJPopupMenu extends JPopupMenu{
	private JMenuItem delFromShelf=new JMenuItem("从我的收藏中删除");
	public StoreJPopupMenu(final String bookName)
	{
		this.add(delFromShelf);
		delFromShelf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				hid();
				int optionType = JOptionPane.YES_NO_OPTION; // ��ť����
				int messageType = JOptionPane.WARNING_MESSAGE; // ͼ������
				int result = JOptionPane.showConfirmDialog(null,
						"是否确认从我的收藏删除", "消息ʾ", optionType, messageType);
				if (result == JOptionPane.YES_OPTION) 
				{
					File f=new File("src/info/store/"+bookName);
					if(f.delete())
					{
						JOptionPane.showMessageDialog(null, "从我的收藏删除成功");
						for(int i=0;i<frame.MainFrame.getStoreJTextArea().size();i++)
						{
							if(frame.MainFrame.getStoreJTextArea().get(i).getText().equals(bookName))
							{
								frame.MainFrame.getjPanel_book().remove(frame.MainFrame.getStoreJTextArea().get(i));
								frame.MainFrame.getjPanel_book().remove(frame.MainFrame.getStoreJLabel().get(i));
								frame.MainFrame.getjPanel_book().repaint();
								break;
							}
						}
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
