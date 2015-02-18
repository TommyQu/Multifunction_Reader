package mainframeaction;

import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class CkLogin {
	private int isLogin=0;
	public CkLogin()
	{
		String url="http://localhost/reader/cklogin.php";
		PostMethod postMethod=new PostMethod(url);
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		try {
			int statusCode = frame.MainFrame.getHttpClient().executeMethod(postMethod);
			System.out.println(statusCode);
			if (statusCode == HttpStatus.SC_OK) 
			{
				byte[] responseBody=postMethod.getResponseBody();
				String str_response=new String(responseBody);
				System.out.println(str_response);
				if(str_response.equals("已登录"))
					isLogin=1;
				else
					isLogin=0;
			}
		} catch (HttpException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public int getIsLogin() {
		return isLogin;
	}
	public void setIsLogin(int isLogin) {
		this.isLogin = isLogin;
	}
}
