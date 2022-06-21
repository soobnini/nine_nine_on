<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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