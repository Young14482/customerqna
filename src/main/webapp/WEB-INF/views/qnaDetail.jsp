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
			<h3>${ QNA.title }</h3>
			<p>조회수: ${ QNA.views }</p>
			<p>작성자: ${ QNA.username }</p>
			<p>작성일: ${ QNA.createdAtStr }</p>
			<p>수정일: ${ QNA.updatedAtStr }</p>
		</header>
		<h4>내용</h4>
		<p>${ QNA.content }</p>
		 <div>
            <a href="/qna">메인으로</a>
            <button onclick="showPasswordDialog('edit','${QNA.articleId}')">수정</button>
            <button onclick="showPasswordDialog('delete','${QNA.articleId}')">삭제</button>
        </div>
	</article>
	 <!-- 비밀번호 입력 모달 다이얼로그 -->
    <dialog id="passwordDialog">
        <h3>비밀번호 입력</h3>
        <input type="password" id="passwordInput" placeholder="비밀번호를 입력하세요">
        <button id="confirmButton">확인</button>
        <button onclick="document.getElementById('passwordDialog').close()">취소</button>
    </dialog>
</body>
</html>