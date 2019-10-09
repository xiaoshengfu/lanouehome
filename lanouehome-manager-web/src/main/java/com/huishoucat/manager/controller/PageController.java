package com.huishoucat.manager.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转Controller
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月3日 下午8:57:09
 * @version V1.0
 */
@Controller
public class PageController {
	
	@RequestMapping("/")
	public String showIndex(HttpServletRequest request) {
		return "index";
	}
	
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page) {
		return page;
	}
}
