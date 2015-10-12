package bit;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;


public class BitJwc {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet;
			HttpPost httpPost;
			HttpResponse httpResponse;
			HttpEntity httpEntity;
			CookieStore cookieStore;
			List<Cookie> cookList;
			List<NameValuePair> postDict = new ArrayList<NameValuePair>();
			String strTokenValue = "";
			String content;
			
            httpPost = new HttpPost("http://grdms.bit.edu.cn/yjs/login.do");
            //postDict.add(new BasicNameValuePair("loginType", "0"));
            postDict.add(new BasicNameValuePair("j_username", "用户名"));
            postDict.add(new BasicNameValuePair("j_password", "密码"));
            httpEntity = new UrlEncodedFormEntity(postDict);
            httpPost.setEntity(httpEntity);
            httpResponse = httpClient.execute(httpPost);
            httpPost.abort();
            cookieStore = httpClient.getCookieStore();
            cookList = cookieStore.getCookies();
            for (Cookie cookie : cookList) {
				System.out.println(cookie.toString());
			}

//			httpGet = new HttpGet("http://grdms.bit.edu.cn/yjs/yanyuan/message.do?method=remindMessage&t="+System.currentTimeMillis());
			HttpContext localContext = new BasicHttpContext();
//			localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
//			httpResponse = httpClient.execute(httpGet,localContext);
//			httpEntity = httpResponse.getEntity();   
//			content = EntityUtils.toString(httpEntity, "gb2312");
//			add2File(null, content, "gb2312");
//            httpGet.abort();
            
            httpGet = new HttpGet("http://grdms.bit.edu.cn/yjs/yanyuan/py/pychengji.do?method=enterChaxun");
			localContext = new BasicHttpContext();
			localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
			httpResponse = httpClient.execute(httpGet,localContext);
			httpEntity = httpResponse.getEntity();   
			content = EntityUtils.toString(httpEntity, "gb2312");
			add2File(null, content, "gb2312");
			
            httpGet.abort();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void add2File(String path, String content, String charset) {
		try {
			if (path == null) {
				path = "chenji.html";
			}
			if (charset == null) {
				charset = "gb2312";
			}
			OutputStreamWriter w = new OutputStreamWriter(new FileOutputStream(
					path), charset);
			w.write(content);
			w.flush();
			w.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
