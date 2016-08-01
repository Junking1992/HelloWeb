package test.service;

import java.io.IOException;
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
		return Jsoup.connect(url).timeout(5000).get();
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

	public static void main(String[] args) throws IOException {
	}

}
