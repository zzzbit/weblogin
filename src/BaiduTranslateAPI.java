import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


public class BaiduTranslateAPI {

	DefaultHttpClient httpClient = new DefaultHttpClient();
	HttpGet httpGet;
	HttpPost httpPost;
	HttpResponse httpResponse;
	HttpEntity httpEntity;
	byte[] mybyte = null;
	String strTokenValue = "";
	String result = "";
	String client_id = "clientId";
	String from = "auto";
	String to = "auto";
	/**
	 * @param args
	 */
	public BaiduTranslateAPI(){
		
	}
	
	public BaiduTranslateAPI(String client_id){
		this.client_id = client_id;
	}
	
	public BaiduTranslateAPI(String from,String to){
		this.from = from;
		this.to = to;
	}
	
	public BaiduTranslateAPI(String client_id,String from,String to){
		this.client_id = client_id;
		this.from = from;
		this.to = to;
	}
	
	public String getResult(String input){
		try {
			String src = URLEncoder.encode(input,"utf-8");
			httpGet = new HttpGet("http://openapi.baidu.com/public/2.0/bmt/translate?client_id="+client_id+"&q="+src+"&from="+from+"&to="+to);
			httpResponse = httpClient.execute(httpGet);
			httpEntity = httpResponse.getEntity();
			String content = EntityUtils.toString(httpEntity, "utf-8");
			Matcher matcher = Pattern.compile("\"dst\":\"(?<tokenVal>.*?)\"}").matcher(content);
			
			if (matcher.find()){
            	strTokenValue = matcher.group("tokenVal");
            	String[] sarray = strTokenValue.split("\\\\u");
            	if (sarray.length <= 1){
            		result = strTokenValue;
            	}
            	else {
            		mybyte = new byte[2*sarray.length-2];
            		for (int i = 0; i < sarray.length-1; i++){
            			mybyte[2*i] = (byte)Integer.parseInt(sarray[i+1].substring(0, 2), 16);
            			mybyte[2*i+1] = (byte)Integer.parseInt(sarray[i+1].substring(2, 4), 16);
            		}
            		result = new String(mybyte,"utf-16");
				}
            }
			httpGet.abort();
			return result;
		} catch (Exception e) {
			return null;
		}
	}
	public static void main(String[] args) {
		try {
			String filename = "c:\\in.srt";
			String s = "";
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(filename)));
			OutputStreamWriter w = new OutputStreamWriter(new FileOutputStream(filename.replace(".srt", "_new.srt")));
			BaiduTranslateAPI baiduTranslateAPI = new BaiduTranslateAPI("zh","en");
			while ((s = br.readLine()) != null) {
				if (s.length() <= 0 || s.charAt(0) >= '0' && s.charAt(0) <= '9'){
					w.write(s+"\n");
				}
				else {
					w.write(baiduTranslateAPI.getResult(s)+"\n");
				}
				w.flush();
			}
			br.close();
			w.close();
            
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
