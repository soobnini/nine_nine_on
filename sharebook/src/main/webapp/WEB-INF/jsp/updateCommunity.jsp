<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>게시글 수정</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
</head>
<body>
	<div th:replace="fragments/common :: header"></div>
	<div class="container w-75 py-5">
		<form method="post" action="/book/community/update.do">
			<div class="row mb-3">
				<label for="inputTitle" class="col-sm-2 col-form-label">제목</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="title" id="title"
						value="${Post.title}" />
				</div>
			</div>
			<div class="row mb-3">
				<div class="row mb-3 form-group">
					<label for="inputCategory" class="col-sm-2 col-form-label">분류</label>
					<div class="col-sm-10">
						<select class="col-sm-10 form-control" name="category" id="category">
							<option value="0">질문</option>
							<option value="1">토론</option>
							<option value="2">모집</option>
						</select>
					</div>
				</div>
			</div>
			<div class="row mb-3">
				<label for="inputContent" class="col-sm-2 col-form-label">내용</label>
				<div class="col-sm-10">
					<input type="hidden" name="cid" value="${Post.communityId}" />
					<textarea class="form-control" name="content" id="content"
						rows="15"><c:out value="${Post.content}"></c:out></textarea>
				</div>
			</div>
			<div class="d-grid gap-2 col-2 mx-auto">
				<button type="submit" class="btn btn-primary">등록</button>
			</div>
		</form>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>
</body>
</html>