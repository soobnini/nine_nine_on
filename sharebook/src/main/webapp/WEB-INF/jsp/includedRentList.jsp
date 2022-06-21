<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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