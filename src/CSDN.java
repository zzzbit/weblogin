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


public class CSDN {

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
			
            httpGet = new HttpGet("https://passport.csdn.net/ajax/accounthandler.ashx?t=log&u=zzzbit&p=izzz0928&remember=0&f=http%3A%2F%2Fwww.csdn.net%2F");
			httpResponse = httpClient.execute(httpGet,localContext);
            httpGet.abort();
            
//            httpPost = new HttpPost("http://www.renren.com/ajax/ShowCaptcha");
//            postDict.add(new BasicNameValuePair("email", "zhangzhizhibit@163.com"));
//            postDict.add(new BasicNameValuePair("_rtk", strTokenValue));
//            httpEntity = new UrlEncodedFormEntity(postDict);
//            httpPost.setEntity(httpEntity);
//            httpResponse = httpClient.execute(httpPost,localContext);
//            httpPost.abort();
            
//            httpPost = new HttpPost("http://www.renren.com/ajaxLogin/login");
//            postDict.add(new BasicNameValuePair("email", "zhangzhizhibit%40163.com"));
//            postDict.add(new BasicNameValuePair("password", "09472d184cc5cacf2e6fb555b792ce7ee13b1349fd3f23c32702937b22684223"));
//            postDict.add(new BasicNameValuePair("icode", cookieStore.getCookies().get(4).toString()));
//            System.out.println(cookieStore.getCookies().get(4).toString());
//            postDict.add(new BasicNameValuePair("origURL", "http://www.renren.com/home"));
//            postDict.add(new BasicNameValuePair("domain", "renren.com"));
//            postDict.add(new BasicNameValuePair("key_id", "1"));
//            postDict.add(new BasicNameValuePair("captcha_type", "web_login"));
//            postDict.add(new BasicNameValuePair("_rtk", strTokenValue));
//            httpEntity = new UrlEncodedFormEntity(postDict);
//            httpPost.setEntity(httpEntity);
//            httpResponse = httpClient.execute(httpPost,localContext);
//			httpEntity = httpResponse.getEntity();
//			content = EntityUtils.toString(httpEntity, "utf-8");
//			System.out.println(content);
//            httpPost.abort();
            
			
//			httpPost = new HttpPost("http://www.renren.com/ajaxLogin/login?1=1&uniqueTimestamp=20131031412522");
//			content = "email=zhangzhizhibit%40163.com&icode=&origURL=http%3A%2F%2Fwww.renren.com%2Fhome&domain=renren.com&key_id=1&captcha_type=web_login&password=09472d184cc5cacf2e6fb555b792ce7ee13b1349fd3f23c32702937b22684223&rkey=d0cf42c2d3d337f9e5d14083f2d52cb2";
//			httpEntity = new ByteArrayEntity(content.getBytes());
//			httpPost.setEntity(httpEntity);
//			httpResponse = httpClient.execute(httpPost,localContext);
//			httpEntity = httpResponse.getEntity();
//			content = EntityUtils.toString(httpEntity, "utf-8");
//			System.out.println(content);
//			httpPost.abort();
            

			httpGet = new HttpGet("http://download.csdn.net/my");
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
