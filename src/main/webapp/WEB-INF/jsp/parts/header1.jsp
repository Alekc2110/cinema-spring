<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page isELIgnored="false" %>
<%@ page isELIgnored="false" %>

<div style="margin: 0; padding-left: 0" class="nav justify-content-end">
    <div>
        <div class="lang_block">
            <div>
                <h4>
                    <sec:authorize access="hasAnyRole('ADMIN', 'USER')">
                        <a href="${pageContext.request.contextPath}/profile">
                            <fmt:message key="nav.bar.profile"/></a>
                    </sec:authorize>
                </h4>
            </div>
        </div>
    </div>
</div>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link active" href="${pageContext.request.contextPath}/nowShowing">
                <fmt:message key="nav.bar.now.showing"/></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/info">
                <fmt:message key="nav.bar.contacts"/></a>
        </li>
        <li class="nav-item">
            <sec:authorize access="hasRole('ADMIN')">
                <a class="nav-link" href="${pageContext.request.contextPath}/admin/manageMovie">
                    <fmt:message key="nav.bar.all.movies"/></a>
            </sec:authorize>
        </li>

        <li class="nav-item">
            <sec:authorize access="hasAnyRole('ADMIN', 'USER')">
                <a class="nav-link" href="${pageContext.request.contextPath}/cinema/showMovieTable">
                    <fmt:message key="nav.bar.all.movies.table"/></a>
            </sec:authorize>
        </li>

        <li class="nav-item">
            <sec:authorize access="hasRole('USER')">
                <a class="nav-link" href="${pageContext.request.contextPath}/cinema/movieTimeTable">
                    <fmt:message key="nav.bar.all.movies.timetable"/></a>
            </sec:authorize>
        </li>

        <li class="nav-item">
            <sec:authorize access="hasAnyRole('ADMIN', 'USER')">
                <a class="nav-link" href="${pageContext.request.contextPath}/cinema/ticketsTable">
                    <fmt:message key="nav.bar.all.movies.tickets"/></a>
            </sec:authorize>
        </li>

        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/login">
                <fmt:message key="user.account.login"/></a>
        </li>
        <li class="nav-item">
            <sec:authorize access="isAuthenticated()">
                <a class="nav-link" href="${pageContext.request.contextPath}/logout">
                    <fmt:message key="user.account.logout"/></a>
            </sec:authorize>
        </li>
    </ul>
</nav>

