package mainframeaction.onlineshelfaction;

import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import domain.IconNode;

public class DelOnlineShelf {
	public DelOnlineShelf(String nodeText) {
		// TODO Auto-generated constructor stub
		if(nodeText.equals("默认书架"))
			JOptionPane.showMessageDialog(null, "无法删除默认书架");
		else
		{
		String url="http://localhost/reader/DelOnlineShelf.php";
		PostMethod postMethod=new PostMethod(url);
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		NameValuePair[] editshelfInformation={ new NameValuePair("username", frame.MainFrame.getLoginName().getText()),
				new NameValuePair("nodetext", nodeText)};
		postMethod.setRequestBody(editshelfInformation);
		try {
			int statusCode = frame.MainFrame.getHttpClient().executeMethod(postMethod);
			if (statusCode == HttpStatus.SC_OK) 
			{
				byte[] responseBody=postMethod.getResponseBody();
				String str_response=new String(responseBody);
				System.out.println(str_response);
				if(str_response.equals("删除成功"))
				{
					JOptionPane.showMessageDialog(null, "删除成功");
					for(int i=0;i<frame.MainFrame.getOnline().getChildCount();i++) //找到删除的节点，从MainFrame删除
					{
						if(nodeText.equals(((IconNode)frame.MainFrame.getOnline().getChildAt(i)).getText()))
						{
							frame.MainFrame.getOnline().remove(i);
							frame.MainFrame.getTreeModel().reload(frame.MainFrame.getOnline());
							frame.MainFrame.getjPanel_book().removeAll();
							frame.MainFrame.getjPanel_book().repaint();
						}
					}
				}
				else
					JOptionPane.showMessageDialog(null, "删除失败");
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
