<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>삭제 완료</title>
    <script>
        // 페이지가 로드되면 알림을 표시하고,  /qna로 리디렉션
        window.onload = function() {
            alert("삭제가 완료되었습니다.");
            // /qna로 리디렉션
            setTimeout(function() {
                window.location.href = "/qna";
            }, 0); 
        };
    </script>
</head>
<body>
</body>
</html>
