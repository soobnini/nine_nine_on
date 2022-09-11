<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<c:set var="targetUrl"><c:url value="/book/register/2.do" /></c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">

<title>회원가입</title>
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
	border-radius: 10px;
 	width: 45%;
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
	
		<h2 class="h2 mb-3 fw-normal fw-bold" style="text-align: center;">회원가입</h2>
		<div class="total_step1">
			<img src="/images/회원가입 단계바1.png" width=100%, height=100%>
		</div><br>
		
		<form action="${targetUrl}" method="get">
			<label>
				<input type="checkbox" name="total_agree" value="total_agree">
				<span>
					이웃책장 이용약관, 개인정보 수집 및 이용, 위치기반서비스 이용약관(선택),<br>
					프로모션 정보 수신(선택)에 모두 동의합니다.
				</span>
			</label><br><br>
			
			<label>
				<input type="checkbox" name="service_agree" value="service_agree">
				<span>이용약관 동의</span>
				<span style="color:#80C40A;"><b>(필수)</b></span>
			</label><br><br>
			
			<div class="description">
				<span>
					안녕하세요! 이웃책장입니다. 이웃책장을 이용해 주셔서 감사합니다.
					본 약관은 다양한 이웃책장 서비스의 이용과 관련하여 이웃책장 서비스를 제공하는 이웃책장 주식회사(이하 ‘이웃책장’)와 이를 이용하는 이웃책장 서비스 회원(이하 ‘회원’) 또는 비회원과의 관계를 설명하며, 아울러 여러분의 이웃책장 서비스 이용에 도움이 될 수 있는 유익한 정보를 포함하고 있습니다.
				</span>
			</div><br>
			
			<label>
				<input type="checkbox" name="service_agree" value="service_agree">
				<span>개인정보 수집 이용 동의</span>
				<span style="color:#80C40A;"><b>(필수)</b></span>
			</label><br>
			
			<div class="description">
				<span>
					개인정보보호법에 따라 이웃책장에 회원가입 신청하시는 분께 수집하는 개인정보의 항목, 개인정보의 수집 및 이용목적, 개인정보의 보유 및 이용기간, 동의 거부권 및 동의 거부 시 불이익에 관한 사항을 안내 드리오니 자세히 읽은 후 동의하여 주시기 바랍니다.
				</span>
			</div><br>
			
			<label class="button_div">
				<input type="checkbox" name="service_agree" value="service_agree">
				<span>위치 기반 서비스 이용 동의</span>
				<span style="color:#80C40A;"><b>(필수)</b></span>
			</label><br>
			
			<div class="description">
				<span>
					위치기반서비스 이용약관에 동의하시면, 위치를 활용한 광고 정보 수신 등을 포함하는 이웃책장 위치기반 서비스를 이용할 수 있습니다.
					<br><br>
					제 1 조 (목적)
				</span>
			</div><br>
			
			<div class="button_div">
				<button type="reset" class="cancel_btn"><b>취소</b></button>
				<button type="submit" class="ok_btn"><b>확인</b></button>
			</div>
			
		</form>
	</div>
	
</body>
</html>