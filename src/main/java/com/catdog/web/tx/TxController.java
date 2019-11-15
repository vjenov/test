package com.catdog.web.tx;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catdog.web.pxy.CrawlingProxy;
import com.catdog.web.pxy.PageProxy;
import com.catdog.web.pxy.Proxy;
import com.catdog.web.pxy.Trunk;
import com.catdog.web.util.Printer;

@RestController
@RequestMapping("/tx")
@Transactional
public class TxController {
	@Autowired Proxy proxy;
	@Autowired PageProxy pager;
	@Autowired TxService txService;
	@Autowired Printer printer;
	@Autowired Trunk<String> trunk;
	@Autowired CrawlingProxy crawler;
	@GetMapping("/crawling/{site}")
	public void bringUrl(@PathVariable String site) {
		printer.accept("site 이름 : " + site);
		trunk.put(Arrays.asList("site"), Arrays.asList(site));
		txService.crawling(trunk.get());
	}
	@GetMapping("/page/{pageNo}/size/{pageSize}")
	public Map<?,?> list(@PathVariable String pageNo,
			@PathVariable String pageSize) {
		System.out.println("넘어온 페이지 넘버: "+pageNo);
		pager.setPageNum(proxy.integer(pageNo));
		pager.setPageSize(proxy.integer(pageSize));
		pager.setTotalCount(100);
		pager.paging();
//		trunk.put(Arrays.asList("data","pxy"), Arrays.asList(crawler.crawling(pageSize),pager));
		return trunk.get();
	}
}