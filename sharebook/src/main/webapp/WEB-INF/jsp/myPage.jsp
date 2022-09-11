<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="checkMemberUrl"><c:url value="/book/mypage/member/check.do" /></c:set>
<c:set var="updateMemberUrl"><c:url value="/book/mypage/member/update.do" /></c:set>
<c:set var="bookLikesListUrl"><c:url value="/book/mypage/likes/book.do" /></c:set>
<c:set var="fundingLikesListUrl"><c:url value="/book/mypage/likes/funding.do" /></c:set>
<c:set var="bookListUrl"><c:url value="/book/mypage/rent.do" /></c:set>
<c:set var="fundingListUrl"><c:url value="/book/mypage/funding.do" /></c:set>
<c:set var="communityListUrl"><c:url value="/book/mypage/community.do" /></c:set>
<c:set var="WithdrawaltUrl"><c:url value="/book/mypage/withdrawal.do" /></c:set>

<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">	
	 
<!-- Option 1: Bootstrap Bundle with Popper -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
	
<link rel="stylesheet" href="/css/mypage.css" />
<script type="text/javascript" src="/js/mypage.js" ></script>
<title>myPage</title>

</head>
<body>
	<%@ include file="header.jsp"%>
	<!-- 프로필 -->
	
	<div class="main">
	
		<h4 style="margin-top:50px;">마이페이지</h4>
		<div class="row">
			<div class="col-3">
				<div id=circle><img src="/images/ex_image.png" alt="프로필 이미지" id=profile></div>
			</div>
			<div class="col-8">
				<div class="row">
					</br></br>
				</div>
				<div class="row">
					<div id=nick>
						<b><c:out value="${member.name}" /></b>
						<span style="color:black; font-size:15px; vertical-align:top;">몇페이지 읽었는지 추가 요망</span>
					</div>
				</div>
			</div>
		</div>	
		
		
		<!-- 여기에 추가해야 함 -->
		<div>
			<span>회원정보 수정</span><span>&nbsp;&nbsp;|&nbsp;&nbsp;</span>
			<span>대여목록</span><span>&nbsp;&nbsp;|&nbsp;&nbsp;</span>
			<span>찜목록</span><span>&nbsp;&nbsp;|&nbsp;&nbsp;</span>
			<span>나의 활동</span><span>&nbsp;&nbsp;|&nbsp;&nbsp;</span>
			<span>알림</span><span>&nbsp;&nbsp;|&nbsp;&nbsp;</span>
			<span>로그아웃</span>
		</div>
		<hr>
		
		<div style="width: 96%; margin: auto;">
			<div>
				<span><h5>회원정보 수정</h5></span>
				<div class="container py-5 w-50">
					<main class="form-signin mx-auto">
						<form action="${checkMemberUrl}" method="post" class="d-block" name="loginForm">
							<div class="form-floating">
								<input type="password" class="form-control" id="floatingPassword"
									placeholder="Password" name="password"> <label for="floatingPassword">비밀번호를 입력해주세요.</label><br>
								<input type="button" value="로그인" onClick="loginAction()" class="startBtn" style="width:420px;"> <br>
							</div>
						</form>
					</main>
				</div>
			</div>
			
			<div>
				<span><h5>알림</h5></span>
				<div style="overflow:auto; width:100%; height:200px;">
					ㅈㄷㄱ<br>
					ㅈㄷㄱ<br><hr>
					ㅈㄷㄱ<br>
					ㅈㄷㄱ<br><hr>
					ㅈㄷㄱ<br>
					ㅈㄷㄱ<br><hr>
					ㅈㄷㄱ<br>
					ㅈㄷㄱ<br><hr>
					ㅈㄷㄱ<br>
					ㅈㄷㄱ<br><hr>
				</div>
			</div>
		</div><br><br>
	
		<div>
			<span><h5>대여목록</h5></span>
			<div>빌렸어요</div>	
			<div>빌려줬어요</div>	
		</div><br><br>
		
		<div>
			<span><h5>나의 활동</h5></span>
			<form>
				글, 댓글 나눠야 함
			</form>
		</div><br><br>
		
		
	<div class="container">
		<h4 style="margin-top:50px;">마이페이지</h4>
		<div class="row">
			<div class="col-3">
				<div id=circle><img src="${member.image}" alt="프로필 이미지" id=profile></div>
			</div>
			<div class="col-8">
				<div class="row">
					</br> </br> </br>
				</div>
				<div class="row">
					<div class="col-6" id =deg2>
						<strong>나의 온도 &nbsp;&nbsp;&nbsp;<span id=deg><c:out value="${member.temperature}" /> </span></strong> ºC
					</div>
				</div>
				
				<div class="row">
					<div id= nick>
						<c:out value="${member.name}" />
					</div>
				</div>
			</div>
		</div>
		<!-- contents box -->
		<div class="row" id=mainC>
		<!-- menu -->
			<div class="col" style="text-align : center">
				<ul class="nav flex-column">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="${checkMemberUrl}"></a><p><span id = navlink>
							<a href='<c:url value="${checkMemberUrl}"></c:url>'>회원정보 수정</a>
						</span></p></li>
					<li class="nav-item"><a class="nav-link" href="${bookLikesListUrl}"></a><p><span id = navlink>
						<a href='<c:url value="${bookLikesListUrl}"></c:url>'>책 찜 목록(♥)</a>
					</span></p></li>
					<li class="nav-item"><a class="nav-link" href="${fundingLikesListUrl}"></a><p><span id = navlink>
						<a href='<c:url value="${fundingLikesListUrl}"></c:url>'>펀딩 찜 목록(♥)</a>
					</span></p></li>
					<li class="nav-item"><a class="nav-link" href="${bookListUrl}"></a><p><span id = navlink>
						<a href='<c:url value="${bookListUrl}"></c:url>'>대여 리스트</a>
					</span></p></li>
					<li class="nav-item"><a class="nav-link" href="${fundingListUrl}"></a><p><span id = navlink>
						<a href='<c:url value="${fundingListUrl}"></c:url>'>참여한 펀딩</a>
					</span></p></li>
					<li class="nav-item"><a class="nav-link" href="${communityListUrl}"></a><p><span id = navlink>
						<a href='<c:url value="${communityListUrl}"></c:url>'>커뮤니티 활동 내역</a>
					</span></p></li>
					<li class="nav-item"><a class="nav-link" href="${WithdrawaltUrl}" onclick="return withdrawal();"></a><p><span id = navlink onclick="return withdrawal();">
						<a href='<c:url value="${WithdrawaltUrl}"></c:url>'>회원 탈퇴</a>
					</span></p></li>
				</ul>
			</div>
			<!-- main -->
			<div class="col-10">
				<table class="table table-hover mt-3">
				 	<c:choose>
				 		<c:when test="${category == 'memberCheck'}">
				 			<div class="container py-5 w-50">
								<main class="form-signin mx-auto">
									<form action="${checkMemberUrl}" method="post" class="d-block" name="loginForm">
										<h1 class="h3 mb-3 fw-normal fw-bold">정보를 안전하게 보호하기 위해<br>비밀번호를 다시 한 번 확인합니다</h1>
						
										<div class="form-floating">
											<input type="email" class="form-control" id="floatingInput"
												placeholder="name@example.com" name="id"> <label for="floatingInput" >아이디</label>

										</div>
										<div class="form-floating">
											<input type="password" class="form-control" id="floatingPassword"
												placeholder="Password" name="password"> <label for="floatingPassword">비밀번호</label><br>
										</div>
										
										<input type="button" value="확인" onClick="loginAction()" class="w-100 btn btn-lg btn-primary"><br>
									</form>
								</main>
							</div>
				 		</c:when>
				 		
				 		<c:when test="${category == 'memberUpdate'}">
				 			<div class="container w-75 py-5">
								<form action="${updateMemberUrl}" method="post" enctype="multipart/form-data">
									<div class="row mb-3">
										<label for="inputId" class="col-sm-2 col-form-label">아이디(이메일)</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" name="email" value="${member.email}" readonly>
										</div>
									</div>
									<div class="row mb-3">
										<label for="inputPassword" class="col-sm-2 col-form-label">새 비밀번호</label>
										<div class="col-sm-10">
											<input type="password" class="form-control" name="password">
										</div>
									</div>
									<div class="row mb-3">
										<label for="inputPasswordCheck" class="col-sm-2 col-form-label">새 비밀번호
											확인</label>
										<div class="col-sm-10">
											<input type="password" class="form-control" name="passwordCheck">
										</div>
									</div>
									<div class="row mb-3">
										<label for="inputName" class="col-sm-2 col-form-label">이름</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" name="name" value="${member.name}" >
										</div>
									</div>
									<div class="row mb-3">
										<label for="inputNickname" class="col-sm-2 col-form-label">닉네임</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" name="nickname" value="${member.nickname}">
										</div>
									</div>
									<div class="row mb-3">
										<label for="inputTel" class="col-sm-2 col-form-label">전화번호</label>
										<div class="col-sm-10">
											<input type="tel" class="form-control" name="phone" value="${member.phone}">
										</div>
									</div>
									<div class="row mb-3">
										<label for="inputAddress" class="col-sm-2 col-form-label">주소1</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" name="address1" value="${member.address1}">
										</div>
									</div>
									<div class="row mb-3">
										<label for="inputAddress" class="col-sm-2 col-form-label">주소2</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" name="address2" value="${member.address2}">
										</div>
									</div>
									<div class="row mb-3">
										<label for="inputAddress" class="col-sm-2 col-form-label">프로필 사진 수정</label>
										<div class="col-sm-10">
											<!-- <input type="text" class="form-control" name="address2" value="${member.image}">   -->
											<input type="file" name="uploadImage" />
										</div>
									</div>
									<div class="d-grid gap-2 col-3 mx-auto">
										<button type="submit" class="btn btn-primary">회원정보 수정</button>
									</div>
								</form>
							</div>
				 		</c:when>
				 		
				 		<c:when test="${category == 'likesBook'}">
				 			<%@ include file="includedBookLikeList.jsp"%>
						</c:when>
						
						<c:when test="${category == 'likesFunding'}">
							<%@ include file="includedFudingLikeList.jsp"%>
						</c:when>
						
						<c:when test="${category == 'rent'}">
							<%@ include file="includedRentList.jsp"%>
						</c:when>
						
						<c:when test="${category == 'funding'}">
							<%@ include file="includedFundingList.jsp"%>
						</c:when>
				 		
						<c:when test="${category == 'community'}">
							<%@ include file="includedCommunityList.jsp"%>
						</c:when>
					</c:choose>
		        </table>
			</div>
		</div>
	</div>
</body>
</html>