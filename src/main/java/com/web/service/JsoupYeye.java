package com.web.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.web.model.ListEntry;

@Service
public class JsoupYeye {

	private String url = "http://www.yeyezy.info";

	private String searchUrl = url + "/search.asp?searchtype=-1&searchword=";

	private List<ListEntry> listEntrys;

	private int all = 0;

	public Document parseUrl(String url) throws IOException {
		return Jsoup.connect(url).timeout(10000).get();
	}

	public void parseListElement(Document doc) {
		Element content = doc.getElementsByClass("cont").last();
		Elements list = content.getElementsByClass("lit");
		Elements pagebox = content.getElementsByClass("pagebox");

		if (pagebox.select("span").first() != null) {
			// 全部数量
			String allStr = pagebox.select("span").first().html();
			all = Integer.parseInt(allStr.substring(allStr.indexOf("共") + 1, allStr.indexOf("条")));
		}

		// 实例化list对象
		Elements dls = list.get(0).getElementsByTag("dl");
		listEntrys = new ArrayList<ListEntry>();
		for (Element dl : dls) {
			ListEntry entry = new ListEntry();
			entry.setImages(url + dl.select("dt").select("img").attr("src"));
			entry.setUrl(url + dl.select("dt").select("a").attr("href"));
			entry.setTitle(dl.select("dd").get(0).select("a").html());
			entry.setCountry(dl.select("dd").get(2).html());
			entry.setSource(dl.select("dd").get(3).html());
			entry.setDate(dl.select("dd").get(4).html());
			listEntrys.add(entry);
		}
	}

	public String parseInfoPage(String url) throws IOException {
		String firstHtml = parseUrl(url).getElementsByClass("tongyi").select("h4").first().html();
		String finalUrl = "";
		if (firstHtml.contains("jj")) {
			finalUrl = parseUrl(url).getElementsByClass("tongyi").select("a").get(1).attr("href");
		} else {
			finalUrl = parseUrl(url).getElementsByClass("tongyi").select("a").get(0).attr("href");
		}
		return this.url + finalUrl;
	}

	public int getAllCount() {
		return all;
	}

	public List<ListEntry> getList(String key, int page) throws IOException {
		key = java.net.URLEncoder.encode(key, "GBK");
		parseListElement(parseUrl(searchUrl + key + "&page=" + page));
		return listEntrys;
	}

	public List<String> getPagination(int allCount, int currentPage) {
		List<String> pagination = new ArrayList<String>();
		if (allCount < 8) {
			for (int i = 1; i <= allCount; i++) {
				if (currentPage == i) {
					pagination.add("<li>" + i + "</li>");
				} else {
					pagination.add("<li><a href='#'>" + i + "</a></li>");
				}
			}
		} else {
			if((currentPage-2)>0){
				
			}
		}
		return pagination;
	}

	public void end() {
		listEntrys = null;
		all = 0;
	}

}
