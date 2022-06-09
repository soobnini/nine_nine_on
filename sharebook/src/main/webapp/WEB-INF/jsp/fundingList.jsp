<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>이웃 책장 : 종이책 공유 플랫폼</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/main.css">
<!-- 거의 완전 똑같기 때문에 css 공유 -->
<link rel="stylesheet" href="css/bookList.css">
</head>
<body>
	<div th:replace="fragments/common :: header"></div>
	<div id="result-book">
		<!-- ****************************** -->
		<!-- ********* option 부분 나중에 수정 *********** -->
		<!-- ****************************** -->
		<form id="result-sort-form">
			<select id="result-sort-select">
				<option selected>정렬 기준</option>
				<option value="1">가나다순</option>
				<option value="2">저자순</option>
				<option value="3">종료 날짜순</option>
			</select>
		</form>
		<!-- ****************************** -->
		<!-- ********* 나중에 수정 *********** -->
		<!-- ****************************** -->
		<c:forEach begin="0" end="10" step="5" varStatus="status">
			<ul class="result-list">
				<c:forEach begin="0" end="4">
					<li class="result-list-content"><img
						src="images/sampleBook01.jpg" class="result-list-img">
						<h6 class="item-list-img-title">
							<b>컴퓨터 네트워킹</b>
						</h6>
						<p class="result-list-img-writer">James F. Kurose 저</p>
						<p class="result-list-img-desc">• 올린 사람 : 김동덕</p>
						<p class="result-list-img-desc">• 종료 날짜 : 2022년 6월 1일</p></li>
				</c:forEach>
			</ul>
		</c:forEach>
	</div>
</body>
</html>