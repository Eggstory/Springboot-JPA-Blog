package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.service.BoardService;

@RestController
public class BoardApiController {

	@Autowired
	private BoardService boardService;

	
// 아래에 구시대적 로그인방식에서 HttpSession session을 지우고 여기에 써서 DI해줘도됨 /아래꺼 안지우고 여기 DI된거 지워도됨
//	@Autowired
//	private HttpSession session;
	
	
	@PostMapping("/api/board")									// 제네릭 공부해야하나...	// username, password, email
	public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
		boardService.글쓰기(board, principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);	// 자바오브젝트를 JSON으로 변환해서 리턴
	}

	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id){
		boardService.글삭제하기(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board) {
		boardService.글수정하기(id,board);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
		
	}
	
	// 데이터 받을 때 컨트로럴에서 dto를 만들어서 받는게 좋다.
	// dto 사용하지 않은 이유는 - 
	@PostMapping("/api/board/{boardId}/reply")		// (@PathVariable int boardId, @RequestBody Reply reply, @AuthenticationPrincipal PrincipalDetail principal)							
	public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto replySaveRequestDto) {
		boardService.댓글쓰기(replySaveRequestDto);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);	// 자바오브젝트를 JSON으로 변환해서 리턴
	}
	
	@DeleteMapping("/api/board/{boardId}/reply/{replyId}")
	public ResponseDto<Integer> replyDelete(@PathVariable int replyId) {
		boardService.댓글삭제(replyId);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
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
