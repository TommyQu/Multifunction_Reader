package frame;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import sun.java2d.pipe.hw.ExtendedBufferCapabilities;
import bookframeoperation.AddBookmark;
import bookframeoperation.BMAddToBookshelf;
import bookframeoperation.ChangeFontcolor;
import bookframeoperation.ChangeFontsize;
import bookframeoperation.ChangeFontstyle;
import bookframeoperation.ChangeFontthick;
import bookframeoperation.ChkBookmark;
import bookframeoperation.MagBookmark;
import bookframeoperation.SkipPage;

import com.sun.org.apache.bcel.internal.generic.NEW;
public class BookFrame {
	private JFrame book_frame;
	private ImageIcon imageIcon=new ImageIcon("src/img/OpenBookBackground.jpg");
	private JLabel jLabel=new JLabel(imageIcon);
	private static JPanel jPanel;
	private JPanel jToolBar=new JPanel();
//	private static JPanel pagejPanel;
//	private static JPanel jPanel_content=new JPanel();
	private JMenuBar menuBar=new JMenuBar();
	private JMenu menu_book_file=new JMenu("文件");
	private JMenuItem  menu_book_file_open=new JMenuItem("打开");
	private JMenuItem  menu_book_file_close=new JMenuItem("关闭");
	private JMenu menu_book_operation=new JMenu("操作");
	private JMenuItem  menu_book_operation_skipPage=new JMenuItem("跳转页面");
	private JMenuItem  menu_book_operation_addToBookshelf=new JMenuItem("添加至书库");
	private JMenuItem  menu_book_operation_addToStore=new JMenuItem("添加至我的收藏");
	private JMenu menu_book_bookmark=new JMenu("书签");
	private JMenuItem  menu_book_bookmark_addBookmark=new JMenuItem("添加书签");
	private JMenuItem  menu_book_bookmark_chkBookmark=new JMenuItem("查看书签");
	private JMenuItem  menu_book_bookmark_magBookmark=new JMenuItem("管理书签");
	private JMenu menu_settting=new JMenu("设置");
	private JMenuItem  menu_settting_fontstyle=new JMenuItem("设置字体风格");
	private JMenuItem  menu_settting_fontsize=new JMenuItem("设置字体大小");
	private JMenuItem  menu_settting_fontthick=new JMenuItem("设置字体粗细");
	private JMenuItem  menu_settting_fontcolor=new JMenuItem("设置字体颜色");
	private static ImageIcon bigger_icon= new ImageIcon("src/img/bigger.png");
	private static ImageIcon smaller_icon= new ImageIcon("src/img/smaller.png");
	private static JLabel bigger=new JLabel(bigger_icon);
	private static JLabel smaller=new JLabel(smaller_icon);
//	private JMenu menu_book_background=new JMenu("背景");
	private static List<JTextArea> book_area=new ArrayList<JTextArea>();
	private List<String> book_content=new ArrayList<String>();
	private static int pageCount=0;
	private static JTextArea leftPageNum=new JTextArea();
	private static JTextArea rightPageNum=new JTextArea();
	private static int left=1;
	private static int right=2;
	private static String fontstyle="宋体";
	private static int fontthick=Font.PLAIN;
	private static int fontSize=16;
	private static Color fontcolor=Color.BLACK;
	private int isExist=0;
//	private static double X;
//	private static double Y;
	public BookFrame(final File chooseFile, List<String> book_content,final int pageCount) throws IOException {
		this.book_content.clear();
		this.book_frame=new JFrame(chooseFile.getName());
		this.book_content=book_content;
		this.pageCount=pageCount;
		this.leftPageNum.setBounds(400, 620, 20, 20);
		this.rightPageNum.setBounds(1000, 620, 20, 20);
		this.leftPageNum.setOpaque(false);
		this.rightPageNum.setOpaque(false);
		this.leftPageNum.setText(Integer.toString(1));
		this.rightPageNum.setText(Integer.toString(2));
		this.leftPageNum.setFocusable(false);
		this.rightPageNum.setFocusable(false);
		this.jToolBar.setBounds(350,560,700,80);
		this.jToolBar.setBackground(new Color(220,220,220));
		this.jToolBar.setOpaque(false);
		this.jToolBar.setLayout(null);
		this.bigger.setBounds(620, 620, 25, 27);
		this.bigger.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.smaller.setBounds(720, 620, 25, 27);
		this.smaller.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		this.jToolBar.setOpaque(false);
		final JButton previous=new JButton("上一页");
		previous.setBounds(300, 20, 100, 30);
		previous.setVisible(false);
		JButton next=new JButton("下一页");
		this.jToolBar.add(previous);
		book_frame.setBounds(0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());
		jLabel.setBounds(0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());
//		pagejPanel=(JPanel)book_frame.getGlassPane();
//		pagejPanel.setLayout(null);
//		pagejPanel.setSize(book_frame.getWidth(), book_frame.getHeight());
//		pagejPanel.setOpaque(false);
		jPanel=(JPanel)book_frame.getContentPane();
		jPanel.setLayout(null);
		jPanel.setSize(book_frame.getWidth(), book_frame.getHeight());
		jPanel.setOpaque(false);
		jPanel.removeAll();
		jPanel.add(bigger);
		jPanel.add(smaller);
//		jPanel_content.setLayout(null);
//		jPanel_content.setSize(book_frame.getWidth(), book_frame.getHeight());
//		jPanel_content.setOpaque(false);
		for(int i=1;i<=book_content.size();i++)
		{
			JTextArea pageArea=new JTextArea();
			pageArea.setLineWrap(true);
			pageArea.setFocusable(false);
			if(i%2==1)
				pageArea.setBounds(270, 80, 400, 525);
			else
				pageArea.setBounds(750, 80, 400, 525);
			pageArea.setText(book_content.get(i-1));
			pageArea.setOpaque(false);
			pageArea.setFont(new Font(fontstyle,fontthick, fontSize));
			pageArea.setForeground(fontcolor);
			book_area.add(pageArea);
		}
		jPanel.add(leftPageNum);
		jPanel.add(rightPageNum);
		if((book_area.size())>=2)
		{
			jPanel.add(book_area.get(Integer.parseInt(leftPageNum.getText())-1));
			jPanel.add(book_area.get(Integer.parseInt(rightPageNum.getText())-1));
		}
		else if((book_area.size())==1)
		{
			jPanel.add(book_area.get(0));
			System.out.println(pageCount);
		}
		//jPanel.add(jToolBar);
		menu_book_file.add(menu_book_file_open);
		menu_book_file.addSeparator();
		menu_book_file.add(menu_book_file_close);
		menu_book_operation.add(menu_book_operation_skipPage);
		menu_book_operation.add(menu_book_operation_addToBookshelf);
		menu_book_operation.add(menu_book_operation_addToStore);
		menu_book_bookmark.add(menu_book_bookmark_addBookmark);
		menu_book_bookmark.add(menu_book_bookmark_chkBookmark);
		menu_book_bookmark.add(menu_book_bookmark_magBookmark);
		menu_settting.add(menu_settting_fontstyle);
		menu_settting.add(menu_settting_fontsize);
		menu_settting.add(menu_settting_fontthick);
		menu_settting.add(menu_settting_fontcolor);
		menuBar.add(menu_book_file);
		menuBar.add(menu_book_operation);
		menuBar.add(menu_book_bookmark);
		menuBar.add(menu_settting);
		ChangeFontstyle changeFontstyle=new ChangeFontstyle(menu_settting_fontstyle);
		ChangeFontsize changeFontsize=new ChangeFontsize(menu_settting_fontsize);
		ChangeFontthick changeFontthick=new ChangeFontthick(menu_settting_fontthick);
		ChangeFontcolor changeFontcolor=new ChangeFontcolor(menu_settting_fontcolor);
		book_frame.setJMenuBar(menuBar);
		book_frame.getLayeredPane().setLayout(null);
		book_frame.getLayeredPane().add(jLabel,new Integer(Integer.MIN_VALUE));
		book_frame.setVisible(true);
		jPanel.requestFocus();
		jPanel.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_LEFT)
				{
					left=Integer.parseInt(leftPageNum.getText());
					right=Integer.parseInt(rightPageNum.getText());
					if(left==1)
						JOptionPane.showMessageDialog(book_frame,"已翻到首页");
					else
					{
						jPanel.removeAll();
//						jPanel.remove(book_area.get(left-1));
//						jPanel.remove(book_area.get(right-1));
						left-=2;
						right-=2;
						leftPageNum.setText(Integer.toString(left));
						rightPageNum.setText(Integer.toString(right));
						jPanel.add(book_area.get(left-1));
						jPanel.add(book_area.get(right-1));
						jPanel.add(leftPageNum);
						jPanel.add(rightPageNum);
						jPanel.add(bigger);
						jPanel.add(smaller);
					}
				}
				else if(e.getKeyCode()==KeyEvent.VK_RIGHT)
				{
					left=Integer.parseInt(leftPageNum.getText());
					right=Integer.parseInt(rightPageNum.getText());
					if(right==pageCount||pageCount<=2)
						JOptionPane.showMessageDialog(book_frame,"已翻到尾页");
					else
					{
						jPanel.removeAll();
						left += 2;
						right += 2;
						leftPageNum.setText(Integer.toString(left));
						rightPageNum.setText(Integer.toString(right));
						jPanel.add(book_area.get(left - 1));
						jPanel.add(book_area.get(right - 1));
						jPanel.add(leftPageNum);
						jPanel.add(rightPageNum);
						jPanel.add(bigger);
						jPanel.add(smaller);
					}
				}
				else if(e.getKeyCode()==KeyEvent.VK_ADD||e.getKeyCode()==KeyEvent.VK_EQUALS)
				{
					if(fontSize==19)
					{
						JOptionPane.showMessageDialog(null, "字体已到最大");
					}
					else 
					{
						fontSize++;
						for (int i = 0; i < book_area.size(); i++) 
						{
							book_area.get(i).setFont(
									new Font(fontstyle, Font.PLAIN, fontSize));
							jPanel.repaint();
						}
					}
				}
				else if(e.getKeyCode()==KeyEvent.VK_SUBTRACT||e.getKeyCode()==KeyEvent.VK_MINUS)
				{
					if(fontSize==13)
					{
						JOptionPane.showMessageDialog(null, "字体已到最小");
					}
					else 
					{
						fontSize--;
						for (int i = 0; i < book_area.size(); i++) 
						{
							book_area.get(i).setFont(
									new Font(fontstyle, Font.PLAIN, fontSize));
							jPanel.repaint();
						}
					}	
				}
				jPanel.repaint();
				}
		});
		jToolBar.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent e)
			{
				jToolBar.setOpaque(true);
				previous.setVisible(true);
				jPanel.repaint();
			}
			public void mouseExited(MouseEvent e)
			{
				jToolBar.setOpaque(false);;
				previous.setVisible(false);
				jPanel.repaint();
			}
		});
		menu_book_operation_skipPage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				SkipPage skipPage=new SkipPage();
			}
		});
		menu_book_operation_addToBookshelf.addActionListener(new ActionListener() { //添加至书库
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					BMAddToBookshelf bmAddToBookshelf=new BMAddToBookshelf(chooseFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		menu_book_bookmark_addBookmark.addActionListener(new ActionListener() {//添加书签
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					AddBookmark addBookmark=new AddBookmark(chooseFile,leftPageNum.getText());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		menu_book_bookmark_chkBookmark.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					ChkBookmark chkBookmark=new ChkBookmark(chooseFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		menu_book_bookmark_magBookmark.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					MagBookmark magBookshelf=new MagBookmark(chooseFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		book_frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)
			{
				jPanel.removeAll();
				book_area.clear();
				left=1;
				right=2;
				fontstyle="宋体";
				fontSize=16;
				fontcolor=Color.BLACK;
				book_frame.dispose();
			}
		});
		menu_book_file_close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				jPanel.removeAll();
				book_area.clear();
				left=1;
				right=2;
				fontstyle="宋体";
				fontSize=16;
				fontcolor=Color.BLACK;
				book_frame.dispose();
			}
		});
		bigger.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e)
			{
				if(fontSize==19)
				{
					JOptionPane.showMessageDialog(null, "字体已到最大");
				}
				else 
				{
					fontSize++;
					for (int i = 0; i < book_area.size(); i++) 
					{
						book_area.get(i).setFont(
								new Font(fontstyle, Font.PLAIN, fontSize));
						jPanel.repaint();
					}
				}

					
			}
		});
		smaller.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e)
			{
				if(fontSize==13)
				{
					JOptionPane.showMessageDialog(null, "字体已到最小");
				}
				else 
				{
					fontSize--;
					for (int i = 0; i < book_area.size(); i++) 
					{
						book_area.get(i).setFont(
								new Font(fontstyle, Font.PLAIN, fontSize));
						jPanel.repaint();
					}
				}	
			}
		});
		menu_book_operation_addToStore.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				File f=new File(chooseFile.getPath());
				File root=new File("src/info/store");
				File[] files=root.listFiles();
				if(files!=null)
				{
					for(File ff:files)
					{
						if(ff.getName().equals(chooseFile.getName()))
						{
							JOptionPane.showMessageDialog(null, "同名书籍已存在于我的收藏中");
							isExist=1;
							break;
						}
					}
				}
				if(isExist==0)
				{
					try {
						FileInputStream inStream = new FileInputStream(
								f.getPath());
						FileOutputStream outStream = new FileOutputStream(
								"src/info/store/"+chooseFile.getName());
						FileChannel inChannel = inStream.getChannel();
						FileChannel outChannel = outStream.getChannel();
						ByteBuffer buffer = ByteBuffer.allocate(1024);
						while ((inChannel.read(buffer) != -1)) {
							buffer.flip();
							outChannel.write(buffer);
							buffer.clear();
						}
						inStream.close();
						outStream.close();
						inChannel.close();
						outChannel.close();
						JOptionPane.showMessageDialog(null, "《"
								+ chooseFile.getName() + "》导入到 "
								+ "我的收藏中成功");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
//		ProgressMonitorInputStream inputStream=new ProgressMonitorInputStream(book_frame, "进度", new FileInputStream(chooseFile.getPath()));
//		ProgressMonitor monitor=inputStream.getProgressMonitor();
//		int read_unit = 2000;// ����ÿ�ζ�ȡ���ֽ���
//		int all = inputStream.available();// �õ�Ŀ���ļ������ֽ���
//		int readed = 0;// ÿ��ʵ�ʶ�ȡ�����ֽ���
//		byte[] data = new byte[read_unit];// �ֽ����飬�����ȡ�����ֽ���
//		while (inputStream.available() > 0) 
//		{
//		try {
//			Thread.sleep(1);
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}// 
//		int in = inputStream.read(data);
//		readed += in;
//		// System.out.write(data);
//		float process = (float) readed / all * 100;// ����ٷֱ�
//		// System.out.println("archived " + process + " %");
//		monitor.setNote("archived " + process + " %");// ��ʾ�ڽ������
//		inputStream.close();
//		}
	}
	
	public static JPanel getjPanel() {
		return jPanel;
	}

	public static void setjPanel(JPanel jPanel) {
		BookFrame.jPanel = jPanel;
	}
	
	public static int getPageCount() {
		return pageCount;
	}

	public static void setPageCount(int pageCount) {
		BookFrame.pageCount = pageCount;
	}

	public static JTextArea getLeftPageNum() {
		return leftPageNum;
	}
	public static void setLeftPageNum(JTextArea leftPageNum) {
		BookFrame.leftPageNum = leftPageNum;
	}
	public static JTextArea getRightPageNum() {
		return rightPageNum;
	}
	public static void setRightPageNum(JTextArea rightPageNum) {
		BookFrame.rightPageNum = rightPageNum;
	}

	public static List<JTextArea> getBook_area() {
		return book_area;
	}

	public static void setBook_area(List<JTextArea> book_area) {
		BookFrame.book_area = book_area;
	}

	public static int getLeft() {
		return left;
	}

	public static void setLeft(int left) {
		BookFrame.left = left;
	}

	public static int getRight() {
		return right;
	}

	public static void setRight(int right) {
		BookFrame.right = right;
	}

	public static JLabel getBigger() {
		return bigger;
	}

	public static void setBigger(JLabel bigger) {
		BookFrame.bigger = bigger;
	}

	public static JLabel getSmaller() {
		return smaller;
	}

	public static void setSmaller(JLabel smaller) {
		BookFrame.smaller = smaller;
	}

	public static String getFontstyle() {
		return fontstyle;
	}

	public static void setFontstyle(String fontstyle) {
		BookFrame.fontstyle = fontstyle;
	}

	public static int getFontthick() {
		return fontthick;
	}

	public static void setFontthick(int fontthick) {
		BookFrame.fontthick = fontthick;
	}

	public static int getFontSize() {
		return fontSize;
	}

	public static void setFontSize(int fontSize) {
		BookFrame.fontSize = fontSize;
	}

	public static Color getFontcolor() {
		return fontcolor;
	}

	public static void setFontcolor(Color fontcolor) {
		BookFrame.fontcolor = fontcolor;
	}
	
}
