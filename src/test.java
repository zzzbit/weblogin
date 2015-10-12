import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.Decoder;


public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		try {
//			System.out.println(URLEncoder.encode("Ã’‘®√˜","utf-8"));
//		} catch (UnsupportedEncodingException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		try {
//			System.out.println(URLDecoder.decode("%E9%AA%8C%E8%AF%81%E7%A0%81","utf-8"));
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(System.currentTimeMillis());
		
		try {
//			String filename = "C:\\Users\\zhangzhizhi\\Documents\\Everyone\\≤‹—”∑Ê\\ ”∆µ\\in.srt";
//			String s = "";
//			BufferedReader br = new BufferedReader(new InputStreamReader(
//					new FileInputStream(filename)));
//			OutputStreamWriter w = new OutputStreamWriter(new FileOutputStream(filename.replace(".srt", "_new.srt")),"utf-8");
//			int i = 1;
//			while ((s = br.readLine()) != null) {
//				w.write((i++)+"\n");
//				s = br.readLine();
//				int pos = s.indexOf("-->");
//				String first = s.substring(0, pos);
//				String last = s.substring(pos);
//				
//				String firstpart = first.substring(first.indexOf(":")+1, first.lastIndexOf(":"));
//				String lastpart = last.substring(last.indexOf(":")+1,last.lastIndexOf(":"));
//				
//				String result = first.replace(firstpart, ""+(Integer.parseInt(firstpart) - 44));
//				result += last.replace(lastpart, ""+(Integer.parseInt(lastpart) - 44));
//				w.write(result+"\n");
//				s = br.readLine();
//				w.write(s+"\n");
//				s = br.readLine();
//				w.write(s+"\n");
//				if (!s.equals("")){
//					s = br.readLine();
//					w.write(s+"\n");
//				}
//				w.flush();
//			}
//			br.close();
//			w.close();
            
//			Date date=null;  
//			SimpleDateFormat formatter=new SimpleDateFormat("HH:mm:ss,SSS");  
//			date=formatter.parse("00:44:04,543");  
//			System.out.println(date);  
			System.out.println("http://i.wotula.com/note.png?name="+URLEncoder.encode("’≈÷æ÷«")+"&say=HI");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
