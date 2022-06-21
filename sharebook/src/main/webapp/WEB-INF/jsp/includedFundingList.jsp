<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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