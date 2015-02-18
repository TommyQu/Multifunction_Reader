package frame;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.apache.commons.httpclient.HttpClient;

import bookframeoperation.MagBookshelf;
import JPopupMenu.ClearJPopupMenu;
import JPopupMenu.OnlineJPopupMenu;
import JPopupMenu.ShelfJPopupMenu;
import mainframeaction.GetWeather;
import mainframeaction.ImportBook;
import mainframeaction.LoginDialog;
import mainframeaction.OpenBook;
import mainframeaction.bookshelfaction.ClearBookshelf;
import mainframeaction.bookshelfaction.LoadBookshelf;
import mainframeaction.bookshelfaction.LoadCategory;
import mainframeaction.onlineshelfaction.ClearOnlineShelf;
import mainframeaction.onlineshelfaction.LoadOnlineCategory;
import mainframeaction.onlineshelfaction.LoadOnlineShelf;
import mainframeaction.onlineshelfaction.MagOnlineShelf;
import mainframeaction.onlineshelfaction.UploadBook;
import mainframeaction.recentfileaction.ClearRecentFile;
import mainframeaction.recentfileaction.LoadRecentFile;
import mainframeaction.storeaction.ClearStore;
import mainframeaction.storeaction.LoadStore;
import mainframeaction.storeaction.MagStore;

import java.applet.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import compile.*;
import domain.*;

