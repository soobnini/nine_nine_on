function loginAction() {
	if (loginForm.floatingInput.value == "") {
		alert("아이디를 입력해주세요");
		loginForm.floatingInput.focus();
		return false;
	}	
	if (loginForm.floatingPassword.value == "") {
		alert("비밀번호를 입력해주세요");
		loginForm.floatingPassword.focus();
		return false;
	}	
	loginForm.submit();
}

function withdrawal() {
	return confirm("정말 삭제하시겠습니까?");
}