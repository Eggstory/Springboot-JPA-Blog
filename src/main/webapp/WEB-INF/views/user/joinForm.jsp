<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 경로앞에 /없이 쓰면 상대경로 (즉, 이 파일 위치하는곳을 기준으로 잡는거 -->
<!-- 경로에 ../은 뒤로 한칸 가는거 -->
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<div class="form-group">
			<label for="username">Username</label> 
			<input type="text" class="form-control" placeholder="Enter Username" id="username">
		</div>
		
		<div class="form-group">
			<label for="password">Password</label> 
			<input type="password" class="form-control" placeholder="Enter password" id="password">
		</div>

		<div class="form-group">
			<label for="email">Email</label> 
			<input type="email" class="form-control" placeholder="Enter Email" id="email">
		</div>
		
	</form>
	<button id="btn-save" class="btn btn-primary">회원가입완료</button>

</div>

<script src="/js/user.js"></script>		<!-- src 경로에 /넣으면 바로 static 폴더로 간다고 함 -->
<%@ include file="../layout/footer.jsp"%>