public class MainFrame {
	private static JFrame frame = new JFrame("多功能阅读器");
	private static JMenuBar menuBar = new JMenuBar();
	private static JMenu menu_file = new JMenu("文件");
	private static JMenuItem file_open = new JMenuItem("打开");
	private static JMenuItem file_exit = new JMenuItem("关闭");
	private static JMenu menu_import = new JMenu("导入");
	private static JMenuItem import_book = new JMenuItem("导入书籍");
	private static JMenu menu_operation = new JMenu("操作");
	private static JMenuItem operation_magbookshelf = new JMenuItem("管理本地书架");
	private static JMenuItem operation_clearrecent = new JMenuItem("清除最近阅读");
	private static JMenuItem operation_magstore = new JMenuItem("管理我的收藏");
	private static JMenuItem operation_clearstore = new JMenuItem("清空我的收藏");
	private static JMenuItem operation_clearbookshelf=new JMenuItem("清空本地书架");
	private static JMenu menu_online=new JMenu("在线书架");
	private static JMenuItem online_UploadBook = new JMenuItem("上传到在线书架");
	private static JMenuItem online_MagOnlineShlef = new JMenuItem("管理在线书架");
	private static JMenuItem online_CleOnlineShlef = new JMenuItem("清空在线书架");
	private static JMenu menu_user=new JMenu("用户");
	private static JMenuItem user_login = new JMenuItem("登录");
	private static JMenuItem user_logout = new JMenuItem("退出");
	private static ImageIcon imageIcon = new ImageIcon("src/img/background.jpg");
	private static ImageIcon imageIcon2 = new ImageIcon("src/img/search.png");
	private static ImageIcon iconleft=new ImageIcon("src/img/left.png");
	private static ImageIcon iconright=new ImageIcon("src/img/right.png");
	private static JPanel jPanel;
	private static JPanel jPanel_book=new JPanel();
	private static JLabel jLabel = new JLabel(imageIcon);
	private static IconNode bookshelf = new IconNode(new ImageIcon(
			"src/img/nodefont.jpg"), "本地书架");
	private static IconNode recent = new IconNode(new ImageIcon(
			"src/img/nodefont.jpg"), "最近阅读");
	private static IconNode store = new IconNode(new ImageIcon(
			"src/img/nodefont.jpg"), "我的收藏");
	private static IconNode online = new IconNode(new ImageIcon(
			"src/img/nodefont.jpg"), "在线书架");
	private static IconNode blank = new IconNode(new ImageIcon(""), "");
	private static IconNode root = new IconNode(null, null);
	private static JTree jTree=null;
	private static DefaultTreeModel treeModel;
	private static JScrollPane jScrollPane=null;
	private static List<String> List_BookShelf = new ArrayList<String>();
	private static List<String> List_OnlineShelf = new ArrayList<String>();
	private static List<JTextArea> RecentJTextArea=new ArrayList<JTextArea>();
	private static List<JLabel> RecentJLabel = new ArrayList<JLabel>();
	private static List<JTextArea> BookshelfJTextArea=new ArrayList<JTextArea>();
	private static List<JLabel> BookshelfJLabel = new ArrayList<JLabel>();
	private static List<JTextArea> OnlineshelfJTextArea=new ArrayList<JTextArea>();
	private static List<JLabel> OnlineshelfJLabel = new ArrayList<JLabel>();
	private static List<JTextArea> StoreJTextArea=new ArrayList<JTextArea>();
	private static List<JLabel> StoreJLabel = new ArrayList<JLabel>();
	private static int rightClicked=0;
	private static JPopupMenu jPopupMenu=null;
	private static JLabel login=new JLabel();
	private static JLabel loginName=new JLabel();
	private static JLabel logout=new JLabel();
	private static JLabel jlabelleft=new JLabel(iconleft);
	private static JLabel jlabelright=new JLabel(iconright);
	private static HttpClient httpClient=new HttpClient();
	private static JTextArea search_area=new JTextArea();
	private static JLabel search_label=new JLabel(imageIcon2);
	private static int jpanelbookMax=0;
	private static int jpanelbookNum=0;
	private static JTextArea pageNumJTextArea=new JTextArea("第1页");
	public static void main(String[] args) throws IOException {
		try {
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		GetWeather getWeather=new GetWeather();
		jPanel = (JPanel)frame.getContentPane();
		jPanel.setLayout(null);
		jPanel.setOpaque(false);
		jPanel_book.setLayout(null);
		jPanel_book.setOpaque(false);
		user_logout.setEnabled(false);
		file_open.addActionListener(new ActionListener() // 
				{
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						try {
							OpenBook openBook=new OpenBook();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
		// content=compilePdf.getText(filePath);
		file_exit.addActionListener(new ActionListener() { 
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				}); 
		IconNode hideNode=new IconNode(new ImageIcon("src/img/BookshelfFont.png"),"默认书架");
		bookshelf.add(hideNode);  
//		store.add(new IconNode(new ImageIcon("src/img/bookfont.png"), "越女剑"));
//		root.add(bookshelf);
//		root.add(recent);
//		root.add(store);
//		root.add(online);
//		root.add(blank);
		treeModel=new DefaultTreeModel(root);
		treeModel.insertNodeInto(bookshelf, root, 0);
		treeModel.insertNodeInto(recent, root, 1);
		treeModel.insertNodeInto(store, root, 2);
		treeModel.insertNodeInto(online, root, 3);
		jTree = new JTree(treeModel);
		jTree.setFont(new Font("华文行楷", Font.PLAIN, 23));
		jTree.setRowHeight(50);
		jTree.setRootVisible(false);
		// jTree.setToggleClickCount(1);//
		jTree.setCellRenderer(new IconNodeRenderer());
		jTree.setBounds(0, 0, 150, 768);
		jTree.setOpaque(false);
		jTree.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) { // 
					if (e.getClickCount() == 1) {
						pageNumJTextArea.setText("第1页");
						if(jPopupMenu!=null)
							jPopupMenu.setVisible(false); 
						TreePath path = jTree.getSelectionPath();
						if (path != null) {
							IconNode node = (IconNode) path
									.getLastPathComponent();
							jPanel_book.removeAll();
							jPanel_book.repaint();
//							System.out.println("a"+node.getText());
							if (node != null && node.isLeaf() == false
									&& node.getText().equals("本地书架")) 
							{
								if (bookshelf.getLeftFlag() == 0) 
								{
									System.out.println(bookshelf.getChildCount());
									List_BookShelf.clear();
									bookshelf.removeAllChildren(); 
									treeModel.reload(bookshelf);
									bookshelf.setIcon(new ImageIcon(
											"src/img/nodefontclicked.jpg"));
									LoadBookshelf loadBookshelf=null;
									loadBookshelf=new LoadBookshelf();
									List_BookShelf=loadBookshelf.getListBookcategory();  //
									for(int i=0;i<List_BookShelf.size();i++)
									{
										IconNode bookCategory=new IconNode(new ImageIcon("src/img/BookshelfFont.png"), List_BookShelf.get(i));
										bookCategory.setFather("bookshelf");
										treeModel.insertNodeInto(bookCategory, bookshelf, 0);
//										bookshelf.add(bookCategory);								
//										System.out.println(i+":"+bookshelf.getChildCount());
									}
									jPanel.repaint();
									jTree.expandPath(path);
									bookshelf.setLeftFlag(1);
								} 
								else 
								{
//									jPanel_book.removeAll();
//									jPanel_book.repaint();
									bookshelf.setIcon(new ImageIcon(
											"src/img/nodefont.jpg"));
									jTree.collapsePath(path);
									bookshelf.setLeftFlag(0);
								}
								// System.out.println(node.getText()+"--flag--"+node.getFlag());
							} 
							else if (node != null&& node.getText().equals("最近阅读")) 
							{
//								jPanel_book.removeAll();
//								jPanel_book.repaint();
								BookshelfJTextArea.clear();
								BookshelfJLabel.clear();
								if (recent.getLeftFlag() == 0) {
									recent.setIcon(new ImageIcon(
											"src/img/nodefontclicked.jpg"));
									LoadRecentFile loadRecentFile = null;
									try {
										loadRecentFile = new LoadRecentFile();
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									RecentJTextArea = loadRecentFile
											.getRecentJTextArea();
									RecentJLabel = loadRecentFile
											.getRecentJLabel();
									for (int i = 0; i < RecentJTextArea.size(); i++) {
										jPanel_book.add(RecentJTextArea.get(i));
										jPanel_book.add(RecentJLabel.get(i));
									}
									recent.setLeftFlag(1);
								} else {
									for (int i = 0; i < RecentJTextArea.size(); i++) {
										jPanel_book.remove(RecentJLabel.get(i));
										jPanel_book.remove(RecentJTextArea.get(i));
									}
									RecentJTextArea.clear();
									recent.setIcon(new ImageIcon(
											"src/img/nodefont.jpg"));
									recent.setLeftFlag(0);;
								}
							}
							else if(node.getText().equals("我的收藏"))
							{
								if (store.getLeftFlag() == 0) {
									store.setIcon(new ImageIcon(
											"src/img/nodefontclicked.jpg"));
									LoadStore loadstore= new LoadStore();
									StoreJTextArea = loadstore.getStoreJTextArea();
									StoreJLabel = loadstore.getStoreJLabel();
									for (int i = 0; i < StoreJTextArea.size(); i++) {
										jPanel_book.add(StoreJTextArea.get(i));
										jPanel_book.add(StoreJLabel.get(i));
									}
									store.setLeftFlag(1);
								} else {
									for (int i = 0; i < StoreJTextArea.size(); i++) {
										jPanel_book.remove(StoreJLabel.get(i));
										jPanel_book.remove(StoreJTextArea.get(i));
									}
									StoreJTextArea.clear();
									store.setIcon(new ImageIcon(
											"src/img/nodefont.jpg"));
									store.setLeftFlag(0);;
								}
							}
							else if(node.getFather().equals("bookshelf"))  //
							{
								OnlineshelfJTextArea.clear();
								OnlineshelfJLabel.clear();
								LoadCategory loadCategory=null;
								loadCategory=new LoadCategory(node.getText());
								BookshelfJTextArea=loadCategory.getBookshelfJTextArea();
								BookshelfJLabel=loadCategory.getBookshelfJLabel();
								for (int i = 0; i < BookshelfJTextArea.size(); i++) {
									jPanel_book.add(BookshelfJTextArea.get(i));
									jPanel_book.add(BookshelfJLabel.get(i));
								}
//								System.out.println(node.getText());
							}
							else if(node!=null&&node.getText().equals("在线书架"))
							{
//								CkLogin ckLogin=new CkLogin();
								online.removeAllChildren();
								treeModel.reload(online);
								if(loginName.getText().equals("无"))
								{
									JOptionPane.showMessageDialog(null, "请先登录");
								}
								else
								{
									jPanel_book.removeAll();
									jPanel_book.repaint();
									if (online.getLeftFlag() == 0) 
									{
										LoadOnlineShelf loadOnlineShelf=new LoadOnlineShelf(loginName.getText());
										List_OnlineShelf=loadOnlineShelf.getLlist();
										for(int i=0;i<List_OnlineShelf.size();i++)
										{
											IconNode iconNode=new IconNode(new ImageIcon("src/img/BookshelfFont.png"), List_OnlineShelf.get(i));
											iconNode.setFather("online");
											treeModel.insertNodeInto(iconNode, online, 0);
//											frame.MainFrame.getOnline().add(iconNode);
										}
										online.setIcon(new ImageIcon(
												"src/img/nodefontclicked.jpg"));
										online.setLeftFlag(1);
										jTree.expandPath(path);
									}
									else
									{
										online.setIcon(new ImageIcon(
												"src/img/nodefont.jpg"));
										jTree.collapsePath(path);
										online.setLeftFlag(0);
									}
								}
							}
							else if(node.getFather().equals("online"))  //在线书库下书架
							{
								jPanel_book.removeAll();
								BookshelfJTextArea.clear();
								BookshelfJLabel.clear();
								LoadOnlineCategory loadOnlineCategory=new LoadOnlineCategory(node.getText());
								OnlineshelfJTextArea=loadOnlineCategory.getOnlineshelfJTextArea();
								OnlineshelfJLabel=loadOnlineCategory.getOnlineshelfJLabel();
								for (int i = 0; i < OnlineshelfJTextArea.size(); i++) {
									jPanel_book.add(OnlineshelfJTextArea.get(i));
									jPanel_book.add(OnlineshelfJLabel.get(i));
								}
								jPanel_book.repaint();
							}
						} 
						else {
							// 
							//System.out.println("aaa");
						}
//						jTree.repaint();
//						jPanel.repaint();
					}
					else //clickcount
					{
					}
				}
				 else if(e.getButton()==MouseEvent.BUTTON3) //
				 {
					 if(jPopupMenu!=null)
						 jPopupMenu.setVisible(false);
//					 int selRow = jTree.getRowForLocation(e.getX(), e.getY()); 
					 TreePath path = jTree.getPathForLocation(e.getX(), e.getY());
					 if(path!=null)
					 {
						IconNode node = (IconNode) path.getLastPathComponent();
						if(node.getText().equals("本地书架"))
						{
							ShelfJPopupMenu shelfJPopupMenu=new ShelfJPopupMenu(node.getText(),jPanel);
							jPopupMenu = shelfJPopupMenu;
							jPopupMenu.setLocation(e.getXOnScreen(), e.getYOnScreen());
							shelfJPopupMenu=null;
						}
						else if (node.getText().equals("最近阅读")) 
						{
							ClearJPopupMenu clearJPopupMenu=new ClearJPopupMenu();
							jPopupMenu = clearJPopupMenu;
							jPopupMenu.setLocation(e.getXOnScreen(), e.getYOnScreen());
							clearJPopupMenu=null;
						}
						else if(node.getText().equals("在线书架"))
						{
							if(loginName.getText().equals("无")==false)
							{
								OnlineJPopupMenu onlineJPopupMenu=new OnlineJPopupMenu(node.getText());
								jPopupMenu=onlineJPopupMenu;
								jPopupMenu.setLocation(e.getXOnScreen(), e.getYOnScreen());
								onlineJPopupMenu=null;
							}
						}
						else if(node.getFather().equals("bookshelf"))
						{
							System.out.println(node.getText());
//							node.isLeaf()&&node.getText().equals("最近阅读")==false&&node.getText().equals("我的收藏")==false&&node.getText().equals("在线书架")==false
							ShelfJPopupMenu shelfJPopupMenu=new ShelfJPopupMenu(node.getText(),jPanel);
							jPopupMenu = shelfJPopupMenu;
							jPopupMenu.setLocation(e.getXOnScreen(), e.getYOnScreen());
							shelfJPopupMenu=null;
						}
						else if(node.getFather().equals("online"))
						{
							OnlineJPopupMenu onlineJPopupMenu=new OnlineJPopupMenu(node.getText());
							jPopupMenu=onlineJPopupMenu;
							jPopupMenu.setLocation(e.getXOnScreen(), e.getYOnScreen());
							onlineJPopupMenu=null;
						}
						jTree.repaint();
						if(jPopupMenu!=null)
							jPopupMenu.setVisible(true);
//						jTree.add(jPopupMenu);
					 }
					 else
					 {
						 
					 }
//					 System.out.println(e.getComponent());
				 }
				else { //
					
				}
			}
		});
		logout.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e)
			{
				int optionType = JOptionPane.YES_NO_OPTION; //
				int messageType = JOptionPane.WARNING_MESSAGE; // 
				int result = JOptionPane.showConfirmDialog(null,
						"是否确认退出登录", "消息", optionType, messageType);
				if (result == JOptionPane.YES_OPTION) 
				{
					loginName.setText("无");
					logout.setVisible(false);
				}
			}
		});
		user_login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(loginName.getText().equals("无")==false)
					JOptionPane.showMessageDialog(null, "您已经登陆");
				else
				{
					LoginDialog loginDialog=new LoginDialog();
				}
			}
		});
		user_logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(loginName.getText().equals("无"))
				{
					JOptionPane.showMessageDialog(null, "您还未登陆");
				}
				else 
				{
					int optionType = JOptionPane.YES_NO_OPTION; //
					int messageType = JOptionPane.WARNING_MESSAGE; //
					int result = JOptionPane.showConfirmDialog(null,
							"是否确认退出登录", "消息", optionType, messageType);
					if (result == JOptionPane.YES_OPTION) {
						loginName.setText("无");
						logout.setVisible(false);
						user_login.setEnabled(true);
						user_logout.setEnabled(false);
					}
				}
			}
		});
		search_area.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					if(BookshelfJTextArea.size()!=0)
					{
						int flag=0;
						jPanel_book.removeAll();
						for(int i=0;i<BookshelfJTextArea.size();i++)
						{
							if(BookshelfJTextArea.get(i).getText().contains(search_area.getText()))
							{
								jPanel_book.add(BookshelfJTextArea.get(i));
								jPanel_book.add(BookshelfJLabel.get(i));
								flag=1;
							}
						}
						if(flag==0)
							JOptionPane.showMessageDialog(null, "未找到匹配书籍");
						jPanel_book.repaint();
					}
					else if(OnlineshelfJTextArea.size()!=0)
					{
						int flag=0;
						jPanel_book.removeAll();
						for(int i=0;i<OnlineshelfJTextArea.size();i++)
						{
							if(OnlineshelfJTextArea.get(i).getText().contains(search_area.getText()))
							{
								jPanel_book.add(OnlineshelfJTextArea.get(i));
								jPanel_book.add(OnlineshelfJLabel.get(i));
								flag=1;
							}
						}
						if(flag==0)
							JOptionPane.showMessageDialog(null, "未找到匹配书籍");
						jPanel_book.repaint();
					}
