package bit;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.awt.Toolkit;

public class BitLogin {

	private JFrame frame;
	private JTextField txt_username;
	private JTextField txt_password;
	private JLabel state;
	private JButton button_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BitLogin window = new BitLogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BitLogin() {
		initialize();
	}

	private static HttpMethod login(String username,String password) throws IOException {
		String url = "http://10.0.0.55/cgi-bin/do_login";
		PostMethod post = new PostMethod(url);
		post.setRequestHeader("Accept", "*/*");
		post.setRequestHeader("Accept-Charset", "GBK,utf-8;q=0.7,*;q=0.3");
		post.setRequestHeader("Accept-Encoding", "gzip,deflate,sdch");
		post.setRequestHeader("Accept-Language", "zh-CN,zh;q=0.8");
		post.setRequestHeader("Connection", "keep-alive");
		post.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		post.setRequestHeader("Host", "10.0.0.55");
		post.setRequestHeader("Origin", "10.0.0.55");
		post.setRequestHeader("Referer", "http://10.0.0.55/index.html?http://www.baidu.com/");
		post.setRequestHeader(
				"User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.84 Safari/535.11 SE 2.X MetaSr 1.0");
		NameValuePair[] param = { new NameValuePair("username", username),new NameValuePair("password", MD5.Md5_16(password)),new NameValuePair("drop", "0"),new NameValuePair("type", "1"),new NameValuePair("n", "100") };
		post.setRequestBody(param);
		post.releaseConnection();
		return post;
	}
	private static HttpMethod logout(String username,String password) throws IOException {
		String url = "http://10.0.0.55/cgi-bin/force_logout";
		PostMethod post = new PostMethod(url);
		post.setRequestHeader("Accept", "*/*");
		post.setRequestHeader("Accept-Charset", "GBK,utf-8;q=0.7,*;q=0.3");
		post.setRequestHeader("Accept-Encoding", "gzip,deflate,sdch");
		post.setRequestHeader("Accept-Language", "zh-CN,zh;q=0.8");
		post.setRequestHeader("Connection", "keep-alive");
		post.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		post.setRequestHeader("Host", "10.0.0.55");
		post.setRequestHeader("Origin", "10.0.0.55");
		post.setRequestHeader("Referer", "http://10.0.0.55/index.html?http://www.baidu.com/");
		post.setRequestHeader(
				"User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.84 Safari/535.11 SE 2.X MetaSr 1.0");
		NameValuePair[] param = { new NameValuePair("username", username),new NameValuePair("password", password),new NameValuePair("drop", "0"),new NameValuePair("type", "1"),new NameValuePair("n", "1") };
		post.setRequestBody(param);
		post.releaseConnection();
		return post;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("\u767B\u5F55");
		frame.setBounds(100, 100, 293, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u7528\u6237\u540D\uFF1A");
		label.setBounds(29, 49, 54, 15);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u5BC6\u7801\uFF1A");
		label_1.setBounds(29, 95, 54, 15);
		frame.getContentPane().add(label_1);
		
		txt_username = new JTextField();
		txt_username.setBounds(109, 46, 122, 21);
		frame.getContentPane().add(txt_username);
		txt_username.setColumns(10);
		
		txt_password = new JTextField();
		txt_password.setColumns(10);
		txt_password.setBounds(109, 92, 122, 21);
		frame.getContentPane().add(txt_password);
		
		JButton button = new JButton("\u767B\u5F55");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String username;
					String password;
					if (txt_username.getText().equals("")){
						username = "用户名";
						password = "密码";
					}
					else {
						username = txt_username.getText();
						password = txt_password.getText();
					}
					HttpClient httpClient = new HttpClient();
					
					HttpMethod httpMethod = login(username,password);
					httpClient.executeMethod(httpMethod);
					byte[] responseBody = httpMethod.getResponseBody();
					String response = new String(responseBody,"utf-8");
					if (!response.equals("ip_exist_error")){
						System.out.println(response);
						state.setText("���ӳɹ�");
						return;
					}
					
					state.setText("����ʧ��");

				} catch (Exception e1) {
					state.setText("���ִ���");
				}
			}
		});
		button.setBounds(91, 141, 93, 23);
		frame.getContentPane().add(button);
		
		state = new JLabel("");
		state.setBounds(91, 207, 105, 15);
		frame.getContentPane().add(state);
		
		button_1 = new JButton("\u6CE8\u9500");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username;
				String password;
				if (txt_username.getText().equals("")){
					username = "用户名";
					password = "密码";
				}
				else {
					username = txt_username.getText();
					password = txt_password.getText();
				}
				System.out.println(username);
				System.out.println(password);
				HttpClient httpClient = new HttpClient();
				try {
					httpClient.executeMethod(logout(username,password));
				} catch (Exception e1) {

				}
				state.setText("ע��ɹ�");
			}
		});
		button_1.setBounds(91, 174, 93, 23);
		frame.getContentPane().add(button_1);
	}
}
