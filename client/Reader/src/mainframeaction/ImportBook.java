package mainframeaction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

import bookframeoperation.BMAddToBookshelf;

public class ImportBook {
	JFileChooser fileChooser = new JFileChooser();
	File chooseFile;
	public ImportBook(JMenuItem import_book) {
		// TODO Auto-generated constructor stub
		import_book.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				fileChooser.setCurrentDirectory(new File("F:\\"));
				fileChooser.setAcceptAllFileFilterUsed(false);
				fileChooser
						.addChoosableFileFilter(new javax.swing.filechooser.FileFilter() {
							@Override
							public boolean accept(File file) {
								if (file.getName().endsWith(".txt")
										|| file.getName().endsWith(
												".pdf")
										|| file.getName().endsWith(
												".epub")
										|| file.getName().endsWith(
												".mobi")||file.isDirectory()) {
									return true;
								} else
									return false;
							}

							@Override
							public String getDescription() {
								// TODO Auto-generated method stub
								return "请选择txt.pdf.epub.mobi格式的文件";
							}
						});
				fileChooser.showOpenDialog(fileChooser);
				chooseFile = fileChooser.getSelectedFile();
				try {
					BMAddToBookshelf bmaddToBookshelf=new BMAddToBookshelf(chooseFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

}
