<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
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

<title>펀딩상세</title>
<style type="text/css">
#bookImage {
	width: 400px;
	height: 500px;
	border: 3px solid #70D047;
}

#bookimg {
	object-fit: contain;
	width: 100%;
	height: 100%;
}

#title {
	font-size: 30px;
	color: #468f36;
}

#title2 {
	font-size: 25px;
	color: #468f36;
}

#content {
	font-size: 20px;
	color: #70D047;
}

#icons {
	object-fit: contain;
	width: 30px;
	height: 30px;
}

#shareicons {
	object-fit: contain;
	width: 50px;
	height: 50px;
	border-radius: 10%;
	margin: 20px;
}

#price {
	width: 20%;
	color: #ffffff;
	background-color: rgba(255, 255, 255, 0);
	border-top: none;
	border-left: none;
	border-right: none;
	border-bottom: 2px solid #ffffff;
	background-color: rgba(255, 255, 255, 0);
}

.row {
	margin-bottom: 15px;
}

.card {
	border: 1.5px solid #70D047;
	color: #468f36;
	margin: 10px;
}

.list-group-item {
	color: #468f36;
}
</style>
</head>
<body>

	<!-- <div th:replace="fragments/common :: header"></div> -->
	<div class="container">
		<div class="row">
			<!-- 책 이미지 -->
			<div class="col-5">
				<div id=bookImage>
					<img src="/images/sampleBook01.jpg" alt="책 이미지" id=bookimg>
				</div>
			</div>
			<!-- 책정보 -->
			<div class="col-6">
				<div class="row d-flex align-items-end">
					<div class="col">
						<c:choose>
							<c:when test="${dDay >= 0}">
								<span id=content>펀딩 종료까지 <c:out value="${dDay}"/>일 남았어요</span>
							</c:when>
							<c:otherwise>
								<span id=content>펀딩이 종료되었어요</span>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="row d-flex align-items-end">
					<div class="col-8">
						<span id=title><c:out value="${funding.title}"/></span>
					</div>
					<div class="col">
						<img src="/images/view.png" alt="조회수" id=icons><span id=content>
							<c:out value="${funding.views}"/> </span>
						<a href='<c:url value="/book/funding/${funding.funding_id}/likes.do"></c:url>'>
						<img src="${likes}" alt="좋아요" id=icons></a><span id=content>
								<c:out value="${likesCount}"/> </span>
					</div>
				</div>
				<div class="row">
					<br>
				</div>
				<div class="row d-flex align-items-end">
					<div class="col-3">
						<span id=content>저자</span>
					</div>
					<div class="col">
						<span id=title2><c:out value="${funding.author}"/></span>
					</div>
				</div>
				<div class="row">
					<div class="col-3">
						<span id=content>설명</span>
					</div>
					<div class="col"><c:out value="${funding.description}"/></div>
				</div>
				<div class="row d-flex align-items-end">
					<div class="col-3">
						<span id=content>올린 사람</span>
					</div>
					<div class="col">
						<span id=title2><c:out value="${funding.member.name}"/>(<c:out value="${funding.member.temperature}"/> ºC)</span>
					</div>
				</div>
				<div class="row d-flex align-items-end">
					<div class="col-3">
						<span id=content>지역</span>
					</div>
					<div class="col">
						<span id=title2><c:out value="${funding.member.address1}"/></span>
					</div>
				</div>
				<div id="row">
					<br>
					<div class="d-grid gap-2 col-8 mx-auto">
						<form id="createFundingOrder" method="GET" action="/book/funding/order.do">
							<button class="btn btn-success btn-lg" type="button" onclick="fundingOrder()">
								<input type="text" onkeypress="onlyNumber();" id=price name="price" /> 원 후원하기
							</button>
							<input type="hidden" name="fundingId" value="${funding.funding_id}" />
						</form>
					</div>
					<br>
				</div>
				<div id="row">
					<div class="col" align="center">
						<img src="/images/naver.png" alt="네이버" id=shareicons><img
							src="/images/kakao.png" alt="카카오" id=shareicons> <img
							src="/images/twitter.png" alt="트위터" id=shareicons><img
							src="/images/url.png" alt="url" id=shareicons>

					</div>
					<br>
				</div>
			</div>
		</div>
		<div class="row">
		<!-- achievementRate에 따라 색칠되는 비율이 다르도록 수정 -->
			<div class="card" style="text-align: center; background: linear-gradient(90deg, #AAEBAA 80%, #ffffff 50%);">
				<span id="title">
					목표 금액은 <c:out value="${funding.goal_amount}"/>원이에요<br>
					목표 달성률은 <c:out value="${achievementRate}"/>%에요
				</span>
			</div>
		</div>
		<!-- reward목록 -->
		<div class="row">
			<div class="row">
				<c:forEach var="reward" items="${rewardList}">
				<div class="col-4">
					<div class="card" style="width: 18rem;">
						<img src="${reward.image}" class="card-img-top" alt="리워드 이미지">
						<div class="card-body">
							<h5 class="card-title"><c:out value="${reward.price}"/>원 후원</h5>
						</div>
						<ul class="list-group list-group-flush">
							<li class="list-group-item"><c:out value="${reward.prize}"/></li>
						</ul>
					</div>
				</div>
				</c:forEach>
			</div>
		</div>
	</div>


	<!-- Option 1: Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>
		
	<script>
		/* 후원 금액 입력 시 숫자만 입력 가능하도록 동작 */
		function onlyNumber() {
			if (event.keyCode >= 48 && event.keyCode <= 57) {
				event.returnValue = true;
			} else {
				event.returnValue = false;
			}
		}
	
		/* 후원 버튼 클릭 시 동작 */
		function fundingOrder() {
			let price = document.getElementById('price').value.trim();
			
			if (price == '') {
				return;
			} else {
				price = parseInt(price);
			}
			
			if (price <= 0) {
				alert('올바른 금액을 입력해주세요');
			} else {
				if (confirm(price + '원을 후원하시겠습니까?')) {
					
					alert(price + '원 후원이 완료되었습니다');
				}
			}
			
			document.getElementById('price').value = '';
		}
	</script>
</body>
</html>