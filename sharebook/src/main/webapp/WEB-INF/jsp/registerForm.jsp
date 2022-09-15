<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="targetUrl">
	<c:url value="/book/register/2.do" />
</c:set>

<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원가입</title>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap"
	rel="stylesheet">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script></script>
<style>
* {
	font-family: 'Noto Sans KR', sans-serif;
}

.main {
	width: 40%;
	margin: auto;
}

h1 {
	text-align: center;
}

.seperate_space {
	display: inline-block;
	width: 45%;
}

.seperate_check {
	display: inline-block;
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

.btn-check:checked+.btn-outline-success {
	border: none;
	border-radius: 50px;
	background-color: #80C40A;
	color: white;
}

.btn.btn-outline-success:hover {
	border: none;
	border-radius: 50px;
	background-color: #80C40A;
	color: white;
}

.btn.btn-outline-success {
	border: 1px solid #E1E1E1;
	border-radius: 50px;
	background-color: white;
	color: #5C5C5C;
}

#checkList {
	margin-top: 5px;
}
.form-select{
	margin-top: 10px;
}
</style>
</head>
<body>
	<div id="header"></div>
	<div class="main">
		<h1 class="h2 mb-3 fw-normal fw-bold">회원가입</h1>
		<div class="total_step2">
			<img src="/images/회원가입 단계바2.png" width=100%, height=100%>
		</div>
		<br>
		<form:form modelAttribute="memberCommand" action="${targetUrl}"
			method="post">
			<div>
				아이디(이메일)<span style="color: red;">*</span>
			</div>
			<form:input path="email" class="form-control"
				placeholder="예시) abc.@naver.com" />
			<form:errors path="email" />
			<div style="color: gray;">6~18자여야 하며, 영문 소문자와 숫자만 사용할 수 있어요.</div>
			<br>

			<div>
				비밀번호<span style="color: red;">*</span>
			</div>
			<form:password path="password" class="form-control" />
			<form:errors path="password" />
			<div style="color: gray;">8~18자여야 하며 영어, 숫자, 특수문자를 꼭 사용해야 해요.</div>
			<br>

			<div>
				비밀번호 확인<span style="color: red;">*</span>
			</div>
			<form:password path="passwordCheck" class="form-control" />
			<form:errors path="passwordCheck" />
			<br>

			<span class="seperate_space" style="margin-right: 9%;">
				<div>
					이름<span style="color: red;">*</span>
				</div> <form:input path="name" class="form-control" /> <form:errors
					path="name" />
			</span>

			<span class="seperate_space">
				<div>
					닉네임<span style="color: red;">*</span>
				</div> <form:input path="nickname" class="form-control" /> <form:errors
					path="nickname" />
			</span>
			<br>
			<br>

			<span class="seperate_space" style="width: 60%; margin-right: 5%;">
				<div>
					휴대폰 번호<span style="color: red;">*</span>
				</div> <form:input path="phone" class="form-control"
					placeholder="예시) 010-1111-1111" style="width=70%;" /> <form:errors
					path="phone" />
			</span>

			<span class="seperate_space" style="width: 29%;"> <label>
					<input type="checkbox" name="service_agree" value="service_agree">
					<span>SMS 수신 동의</span> <span>(선택)</span>
			</label>
			</span>
			<br>
			<br>
			
			<div>
				주소<span style="color: red;">*</span>
			</div>

			<form:select path="address1" name="address1" id="address1" class="form-select"></form:select>
			<form:select path="address2" name="address2" id="address2" class="form-select"></form:select>

			<br>
			<br>

			<div>관심 있는 장르를 선택해 주세요.(최소 3개)</div>
			<div class="row">
				<c:forEach var="genre" items="${genreList}">
					<div class="col-sm-auto" id="checkList">
						<input type="checkbox" class="btn-check" autocomplete="off"
							id="${genre.genreId}" value="${genre.genreId}" name="genreList">
						<label class="btn btn-outline-success" for="${genre.genreId}">${genre.name}</label>
					</div>
				</c:forEach>
			</div>
			<br />
			<div style="text-align: center;">
				<button type="submit" class="submit_btn" onclick="${targetUrl}">가입하기</button>
			</div>

		</form:form>
		<br />
	</div>
	<div id="footer"></div>
	
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="/js/loadAjax.js" type="text/javascript" charset="UTF-8"></script>
	<script src="/js/loadHeaderAndFooter.js" type="module" charset="UTF-8"></script>
	
	<script>$('document').ready(function() {
		 var area0 = ["시/도 선택","서울특별시","인천광역시","대전광역시","광주광역시","대구광역시","울산광역시","부산광역시","경기도","강원도","충청북도","충청남도","전라북도","전라남도","경상북도","경상남도","제주도"];
		  var area1 = ["강남구","강동구","강북구","강서구","관악구","광진구","구로구","금천구","노원구","도봉구","동대문구","동작구","마포구","서대문구","서초구","성동구","성북구","송파구","양천구","영등포구","용산구","은평구","종로구","중구","중랑구"];
		   var area2 = ["계양구","남구","남동구","동구","부평구","서구","연수구","중구","강화군","옹진군"];
		   var area3 = ["대덕구","동구","서구","유성구","중구"];
		   var area4 = ["광산구","남구","동구",     "북구","서구"];
		   var area5 = ["남구","달서구","동구","북구","서구","수성구","중구","달성군"];
		   var area6 = ["남구","동구","북구","중구","울주군"];
		   var area7 = ["강서구","금정구","남구","동구","동래구","부산진구","북구","사상구","사하구","서구","수영구","연제구","영도구","중구","해운대구","기장군"];
		   var area8 = ["고양시","과천시","광명시","광주시","구리시","군포시","김포시","남양주시","동두천시","부천시","성남시","수원시","시흥시","안산시","안성시","안양시","양주시","오산시","용인시","의왕시","의정부시","이천시","파주시","평택시","포천시","하남시","화성시","가평군","양평군","여주군","연천군"];
		   var area9 = ["강릉시","동해시","삼척시","속초시","원주시","춘천시","태백시","고성군","양구군","양양군","영월군","인제군","정선군","철원군","평창군","홍천군","화천군","횡성군"];
		   var area10 = ["제천시","청주시","충주시","괴산군","단양군","보은군","영동군","옥천군","음성군","증평군","진천군","청원군"];
		   var area11 = ["계룡시","공주시","논산시","보령시","서산시","아산시","천안시","금산군","당진군","부여군","서천군","연기군","예산군","청양군","태안군","홍성군"];
		   var area12 = ["군산시","김제시","남원시","익산시","전주시","정읍시","고창군","무주군","부안군","순창군","완주군","임실군","장수군","진안군"];
		   var area13 = ["광양시","나주시","목포시","순천시","여수시","강진군","고흥군","곡성군","구례군","담양군","무안군","보성군","신안군","영광군","영암군","완도군","장성군","장흥군","진도군","함평군","해남군","화순군"];
		   var area14 = ["경산시","경주시","구미시","김천시","문경시","상주시","안동시","영주시","영천시","포항시","고령군","군위군","봉화군","성주군","영덕군","영양군","예천군","울릉군","울진군","의성군","청도군","청송군","칠곡군"];
		   var area15 = ["거제시","김해시","마산시","밀양시","사천시","양산시","진주시","진해시","창원시","통영시","거창군","고성군","남해군","산청군","의령군","창녕군","하동군","함안군","함양군","합천군"];
		   var area16 = ["서귀포시","제주시","남제주군","북제주군"];

		 

		 // 시/도 선택 박스 초기화

		 $("select[name^=address1]").each(function() {
		  $selsido = $(this);
		  $.each(eval(area0), function() {
		   $selsido.append("<option value='"+this+"'>"+this+"</option>");
		  });
		  $selsido.next().append("<option value=''>구/군 선택</option>");
		 });

		 

		 // 시/도 선택시 구/군 설정

		 $("select[name^=address1]").change(function() {
		  var area = "area"+$("option",$(this)).index($("option:selected",$(this))); // 선택지역의 구군 Array
		  var $gugun = $(this).next(); // 선택영역 군구 객체
		  $("option",$gugun).remove(); // 구군 초기화

		  if(area == "area0")
		   $gugun.append("<option value=''>구/군 선택</option>");
		  else {
		   $.each(eval(area), function() {
		    $gugun.append("<option value='"+this+"'>"+this+"</option>");
		   });
		  }
		 });


		});</script>
</body>
</html>