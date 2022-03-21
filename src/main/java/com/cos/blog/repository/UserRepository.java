package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;

// DAO
// 자동으로 bean등록이 된다
// @Repository // 생략가능
public interface UserRepository extends JpaRepository<User, Integer> {	// User테이블이 관리하는 레포지터리라는 뜻

	// JPA Naming 전략
	// SELECT * FROM user WHERE username = ? AND password = ?;
	// User findByUsernameAndPassword(String username, String password);	// 구시대적 로그인방식에 사용 , 그러니 주석
	
	//위와 똑같은 방식이지만 잘 안쓴다. 일부 코드에서 오작동이 생길수 있어서
//	@Query(value = "SELECT * FROM user WHERE username = ?1 AND passowrd = ?2",nativeQuery = true)
//	User login(String username, String password);
	
	// SELECT * FROM user WHERE username = 1?;
	Optional<User> findByUsername(String username);		// findBy~~ 규칙임
	
	
	
	
	
}
