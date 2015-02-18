package mainframeaction;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class LoginDialog {
	public LoginDialog()
	{
		final JDialog jDialog=new JDialog();
		jDialog.setTitle("登陆界面");
		jDialog.setLayout(null);
//		jDialog.setLocationRelativeTo(null);
		jDialog.setBounds(500,200,350, 250);
		JLabel jLabel=new JLabel("用户名");
		jLabel.setBounds(70, 50, 80, 20);
		JLabel jLabel_password=new JLabel("密码");
		jLabel_password.setBounds(70, 100, 80, 20);
		final JTextArea jTextArea=new JTextArea();
		jTextArea.setBounds(120, 50, 100, 20);
		jTextArea.setBorder(BorderFactory.createMatteBorder (1,1,1,1, Color.gray));
		final JPasswordField jTextArea_password=new JPasswordField();
		jTextArea_password.setBounds(120, 100, 100, 20);
		jTextArea_password.setBorder(BorderFactory.createMatteBorder (1,1,1,1, Color.gray));
		JButton confirm=new JButton("确认");
		confirm.setBounds(70, 150, 80, 20);
		JButton cancel=new JButton("取消");
		cancel.setBounds(170, 150, 80, 20);
		JLabel register=new JLabel();
		register.setText("注册新用户");
		register.setFont(new Font("华文隶书", Font.PLAIN, 15));
		register.setBounds(230, 50, 100, 20);
		register.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jDialog.add(jTextArea);
		jDialog.add(jTextArea_password);
		jDialog.add(jLabel);
		jDialog.add(jLabel_password);
		jDialog.add(confirm);
		jDialog.add(cancel);
		jDialog.add(register);
		jDialog.setVisible(true);
		jDialog.setAlwaysOnTop(true);
		register.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e)
			{
				RegisterDialog registerDialog=new RegisterDialog();
			}
		});
		confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String url="http://localhost/reader/login.php";
				PostMethod postMethod=new PostMethod(url);
				postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
				postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
						new DefaultHttpMethodRetryHandler());
				NameValuePair[] loginInformation={ new NameValuePair("username", jTextArea.getText()),
						new NameValuePair("password", jTextArea_password.getText()) };
				postMethod.setRequestBody(loginInformation);
				try {
					int statusCode = frame.MainFrame.getHttpClient().executeMethod(postMethod);
					if (statusCode == HttpStatus.SC_OK) 
					{
						byte[] responseBody=postMethod.getResponseBody();
						String str_response=new String(responseBody);
						if(str_response.equals("﻿登录成功"))
						{
							JOptionPane.showMessageDialog(jDialog, "登录成功");
							frame.MainFrame.getLoginName().setText(jTextArea.getText());
							jDialog.dispose();
							frame.MainFrame.getLogout().setVisible(true);
							frame.MainFrame.getUser_login().setEnabled(false);
							frame.MainFrame.getUser_logout().setEnabled(true);
						}
						else
							JOptionPane.showMessageDialog(jDialog, "用户名密码错误");
//						Header locationHeader = postMethod.getResponseHeader("location");
//						String location = null;
//						if (locationHeader != null) 
//						{
//							location = locationHeader.getValue();
//							System.out.println("The page was redirected to:" + location);
//						}
//						else 
//						{
//							System.err.println("Location field value is null.");
//						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "无法连接到服务器");
					}
				} catch (HttpException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "无法连接到服务器");
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "无法连接到服务器");
					e1.printStackTrace();
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
