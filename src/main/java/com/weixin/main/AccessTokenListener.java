package com.weixin.main;

import java.io.BufferedReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AccessTokenListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			new Runnable() {
				String APPID = "wx110a685a82054c59";
				String APPSECRET = "4f58d5bc1e01ea2e957e90d6a5c2acb1";
				String urlStr = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID
						+ "&secret=" + APPSECRET;
				URL url = new URL(urlStr);
				URLConnection connection = null;
				BufferedReader reader = null;
				String line = "";
				StringBuffer sb = null;
				int i = 1;

				@Override
				public void run() {
					// try {
					// connection = url.openConnection();
					// connection.connect();
					// // 取得输入流，并使用Reader读取
					// // 设置编码,否则中文乱码
					// reader = new BufferedReader(new
					// InputStreamReader(connection.getInputStream(), "utf-8"));
					// sb = new StringBuffer();
					// while ((line = reader.readLine()) != null) {
					// sb.append(line);
					// }
					// reader.close();
					// System.out.println(sb.toString());
					// } catch (IOException e) {
					// e.printStackTrace();
					// }
//					while (true) {
						try {
							System.out.println("微信AccessToken刷新第" + i + "次！");
							i++;
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
//					}
				}
			}.run();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
