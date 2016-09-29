package test.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Douban {
	private String url = "https://movie.douban.com";
	private String key = "https://movie.douban.com/subject/";

	public Document parseUrl(String url) throws IOException {
		return Jsoup.connect(url).timeout(10000).get();
	}
	
	public static void main(String[] args) throws IOException {
		Douban douban = new Douban();
		Document document = douban.parseUrl(douban.url);
		String page = document.toString();
		int i = 0;
		int k = 0;
		Set set = new HashSet<String>();
		while(true){
			i = page.indexOf(douban.key, i);
			k = page.indexOf("/",i+douban.key.length());
			if(i == -1){
				break;
			}
			set.add(page.substring(i+douban.key.length(), k));
			i++;
		}
		System.out.println(set);
		System.out.println(set.size());
	}
}
