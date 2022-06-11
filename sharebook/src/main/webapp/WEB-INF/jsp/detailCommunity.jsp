<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>게시글 보기</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
</head>
<body>

	<div class="container w-75 py-5">
		<table class="table mt-3">
			<tr>
				<th class="col-md-1 px-2 py-3">제목</th>
				<td class="px-2 py-3"><c:out value="${Post.title}" /></td>
			</tr>
			<tr>
				<th class="col-md-1 px-2 py-3">분류</th>
				<td class="px-2 py-3"><c:choose>

						<c:when test="${Post.category == '0'}"> 질문 </c:when>

						<c:when test="${Post.category == '1'}"> 토론 </c:when>

						<c:when test="${Post.category == '2'}"> 모집 </c:when>

					</c:choose></td>
			</tr>
			<tr>
				<th class="col-md-1 px-2 py-3">내용</th>
				<td class="px-2 py-3"><c:out value="${Post.content}" /></td>
			</tr>
			<tr>
				<td colspan="2" class="text-end border-0"><a
					class="btn btn-primary m-1" href="#" role="button">수정</a> <a
					class="btn btn-primary m-1 float-end" href="#" role="button">삭제</a>
				</td>
			</tr>
		</table>

		<form class="pt-2 ps-2">
			<div class="row mb-3">
				<label for="inputComment" class="col-sm-2 col-form-label"><strong>댓글</strong></label>
				<div class="col-sm-8">
					<textarea class="form-control" id="comment" rows="1"></textarea>
				</div>
				<div class="d-grid gap-2 col-2 mx-auto">
					<button type="submit" class="btn btn-primary">작성</button>
				</div>
			</div>
		</form>

		<table class="table mt-3">
		<c:forEach var="comment" items="${Comments}">
			<tr>
				<th>
				<td class="col-md-2"><c:out value="${comment.member.name}" /></td>
				</th>
				<td class="col-md-8"><c:out value="${comment.content}" /></td>
				<td class="text-center"><c:out value="${comment.upload_date}" /></td>
			</tr>
			</c:forEach>
		</table>
		<div class="text-center">
			<ul class="list-group list-group-horizontal justify-content-center">
				<li class="list-group-item"><a href="#">1</a></li>
				<li class="list-group-item"><a href="#">2</a></li>
				<li class="list-group-item"><a href="#">3</a></li>
			</ul>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>
</body>
</html>