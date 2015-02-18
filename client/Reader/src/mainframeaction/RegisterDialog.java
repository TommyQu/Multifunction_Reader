package mainframeaction;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class RegisterDialog {
	public RegisterDialog()
	{
		final JDialog jDialog=new JDialog();
		jDialog.setTitle("注册界面");
		jDialog.setLayout(null);
//		jDialog.setLocationRelativeTo(null);
		jDialog.setBounds(500,150,350, 450);
		JLabel jLabel=new JLabel("用户名");
		jLabel.setBounds(60, 50, 80, 20);
		JLabel jLabel2=new JLabel("检查用户名");
		jLabel2.setBounds(245, 40, 80, 40);
		jLabel2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jLabel2.setFont(new Font("华文隶书", Font.PLAIN, 15));
		JLabel jLabel_password=new JLabel("密码");
		jLabel_password.setBounds(60, 100, 80, 20);
		JLabel jLabel_password_confirm=new JLabel("确认密码");
		jLabel_password_confirm.setBounds(60, 150, 80, 20);
		JLabel jLabel_sex=new JLabel("性别");
		jLabel_sex.setBounds(60, 200, 80, 20);
		JLabel jLabel_category=new JLabel("喜爱书类");
		jLabel_category.setBounds(60, 250, 80, 20);
		JLabel jLabel_mailbox=new JLabel("邮箱");
		jLabel_mailbox.setBounds(60, 300, 80, 20);
		final JTextArea jTextArea=new JTextArea();
		jTextArea.setBounds(130, 50, 100, 20);
		jTextArea.setBorder(BorderFactory.createMatteBorder (1,1,1,1, Color.gray));
		final JPasswordField jTextArea_password=new JPasswordField();
		jTextArea_password.setBounds(130, 100, 100, 20);
		jTextArea_password.setBorder(BorderFactory.createMatteBorder (1,1,1,1, Color.gray));
		final JPasswordField jTextArea_password_confirm=new JPasswordField();
		jTextArea_password_confirm.setBounds(130, 150, 100, 20);
		jTextArea_password_confirm.setBorder(BorderFactory.createMatteBorder (1,1,1,1, Color.gray));
		ButtonGroup bg=new ButtonGroup();
		final JRadioButton male=new JRadioButton("男");
		male.setBounds(130, 200, 40, 20);
		final JRadioButton female=new JRadioButton("女");
		female.setBounds(180, 200, 40, 20);
		bg.add(male);
		bg.add(female);
		final JTextArea jTextArea_category=new JTextArea();
		jTextArea_category.setBounds(130, 250, 100, 20);
		jTextArea_category.setBorder(BorderFactory.createMatteBorder (1,1,1,1, Color.gray));
		final JTextArea jTextArea_mailbox=new JTextArea();
		jTextArea_mailbox.setBounds(130, 300, 100, 20);
		jTextArea_mailbox.setBorder(BorderFactory.createMatteBorder (1,1,1,1, Color.gray));
		
		JButton confirm=new JButton("确认");
		confirm.setBounds(70, 350, 80, 20);
		JButton cancel=new JButton("取消");
		cancel.setBounds(170, 350, 80, 20);
		jDialog.add(jTextArea);
		jDialog.add(jTextArea_password);
		jDialog.add(jLabel);
		jDialog.add(jLabel2);
		jDialog.add(jLabel_password);
		jDialog.add(jLabel_password_confirm);
		jDialog.add(jTextArea_password_confirm);
		jDialog.add(jLabel_category);
		jDialog.add(jTextArea_category);
		jDialog.add(male);
		jDialog.add(female);
		jDialog.add(jLabel_sex);
		jDialog.add(jLabel_mailbox);
		jDialog.add(jTextArea_mailbox);
		jDialog.add(confirm);
		jDialog.add(cancel);
		jDialog.setVisible(true);
		jDialog.setAlwaysOnTop(true);
		jLabel2.addMouseListener(new MouseAdapter() {  //检查用户名是否存在
			public void mouseClicked(MouseEvent e)
			{
				Pattern p=Pattern.compile("^([a-zA-Z0-9_-])+$");  //先验证用户名合法性
				Matcher m = p.matcher(jTextArea.getText());
				if(jTextArea.getText().length()<4)
					JOptionPane.showMessageDialog(jDialog, "用户名长度必须大于4个字符");
				else if(m.matches()==false)
					JOptionPane.showMessageDialog(jDialog, "用户名只能包含大小写字母和数字");
				else
				{
					String url = "http://localhost/reader/ckusername.php";
					PostMethod postMethod = new PostMethod(url);
					postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
					postMethod.getParams().setParameter(
							HttpMethodParams.RETRY_HANDLER,
							new DefaultHttpMethodRetryHandler());
					NameValuePair[] username = {new NameValuePair("username",jTextArea.getText())};
					postMethod.setRequestBody(username);
					try {
						int statusCode = frame.MainFrame.getHttpClient().executeMethod(postMethod);
						System.out.println(statusCode);
						if (statusCode == HttpStatus.SC_OK) {
							byte[] responseBody = postMethod.getResponseBody();
							String str_response = new String(responseBody);
							System.out.println(str_response);
							if (str_response.equals("﻿用户名不存在"))
								JOptionPane.showMessageDialog(jDialog,
										"恭喜!可以使用");
							else
								JOptionPane
										.showMessageDialog(jDialog, "用户名已存在");
						} else
							JOptionPane.showMessageDialog(jDialog, "服务器错误");
					} catch (HttpException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Pattern p_mail = Pattern.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\\.([a-zA-Z0-9_-])+)+$");
				Matcher m_mail = p_mail.matcher(jTextArea_mailbox.getText());
				Pattern p=Pattern.compile("^([a-zA-Z0-9_-])+$");
				Matcher m=p.matcher(jTextArea.getText());
				if(jTextArea.getText().equals("")||  //输入不能为空
						jTextArea_password.getText().equals("")||
						jTextArea_password_confirm.getText().equals("")||
						((male.isSelected()||female.isSelected())==false)||
						jTextArea_category.getText().equals("")||
						jTextArea_mailbox.getText().equals("")
						)
				{
					JOptionPane.showMessageDialog(jDialog, "请输入完整信息");
				}
				else if(jTextArea.getText().length()<4)
					JOptionPane.showMessageDialog(jDialog, "用户名长度必须大于4个字符");
				else if(m.matches()==false)
					JOptionPane.showMessageDialog(jDialog, "用户名只能包含大小写字母和数字");
				else if(jTextArea_password.getText().length()<6)
					JOptionPane.showMessageDialog(jDialog, "密码长度不能小于6位");
				else if(jTextArea_password.getText().equals(jTextArea_password_confirm.getText())==false)
					JOptionPane.showMessageDialog(jDialog, "密码输入不一致");
				else if(m_mail.matches()==false)
					JOptionPane.showMessageDialog(jDialog, "请输入合法邮箱");
				else
				{
					String sex;
					if(male.isSelected())
						sex=male.getText();
					else
						sex=female.getText();
					HttpClient httpClient=new HttpClient();
					String url="http://localhost/reader/register.php";
					PostMethod postMethod=new PostMethod(url);
					postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
					postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
							new DefaultHttpMethodRetryHandler());
					NameValuePair[] loginInformation={new NameValuePair("username", jTextArea.getText()),
							new NameValuePair("password", jTextArea_password.getText()),
							new NameValuePair("sex",sex),
							new NameValuePair("category", jTextArea_category.getText()),
							new NameValuePair("mailbox", jTextArea_mailbox.getText())};
					postMethod.setRequestBody(loginInformation);
					try {
						int statusCode = httpClient.executeMethod(postMethod);
						System.out.println(statusCode);
						if (statusCode == HttpStatus.SC_OK) 
						{
							byte[] responseBody=postMethod.getResponseBody();
							String str_response=new String(responseBody);
							System.out.println(str_response);
							if(str_response.equals("﻿注册成功"))
							{
								JOptionPane.showMessageDialog(jDialog, "注册成功");
								jDialog.dispose();
							}
							else
								JOptionPane.showMessageDialog(jDialog, "注册失败,用户名已存在");
						}
					}
					catch (HttpException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				jDialog.dispose();
			}
		});
	}

}
