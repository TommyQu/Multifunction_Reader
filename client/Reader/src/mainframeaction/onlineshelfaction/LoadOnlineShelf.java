package mainframeaction.onlineshelfaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

public class LoadOnlineShelf {
	private LinkedList<String> llist=null;
	public LoadOnlineShelf(String loginName)
	{
		String url="http://localhost/reader/LoadOnlineShelf.php";
		PostMethod postMethod=new PostMethod(url);
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		NameValuePair[] loginInformation={ new NameValuePair("username", frame.MainFrame.getLoginName().getText())};
		postMethod.setRequestBody(loginInformation);
		try {
			int statusCode = frame.MainFrame.getHttpClient().executeMethod(postMethod);
			if (statusCode == HttpStatus.SC_OK) 
			{
				byte[] responseBody=postMethod.getResponseBody();
				String str_response=new String(responseBody);
				Gson gson=new Gson();
				llist=gson.fromJson(str_response, new TypeToken<LinkedList<String>>() {}.getType());
				if (llist.size() >= 2) 
				{
					llist.remove(0);
					llist.remove(0);
				}
				
//				for(int i=0;i<llist.size();i++)
//				{
//					IconNode iconNode=new IconNode(new ImageIcon("src/img/BookshelfFont.png"), llist.get(i));
//					iconNode.setFather("online");
//					frame.MainFrame.getTreeModel().insertNodeInto(iconNode, frame.MainFrame.getOnline(), 0);
////					frame.MainFrame.getOnline().add(iconNode);
//				}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
			}
		} catch (HttpException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public LinkedList<String> getLlist() {
		return llist;
	}
	public void setLlist(LinkedList<String> llist) {
		this.llist = llist;
	}
	

}
