<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성하기</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/write.css">
<script src="${pageContext.request.contextPath}/static/js/edit.js" defer></script>
</head>
<body>

<h1>글 작성하기</h1>
<form action="/qna/edit/${QNA.articleId}" method="POST">
    <label for="username">사용자 이름:</label>
    <input type="text" id="username" name="username" value="${ QNA.username }" required>

    <label for="password">비밀번호:</label>
    <input type="password" id="password" name="password" required>

    <label for="title">글 제목:</label>
    <input type="text" id="title" name="title" value="${ QNA.title }" required>

    <label for="content">글 내용:</label>
    <textarea id="content" name="content" rows="5"  required>${ QNA.content }</textarea>
    
  	<label for="secure">공개 설정:</label>
    <select id="secure" name="secure" required>
        <option value="false">공개</option>
        <option value="true">비공개</option>
    </select>
    
    <input type="submit" value="작성 완료">
    <button onclick="location.href='/qna'">메인으로</button>
</form>

</body>
</html>
