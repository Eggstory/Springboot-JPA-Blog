package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

// html이 아니라 data를 리턴해주는 Controller
@RestController
public class DummyControllerTest {
	
	@Autowired	// 의존성 주입
	private UserRepository userRepository;
	
	// http://localhost:8888/dummy/user
	@GetMapping("/dummy/users")
	public List<User> list() {
		return userRepository.findAll();
	}
	
	// 한페이지당 2건의 데이터를 리턴받아 볼 예정
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2, sort = "id", direction = Sort.Direction.DESC)
	Pageable pageable) {
//		List<User> users = userRepository.findAll(pageable).getContent();		// 이게 더 쉬운거 같은데 왜 아래꺼 쓰지?
		
		Page<User> pagingUser = userRepository.findAll(pageable);
//		if(pagingUser.isLast()) { }			// 첫번째 데이터인가 분기점을 만들수도 있다 (이렇게 if 사용함으로써)
		List<User> users = pagingUser.getContent();
		return users;
		
	}
	
	// {id} 주소로 파라미터를 전달 받을 수 있음
	// http://localhost:8888/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// ~.findById() 타고 들어가면 타입이 Optional임 그이유는 아래에
		// user/4을 찾으면 내가 데이터베이스에서 못찾아오게 되면 user가 null이 될 것 아냐?
		// 그럼 return null이 리턴이 되자나.. 그럼 프로그램에 문제가 있지 않겠니?
		// optional로 너의 User 객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return해
		
/*		
 		// 방법 1
		User user = userRepository.findById(id).get();
		// id값이 절대 null이 아닐때 사용가능 (불안함)
*/		
		
/*		
		// 방법 2
		User user = userRepository.findById(id).orElseGet(new Supplier<User>() { 
			@Override
			public User get() {
				// TODO Auto-generated method stub
				return new User();
			}
		});
			// 조건에 맞는 id번호가 있으면 findById()까지하고 아래에 return으로 넘어감
			// 조건에 맞지 id번호를 호출하면 .orElseGet~~ 부분이 실행되어 return new User(); = 빈 User객체를 만들어 리턴해준다.
			// 결과에서 오류는 안나지만 id : 0 , username : null , password : null , email : null 이런식으로 나옴
*/		
		
		// 방법 3 (제일 좋은건가봄)
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다. id : " +id);
				// 실행해보면 "해당 유저는 없습니다 id : ~ " + 에러로그가 뜸
				// 나중에 AOP를 사용하면 결과 화면을 가로채 다른 화면을 집어넣을수 있음
			}
		});

/*		
		// 방법 4 (= 방법 3 + 람다식)  :  훨씬 간단해지는데 람다식을 모르겠네 ㅠㅠ
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("해당 사용자는 없습니다.");
		});
*/
		
		// 요청 : 웹브라우저
		// user 객체 = 자바 오브젝트
		// 변환(웹브라우저로 이해할수 있는 데이터) -> Json
		// 스프링부트 = MessageConverter 응답시에 자동작동
		// 만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서
		// user 오브젝트를 json으로 변환해서 브라우저에게 던져준다.
		return user;
	}
	
	// http://localhost:8888/dummy/join (요청)
	// http의 body에 username, password, email 데이터를 가지고 (요청)
	@PostMapping("/dummy/join")
	/*
	public String join(String username, String password, String email) {	// key = value 형태
		System.out.println("username : "+username);							// postman에 입력한 정보(JSON)이랑 파싱됨
		System.out.println("password : "+password);
		System.out.println("email : "+email);
*/	
		
		//위쪽에 PostMapping있음
		public String join(User user) {
		System.out.println("id : "+user.getId());
		System.out.println("username : "+user.getUsername());
		System.out.println("password : "+user.getPassword());
		System.out.println("email : "+user.getEmail());
		System.out.println("role : "+user.getRole());
		System.out.println("createDate : "+user.getCreateDate());
		// 이렇게도 가능 (join(User user)으로 했기때문에 User.java에 있는 필드랑 이름다르면 적용안됨)
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
}
