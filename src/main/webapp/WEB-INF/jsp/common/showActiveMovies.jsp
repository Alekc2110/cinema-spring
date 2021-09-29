<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>


<!DOCTYPE html>
<html lang="${param.lang}">
<jsp:include page="/WEB-INF/jsp/parts/head_tag.jsp"/>

<body class="home">

<div id="wrapper">
    <header id="header">
        <jsp:include page="/WEB-INF/jsp/parts/header.jsp"/>
        <div class="card">
            <div class="card-img-overlay">
                <div class="card-deck">
                    <div class="card bg-primary">
                        <div class="card-body text-center">
                            <c:forEach items="${activeMovies}" var="movie">
                            <div class="container">
                                <h2 style="color: #141315"><c:out value="${movie.title}"/></h2>
                                <p style="color: #141315" class="card-text"><fmt:message key="movie.director"/><c:out value="${movie.director}"/></p>
                                <p style="color: #141315" class="card-text"><fmt:message key="movie.country"/><c:out value="${movie.country}"/></p>
                                <p style="color: #141315" class="card-text"><fmt:message key="movie.year"/><c:out value="${movie.year}"/></p>
                                <div class="card" style="width:400px">
                                    <img class="card-img-top" src="<c:out value="${movie.photoUrl}"/>"
                                         alt="Movie image" style="width:100%">
                                    <div class="card-body">
                                     <a href="${pageContext.request.contextPath}/movieDetail/${movie.id}"  class="btn btn-primary"><fmt:message key="show.movie.details"/></a>
                                    </div>
                                </div>
                                <hr>
                                </c:forEach>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </header>
</div>
</body>
</html>