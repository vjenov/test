package com.catdog.web.pxy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import com.catdog.web.util.Printer;

public class DictionProxy {
	@Autowired Printer p;
	@Autowired Box<String> box;
	public Box<String> crawling(String url) {
		p.accept("넘어온 URL \n"+url);
		try {
			Document rawData = Jsoup.connect(url).timeout(10*1000).get();
			Elements title = rawData.select("");
			for(Element e : title) {
				box.add(e.text() + "::::: \n");
			}
			System.out.println(box);
		}catch(Exception e2) {
			e2.printStackTrace();
		}
		return box;
	}
}
