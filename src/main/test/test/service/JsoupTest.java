package test.service;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class JsoupTest {

	private String url = "http://www.xiuren.org";

	public Document parseUrl(String url) throws IOException {
		return Jsoup.connect(url).timeout(10000).get();
	}

	public List<String> getMenuHref(Document document) {
		Elements elements = document.getElementsByClass("in").get(0).getElementsByTag("a");
		List<String> hrefs = new ArrayList<String>();
		for (Element element : elements) {
			hrefs.add(element.attr("href"));
		}
		return hrefs;
	}

	public List<String> getMainHref(Document document) {
		Elements elements = document.getElementsByClass("content");
		List<String> hrefs = new ArrayList<String>();
		for (Element element : elements) {
			Elements as = element.getElementsByTag("a");
			hrefs.add(as.get(0).attr("href"));
		}
		return hrefs;
	}

	public List<String> getImageHref(Document document) {
		Elements elements = document.getElementsByClass("post").get(0).getElementsByTag("a");
		List<String> hrefs = new ArrayList<String>();
		for (Element element : elements) {
			if (StringUtils.isBlank(element.attr("target"))) {
				hrefs.add(element.attr("href"));
			}
		}
		return hrefs;
	}

	public void downloadImage(String imageUrl, String path) throws Exception {
		DataInputStream dataInputStream = null;
		FileOutputStream fileOutputStream = null;
		try {
			long startTime = System.currentTimeMillis();
			URL url = new URL(imageUrl);
			String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
			long freeSpace = new File(path.substring(0, 3)).getFreeSpace() / 1024 / 1024;
			if (freeSpace < 100) {
				throw new Exception("磁盘小于100MB!");
			}
			File pathFile = new File(path);
			if(!pathFile.exists()){
				pathFile.mkdirs();
			}
			File file = new File(path + fileName);
			if (file.exists()) {
				System.out.println(file.getName() + "已存在！");
				return;
			}
			dataInputStream = new DataInputStream(url.openStream());
			fileOutputStream = new FileOutputStream(file);

			byte[] buffer = new byte[1024];
			int length;

			while ((length = dataInputStream.read(buffer)) > 0) {
				fileOutputStream.write(buffer, 0, length);
			}
			long endTime = System.currentTimeMillis();
			System.out.println("下载完成！" + file.length() / 1024 + "kb,用时:" + (endTime - startTime) + "ms - " + file.getName());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (dataInputStream != null) {
				try {
					dataInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		String path = "E:/SSH/data/";
		JsoupTest jsoupTest = new JsoupTest();
		List<String> menuHrefs = jsoupTest.getMenuHref(jsoupTest.parseUrl(jsoupTest.url));
		String menuFile, mainFile;
		for (String menuHref : menuHrefs) {
			menuFile = path + menuHref.substring(menuHref.lastIndexOf("/")+1, menuHref.lastIndexOf(".")) + "/";
			List<String> mainHrefs = jsoupTest.getMainHref(jsoupTest.parseUrl(menuHref));
			for (String mainHref : mainHrefs) {
				mainFile = menuFile + mainHref.substring(mainHref.lastIndexOf("/")+1, mainHref.lastIndexOf(".")) + "/";
				List<String> imageHrefs = jsoupTest.getImageHref(jsoupTest.parseUrl(mainHref));
				for (String imageHref : imageHrefs) {
					jsoupTest.downloadImage(imageHref, mainFile);
					System.out.println();
				}
			}
		}

	}

}
