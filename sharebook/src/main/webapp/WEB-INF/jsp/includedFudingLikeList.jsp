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
     <c:forEach var="findingOrderList" items="${FundingOrderList}">
		<tr>
			<th><c:out value="${findingOrderList.funding.funding_id}" /></th>
			<td><a href='<c:url value="/book/funding/${findingOrderList.funding.funding_id}.do"></c:url>'><c:out value="${findingOrderList.funding.title}" /></a></td>
			<td><c:out value="${findingOrderList.funding.author}" /></td>
			<td><c:out value="${findingOrderList.funding.views}" /></td>
		</tr>
	</c:forEach>
</tbody>