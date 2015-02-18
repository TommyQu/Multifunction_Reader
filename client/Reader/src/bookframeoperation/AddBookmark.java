package bookframeoperation;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class AddBookmark {
	private File file;
	private List<String> bookmarks=new ArrayList<String>();
	public AddBookmark(final File chooseFile, final String pageNum) throws IOException {
		// TODO Auto-generated constructor stub
		final JDialog jDialog=new JDialog();
		jDialog.setTitle("添加新的书签");
		jDialog.setLayout(null);
//		jDialog.setLocationRelativeTo(null);
		jDialog.setSize(300, 200);
		JLabel jLabel=new JLabel("书签名");
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
				String str;
				int isExist=0;
				file=new File("src/info/bookmark/"+chooseFile.getName()+".txt");
				try {
					if(file.exists()==false)
						file.createNewFile();
					bookmarks.add(jTextArea.getText()+",页数:"+pageNum);
					BufferedReader br = new BufferedReader(new FileReader(file
							.getPath()));
					while (br.ready()) {
						str = br.readLine();
						if(str.equals(jTextArea.getText()+",页数:"+pageNum))
						{
							System.out.println(str);
							isExist=1;  //如果找到同名书签，跳出循环
							break;
						}
						bookmarks.add(str);
					}
					br.close();
					if(isExist==0)
					{
						FileWriter fw = new FileWriter(new File(file.getPath()));
						fw.write("");
						fw.close();
						BufferedWriter bw = new BufferedWriter(new FileWriter(
								file.getPath(), true));
						bw.write("");
						for (int i = 0; i < bookmarks.size(); i++) {
							bw.write(bookmarks.get(i));
							bw.newLine();
						}
						bw.close();
						JOptionPane.showMessageDialog(null, "添加书签成功");
						jDialog.dispose();
					}
					else
						JOptionPane.showMessageDialog(jDialog, "同名书签已存在");
				} 
				catch (Exception e) {
					e.printStackTrace();
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
