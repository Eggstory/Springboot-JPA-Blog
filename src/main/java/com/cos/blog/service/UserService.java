package com.cos.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public void 회원가입(User user) {
		userRepository.save(user);// 트랜잭션 여러개 쓰기 가능 - 서비스의 역할!

	}
	
	@Transactional(readOnly = true)	// Select할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료 (정합성 유지)
	public User 로그인(User user) {
		return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
		// 트랜잭션 여러개 쓰기 가능 - 서비스의 역할!

	}

}