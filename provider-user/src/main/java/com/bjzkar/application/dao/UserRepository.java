package com.bjzkar.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bjzkar.application.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	@Query(value = "select u from cloud_user u where u.user_name = :user_name")
	public User findByUsername(@Param("user_name")String username);
	
	@Modifying @Query(value = "update cloud_user u set u.user_password = :user_password")
	public int updatePassword(@Param("user_password")String password);
}
