<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 화면</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/qna.css">
<script src="${pageContext.request.contextPath}/static/js/qna.js" defer></script>
</head>
<body>
	<h1>관리자 페이지</h1>
 	<!-- 검색 및 정렬 폼 -->
    <form action="/qna/admin" method="get">
        <label for="searchTerm">검색어:</label>
        <input type="text" id="searchTerm" name="searchTerm" value="${searchTerm}" placeholder="검색어를 입력하세요">
        
        <label for="sortColumn">정렬방식:</label>
        <select id="sortColumn" name="sortColumn">
            <option value="created_At" <c:if test="${sortColumn == 'createdAt'}">selected</c:if>>최신순</option>
            <option value="views" <c:if test="${sortColumn == 'views'}">selected</c:if>>조회수순</option>
        </select>
        
        <select id="sortOrder" name="sortOrder">
            <option value="ASC" <c:if test="${sortOrder == 'ASC'}">selected</c:if>>오름차순</option>
            <option value="DESC" <c:if test="${sortOrder == 'DESC'}">selected</c:if>>내림차순</option>
        </select>
        
        <button type="submit">검색</button>
    </form>

    <c:if test="${not empty qnaList}">
        <table>
		    <thead>
		        <tr align="center" bgcolor="white">
		            <th>글번호</th>
		            <th>글제목</th>
		            <th>작성자</th>
		            <th>조회수</th>
		            <th>작성일</th>
		            <th>작업</th> <!-- 작업 열 추가 -->
		        </tr>
		    </thead>
		    <tbody>
		        <c:forEach var="qna" items="${qnaList}">
		            <tr onclick="fetchPasswordAndRedirect('${qna.articleId}', ${qna.secure})">
		                <td>${qna.articleId}</td>
		                <td>${qna.title}</td>
		                <td>${qna.username}</td>
		                <td>${qna.views}</td>
		                <td>${qna.createdAtStr}</td>
		                <td>
		                    <form action="/qna/admin/delete/${qna.articleId}?id=${adminId}" method="post" style="display:inline;">
		                        <button type="submit" onclick="return confirm('삭제하시겠습니까?');">삭제</button>
		                    </form>
		                </td>
		            </tr>
		        </c:forEach>
		    </tbody>
		</table>
        
        <!-- 페이지 네비게이션 -->
        <nav>
            <a href="/qna/admin?page=0">&lt;&lt;</a>
            <c:forEach var="i" begin="0" end="${totalPages}">
                <c:if test="${(currentPage <= 4 && i <= 10) || (i >= currentPage - 4 && i <= currentPage + 5)}">
                    <a href="/qna/admin?page=${i}&sortColumn=${sortColumn}&sortOrder=${sortOrder}&searchTerm=${searchTerm}" 
                       style="${i == currentPage ? 'font-weight: bold; color: #007BFF;' : ''}">
                        ${i + 1}
                    </a>
                </c:if>
            </c:forEach>
            <a href="/qna/admin?page=${totalPages}">&gt;&gt;</a>
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