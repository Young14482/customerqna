<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세정보</title>
<script src="${pageContext.request.contextPath}/static/js/qnaDetail.js" defer></script>
</head>
<body>
	<article>
		<header>
			<h3>제목: ${ QNA.title }</h3>
			<p>조회수: ${ QNA.views }</p>
			<p>작성자: ${ QNA.username }</p>
		</header>
		<h4>내용</h4>
		<p>${ QNA.content }</p>
		<a href="/qna">메인으로</a>
	</article>
</body>
</html>