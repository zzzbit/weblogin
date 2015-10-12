package bit;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

public class BitEmail {

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
			
            httpPost = new HttpPost("http://bit.edu.cn/user/?q=login.do");
            postDict.add(new BasicNameValuePair("user", "用户名"));
            postDict.add(new BasicNameValuePair("password", "密码"));
            postDict.add(new BasicNameValuePair("auth_code", "%E9%AA%8C%E8%AF%81%E7%A0%81"));
            httpEntity = new UrlEncodedFormEntity(postDict);
            httpPost.setEntity(httpEntity);
            httpResponse = httpClient.execute(httpPost);
            httpPost.abort();
            cookieStore = httpClient.getCookieStore();
            cookList = cookieStore.getCookies();

           
            httpPost = new HttpPost("http://bit.edu.cn/?q=base&module=listmail&_data=listmail%3D%3Ffid%3D1");
            postDict.add(new BasicNameValuePair("user", "用户名"));
            postDict.add(new BasicNameValuePair("password", "密码"));
            postDict.add(new BasicNameValuePair("auth_code", "%E9%AA%8C%E8%AF%81%E7%A0%81"));
            httpEntity = new UrlEncodedFormEntity(postDict);
			httpPost.setEntity(httpEntity);
            HttpContext localContext = new BasicHttpContext();
			localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
            httpResponse = httpClient.execute(httpPost,localContext);
            httpEntity = httpResponse.getEntity();
            content = EntityUtils.toString(httpEntity, "utf-8");
			add2File(null, content, "utf-8");
            httpPost.abort();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void add2File(String path, String content, String charset) {
		try {
			if (path == null) {
				path = "html\\Email.html";
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
