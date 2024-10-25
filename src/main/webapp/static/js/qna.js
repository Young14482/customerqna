function fetchPasswordAndRedirect(articleId, isSecure) {

	if (isSecure) {
		// AJAX 요청을 통해 비밀번호를 가져옵니다.
		fetch(`/qna/getPassword?articleId=${articleId}`)
			.then(response => {
				if (!response.ok) {
					throw new Error('Network response was not ok');
				}
				return response.json();
			})
			.then(data => {
				const storedPassword = data.password; // 서버에서 받은 비밀번호
				showPasswordDialog(articleId, storedPassword);
			})
			.catch(error => console.error('Error fetching password:', error));
	} else {
		const form = document.createElement('form');
		form.method = 'POST';
		form.action = '/qna/' + articleId;
		
		const articleIdField = document.createElement('input');
		articleIdField.type = 'hidden';
		articleIdField.name = 'articleId';
		articleIdField.value = articleId;
		form.appendChild(articleIdField);

		document.body.appendChild(form);
		form.submit();
	}
}

function showPasswordDialog(articleId, storedPassword) {
	document.getElementById('passwordDialog').showModal();

	document.getElementById('confirmButton').onclick = function() {
		const password = document.getElementById('passwordInput').value;
		if (password === storedPassword) {
			const form = document.createElement('form');
			form.method = 'POST';
			form.action = '/qna/' + articleId;

			const articleIdField = document.createElement('input');
			articleIdField.type = 'hidden';
			articleIdField.name = 'articleId';
			articleIdField.value = articleId;
			form.appendChild(articleIdField);

			document.body.appendChild(form);
			form.submit();
		} else {
			alert('비밀번호가 틀렸습니다.');
			document.getElementById('passwordInput').value = ''; // 입력창 초기화
		}
	};
}


