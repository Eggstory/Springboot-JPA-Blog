<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 경로앞에 /없이 쓰면 상대경로 (즉, 이 파일 위치하는곳을 기준으로 잡는거 -->
<!-- 경로에 ../은 뒤로 한칸 가는거 -->
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form action="/auth/loginProc" method="post">
		<div class="form-group">
			<label for="username">Username</label> 
			<input type="text" name="username" class="form-control" placeholder="Enter Username" id="username">
		</div>

		<div class="form-group">
			<label for="password">Password</label> 
			<input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
		</div>
		
		<button id="btn-login" class="btn btn-primary">로그인</button>
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=882b4ba3b4c1e8a5044973220a2cc889&redirect_uri=http://localhost:8888/auth/kakao/callback&response_type=code"><img height="38px" src="/image/kakao_login_button.png"></a>
	</form>



</div>

<!--   <script src="/js/user.js"></script>	-->
<!-- src 경로에 /넣으면 바로 static 폴더로 간다고 함 -->
<%@ include file="../layout/footer.jsp"%>

