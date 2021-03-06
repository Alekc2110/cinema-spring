<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="${param.lang}">
<jsp:include page="/WEB-INF/jsp/parts/head_tag.jsp"/>

<body class="home blog">
<div id="wrapper">
    <header id="header">
        <jsp:include page="/WEB-INF/jsp/parts/header.jsp"/>
    </header>

    <table id="tickets" class="table table-bordered table-sm">
        <thead class="thead-light">
        <tr>
            <th data-type="text" class="th-sm cursor"><fmt:message key="show.ticket.date"/>
                <img src="/resources/img/sort-icon.png" width="20px" height="20px" alt="sort">
            </th>
            <th data-type="text" class="th-sm cursor"><fmt:message key="show.ticket.time"/>
                <img src="/resources/img/sort-icon.png" width="20px" height="20px" alt="sort">
            </th>
            <th data-type="text" class="th-sm cursor"><fmt:message key="show.ticket.row.number"/>
                <img src="/resources/img/sort-icon.png" width="20px" height="20px" alt="sort">
            </th>
            <th data-type="text" class="th-sm cursor"><fmt:message key="show.ticket.seat.number"/>
                <img src="/resources/img/sort-icon.png" width="20px" height="20px" alt="sort">
            </th>
            <th data-type="text" class="th-sm cursor"><fmt:message key="show.ticket.price"/>
                <img src="/resources/img/sort-icon.png" width="20px" height="20px" alt="sort">
            </th>
        </tr>
        </thead>
        <tbody id="tbody">
        <c:if test="${data.size() > 0 }">
        <c:forEach items="${data}" var="ticket">
            <tr>
                <td><c:out value="${ticket.movieSession.showDate}"/></td>
                <td><c:out value="${ticket.movieSession.showTime}"/></td>
                <td><c:out value="${ticket.seat.line.number}"/></td>
                <td><c:out value="${ticket.seat.number}"/></td>
                <td><c:out value="${ticket.movieSession.ticketPrice}"/></td>
            </tr>
        </c:forEach>
        </c:if>
        </tbody>
    </table>
</div>
<c:if test="${data.size() > 0 }">
    <nav aria-label="...">
        <ul class="pagination pagination-sm justify-content-center">
            <c:forEach var="page" begin="0" end="${totalPages-1}">
                <li class="page-item">
                    <a class="page-link"
                       href="${pageContext.request.contextPath}/user/show/ticketsTable?page=${page}&size=${size}">
                        <c:out value="${page+1}"/></a>
                </li>
            </c:forEach>
        </ul>
    </nav>
</c:if>

<script src="/resources/js/orderSort.js"></script>
</body>
</html>