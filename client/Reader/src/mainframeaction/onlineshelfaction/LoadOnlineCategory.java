package mainframeaction.onlineshelfaction;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

import mainframeaction.OpenSelectedBook;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import JPopupMenu.OnlineBookJPopupMenu;
import JPopupMenu.ShelfBookJPopupMenu;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import domain.IconNode;

public class LoadOnlineCategory {
	List<JTextArea> OnlineshelfJTextArea=new ArrayList<JTextArea>();
	List<JLabel> OnlineshelfJLabel=new ArrayList<JLabel>();
	ImageIcon imageIcon = new ImageIcon("src/img/book.png");
	JPopupMenu jPopupMenu=null;
	public LoadOnlineCategory(final String nodeText)
	{
		String url="http://localhost/reader/LoadOnlineCategory.php";
		PostMethod postMethod=new PostMethod(url);
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		NameValuePair[] loginInformation={ new NameValuePair("username", frame.MainFrame.getLoginName().getText()),
				new NameValuePair("nodetext", nodeText)};
		postMethod.setRequestBody(loginInformation);
		try {
			int statusCode = frame.MainFrame.getHttpClient().executeMethod(postMethod);
			if (statusCode == HttpStatus.SC_OK) 
			{
				byte[] responseBody=postMethod.getResponseBody();
				String str_response=new String(responseBody);
				Gson gson=new Gson();
				LinkedList<String> llist=gson.fromJson(str_response, new TypeToken<LinkedList<String>>() {}.getType());
				if(llist.size()>0)
				{
					llist.remove(0);
					llist.remove(0);
				}
				int count=0;
				for(int i=0;i<llist.size();i++)
				{
					count++;
					if(count>=1&&count<=5)
					{
						JLabel jLabel=new JLabel(imageIcon);
						jLabel.setBounds(200*count, 35, 100, 120);
						jLabel.setVisible(true);
						OnlineshelfJLabel.add(jLabel);
						JTextArea jTextArea=new JTextArea(llist.get(i));
						jTextArea.setBounds(200*count, 55, 100, 120);
						jTextArea.setOpaque(false);
						jTextArea.setFocusable(false);
						jTextArea.setEditable(false);
						jTextArea.setVisible(true);
						OnlineshelfJTextArea.add(jTextArea);
					}
					else if(count>=6&&count<=10)
					{
						JLabel jLabel=new JLabel(imageIcon);
						jLabel.setBounds(200*(count-5), 205, 100, 120);
						jLabel.setVisible(true);
						OnlineshelfJLabel.add(jLabel);
						JTextArea jTextArea=new JTextArea(llist.get(i));
						jTextArea.setBounds(200*(count-5), 225, 100, 120);
						jTextArea.setOpaque(false);
						jTextArea.setFocusable(false);
						jTextArea.setEditable(false);
						jTextArea.setVisible(true);
						OnlineshelfJTextArea.add(jTextArea);
					}
					else if(count>=11&&count<=15)
					{
						JLabel jLabel=new JLabel(imageIcon);
						jLabel.setBounds(200*(count-10), 375, 100, 120);
						jLabel.setVisible(true);
						OnlineshelfJLabel.add(jLabel);
						JTextArea jTextArea=new JTextArea(llist.get(i));
						jTextArea.setBounds(200*(count-10), 395, 100, 120);
						jTextArea.setOpaque(false);
						jTextArea.setFocusable(false);
						jTextArea.setEditable(false);
						jTextArea.setVisible(true);
						OnlineshelfJTextArea.add(jTextArea);
					}
					else
					{
						JLabel jLabel=new JLabel(imageIcon);
						jLabel.setBounds(200*(count-15), 545, 100, 120);
						jLabel.setVisible(true);
						OnlineshelfJLabel.add(jLabel);
						JTextArea jTextArea=new JTextArea(llist.get(i));
						jTextArea.setBounds(200*(count-15), 565, 100, 120);
						jTextArea.setOpaque(false);
						jTextArea.setVisible(true);
						OnlineshelfJTextArea.add(jTextArea);
					}
				}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
			}
		} catch (HttpException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(int i=0;i<OnlineshelfJTextArea.size();i++)
		{
			OnlineshelfJTextArea.get(i).setCursor(new Cursor(Cursor.HAND_CURSOR));  
			OnlineshelfJTextArea.get(i).addMouseListener(new MouseAdapter() 
			{
				public void mousePressed(MouseEvent e) 
				{
					if(jPopupMenu!=null)
						jPopupMenu.setVisible(false);
					if (e.getButton() == MouseEvent.BUTTON1) // ���������
					{
							try {
								OpenSelectedBook openShelfBook = new OpenSelectedBook(nodeText,
										((JTextArea) e.getSource()).getText(),"online");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					}
					else if(e.getButton()==MouseEvent.BUTTON3)
					{
						jPopupMenu=new OnlineBookJPopupMenu(nodeText,((JTextArea) e.getSource()).getText());
//						System.out.println(((JTextArea) e.getSource()).getText());
						jPopupMenu.setLocation(e.getXOnScreen(), e.getYOnScreen());
						jPopupMenu.setVisible(true);
					}
				}
			});
		}
	}
	public List<JTextArea> getOnlineshelfJTextArea() {
		return OnlineshelfJTextArea;
	}
	public void setOnlineshelfJTextArea(List<JTextArea> onlineshelfJTextArea) {
		OnlineshelfJTextArea = onlineshelfJTextArea;
	}
	public List<JLabel> getOnlineshelfJLabel() {
		return OnlineshelfJLabel;
	}
	public void setOnlineshelfJLabel(List<JLabel> onlineshelfJLabel) {
		OnlineshelfJLabel = onlineshelfJLabel;
	}
	
}
