package com.bjzkar.application.aop;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bjzkar.application.controller.user.UserController;
import com.bjzkar.application.entity.NoteResult;

@Component
@Aspect
public class CheckLoginInterceptor {
	
	@Around("within(com.bjzkar.application.controller.user.logined..*)")
	public NoteResult getUserId(ProceedingJoinPoint pjp) throws Throwable {
		NoteResult result = null;
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		if (isLogin(request)) {
			result = (NoteResult)pjp.proceed();
		} else {
			result.setStatus("4");
			result.setMsg("用户未登录");
		}
		return result;
	}
	
	private boolean isLogin (HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		System.out.println("====================cookies"+cookies.length+"===================");
		Long userId = null;
		for (Cookie cookie : cookies) {
			if (UserController.SESSION_COOKIE_NAME.equals(cookie.getName())) {
				userId = Long.parseLong(cookie.getValue());
				request.setAttribute(UserController.SESSION_COOKIE_NAME, userId);
			}
		}
		return userId != null;
	}
}
