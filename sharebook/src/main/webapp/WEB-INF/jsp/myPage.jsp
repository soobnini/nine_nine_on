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

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">

<title>myPage</title>
<style type="text/css">
#circle {
	width: 200px;
	height: 200px;
	border: 5px solid #70D047;
	border-radius: 50%;
	margin: 15px;
}

#deg {
	font-size: 50px;
	color: #ff9442;
}

#deg2 {
	font-size: 20px;
	color: #468f36;
}
#nick{
	font-size: 30px;
	color:#37732a
}

#mainC {
	border: 2px solid #70D047;
	height: 800px;
}
.nav-item{
	border-bottom: 2px solid #70D047;
	
}
.nav-item:hover{
	border-bottom: 2px solid #70D047;
	background-color:#ecfccc;
	color: #ffffff;
}
#navlink{
	color: #2f6620;
	font-size:18px;
}

#profile{
  object-fit: cover;
  width: 100%;
  height: 100%;
  border-radius: 50%;
}
          
</style>

<script>
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
</script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<!-- 프로필 -->
	<div class="container">
		<div class="row">
			<div class="col-3">
				<div id=circle><img src="${member.image}" alt="프로필 이미지" id = profile></div>
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
									<!-- 
									<div class="row">
										<div class="col">
											<a href="#"><img class="w-100 my-3"
												src="/images/kakao_login.png" alt=""></a>
										</div>
										<div class="col">
											<a href="#"><img class="w-100 my-3"
												src="/images/naver_login.png" alt=""></a>
										</div>
									</div>
									 -->
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
				            <thead>
				                <tr>
				                    <th>번호</th>
				                    <th>제목</th>
				                    <th>저자</th>
				                    <th>조회</th>
				                </tr>
				            </thead>
				            <tbody>
				                <c:forEach var="bookList" items="${bookList}">
									<tr>
										<th><c:out value="${bookList.book.book_id}" /></th>
										
										<td><a href='<c:url value="/book/view/${bookList.book.book_id}.do"></c:url>'><c:out value="${bookList.book.title}" /></a></td>
										<td><c:out value="${bookList.book.author}" /></td>
										<td><c:out value="${bookList.book.views}" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</c:when>
						
						<c:when test="${category == 'likesFunding'}">
				            <thead>
				                <tr>
				                    <th>번호</th>
				                    <th>제목</th>
				                    <th>저자</th>
				                    <th>조회</th>
				                </tr>
				            </thead>
				            <tbody>
				                <c:forEach var="findingOrderList" items="${FundingOrderList}">
									<tr>
										<th><c:out value="${findingOrderList.funding.funding_id}" /></th>
										<td><a href='<c:url value="/book/funding/${findingOrderList.funding.funding_id}.do"></c:url>'><c:out value="${findingOrderList.funding.title}" /></a></td>
										<td><c:out value="${findingOrderList.funding.author}" /></td>
										<td><c:out value="${findingOrderList.funding.views}" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</c:when>
						
						<c:when test="${category == 'rent'}">
				            <thead>
				                <tr>
				                    <th>번호</th>
				                    <th>제목</th>
				                    <th>저자</th>
				                    <th>대출 시작일</th>
				                    <th>대출 마감일</th>
				                </tr>
				            </thead>
				            <tbody>
				                <c:forEach var="rentList" items="${rentList}">
									<tr>
										<th><c:out value="${rentList.rent_id}" /></th>
										
										<td><a href='<c:url value="/book/view/${rentList.book.book_id}.do"></c:url>'><c:out value="${rentList.book.title}" /></a></td>
										<td><c:out value="${rentList.book.author}" /> </td> 
										<td><fmt:formatDate pattern="yyyy-MM-dd" value="${rentList.start_day}"/> </td>
										<td><fmt:formatDate pattern="yyyy-MM-dd" value="${rentList.end_day}"/> </td>
									</tr>
								</c:forEach>
							</tbody>
						</c:when>
						
						<c:when test="${category == 'funding'}">
				            <thead>
				                <tr>
				                    <th>번호</th>
				                    <th>제목</th>
				                    <th>저자</th>
				                </tr>
				            </thead>
				            <tbody>
				                <c:forEach var="fundingList" items="${fundingList}">
									<tr>
										<th><c:out value="${fundingList.funding_id}" /></th>
										
										<td><a href='<c:url value="/book/funding/${fundingList.funding_id}.do"></c:url>'><c:out value="${fundingList.title}" /></a></td>
										<td><c:out value="${fundingList.author}" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</c:when>
				 		
						<c:when test="${category == 'community'}">
				            <thead>
				                <tr>
				                    <th>번호</th>
				                    <th>분류</th>
				                    <th>제목</th>
				                    <th>작성자</th>
				                    <th>작성일</th>
				                    <th>조회</th>
				                </tr>
				            </thead>
				            <tbody>
				                <c:forEach var="communityList" items="${communityList}">
									<tr>
										<th><c:out value="${communityList.communityId}" /></th>
										<td>
											<c:choose>
												<c:when test="${communityList.category == '0'}"> 질문 </c:when>
												<c:when test="${communityList.category == '1'}"> 토론 </c:when>
												<c:when test="${communityList.category == '2'}"> 모집 </c:when>
											</c:choose>
										</td>
										
										<td><a href='<c:url value="/book/community/detail.do"><c:param name="commId" value="${communityList.communityId}"/></c:url>'><c:out value="${communityList.title}" /></a></td>
										<td><c:out value="${communityList.member.name}" /></td>  
										<td><fmt:formatDate pattern="yyyy-MM-dd" value="${communityList.upload_date}"/> </td>
										<td><c:out value="${communityList.views}" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</c:when>
					</c:choose>
		        
		        </table>
		        
		        
			</div>
		</div>
	</div>


	<!-- Option 1: Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>


</body>
</html>