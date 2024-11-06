<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>상세정보</title>
<script src="${pageContext.request.contextPath}/static/js/qnaDetail.js" defer></script>
<style>
    /* 기본 스타일 */
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f9;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: flex-start;
        min-height: 100vh;
        color: #333;
    }

    article {
        background-color: white;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        width: 60%;
        max-width: 800px;
        margin: 20px;
    }

    header {
        display: flex;
        flex-direction: column;
        align-items: flex-start;
        margin-bottom: 20px;
    }

    h3 {
        font-size: 1.8em;
        color: #2c3e50;
        margin-bottom: 10px;
        font-weight: bold;
    }

    .meta-info {
        display: flex;
        justify-content: space-between;
        width: 100%;
        font-size: 1em;
        color: #7f8c8d;
    }

    .meta-info span {
        margin-right: 20px;
    }

    h4 {
        font-size: 1.5em;
        color: #34495e;
        margin-top: 20px;
        margin-bottom: 10px;
    }

    .content {
        font-size: 1.1em;
        line-height: 1.6;
        color: #2c3e50;
    }

    div {
        display: flex;
        justify-content: space-between;
        margin-top: 20px;
    }

    a {
        text-decoration: none;
        font-size: 1.1em;
        color: #2980b9;
    }

    button {
    	margin: 10px;
        background-color: #3498db;
        color: white;
        border: none;
        padding: 10px 15px;
        font-size: 1em;
        cursor: pointer;
        border-radius: 5px;
        transition: background-color 0.3s;
    }

    button:hover {
        background-color: #2980b9;
    }

</style>
</head>
<body>
	<article>
		<header>
			<h3>${ QNA.title }</h3>
			<div class="meta-info">
				<span>${ QNA.username } | ${ QNA.updatedAtStr }</span>
				<span>조회수: ${ QNA.views }</span>
			</div>
		</header>

		<h4>내용</h4>
		<p class="content">${ QNA.content }</p>

		<div>
            <a href="/qna">메인으로</a>
            <div>
	            <button onclick="showPasswordDialog('edit','${QNA.articleId}')">수정</button>
	            <button onclick="showPasswordDialog('delete','${QNA.articleId}')">삭제</button>
            </div>
        </div>
	</article>
</body>
</html>
