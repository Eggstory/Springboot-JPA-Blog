package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 사용자가 요청 -> 응답(HTML 파일)
// @Controller


// 사용자가 요청 -> 응답(Data)
@RestController
public class HttpControllerTest {
	
	private static final String TAG = "HttpControllerTest : ";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
//		Member m = new Member(1,"ssar","1234","email");		// 기본방식 (순서대로)
// 위에꺼 대신 빌더패턴 사용할떄는 아래처럼
// 빌더패턴의 장점은 아래처럼 username , password , email 순서 상관없이 배치해도됨
		Member m = Member.builder().username("ssar").password("1234").email("ssar@nate.com").build();
		System.out.println(TAG+ "getter : " +m.getId());
		m.setId(5000);
		System.out.println(TAG+ "setter : " +m.getId());
		return "lombok test 완료";
	}
	
/*
	@GetMapping("/http/get")
	public String getTest(@RequestParam int id, @RequestParam String username) {
		return "get 요청 : " + id + " , " + username;
	}
*/	
	//인터넷 브라우저 요청은 무조건 get요청밖에 할 수 없다
	//위랑 결과 똑같음
	// http://localhost:8888/http/post	(select)
	@GetMapping("/http/get")
	public String getTest(Member m) {
		return "get 요청 : " +m.getId()+ " , " +m.getUsername()+ " , " +m.getPassword()+ " , " +m.getEmail();
	}
	
	// 포스트맨에서 raw방식으로 할떄 썼음
	// http://localhost:8888/http/post	(insert)
	@PostMapping("/http/post") //text/plain (평문), application/json (json)
	public String postTest(@RequestBody Member m) {	// MessageConverter가 자동으로 매핑해줌
		return "post 요청 : " +m.getId()+ " , " +m.getUsername()+ " , " +m.getPassword()+ " , " +m.getEmail();
	}
	
	// http://localhost:8888/http/put	(update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청 : " +m.getId()+ " , " +m.getUsername()+ " , " +m.getPassword()+ " , " +m.getEmail();
	}
	
	// http://localhost:8888/http/delete	(delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}

}
