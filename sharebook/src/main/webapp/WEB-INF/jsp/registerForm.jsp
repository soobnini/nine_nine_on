<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="targetUrl"><c:url value="/book/register/2.do" /></c:set>

<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원가입</title>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
<style>
* {
	font-family: 'Noto Sans KR', sans-serif;
}
.main {
	width: 40%;
	margin: auto;
}
h1 {
	text-align:center;
}
.seperate_space {
	display:inline-block; 
	width:45%;
}
.seperate_check {
	display:inline-block; 
}
.member_genre {
	background-color: white;
	border-radius: 10px;
	width: 100px;
	height: 30px;
	border-width: 1px;
	border-color: #E7EBE4;
}
.submit_btn {
	background-color: #E7EBE4;
	color: #C6CAEC;
	border-radius: 10px;
	width: 150px;
	height: 40px;
	text-align: center;
	margin: auto;
	border: 0px;
	text-size: 20px;
}
</style>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="main">
	<h1 class="h2 mb-3 fw-normal fw-bold">회원가입</h1>
		<div class="total_step2">
			<img src="/images/회원가입 단계바2.png" width=100%, height=100%>
		</div><br>
	 <form:form modelAttribute="memberCommand" action="${targetUrl}" method="post">
	 	<div>아이디(이메일)<span style="color:red;">*</span></div>
	 	<form:input path="email" class="form-control" placeholder="예시) abc.@naver.com" />
		<form:errors path="email" />
		<div style="color:gray;">6~18자여야 하며, 영문 소문자와 숫자만 사용할 수 있어요.</div><br>
		
		<div>비밀번호<span style="color:red;">*</span></div>
	 	<form:password path="password" class="form-control" />
		<form:errors path="password" />
		<div style="color:gray;">8~18자여야 하며 영어, 숫자, 특수문자를 꼭 사용해야 해요.</div><br>
		
		<div>비밀번호 확인<span style="color:red;">*</span></div>
	 	<form:password path="passwordCheck" class="form-control" />
					<form:errors path="passwordCheck" /><br>
					
		<span class="seperate_space" style="margin-right:9%;">
			<div>이름<span style="color:red;">*</span></div>
			<form:input path="name" class="form-control" />
			<form:errors path="name" />
		</span>
		
		<span class="seperate_space">
			<div>닉네임<span style="color:red;">*</span></div>
			<form:input path="nickname" class="form-control" />
			<form:errors path="nickname" />
		</span><br><br>
		
		<span class="seperate_space" style="width:60%; margin-right:5%;">
			<div>휴대폰 번호<span style="color:red;">*</span></div>
		 	<form:input path="phone" class="form-control" placeholder="예시) 010-1111-1111" style="width=70%;"/>
			<form:errors path="phone" />
		</span>
		
		<span class="seperate_space" style="width:29%;">
			<label>
				<input type="checkbox" name="service_agree" value="service_agree">
				<span>이용약관 동의</span>
				<span>(필수)</span>
			</label>
		</span><br><br>
		
		
		<span class="seperate_space" style="margin-right:9%;">
			<div>도<span style="color:red;">*</span></div>
			<form:input path="address1" class="form-control" placeholder="예시) 서울특별시" />
			<form:errors path="address1" />
		</span>
		
		<span class="seperate_space">
			<div>시<span style="color:red;">*</span></div>
			<form:input path="address2" class="form-control" placeholder="예시) 성북구" />
					<form:errors path="address2" />
		</span><br><br>
		
		<div>관심 있는 장르를 선택해 주세요.(최소 3개)</div>
	 	<div style="text-align: center;">
	 		<button type="button" class="member_genre">현대소설</button>
	 		<button type="button" class="member_genre">에세이</button>
	 		<button type="button" class="member_genre">자기계발</button>
	 		<button type="button" class="member_genre">시</button>
	 		<button type="button" class="member_genre">고전명작</button>
	 	</div><br>
	 	<div style="text-align: center;">
	 		<button type="button" class="member_genre">추리</button>
	 		<button type="button" class="member_genre">로맨스</button>
	 		<button type="button" class="member_genre">SF</button>
	 		<button type="button" class="member_genre">범죄</button>
	 	</div><br>
	 	<div style="text-align: center;">
	 		<button type="button" class="member_genre">스릴러</button>
	 		<button type="button" class="member_genre">공포</button>
	 		<button type="button" class="member_genre">어드벤쳐</button>
	 		<button type="button" class="member_genre">아동</button>
	 		<button type="button" class="member_genre">외국어</button>
	 	</div><br>

		<div style="text-align: center;">
			<button type="submit" class="submit_btn" onclick="${targetUrl}">가입하기</button>
		</div>

		</form:form>	
	</div>
</body>
</html>