//					search_area.setText("");
				}
			}
		});
		search_label.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e)
			{
				if(BookshelfJTextArea.size()!=0)
				{
					int flag=0;
					jPanel_book.removeAll();
					for(int i=0;i<BookshelfJTextArea.size();i++)
					{
						if(BookshelfJTextArea.get(i).getText().contains(search_area.getText()))
						{
							jPanel_book.add(BookshelfJTextArea.get(i));
							jPanel_book.add(BookshelfJLabel.get(i));
							flag=1;
						}
					}
					if(flag==0)
						JOptionPane.showMessageDialog(null, "未找到匹配书籍");
					jPanel_book.repaint();
				}
				else if(OnlineshelfJTextArea.size()!=0)
				{
					int flag=0;
					jPanel_book.removeAll();
					for(int i=0;i<OnlineshelfJTextArea.size();i++)
					{
						if(OnlineshelfJTextArea.get(i).getText().contains(search_area.getText()))
						{
							jPanel_book.add(OnlineshelfJTextArea.get(i));
							jPanel_book.add(OnlineshelfJLabel.get(i));
							flag=1;
						}
					}
					if(flag==0)
						JOptionPane.showMessageDialog(null, "未找到匹配书籍");
					jPanel_book.repaint();
				}
			}
		});
		jScrollPane=new JScrollPane(jTree);
		jScrollPane.setBounds(0, 0, 150, 768);
		jScrollPane.setOpaque(false);
		jScrollPane.getViewport().setOpaque(false); 
		DefaultTreeCellRenderer cellRenderer = (DefaultTreeCellRenderer) jTree
				.getCellRenderer();
		cellRenderer.setClosedIcon(new ImageIcon("src/img/book.jpg"));
		cellRenderer.setOpenIcon(new ImageIcon("src/img/background.jpg"));
		ClearBookshelf clearBookshelf=new ClearBookshelf(operation_clearbookshelf);
		ClearRecentFile clearRecentFile = new ClearRecentFile(operation_clearrecent);
		ImportBook importBook=new ImportBook(import_book);
		UploadBook uploadbook=new UploadBook(online_UploadBook);
		MagBookshelf magBookshelf=new MagBookshelf(operation_magbookshelf);
		MagOnlineShelf magOnlineShelf=new MagOnlineShelf(online_MagOnlineShlef);
		ClearOnlineShelf clearOnlineShelf=new ClearOnlineShelf(online_CleOnlineShlef);
		MagStore magStore=new MagStore(operation_magstore);
		ClearStore clearStore=new ClearStore(operation_clearstore);
		menu_operation.add(operation_magbookshelf);
		menu_operation.add(operation_clearbookshelf);
		menu_operation.add(operation_magstore);
		menu_operation.add(operation_clearstore);
		menu_operation.add(operation_clearrecent);
		menu_file.add(file_open);
		menu_file.addSeparator(); // 
		menu_file.add(file_exit);
		menu_online.add(online_UploadBook);
		menu_online.add(online_MagOnlineShlef);
