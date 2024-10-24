<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객 센터</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/qna.css"> <!-- CSS 파일 링크 -->
<script src="${pageContext.request.contextPath}/static/js/qna.js" defer></script> <!-- JavaScript 파일 링크 (필요 시) -->
</head>
<body>
	<h1>게시글 목록</h1>
	<a href="/qna/write" class="button">글 작성하기</a> <!-- 버튼 하이퍼링크 -->
	<c:if test="${not empty qnaList}">
		<table>
			<thead>
				<tr align="center" bgcolor="white">
					<th>글번호</th>
					<th>글제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="qna" items="${qnaList}">
				    <tr onclick="fetchPasswordAndRedirect('${qna.articleId}', ${qna.secure})">
				        <td>${qna.articleId}</td>
				        <td>${qna.title}</td>
				        <td>${qna.username}</td>
				        <td>${qna.views}</td>
				        <td>${qna.createdAt}</td>
				    </tr>
				</c:forEach>			
			</tbody>
		</table>
		<!-- 페이지 구현하기  -->
		<nav>
			<c:forEach var="num" begin="0" end="${totalPages}">
				<c:url var="pages" value="/qna">
					<c:param name="page">${num}</c:param>
					<c:param name="size">${size}</c:param>
				</c:url>
				<a href="${pages}" style="${num == currentPage ? 'font-weight: bold;' : ''}"> ${num + 1} </a>
			</c:forEach>
		</nav>
	</c:if>
	<c:if test="${empty qnaList}">
		<p>게시글이 없습니다.</p>
	</c:if>
	
	<dialog id="passwordDialog">
		<h3>비밀번호 입력</h3>
	    <input type="password" id="passwordInput" placeholder="비밀번호를 입력하세요">
	    <button id="confirmButton">확인</button>
	    <button onclick="document.getElementById('passwordDialog').close()">취소</button>
	</dialog>
</body>
</html>
