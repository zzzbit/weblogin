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

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.ws.Response;

public class Baidu {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			System.setProperty("proxySet", "true");
			System.setProperty("http.proxyHost", "10.4.60.164");
			System.setProperty("http.proxyPort", "8080");
			
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

			httpGet = new HttpGet("http://www.baidu.com/");
			httpResponse = httpClient.execute(httpGet);
			cookieStore =httpClient.getCookieStore(); 
			cookList = cookieStore.getCookies();
			httpGet.abort();
			
			httpGet = new HttpGet("https://passport.baidu.com/v2/api/?getapi&class=login&tpl=mn&tangram=true");
			httpGet.setHeader(CoreProtocolPNames.USER_AGENT, "");
			httpGet.setHeader(ClientPNames.HANDLE_REDIRECTS,"true");
			HttpContext localContext = new BasicHttpContext();
			localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
			httpResponse = httpClient.execute(httpGet,localContext);
			cookList = cookieStore.getCookies();
			for (Cookie cookie : cookList) {
				System.out.println(cookie.toString());
			}
			httpEntity = httpResponse.getEntity();   
            if (httpEntity != null) {
            	content = EntityUtils.toString(httpEntity, "UTF-8");
                Matcher matcher = Pattern.compile("bdPass\\.api\\.params\\.login_token='(?<tokenVal>\\w+)';").matcher(content);
                if (matcher.find()){
                	strTokenValue = matcher.group("tokenVal");
                	System.out.println("Response content:" + matcher.group("tokenVal"));
                }
            }
            httpGet.abort();
            
            httpPost = new HttpPost("https://passport.baidu.com/v2/api/?login");
            postDict.add(new BasicNameValuePair("charset", "utf-8"));
            postDict.add(new BasicNameValuePair("token", strTokenValue));
            postDict.add(new BasicNameValuePair("isPhone", "false"));
            postDict.add(new BasicNameValuePair("index", "0"));
            postDict.add(new BasicNameValuePair("staticpage", "http://www.baidu.com/cache/user/html/jump.html"));
            postDict.add(new BasicNameValuePair("loginType", "1"));
            postDict.add(new BasicNameValuePair("tpl", "mn"));
            postDict.add(new BasicNameValuePair("callback", "parent.bdPass.api.login._postCallback"));
            postDict.add(new BasicNameValuePair("username", "用户名"));
            postDict.add(new BasicNameValuePair("password", "密码"));
            postDict.add(new BasicNameValuePair("verifycode", ""));
            postDict.add(new BasicNameValuePair("mem_pass", "on"));
            httpEntity = new UrlEncodedFormEntity(postDict);
            httpPost.setEntity(httpEntity);
            httpResponse = httpClient.execute(httpPost);
            httpPost.abort();

            httpGet = new HttpGet("http://i.baidu.com/history/list");
			httpResponse = httpClient.execute(httpGet,localContext);
			httpEntity = httpResponse.getEntity();   
			content = EntityUtils.toString(httpEntity, "utf-8");
			add2File(null, content, "utf-8");
            httpGet.abort();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void add2File(String path, String content, String charset) {
		try {
			if (path == null) {
				path = "C:\\test.html";
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