//		menu_online.add(online_CleOnlineShlef);
		menu_import.add(import_book);
		menu_user.add(user_login);
		menu_user.add(user_logout);
		menuBar.add(menu_file);
		menuBar.add(menu_import);
		menuBar.add(menu_operation);
		menuBar.add(menu_online);
		menuBar.add(menu_user);
		login.setBounds(200, 8, 100, 25);
		login.setText("登陆用户:");
		login.setFont(new Font("华文隶书", Font.PLAIN, 20));
		loginName.setBounds(300, 8, 100, 25);
		loginName.setText("无");
		loginName.setFont(new Font("华文隶书", Font.PLAIN, 20));
		logout.setBounds(1300, 8, 100, 25);
		logout.setText("退出");
		logout.setFont(new Font("华文隶书", Font.PLAIN, 20));
		logout.setCursor(new Cursor(Cursor.HAND_CURSOR));
		logout.setVisible(false);
		search_area.setBounds(1200, 8, 100, 20);
		search_area.setBorder(BorderFactory.createMatteBorder (1,1,1,1, Color.black));
		search_area.setOpaque(false);
		search_label.setBounds(1300, 2, 30, 30);
		search_label.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jlabelleft.setBounds(660, 2, 30, 30);;
		jlabelleft.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jlabelright.setBounds(780, 2, 30, 30);
		jlabelright.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pageNumJTextArea.setBounds(712, 8, 100, 30);
		pageNumJTextArea.setFont(new Font("华文隶书", Font.PLAIN, 20));
		pageNumJTextArea.setOpaque(false);
		pageNumJTextArea.setFocusable(false);
		jPanel_book.setBounds(0, 25, imageIcon.getIconWidth(), imageIcon.getIconHeight());
		jPanel_book.setVisible(true);
		jLabel.setBounds(0, 0, imageIcon.getIconWidth(), imageIcon
				.getIconHeight());
		jPanel_book.add(jLabel);
		frame.setBounds(0, 0, imageIcon.getIconWidth(), imageIcon
				.getIconHeight());
		jPanel.add(jScrollPane);
		jPanel.add(jPanel_book);
		jPanel.add(login);
		jPanel.add(loginName);
		jPanel.add(search_area);
		jPanel.add(search_label);
		jPanel.add(jlabelleft);
		jPanel.add(jlabelright);
		jPanel.add(pageNumJTextArea);
		//jPanel.add(logout);
		frame.setJMenuBar(menuBar);
		frame.getLayeredPane().setLayout(null);
		frame.getLayeredPane().add(jLabel, new Integer(Integer.MIN_VALUE));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jlabelleft.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e)
			{
				if(jpanelbookNum==0)
					JOptionPane.showMessageDialog(null, "已到首页");
				else
				{
					jpanelbookNum--;
					jPanel_book.removeAll();
					if(BookshelfJTextArea.size()!=0)
					{
						for(int i=20*jpanelbookNum;i<20*jpanelbookNum+20;i++)
						{
							try
							{
								jPanel_book.add(BookshelfJTextArea.get(i));
								jPanel_book.add(BookshelfJLabel.get(i));
							}
							catch(Exception e1)
							{
								break;
							}
						}
					}
					else if(OnlineshelfJTextArea.size()!=0)
					{
						for(int i=20*jpanelbookNum;i<20*jpanelbookNum+20;i++)
						{
							try
							{
								jPanel_book.add(OnlineshelfJTextArea.get(i));
								jPanel_book.add(OnlineshelfJLabel.get(i));
							}
							catch(Exception e1)
							{
								break;
							}
						}
					}
					pageNumJTextArea.setText("第"+Integer.toString(jpanelbookNum+1)+"页");
					jPanel_book.repaint();
				}
			}
		});
		jlabelright.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e)
			{
				System.out.println(BookshelfJTextArea.size());
				if(jpanelbookNum==jpanelbookMax)
					JOptionPane.showMessageDialog(null, "已到尾页");
				else
				{
					jpanelbookNum++;
					jPanel_book.removeAll();
					if(BookshelfJTextArea.size()!=0)
					{
						for(int i=20*jpanelbookNum;i<20*jpanelbookNum+20;i++)
						{
							try
							{
								jPanel_book.add(BookshelfJTextArea.get(i));
								jPanel_book.add(BookshelfJLabel.get(i));
							}
							catch(Exception e1)
							{
								break;
							}
						}
					}
					else if(OnlineshelfJTextArea.size()!=0)
					{
						for(int i=20*jpanelbookNum;i<20*jpanelbookNum+20;i++)
						{
							try
							{
								jPanel_book.add(OnlineshelfJTextArea.get(i));
								jPanel_book.add(OnlineshelfJLabel.get(i));
							}
							catch(Exception e1)
							{
								break;
							}
						}
					}
					pageNumJTextArea.setText("第"+Integer.toString(jpanelbookNum+1)+"页");
					jPanel_book.repaint();
				}
			}
		});
