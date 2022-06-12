<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="fixMemberUrl"><c:url value="/book/mypage/member.do" /></c:set>
<c:set var="likesListUrl"><c:url value="/book/mypage/likes.do" /></c:set>
<c:set var="bookListUrl"><c:url value="/book/mypage/rent.do" /></c:set>
<c:set var="fundingListUrl"><c:url value="/book/mypage/funding.do" /></c:set>
<c:set var="communityListUrl"><c:url value="/book/mypage/community.do" /></c:set>

<!-- 
<li class="nav-item"><a class="nav-link active"
	aria-current="page" href="#"></a><p><span id = navlink>회원정보 수정</span></p></li>
<li class="nav-item"><a class="nav-link" href="#"></a><p><span id = navlink>찜 목록(♥)</span></p></li>
<li class="nav-item"><a class="nav-link" href="#"></a><p><span id = navlink>대여 리스트</span></p></li>
<li class="nav-item"><a class="nav-link" href="#"></a><p><span id = navlink>참여한 펀딩</span></p></li>
<li class="nav-item"><a class="nav-link" href="#"></a><p><span id = navlink>커뮤니티 활동 내역</span></p></li>
 -->

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

</head>
<body>

	<!-- 프로필 -->
	<div class="container">
		<div class="row">
			<div class="col-3">
				<div id=circle><img src="Asset 53.png" alt="프로필 이미지" id = profile></div>
			</div>
			<div class="col-8">
				<div class="row">
					</br> </br> </br>
				</div>
				<div class="row">
					<div class="col-6" id =deg2>
						<strong>나의 온도 &nbsp;&nbsp;&nbsp;<span id=deg><c:out value="${temperature}" /> </span></strong> ºC
					</div>
				</div>
				
				<div class="row">
					<div id= nick>
						<c:out value="${name}" />
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
						aria-current="page" href="${fixMemberUrl}"></a><p><span id = navlink>
							<a href='<c:url value="${fixMemberUrl}"></c:url>'>회원정보 수정</a>
						</span></p></li>
					<li class="nav-item"><a class="nav-link" href="${likesListUrl}"></a><p><span id = navlink>
						<a href='<c:url value="${likesListUrl}"></c:url>'>찜 목록(♥)</a>
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
				</ul>
			</div>
			<!-- main -->
			<div class="col-10">
				<table class="table table-hover mt-3">
				 	<c:choose>
				 		<c:when test="${category == 'likes'}">
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
										<td><c:out value="${rentList.start_day}" /> </td>
										<td><c:out value="${rentList.end_day}" /></td>
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
										<td><c:out value="${communityList.upload_date}" /> </td>
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