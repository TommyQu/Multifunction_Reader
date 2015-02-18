package mainframeaction.storeaction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import domain.IconNode;

public class ClearStore {

	public ClearStore(JMenuItem operation_clearstore) {
		operation_clearstore.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				// TODO Auto-generated method stub
				int optionType = JOptionPane.YES_NO_OPTION; // 
				int messageType = JOptionPane.WARNING_MESSAGE; //
				int result = JOptionPane.showConfirmDialog(null,
						"是否确认清空我的收藏?", "消息", optionType, messageType);
				if (result == JOptionPane.YES_OPTION) 
				{
					File root=new File("src/info/store");
					File[] files=root.listFiles();
					if(files!=null)
					{
						try
						{
							for(File f:files)
							{
								f.delete();
							}
							frame.MainFrame.getjPanel_book().removeAll();
							frame.MainFrame.getjPanel_book().repaint();
							JOptionPane.showMessageDialog(null, "清空我的收藏成功");
						}
						catch(Exception e)
						{
							JOptionPane.showMessageDialog(null, "清空我的收藏失败");
						}
						
					}
				}
			}
				
		});
	}

}