//		jPanel_book.addKeyListener(new KeyAdapter() {
//			public void keyPressed(KeyEvent e)
//			{
//				if(e.getKeyCode()==KeyEvent.VK_LEFT)
//				{
//					if(jpanelbookNum==1)
//						JOptionPane.showMessageDialog(null, "已到首页");
//					else
//					{
//						jpanelbookNum--;
//						jPanel_book.removeAll();
//						if(BookshelfJTextArea.size()!=0)
//						{
//							jPanel_book.add(BookshelfJTextArea.get(20*jpanelbookNum));
//							jPanel_book.add(BookshelfJLabel.get(20*jpanelbookNum));
//						}
//						else if(OnlineshelfJTextArea.size()!=0)
//						{
//							jPanel_book.add(OnlineshelfJTextArea.get(20*jpanelbookNum));
//							jPanel_book.add(OnlineshelfJLabel.get(20*jpanelbookNum));
//						}
//						jPanel_book.repaint();
//					}
//				}
//				else if(e.getKeyCode()==KeyEvent.VK_RIGHT)
//				{
//					if(jpanelbookNum==jpanelbookMax)
//						JOptionPane.showMessageDialog(null, "已到尾页");
//					else
//					{
//						jpanelbookNum++;
//						jPanel_book.removeAll();
//						if(BookshelfJTextArea.size()!=0)
//						{
//							jPanel_book.add(BookshelfJTextArea.get(20*jpanelbookNum));
//							jPanel_book.add(BookshelfJLabel.get(20*jpanelbookNum));
//						}
//						else if(OnlineshelfJTextArea.size()!=0)
//						{
//							jPanel_book.add(OnlineshelfJTextArea.get(20*jpanelbookNum));
//							jPanel_book.add(OnlineshelfJLabel.get(20*jpanelbookNum));
//						}
//						jPanel_book.repaint();
//					}
//				}
//			}
//		});
//		jPanel_book.addMouseListener(new MouseAdapter() {
//			public void mousePressed(MouseEvent e)
//			{
//				if (e.getButton() == MouseEvent.BUTTON3) 
//				{
//					if (e.getSource().equals(JTextArea.class)) {
//						System.out.println("aa");
//					}
//				}
//			}
//		});
	}
	public static JPanel getjPanel_book() {
		return jPanel_book;
	}
	public static void setjPanel_book(JPanel jPanel_book) {
		MainFrame.jPanel_book = jPanel_book;
	}
	public static List<JTextArea> getRecentJTextArea() {
		return RecentJTextArea;
	}
	public static void setRecentJTextArea(List<JTextArea> recentJTextArea) {
		RecentJTextArea = recentJTextArea;
	}
	public static List<JLabel> getRecentJLabel() {
		return RecentJLabel;
	}
	public static void setRecentJLabel(List<JLabel> recentJLabel) {
		RecentJLabel = recentJLabel;
	}
	public static IconNode getBookshelf() {
		return bookshelf;
	}
	public static void setBookshelf(IconNode bookshelf) {
		MainFrame.bookshelf = bookshelf;
	}
	public static JPanel getjPanel() {
		return jPanel;
	}
	public static void setjPanel(JPanel jPanel) {
		MainFrame.jPanel = jPanel;
	}
	public static JTree getjTree() {
		return jTree;
	}
	public static void setjTree(JTree jTree) {
		MainFrame.jTree = jTree;
	}
	public static JLabel getLoginName() {
		return loginName;
	}
	public static void setLoginName(JLabel loginName) {
		MainFrame.loginName = loginName;
	}
	public static HttpClient getHttpClient() {
		return httpClient;
	}
	public static void setHttpClient(HttpClient httpClient) {
		MainFrame.httpClient = httpClient;
	}
	public static IconNode getOnline() {
		return online;
	}
	public static void setOnline(IconNode online) {
		MainFrame.online = online;
	}
	public static JLabel getLogout() {
		return logout;
	}
	public static void setLogout(JLabel logout) {
		MainFrame.logout = logout;
	}
	public static DefaultTreeModel getTreeModel() {
		return treeModel;
	}
	public static void setTreeModel(DefaultTreeModel treeModel) {
		MainFrame.treeModel = treeModel;
	}
	public static JMenuItem getUser_login() {
		return user_login;
	}
	public static void setUser_login(JMenuItem user_login) {
		MainFrame.user_login = user_login;
	}
	public static JMenuItem getUser_logout() {
		return user_logout;
	}
	public static void setUser_logout(JMenuItem user_logout) {
		MainFrame.user_logout = user_logout;
	}
	public static List<JTextArea> getBookshelfJTextArea() {
		return BookshelfJTextArea;
	}
	public static void setBookshelfJTextArea(List<JTextArea> bookshelfJTextArea) {
		BookshelfJTextArea = bookshelfJTextArea;
	}
	public static List<JLabel> getBookshelfJLabel() {
		return BookshelfJLabel;
	}
	public static void setBookshelfJLabel(List<JLabel> bookshelfJLabel) {
		BookshelfJLabel = bookshelfJLabel;
	}
	public static List<JTextArea> getOnlineshelfJTextArea() {
		return OnlineshelfJTextArea;
	}
	public static void setOnlineshelfJTextArea(List<JTextArea> onlineshelfJTextArea) {
		OnlineshelfJTextArea = onlineshelfJTextArea;
	}
	public static List<JLabel> getOnlineshelfJLabel() {
		return OnlineshelfJLabel;
	}
	public static void setOnlineshelfJLabel(List<JLabel> onlineshelfJLabel) {
		OnlineshelfJLabel = onlineshelfJLabel;
	}
	public static List<JTextArea> getStoreJTextArea() {
		return StoreJTextArea;
	}
	public static void setStoreJTextArea(List<JTextArea> storeJTextArea) {
		StoreJTextArea = storeJTextArea;
	}
	public static List<JLabel> getStoreJLabel() {
		return StoreJLabel;
	}
	public static void setStoreJLabel(List<JLabel> storeJLabel) {
		StoreJLabel = storeJLabel;
	}
	public static int getJpanelbookMax() {
		return jpanelbookMax;
	}
	public static void setJpanelbookMax(int jpanelbookMax) {
		MainFrame.jpanelbookMax = jpanelbookMax;
	}
	public static int getJpanelbookNum() {
		return jpanelbookNum;
	}
	public static void setJpanelbookNum(int jpanelbookNum) {
		MainFrame.jpanelbookNum = jpanelbookNum;
	}
	
	
}
