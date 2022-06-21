<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<thead>
    <tr>
        <th>번호</th>
        <th>분류</th>
        <th>제목</th>
        <th>작성자</th>
        <th>작성일</th>
        <th>조회</th>
    </tr>
</thead>
<tbody>
  <c:forEach var="communityList" items="${communityList}">
		<tr>
			<th><c:out value="${communityList.communityId}" /></th>
			<td>
				<c:choose>
					<c:when test="${communityList.category == '0'}"> 질문 </c:when>
					<c:when test="${communityList.category == '1'}"> 토론 </c:when>
					<c:when test="${communityList.category == '2'}"> 모집 </c:when>
				</c:choose>
			</td>
			
			<td><a href='<c:url value="/book/community/detail.do"><c:param name="commId" value="${communityList.communityId}"/></c:url>'><c:out value="${communityList.title}" /></a></td>
			<td><c:out value="${communityList.member.name}" /></td>  
			<td><fmt:formatDate pattern="yyyy-MM-dd" value="${communityList.upload_date}"/> </td>
			<td><c:out value="${communityList.views}" /></td>
		</tr>
	</c:forEach>	
</tbody>