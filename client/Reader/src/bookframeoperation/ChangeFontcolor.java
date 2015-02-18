package bookframeoperation;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ChangeFontcolor {

	public ChangeFontcolor(JMenuItem menu_settting_fontcolor) {
		// TODO Auto-generated constructor stub
		menu_settting_fontcolor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JDialog jDialog=new JDialog();
				jDialog.setTitle("设置字体颜色");
				jDialog.setBounds(0, 0, 600, 500);
				final JColorChooser jColorChooser=new JColorChooser(Color.BLACK);
				jColorChooser.show();
				jDialog.add(jColorChooser);
				jDialog.setVisible(true);
				jDialog.setAlwaysOnTop(true);
				ColorSelectionModel colorSelectionModel=jColorChooser.getSelectionModel();
				colorSelectionModel.addChangeListener(new ChangeListener() {
					
					@Override
					public void stateChanged(ChangeEvent arg0) {
						// TODO Auto-generated method stub
						Color newcolor=jColorChooser.getColor();
						frame.BookFrame.setFontcolor(newcolor);
						for(int j=0;j<frame.BookFrame.getBook_area().size();j++)
						{
							frame.BookFrame.getBook_area().get(j).setForeground(frame.BookFrame.getFontcolor());
							frame.BookFrame.getjPanel().repaint();
						}
					}
				});

			}
		});
	}

}
