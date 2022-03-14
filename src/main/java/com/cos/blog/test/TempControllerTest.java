package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {
	
	// 정적(html)은 static폴더
	// 템플릿은 템플릿폴더
	// 정적은 기본세팅으로 되있어서 리턴에 /랑 .html 넣는다.
	
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("tempHome()");
		// 파일리턴 기본경로 : src/main/resources/static
		// 리턴명 : /home.html
		// 풀경로 : src/main/resources/static/home.html
		return "/home.html";
	}
	
	// 동적(JSP)은 src/main/webapp/WEB-INF/views 에
	// 동적은 리턴에 /도 없고 뒤에 .jsp도 없다 (yml파일에서 다 설정해놔서)
	// 해보니까 앞에 /정도는 넣어도 스프링이 알아서 처리해줌
	
	@GetMapping("/temp/jsphome")
	public String jsphome() {
		return "jsptest";
	}

}
