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


public class BitUnion {

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
			
            httpPost = new HttpPost("http://www.bitunion.org/open_api/bu_logging.php");
            content = "{\"action\":\"login\",\"username\":\""+URLEncoder.encode("��Ԩ��","utf-8")+"\",\"password\":\"密码\"}";
            httpEntity = new ByteArrayEntity(content.getBytes());
			httpPost.setEntity(httpEntity);
            httpResponse = httpClient.execute(httpPost);
            httpEntity = httpResponse.getEntity();
            content = EntityUtils.toString(httpEntity, "gb2312");
            System.out.println(content);
            httpPost.abort();
            cookieStore = httpClient.getCookieStore();
            cookList = cookieStore.getCookies();

           
            httpPost = new HttpPost("http://www.bitunion.org/open_api/bu_forum.php");
            content = "{\"action\":\"forum\",\"username\":\""+URLEncoder.encode("��Ԩ��","utf-8")+"\",\"session\":\""+cookList.get(1).getValue()+"\"}";
            httpEntity = new ByteArrayEntity(content.getBytes());
			httpPost.setEntity(httpEntity);
            HttpContext localContext = new BasicHttpContext();
			localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
            httpResponse = httpClient.execute(httpPost,localContext);
            httpEntity = httpResponse.getEntity();
            content = EntityUtils.toString(httpEntity, "gb2312");
			add2File(null, URLDecoder.decode(content,"utf-8"), "gb2312");
            httpPost.abort();

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
