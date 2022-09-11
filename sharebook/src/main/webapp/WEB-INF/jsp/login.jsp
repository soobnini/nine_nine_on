<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="targetUrl"><c:url value="/book/login.do" /></c:set>
<c:set var="registerUrl"><c:url value="/book/register/1.do" /></c:set>
<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">

<script type="text/javascript" src="/js/login.js" ></script>
<style>
* {
	font-family: 'Noto Sans KR', sans-serif;
}
h1 {
	text-align : center;
}
.startBtn {
  background-color: #80C40A;
  border-radius: 10px;
  display: block;
  width: 370px;
  height: 50px;
  border: 0px;
  font-size: 20px;
  color: white;
}
.line {
	text-align : right;
	font-size: 15px;
	margin-top: 10px;
}
.kakao_login {
	text-align : center;
}
.href_link {
	text-decoration-line: none;
	color : black;
}
</style>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
<title>로그인</title>
</head>
	<%@ include file="header.jsp"%>
	<div class="container py-5 w-25">
		<main class="form-signin mx-auto">
			<form action="${targetUrl}" method="post" class="d-block" name="loginForm">
				<h1 class="h2 mb-3 fw-normal fw-bold">로그인</h1><br><br>

				<div class="form-floating">
					<input type="email" class="form-control" id="floatingInput"
						placeholder="name@example.com" name="id"> <label for="floatingInput" >아이디를 입력해주세요.</label>
				</div><br>
				<div class="form-floating">
					<input type="password" class="form-control" id="floatingPassword"
						placeholder="Password" name="password"> <label for="floatingPassword">비밀번호를 입력해주세요.</label>
				</div><br>

				<input type="button" value="로그인" onClick="loginAction()" class="startBtn"> 
				<div class="line">
					<span>아이디/비밀번호 찾기&nbsp;&nbsp;</span>
					<span><a class="href_link" href="${registerUrl}">회원가입</a></span>
				</div>
			</form>
			
			<br><br>
			<div class="kakao_login">
				<a href="https://kauth.kakao.com/oauth/authorize?client_id=1e0eb0e243daeec06175d610aca6d1e2&redirect_uri=http://localhost:8080/book/register/1.do&response_type=code">
				<img src="/images/소셜 로그인2.png" width=100%, height=100%>
			</a>
			</div>
			
		</main>
	</div>
</body>
</html>