package mainframeaction.recentfileaction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class ClearRecentFile {
	public ClearRecentFile(JMenuItem operation_clearrecent) throws IOException
	{
		operation_clearrecent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int optionType = JOptionPane.YES_NO_OPTION; // 
				int messageType = JOptionPane.WARNING_MESSAGE; //
				int result = JOptionPane.showConfirmDialog(null,
						"是否确认清除最近阅读", "消息", optionType, messageType);
				if (result == JOptionPane.YES_OPTION) 
				{
					try 
					{
						FileWriter fw = new FileWriter(new File(
								"src/info/recent.txt"));
						fw.write("");
						fw.close();
						JOptionPane.showMessageDialog(null, "清除最近阅读成功");
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
					}
				}
			}
		});
	}
}
