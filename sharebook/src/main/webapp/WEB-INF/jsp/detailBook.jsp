<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="targetUrl"><c:url value="/book.do" /></c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">


<title>책 보기</title>
<style>
* {
	font-family: 'Noto Sans KR', sans-serif;
}
h1 {
	text-align : center;
}
.step1 {
	text-align:center;
	display:table-cell;
	vertical-align:middle;
	width: 150px;
	height: 100px;
	background-color: #80C40A;
	border-radius: 20px;
}
.total_step1 {
	text-align: center;
	 margin: auto;
}
.description {
	overflow: scroll; 
	width: 520px; 
	height: 100px; 
	padding: 10px;
	margin: auto;
}
.main {
	width: 40%;
	margin: auto;
}
label {

}
button {
	border-radius: 20px;
 	width: 25%;
 	height: 50px;
	border: 0px;
	font-size: 20px;
}
.cancel_btn {
	color: #9A9A9A;
}
.ok_btn {
	color: white;
	background-color: #80C40A;
}
.button_div {
	text-align : center;
	margin: auto;
}
</style>
</head>
<body>

	<div class="main">
	
		<h2 class="h2 mb-3 fw-normal fw-bold" style="text-align : center;">회원가입이 완료되었습니다.</h2>
		<div class="total_step1">
			<img src="/images/회원가입 단계바3.png" width=100%, height=100%><br><br>
			<span style="color:#BFE184;"><b>반가워요, <span>${name}</span>님! 이웃에 책을 나눠주세요!</b></span><br><br>
			<img src="/images/회원가입-3단계 회원가입 완료.jpg" width=100%, height=100%><br>
		</div><br>

			<div class="button_div">
				<button class="ok_btn" onClick="location.href='${targetUrl}'"><b>둘러보기</b></button>
			</div>
			
		</form>
	</div>
	
</body>
</html>