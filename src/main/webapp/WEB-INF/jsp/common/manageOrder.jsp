<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="${param.lang}">
<jsp:include page="/WEB-INF/jsp/parts/head_tag.jsp"/>

<div id="wrapper">
    <header id="header">
        <jsp:include page="/WEB-INF/jsp/parts/header1.jsp"/>
        <div style="width: 100%;" class="container">

            <img src="<c:out value="${activeMovie.photoUrl}"/>" alt="movie image url"
                 style="vertical-align: middle" width=600 height=600>
            <span style="vertical-align: middle; display: inline-block; width: 400px;">
                 <h3><fmt:message key="show.movie.session.table.title"/></h3>
                <c:forEach items="${movieSessions}" var="movieSession">
                    <p>
                        <a style="color: black" href="${pageContext.request.contextPath}/orderTickets/${movieSession.id}">
                            <c:out value="${movieSession.showDate}"/> --
                            <c:out value="${movieSession.showTime}"/>
                            <fmt:message key="show.movie.session.price"/>
                            <c:out value="${movieSession.ticketPrice}"/>
                            <fmt:message key="show.movie.session.currency"/>
                        </a>
                        <br>
                    </p>
                </c:forEach>
            </span>
        </div>
    </header>
</div>
</html>
