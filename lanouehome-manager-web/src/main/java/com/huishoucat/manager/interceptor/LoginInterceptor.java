package com.huishoucat.manager.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.huishoucat.manager.pojo.TManager;

/**
 * 管理员登录处理拦截器
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月3日 下午9:15:44
 * @version V1.0
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 前处理，执行handler之前执行此方法。返回 true:放行 false:拦截
		TManager loginManager = (TManager) request.getSession().getAttribute("loginManager");
		if (loginManager == null) {
			request.getRequestDispatcher("/login").forward(request, response);
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// handler执行之后，返回ModeAndView之前

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 完成处理，返回ModelAndView之后。可以再此处理异常

	}

}
