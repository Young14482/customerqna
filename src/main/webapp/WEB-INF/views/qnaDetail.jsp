<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세정보</title>
</head>
<body>
	<c:if test="${empty QNA}">
		<p>제하하하하하하하하하하하하하하하 네놈의 글 탐나는걸!!! 크로우즈!!!!!!</p>
		<a href="/qna">위하하하하하하 선장 힘힘열매의 힘 대단하다고!!!!!</a>
	</c:if>
	<c:if test="${not empty QNA}">
		<p>제목: ${ QNA.title }</p>
		<p>내용: ${ QNA.content }</p>
		<p>작성자: ${ QNA.username }</p>
		<a href="/qna">위하하하하하하 선장 힘힘열매의 힘 대단하다고!!!!!</a>
	</c:if>
</body>
</html>