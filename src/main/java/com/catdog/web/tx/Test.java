package com.catdog.web.tx;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			try {
				ArrayList<String> box = new ArrayList<>();
					Document rawData = Jsoup.connect("https://endic.naver.com/?sLn=kr").timeout(10*1000).get();
					Elements word = rawData.select("a [class=word_link]");
					Elements mean = rawData.select("div[class=txt_trans]");
					for(Element e : word) {
						box.add(e.text() + ":::::");
					}
					for(Element e : mean) {
						box.add(e.text() + ":::::");
					}
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
}
