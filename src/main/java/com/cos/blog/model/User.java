package com.cos.blog.model;



import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
		// ORM -> Java(or 다른언어) Object -> 테이블로 매핑해주는 기술
@Entity	// User 클래스가 MySQL에 테이블이 생성된다.
// @DynamicInsert		// insert시에 null인 필드는 제외해준다.
public class User {

	@Id	// primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; // oracle에선 시퀀스 , mysql에서는 auto_increment
	
	@Column(nullable = false , length = 30, unique=true)
	private String username;	// 아이디
	
	@Column(nullable = false , length = 100) // 123456 => 해쉬(비밀번호 암호화)
	private String password;
	
	@Column(nullable = false , length = 50)
	private String email;
	
	// @ColumnDefault("user")
	// RoleType.Enum을 만들어줬고 DB에선 RoleType을 쓸 수 없으니까
	@Enumerated(EnumType.STRING)	// 이와 같이 어노테이션 적용해준다.
	private RoleType role;	// Enum을 쓰는게 좋다. 이유 : data의 도메인을 만들수 있어서
							// 도메인이란 범위를 정해줄 수 있다는 말 (아래 3개 중에 선택하는것처럼)
							// ex) ADMIN , USER
	
						// import java.sql.Timestamp; 이걸로 해야 오류 안나더라
	@CreationTimestamp	// 시간이 자동입력
	private Timestamp createDate;
}
