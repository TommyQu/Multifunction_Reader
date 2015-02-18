package mainframeaction.onlineshelfaction;

import java.io.IOException;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import domain.IconNode;

public class AddOnlineShelf {
	public AddOnlineShelf(String newshelf)
	{
		if(newshelf.contains(" ")||
				newshelf.contains("<")||
				newshelf.contains(">")||
				newshelf.contains("/")||
				newshelf.contains("\\")||
				newshelf.contains(":")||
				newshelf.contains("*")||
				newshelf.contains("?")||
				newshelf.equals("\""))
			JOptionPane.showMessageDialog(null, "书架名为空或包含不合法字符");
		else
		{
			String url = "http://localhost/reader/AddOnlineShelf.php";
			PostMethod postMethod = new PostMethod(url);
			postMethod.getParams().setParameter(
					HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
			postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler());
			NameValuePair[] addshelfInformation = {
					new NameValuePair("newshelf", newshelf),
					new NameValuePair("username", frame.MainFrame
							.getLoginName().getText()) };
			postMethod.setRequestBody(addshelfInformation);
			try {
				int statusCode = frame.MainFrame.getHttpClient().executeMethod(
						postMethod);
				System.out.println(statusCode);
				if (statusCode == HttpStatus.SC_OK) {
					byte[] responseBody = postMethod.getResponseBody();
					String str_response = new String(responseBody);
					System.out.println(str_response);
					if (str_response.equals("添加成功")) {
						JOptionPane.showMessageDialog(null, "添加成功");
						IconNode iconNode = new IconNode(new ImageIcon(
								"src/img/BookshelfFont.png"), newshelf);
						iconNode.setFather("online");
						System.out.println(iconNode.getText());
						frame.MainFrame.getTreeModel().insertNodeInto(iconNode, frame.MainFrame.getOnline(), 1);
						frame.MainFrame.getTreeModel().reload(frame.MainFrame.getOnline());
					} else
						JOptionPane.showMessageDialog(null, "书架已存在");
				}
			} catch (HttpException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
