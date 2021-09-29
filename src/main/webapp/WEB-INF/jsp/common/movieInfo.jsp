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
        <div class="card">
            <div class="card-body">
                <h1 class="card-title"><c:out value="${movie.title}"/></h1>
                <h3 class="card-text"><small class="text-black-50"><fmt:message key="movie.director"/><c:out
                        value="${movie.director}"/></small></h3>
                <h3 class="card-text"><small class="text-black-50"><fmt:message key="movie.country"/><c:out
                        value="${movie.country}"/></small></h3>
                <h3 class="card-text"><small class="text-black-50"><fmt:message key="movie.year"/><c:out
                        value="${movie.year}"/></small></h3>
                <h3 class="card-text"><small class="text-black-50"><fmt:message key="movie.description"/><c:out
                        value="${movie.description}"/></small></h3>
            </div>
            <img class="card-img-bottom" src="<c:out value="${movie.photoUrl}"/>" alt="movie image cap"
                 style="width:50%; position: center">
            <a href="${pageContext.request.contextPath}/manageMovieOrder/${movie.id}" class="btn btn-primary"><fmt:message key="show.movie.order.tickets"/></a>
        </div>
    </header>
</div>
</html>
