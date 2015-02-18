package JPopupMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

public class ClearJPopupMenu extends JPopupMenu {
	private JMenuItem clearRecent = new JMenuItem("清除最近阅读");

	public ClearJPopupMenu() 
	{
		this.add(clearRecent);
		clearRecent.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				// TODO Auto-generated method stub
				hid();
				int optionType = JOptionPane.YES_NO_OPTION; // ��ť����
				int messageType = JOptionPane.WARNING_MESSAGE; // ͼ������
				int result = JOptionPane.showConfirmDialog(null,
						"是否确认清除最近阅读", "消息ʾ", optionType, messageType);
				if (result == JOptionPane.YES_OPTION) 
				{
					try 
					{
						FileWriter fw = new FileWriter(new File(
								"src/info/recent.txt"));
						fw.write("");
						fw.close();
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
					}
				}
				else
				{
					
				}
				hid();
			}
		});
	}

	public void hid() 
	{
		this.setVisible(false);
	}
}
