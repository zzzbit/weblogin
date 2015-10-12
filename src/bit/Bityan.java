package bit;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class Bityan {

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
			String content = "";

			httpPost = new HttpPost("http://bityan.bitnp.com/admins/login.php");
			postDict.add(new BasicNameValuePair("userid", "用户名"));
			postDict.add(new BasicNameValuePair("pwd", "密码"));
			httpEntity = new UrlEncodedFormEntity(postDict);
			httpPost.setEntity(httpEntity);
			httpResponse = httpClient.execute(httpPost);
			cookieStore = httpClient.getCookieStore();
			cookList = cookieStore.getCookies();
			httpPost.abort();

			String s = null;
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream("C:\\handlehyl.txt"), "gb2312"));
			while ((s = br.readLine()) != null) {
				content += s+"\n";
			}
			br.close();
			
			httpPost = new HttpPost("http://bityan.bitnp.com/admins/handle.php");
			httpPost.setHeader("Accept", "text/html, application/xhtml+xml, */*");
			httpPost.setHeader("Referer", "http://bityan.bitnp.com/admins/article.php?type=add");
			httpPost.setHeader("Accept-Language", "zh-CN");
			httpPost.setHeader("User-Agent","Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0)");
			httpPost.setHeader("Host", "bityan.bitnp.com");
			httpPost.setHeader("Proxy-Connection", "Keep-Alive");
			httpPost.setHeader("DNT", "1");
			httpPost.setHeader("Content-Type", "multipart/form-data; boundary=---------------------------7dd3b4251b0994");
			httpPost.setHeader("Cookie", "PHPSESSID=gshj368rvn2cr5r5ottd5o4ns2");
			httpEntity = new ByteArrayEntity(content.getBytes("gb2312"));
			httpPost.setEntity(httpEntity);
			httpResponse = httpClient.execute(httpPost);
			httpPost.abort();
			
			httpGet = new HttpGet("http://bityan.bitnp.com/admins/listarticle.php");
			httpPost.setHeader("Accept", "text/html, application/xhtml+xml, */*");
			httpPost.setHeader("Referer", "http://bityan.bitnp.com/admins/handle.php?actions=delarticle&id=655");
			httpPost.setHeader("Accept-Language", "zh-CN");
			httpPost.setHeader("User-Agent","Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0)");
			httpPost.setHeader("Host", "bityan.bitnp.com");
			httpPost.setHeader("Proxy-Connection", "Keep-Alive");
			httpPost.setHeader("DNT", "1");
			//httpGet.setHeader("Cookie", "PHPSESSID="+cookList.get(0).getValue());
			httpPost.setHeader("Cookie", "PHPSESSID=gshj368rvn2cr5r5ottd5o4ns2");
			httpResponse = httpClient.execute(httpGet);
			httpEntity = httpResponse.getEntity();
            content = EntityUtils.toString(httpEntity, "gb2312");
			add2File(null, content, null);
			httpGet.abort();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Bityan() {
		try {

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void setProxy(String ip, String port) {
		if (ip == null) {
			ip = "10.108.12.75";
		}
		if (port == null) {
			port = "8080";
		}
		System.setProperty("proxySet", "true");
		System.setProperty("http.proxyHost", ip);
		System.setProperty("http.proxyPort", port);
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
