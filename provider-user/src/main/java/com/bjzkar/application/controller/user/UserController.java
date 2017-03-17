package com.bjzkar.application.controller.user;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bjzkar.application.entity.NoteResult;
import com.bjzkar.application.entity.User;
import com.bjzkar.application.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	public static final String SESSION_COOKIE_NAME = "USER_ID";
	public static final int COOKIE_MAXAGE = 60;
	public static final String COOKIE_PATH = "/";
	public static final String COOKIE_DOMAIN = "localhost";


	
	@PostMapping("/login.form")
	public NoteResult login(HttpServletRequest request, HttpServletResponse response, User user) {
		System.out.println(user.getUser_name());
		String username = user.getUser_name();
		String userpwd = user.getUser_password();
		NoteResult result = userService.checkLogin(username, userpwd);
/*		if ("0".equals(result.getStatus())) {
			Map<String, Object> data = (Map)result.getData();
			//request.getSession().setAttribute("userId", ((User)data.get("user")).getUser_id());
			Cookie cookie = new Cookie("userId", ((User)data.get("user")).getUser_id().toString());
			cookie.setMaxAge(COOKIE_MAXAGE);
			cookie.setPath(COOKIE_PATH);
		    cookie.setDomain(COOKIE_DOMAIN);
			response.addCookie(cookie);
		}*/
		return result;
	}
	
	@PostMapping("/regist.form")
	public NoteResult regist(User user) {
		NoteResult result = userService.regist(user);
		return result;
	}
}