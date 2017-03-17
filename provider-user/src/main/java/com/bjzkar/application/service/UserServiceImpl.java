package com.bjzkar.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bjzkar.application.dao.UserRepository;
import com.bjzkar.application.entity.NoteResult;
import com.bjzkar.application.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component("userService")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;

	public NoteResult checkLogin(String username, String userpwd) {
		NoteResult result = new NoteResult();
		User user = checkExist(username);
		if (user == null) {
			result.setStatus("1");
			result.setMsg("用户名不存在");
			return result;
		}
		if (userpwd.equals(user.getUser_password())) {
			String tokenId = UUID.randomUUID().toString();
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("tokenId", tokenId);
			data.put("user", user);
			result.setStatus("0");
			result.setMsg("成功");
			result.setData(data);
		} else {
			result.setStatus("3");
			result.setMsg("密码错误");
		}
		return result;
	}

	public NoteResult checkUsername(String username) {
		NoteResult result = new NoteResult();
		User user = checkExist(username);
		if (user != null) {
			result.setStatus("2");
			result.setMsg("用户已存在");
			result.setData(user);
		} else {
			result.setStatus("1");
			result.setMsg("用户名不存在");
		}
		return result;
	}
	
	public NoteResult regist(User user) {
		NoteResult result = new NoteResult();
	    User check = checkExist(user.getUser_name());
		if (check != null) {
			result.setStatus("2");
			result.setMsg("用户已存在");
			return result;
		}
		User newUser = saveUser(user);
		result.setStatus("0");
		result.setMsg("成功");
		result.setData(newUser);
		return result;
	}
	
	public NoteResult modify(User user) {
		NoteResult result = new NoteResult();
		User newUser = saveUser(user);
		result.setStatus("0");
		result.setMsg("成功");
		result.setData(newUser);
		return result;
	}
	
	public NoteResult loadUserInfo(Long userId) {
		NoteResult result = new NoteResult();
		User user = userRepository.findOne(userId);
		if (user != null) {
			result.setStatus("0");
			result.setMsg("成功");
			result.setData(user);
		} else {
			result.setStatus("1");
			result.setMsg("用户名不存在");
		}
		return result;
	}
	private User saveUser(User user) {
		return userRepository.save(user);

	}
	
	private User checkExist(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List findAll() {	
		return userRepository.findAll();
	}

	@Override
	public NoteResult deleteUser(User user) {
		NoteResult result = new NoteResult();
		userRepository.delete(user);
		result.setStatus("0");
		result.setMsg("成功");
		return result;
	}
}
