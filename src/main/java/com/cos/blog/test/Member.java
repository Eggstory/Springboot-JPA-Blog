package com.cos.blog.test;

import lombok.Builder;
//import lombok.AllArgsConstructor;
import lombok.Data;
//import lombok.RequiredArgsConstructor;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor	// 전체 생성자 생성 (source -> generate constructor using fields 랑 같음)
//@RequiredArgsConstructor	// final붙은 애들 생성자 생성
@NoArgsConstructor	// 빈 생성자 생성
public class Member {

	//final 붙이는 이유는 불변성 유지때문에 (final 쓸때 @RequiredArgsConstructor 쓰는듯)
	private int id;
	private String username;
	private String password;
	private String email;
	
	@Builder
	public Member(int id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	
	
	
}
