<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

        <c:forEach items="${user.roles}" var="role">
            <c:if test="${role.name().equals('ADMIN')}">
                <form:form method="POST" action="${pageContext.request.contextPath}/admin/showStat">
                    <div class="form-group">
                        <h2><fmt:message key="user.account.show.stat"/></h2>
                        <label for="inputDate"><fmt:message key="user.account.enter.date"/></label>
                        <input style="width: 20%" type="date"  name="date" class="form-control" id="inputDate" value="${_csrf.token}">
                    </div>
                    <button type="submit" class="buttonSub"><fmt:message key="login.submit.btn"/></button>
                </form:form>
                <br>
                <div style="width: 30%" class="progress">
                    <div class="progress-bar" title="<c:out value="${}"/>%" style="width:<c:out value="${requestScope.percentage}"/>%"><c:out value="${percentage}"/>%</div>
                </div>
            </c:if>
        </c:forEach>
    </div>

</div>


<jsp:include page="/WEB-INF/jsp/parts/footer.jsp"/>
</body>
</html>