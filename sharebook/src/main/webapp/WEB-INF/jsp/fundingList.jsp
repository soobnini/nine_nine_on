<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<title>이웃 책장 : 종이책 공유 플랫폼</title>
<link rel="stylesheet" href="/css/main.css">
<!-- 거의 완전 똑같기 때문에 css 공유 -->
<link rel="stylesheet" href="/css/bookList.css">
</head>
	<%@ include file="header_funding.jsp"%>
	<div id="result-book">
		<form id="result-sort-form" method="GET" action="/book/funding.do">
			<select id="result-sort-select" name="sortType" onchange="this.form.submit()">
				<option disabled selected>정렬 기준</option>
				<option value="1">가나다순</option>
				<option value="2">저자순</option>
				<option value="3">종료 날짜순</option>
				<option value="4">조회순</option>
			</select>
			<input type="hidden" id="sortingType" value="${sortingType}" />
		</form>
		
 		<c:forEach var="funding" items="${fundingList}" varStatus="status">
			<c:if test="${status.index % 5 eq 0}">
				<ul class="result-list">
			</c:if>
			<li class="result-list-content">
				<a href='<c:url value="/book/funding/${funding.funding_id}.do"></c:url>'>
				<img src="${funding.image}" class="result-list-img"></a>
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
	
	<script>
		let sortingType = document.getElementById('sortingType').value;
		let select = document.getElementById('result-sort-select');
		let len = select.options.length;
		for (let i = 0; i < len; i++) {
			if (select.options[i].value == sortingType) {
				select.options[i].selected = true;
			}
		}
	</script>
</body>
</html>