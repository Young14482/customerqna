function showPasswordDialog(action, articleId) {
	const passwordDialog = document.getElementById('passwordDialog');

	passwordDialog.showModal();

	document.getElementById('confirmButton').onclick = function() {
		const password = document.getElementById('passwordInput').value;

		// 비밀번호 확인 후 행동 결정
		fetch(`/qna/getPassword?articleId=${articleId}`)
			.then(response => {
				if (response.ok) {
					return response.json();
				} else {
					alert('비밀번호가 틀렸습니다.');
					document.getElementById('passwordInput').value = ''; // 입력창 초기화
				}
			})
			.then(data => {
				const storedPassword = data.password; // 서버에서 받은 비밀번호
				if (password === storedPassword) {
					if (action === 'edit') {
						window.location.href = `/qna/edit/${articleId}`; // 수정 페이지로 이동
					} else if (action === 'delete') {
						window.location.href = `/qna/delete/${articleId}`; // 삭제 요청
					}
				} else {
					alert('비밀번호가 틀렸습니다.');
					document.getElementById('passwordInput').value = '';
				}
			})
			.catch(error => console.error('Error:', error));
	};
}
