function redirectTo(articleId, isSecure, storedPassword) {
    if (isSecure) {
        document.getElementById('passwordDialog').showModal();
        
        document.getElementById('confirmButton').onclick = function() {
            const password = document.getElementById('passwordInput').value;
            if (password === storedPassword) {
                window.location.href = '/qna/' + articleId; // 비밀번호가 맞으면 리디렉션
            } else {
                alert('비밀번호가 틀렸습니다.');
                document.getElementById('passwordInput').value = ''; // 입력창 초기화
            }
        };
    } else {
        window.location.href = '/qna/' + articleId; // 비밀번호 필요 없음, 리디렉션
    }
}