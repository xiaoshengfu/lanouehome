package com.huishoucat.manager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.manager.pojo.TManager;
import com.huishoucat.ucenter.service.IManagerService;

/**
 * 管理员Controller
 * 
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月3日 下午10:07:11
 * @version V1.1
 */
@Controller
public class ManagerController {

	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;

	@Autowired
	private IManagerService managerService;

	/**
	 * 管理员登录
	 * @param manager
	 * @param rememberMe
	 * @param request
	 * @return
	 */
	@RequestMapping("/huishoucat_manager/login")
	public String managerLogin(TManager manager, Boolean rememberMe, HttpServletRequest request) {
		if (manager.getJobNumber() != null && StringUtils.isNoneBlank(manager.getPassword())) {
			HuishoucatResult huishoucatResult = managerService.managerLogin(manager.getJobNumber(),
					manager.getPassword());
			if (huishoucatResult.getStateCode() == 200) {
				TManager loginManager = (TManager) huishoucatResult.getData();
				// 密码置为null
				loginManager.setPassword(null);
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("loginManager", loginManager);
				httpSession.setAttribute("imageServiceUrl", IMAGE_SERVER_URL);
				return "index";
			}
		}
		return "login";
	}
}
