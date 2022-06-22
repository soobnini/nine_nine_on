<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
:root {
	--achievementRate: 11%;
}

#achieve {
	text-align: center;
	background: linear-gradient(to right, #AAEBAA var(--achievementRate), #ffffff var(--achievementRate));
}

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
	<%@ include file="header_funding.jsp"%>

	<!-- <div th:replace="fragments/common :: header"></div> -->
	<div class="container">
		<div class="row">
			<!-- 책 이미지 -->
			<div class="col-5">
				<div id=bookImage>
					<img src="${funding.image}" alt="책 이미지" id=bookimg>
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
						<form id="createFundingOrder" method="POST" action="/book/funding/order.do">
							<button class="btn btn-success btn-lg" type="button" onclick="fundingOrder()">
								<input type="text" onkeypress="onlyNumber();" id=price name="price" /> 원 후원하기
							</button>
							<input type="hidden" name="fundingId" value="${funding.funding_id}" />
						</form>
						<input type="hidden" id="orderSuccess" value="${orderSuccess}" />
						<input type="hidden" id="orderSuccessPrice" value="${orderSuccessPrice}" />
						<input type="hidden" id="getReward" value="${getReward}" />
					</div>
					<br>
				</div>
				<div id="row">
					<div class="col" align="center">
						<img src="/images/facebook.jpg" alt="페이스북" id=shareicons onclick="js:shareFacebook()"> 
						<img src="/images/kakao.png" alt="카카오" id=shareicons onclick="js:shareKakao()"> 
						<img src="/images/twitter.png" alt="트위터" id=shareicons onclick="js:shareTwitter()">
						<img src="/images/url.png" alt="url" id=shareicons onclick="js:shareURL()">

					</div>
					<br>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="card" id="achieve">
				<span id="title">
					목표 금액은 <c:out value="${funding.goal_amount}"/>원이에요<br>
					목표 달성률은 <c:out value="${achievementRate}"/>%에요
				</span>
				<input type="hidden" id="achievementRate" value="${achievementRate}" />
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
					
				}
			}
			
			document.getElementById('createFundingOrder').submit();
		}
		
		/* order 후 동작 */
		let orderSuccess = document.getElementById('orderSuccess').value;
		let orderSuccessPrice = document.getElementById('orderSuccessPrice').value;
		let getReward = document.getElementById('getReward').value;
		if (orderSuccess == 1) {
			let message = orderSuccessPrice + '원 후원이 완료되었습니다';
			if (getReward.trim() != "") {
				message += '\n' + getReward + '를 획득하셨습니다';
			}
			message += '\n감사합니다';
			alert(message);
		} else if (orderSuccess == -1) {
			alert('후원이 완료되지 않았습니다');
		}
		
		/* 달성률 그래프 설정 */
		let achievementRate = document.getElementById('achievementRate').value;
		document.documentElement.style.setProperty("--achievementRate", achievementRate + '%');
	</script>
	
	<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
    <script>
        Kakao.init('476a4c18380b56b521177a94457bb719');
        Kakao.isInitialized();
    </script>
    
    <script>
	    const prefix = 'http://localhost:8080/book/funding/';
	    const book_Id = '${funding.funding_id}';
	    const suffix = '.do';
	    const shareUrl = prefix + book_Id + suffix;
	    const title = '${funding.title}';
	    const description = '${funding.description}';
	
	    function shareKakao() {
	      var shareImage = 'https://ifh.cc/g/ysmnbB.png';
	      const shareTitle = title;
	      const shareDes = description;
	
	      Kakao.Link.sendDefault({
	        objectType: 'feed',
	        content: {
	          title: shareTitle,
	          description: shareDes,
	          imageUrl: shareImage,
	          link: {
	            mobileWebUrl: shareUrl,
	            webUrl: shareUrl
	          },
	        },
	        buttons: [
	          {
	            title: '펀딩 확인하기',
	            link: {
	              mobileWebUrl: shareUrl,
	              webUrl: shareUrl
	            },
	          }
	        ]
	      });
	    }
	
		    
	    function shareTwitter() {
	        var sendText = "이웃책장";
	        window.open("https://twitter.com/intent/tweet?text=" + sendText + "&url=" + shareUrl);
	    }
	    
	    function shareFacebook() {
	    	var sendUrl = shareUrl; // 전달할 URL
	        window.open("http://www.facebook.com/sharer/sharer.php?u=" + sendUrl);
	    }

	    function shareURL() {
	      var url = shareUrl;
	      var textarea = document.createElement("textarea");
	      document.body.appendChild(textarea);
	      textarea.value = url;
	      textarea.select();
	      document.execCommand("copy");
	      document.body.removeChild(textarea);
	      alert("URL이 복사되었습니다.");
	    }
    </script>
</body>
</html>