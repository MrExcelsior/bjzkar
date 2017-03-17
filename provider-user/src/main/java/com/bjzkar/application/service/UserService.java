package com.bjzkar.application.service;

import java.util.List;

import com.bjzkar.application.entity.NoteResult;
import com.bjzkar.application.entity.User;

public interface UserService {
	public NoteResult checkLogin(String username, String userpwd);
	public NoteResult regist(User user);
	public NoteResult modify(User user);
	public NoteResult loadUserInfo(Long userId);
	public List findAll();
	public NoteResult deleteUser(User user);
}
