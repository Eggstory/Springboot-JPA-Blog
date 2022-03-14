package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.cos.blog.model.User;

// DAO
// 자동으로 bean등록이 된다
// @Repository // 생략가능
public interface UserRepository extends JpaRepository<User, Integer> {	// User테이블이 관리하는 레포지터리라는 뜻

}
