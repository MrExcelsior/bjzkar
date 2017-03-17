package com.bjzkar.application.controller.user.logined;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bjzkar.application.controller.user.UserController;
import com.bjzkar.application.entity.NoteResult;
import com.bjzkar.application.entity.User;
import com.bjzkar.application.service.UserService;

@RestController
public class LoginedUserController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/loadUserInfo.form")
	public NoteResult loadUserInfo(HttpServletRequest request) {
		//System.out.println(request.getSession().getId());
		//Long userId = (Long)request.getSession().getAttribute("userId");
		Long userId = getUserId(request);
		//System.out.println(userId);
		NoteResult result = null;
		if (userId == null) {
			result = new NoteResult();
			result.setStatus("4");
			result.setMsg("用户未登录");
		}
		result = userService.loadUserInfo(userId);
		return result;
	}
	
	@PostMapping("/modify.form")
	public NoteResult modify(HttpServletRequest request, User user) {
		String username = user.getUser_name();
		String userpwd = user.getUser_password();
		NoteResult checkResult = userService.checkLogin(username, userpwd);
		if ("0".equals(checkResult.getStatus())) {
			String newPassword = request.getParameter("new_password");
			if (newPassword != null) {
				user.setUser_password(newPassword);
			}
		}
		NoteResult result = userService.modify(user);
		return result;
	}
	
	@PostMapping("/del.form")
	public NoteResult deleteUser(User user) {
		return userService.deleteUser(user);
	}
	
	@GetMapping("/All.form")
	public List all() {
		return (List) userService.findAll();
	}
	
	private Long getUserId(HttpServletRequest request) {
		return (Long) request.getAttribute(UserController.SESSION_COOKIE_NAME);
	}
}
