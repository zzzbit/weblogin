import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
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
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;


public class RenRenDai {

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
			CookieStore cookieStore = new BasicCookieStore();
			List<NameValuePair> postDict = new ArrayList<NameValuePair>();
			String strTokenValue = "";
			String content;
			Matcher matcher;
			HttpContext localContext = new BasicHttpContext();
			localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
       
            httpPost = new HttpPost("https://www.renrendai.com/j_spring_security_check");
            postDict.add(new BasicNameValuePair("j_username", "用户名"));
            postDict.add(new BasicNameValuePair("j_password", "密码"));
            postDict.add(new BasicNameValuePair("rememberme", "on"));
            httpEntity = new UrlEncodedFormEntity(postDict);
            httpPost.setEntity(httpEntity);
            httpResponse = httpClient.execute(httpPost,localContext);
            httpPost.abort();

			httpGet = new HttpGet("https://www.renrendai.com/account/index.action");
			httpResponse = httpClient.execute(httpGet, localContext);
			httpEntity = httpResponse.getEntity();
			content = EntityUtils.toString(httpEntity, "utf-8");
			add2File(null, content, "utf-8");
			httpGet.abort();
			
			for (Cookie cookie : cookieStore.getCookies()) {
				System.out.println(cookie.toString());
			}

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
