package com.cos.blog.controller.api;

//import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;
	
// 아래에 구시대적 로그인방식에서 HttpSession session을 지우고 여기에 써서 DI해줘도됨 /아래꺼 안지우고 여기 DI된거 지워도됨
//	@Autowired
//	private HttpSession session;
	
	@PostMapping("/api/user")									// 제네릭 공부해야하나...
	public ResponseDto<Integer> save(@RequestBody User user) {	// username, password, email
		System.out.println("UserApiController : save 호출됨");
		// 실제로 DB에 insert를 하고 아래에서 return이 되면 되요.
		user.setRole(RoleType.USER);
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);	// 자바오브젝트를 JSON으로 변환해서 리턴
	}

/*	
	//이 방식은 전통방식(구시대적) - 요즘은 스프링 시큐리티를 이용해서 로그인함
	@PostMapping("/api/user/login")
	public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) {
		System.out.println("UserApiController : login호출됨");
		User principal = userService.로그인(user); // principal(접근주체)	//userService에 있는 로그인 클래스 안에 메소드
										// findByUsernameAndPassword에 의해 유저네임,패스워드 받아온 것을 principal에 넣음
		if(principal != null) {			// 즉 principal에는 username과 password 정보가 들어가있음 (로그인할시)
			session.setAttribute("principal", principal);	// sessionScope.principal에 principal 값을 저장하나봄
															// 그후 header.java에 sessionScope.principal
		}													// JSTL 필요  // session 쓰는이유는 로그인 기억을 해야하기때문에
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
*/	
	
	
}
