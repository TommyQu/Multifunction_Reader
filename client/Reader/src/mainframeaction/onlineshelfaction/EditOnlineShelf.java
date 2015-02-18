package mainframeaction.onlineshelfaction;

import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import domain.IconNode;

public class EditOnlineShelf {

	public EditOnlineShelf(String oldname, String newname) {
		// TODO Auto-generated constructor stub
		if(newname.contains(" ")||
				newname.contains("<")||
				newname.contains(">")||
				newname.contains("/")||
				newname.contains("\\")||
				newname.contains(":")||
				newname.contains("*")||
				newname.contains("?")||
				newname.equals("\""))
			JOptionPane.showMessageDialog(null, "书架名为空或包含不合法字符");
		else if(oldname.equals("默认书架"))
			JOptionPane.showMessageDialog(null, "无法编辑默认书架");
		else
		{
			String url = "http://localhost/reader/EditOnlineShelf.php";
			PostMethod postMethod = new PostMethod(url);
			postMethod.getParams().setParameter(
					HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
			postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler());
			NameValuePair[] editshelfInformation = {
					new NameValuePair("username", frame.MainFrame
							.getLoginName().getText()),
					new NameValuePair("oldname", oldname),
					new NameValuePair("newname", newname) };
			postMethod.setRequestBody(editshelfInformation);
			try {
				int statusCode = frame.MainFrame.getHttpClient().executeMethod(
						postMethod);
				if (statusCode == HttpStatus.SC_OK) {
					byte[] responseBody = postMethod.getResponseBody();
					String str_response = new String(responseBody);
					System.out.println(str_response);
					if (str_response.equals("修改成功")) {
						JOptionPane.showMessageDialog(null, "修改成功");
						for (int i = 0; i < frame.MainFrame.getOnline()
								.getChildCount(); i++) // 找到删除的节点，从MainFrame删除
						{
							if (oldname.equals(((IconNode) frame.MainFrame
									.getOnline().getChildAt(i)).getText())) {
								((IconNode) frame.MainFrame.getOnline()
										.getChildAt(i)).setText(newname);
								;
								frame.MainFrame.getTreeModel().reload(frame.MainFrame.getOnline());
							}
						}
					} else
						JOptionPane.showMessageDialog(null, "修改失败");
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
