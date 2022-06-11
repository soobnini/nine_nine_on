<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<link rel="stylesheet" href="/css/main.css">
<!-- 거의 완전 똑같기 때문에 css 공유 -->
<link rel="stylesheet" href="/css/bookList.css">
</head>
<body>
	<!-- <div th:replace="fragments/common :: header"></div> -->
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
		
 		<c:forEach var="funding" items="${fundingList}" varStatus="status">
			<c:if test="${status.index % 5 eq 0}">
				<ul class="result-list">
			</c:if>
			<li class="result-list-content">
				<img src="/images/sampleBook01.jpg" class="result-list-img">
				<h6 class="item-list-img-title">
					<b><c:out value="${funding.title}"/></b>
				</h6>
				<p class="result-list-img-writer"><c:out value="${funding.author}"/></p>
				<p class="result-list-img-desc">• 올린 사람 : <c:out value="${funding.member.name}"/></p>
				<p class="result-list-img-desc">• 종료 날짜 : <fmt:formatDate value="${funding.deadline}" pattern="yyyy-MM-dd"/></p>
			</li>
			<c:if test="${status.index % 5 eq 4}">
				</ul>
			</c:if>
		</c:forEach>
	</div>
</body>
</html>