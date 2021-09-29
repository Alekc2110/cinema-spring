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
</div>

<div class="card bg-dark text-white">
    <img src="${pageContext.request.contextPath}resources/img/multiplex_main.jpg" width="1680" height="1050"
         alt="main picture">
    <div class="card-img-overlay justify-content-end">
        <c:forEach items="${user.roles}" var="role">
            <c:if test="${role.name().equals('USER')}">
                <img src="${pageContext.request.contextPath}resources/img/client-image.jpg" width="120px" height="120px"
                     alt="user_icon">
            </c:if>
            <c:if test="${role.name().equals('ADMIN')}">
                <img src="${pageContext.request.contextPath}resources/img/admin-image.jpg" width="120px" height="120px"
                     alt="user_icon">
            </c:if>
        </c:forEach>

        <h5 style="color: #ffffff" class="card-title"><fmt:message key="user.account.role"/>
            <c:forEach items="${user.roles}" var="role">
                <c:out value="${role}"/>
            </c:forEach>
        </h5>
        <br/>
        <h5 style="color: #ffffff" class="card-title"><fmt:message key="user.account.name"/><c:out
                value="${user.name}"/></h5>
        <br/>
        <h5 style="color: #ffffff" class="card-title"><fmt:message key="user.account.email"/><c:out
                value="${user.email}"/></h5>
    </div>
</div>


<jsp:include page="/WEB-INF/jsp/parts/footer.jsp"/>
</body>
</html>