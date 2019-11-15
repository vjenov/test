package com.catdog.web.pxy;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Consumer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.catdog.web.enums.Path;
import com.catdog.web.tx.TxController;
import com.catdog.web.util.Printer;

@Component("crawler")
public class CrawlingProxy extends Proxy{
	@Autowired BugsProxy bugs;
	@Autowired CGVProxy cgv;
	@Autowired DictionProxy dic;
	@Autowired TxController txcon;
	@Autowired Printer p;
	@Autowired Box<String> box;
	@Autowired 
	public Box<String> choose(Map<?, ?> paramMap) {
		switch(string(paramMap.get("site"))) {
		case "벅스" :
			p.accept(Path.BUGS.toString());
			box = bugs.crawling(Path.BUGS.toString());
			break;
		case "CGV" :
			p.accept(Path.CGV.toString());
			box = cgv.crawling(Path.CGV.toString());
		case "네이버영어사전" :
			p.accept(Path.DICTION.toString());
			box = dic.crawling(Path.DICTION.toString());
		default :
			crawling("http://"+paramMap.get("site")+"/");
		}
	    return box;
	}
	public Box<String> crawling(String url) {
		p.accept("넘어온 URL \n"+url);
		try {
			if(url.equals("https://music.bugs.co.kr/chart")) {
				ArrayList<String> box = new ArrayList<>();
				Document rawData = Jsoup.connect(url).timeout(10*1000).get();
				Elements ranking = rawData.select("div[class=ranking]");
				Elements title = rawData.select("p[class=title]");
				Elements artist = rawData.select("p[class=artist]");
				for(Element e : ranking) {
					box.add(e.text() + ":::::");
				}
				for(Element e : title) {
					box.add(e.text() + ":::::");
				}
				for(Element e : artist) {
					box.add(e.text() + ":::::");
				}
				System.out.println(box);
			}else if(url.equals("http://www.cgv.co.kr/movies/?lt=3")) {
				Document rawData = Jsoup.connect(url).timeout(10*1000).get();
				Elements ranking = rawData.select("strong[class=rank]");
				Elements title = rawData.select("strong[class=title]");
				for(Element e : ranking) {
					box.add(e.text() + ":::::");
				}
				for(Element e : title) {
					box.add(e.text() + ":::::");
				}
				System.out.println(box);
			}else if(url.equals("https://endic.naver.com/?sLn=kr")) {
				Document rawData = Jsoup.connect(url).timeout(10*1000).get();
				Elements word = rawData.select("a[class=word_link]");
				Elements mean = rawData.select("div[class=txt_trans]");
				for(Element e : word) {
					box.add(e.text() + ":::::");
				}
				for(Element e : mean) {
					box.add(e.text() + ":::::");
				}
			}
		}catch(Exception e2) {
			e2.printStackTrace();
		}
		return box;
	}
}
