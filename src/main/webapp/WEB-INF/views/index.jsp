<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 경로앞에 /없이 쓰면 상대경로 (즉, 이 파일 위치하는곳을 기준으로 잡는거 -->
<%@ include file="layout/header.jsp"%>

<div class="container">
	<div class="panel panel-default">
		<div class="panel-body">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>No</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>조회수</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="board" items="${boards.content}">
						<tr onclick="location.href='/board/${board.id}'" style="cursor: pointer;">
							<td>${board.id }</td>
							<td>${board.title }</td>
							<td>${board.user.username }</td>
							<td>${board.creatDate }</td>
							<td>${board.count }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

	</div>

	<!-- justify-content-center/end/start   중앙/끝/시작부분  -->
	<ul class="pagination justify-content-center">
		<c:choose>
			<c:when test="${boards.first}">
				<li class="page-item disabled"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
			</c:otherwise>
		</c:choose>

		<c:choose>
			<c:when test="${boards.last}">
				<li class="page-item disabled"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
			</c:otherwise>
		</c:choose>
	</ul>


</div>

<%@ include file="layout/footer.jsp"%>