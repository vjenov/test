package com.catdog.web.tx;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catdog.web.pxy.Box;
import com.catdog.web.pxy.CrawlingProxy;

@Service
public class TxService {
	@Autowired CrawlingProxy crawler;
	@Autowired Box<String> box;
	//@Autowired List<String> txServicelist;
	
	public Box<String> crawling(Map<?,?> paramMap){
		return crawler.choose(paramMap);
	}
}