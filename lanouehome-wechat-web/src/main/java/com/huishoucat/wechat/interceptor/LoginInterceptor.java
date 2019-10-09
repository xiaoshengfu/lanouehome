package com.huishoucat.wechat.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.utils.CookieUtils;
import com.huishoucat.manager.pojo.TUser;
import com.huishoucat.sso.service.ITokenService;

/**
 * 用户登录拦截器
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年3月9日 下午9:56:27
 * @version V1.0
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Value("${SSO_USER_URL}")
	private String SSO_USER_URL;

	@Autowired
	private ITokenService tokenService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 从cookie中取token
		String token = CookieUtils.getCookieValue(request, "token");
		// 判断token是否存在
		if (StringUtils.isBlank(token)) {
			request.getRequestDispatcher("/WEB-INF/pages/user_login.jsp").forward(request, response);
			return false;
		}
		// 如果token存在，需要调用sso系统的服务，根据token取用户信息
		HuishoucatResult result = tokenService.getUserByToken(token);
		// 如果取不到，用户登录已经过期，需要登录。
		if (result.getStateCode() != HuishoucatResult.SUCCESS) {
			request.getRequestDispatcher("/WEB-INF/pages/user_login.jsp").forward(request, response);
			// 拦截
			return false;
		}
		// 如果取到用户信息，是登录状态，需要把用户信息写入request。
		TUser user = (TUser) result.getData();
		request.setAttribute("loginUser", user);
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
