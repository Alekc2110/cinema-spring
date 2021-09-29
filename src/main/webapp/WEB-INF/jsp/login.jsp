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
    <div class="center">
        <div class="register">
            <form:form method="POST" modelAttribute="userForm" action="${pageContext.request.contextPath}/login">
                <div class="container">

                    <h1><fmt:message key="login.sign.in"/></h1>
                    <p><fmt:message key="login.fill.form"/></p>
                    <hr>
                    <label for="name"><b><fmt:message key="login.title.username"/></b></label>
                    <input id="name" type="text" placeholder="<fmt:message key="login.name"/>" name="name" required>

                    <label for="password"><b><fmt:message key="login.title.password"/></b></label>
                    <input id="password" type="password" placeholder="<fmt:message key="login.password"/>"
                           name="password" required>

                    <hr>
                    <button type="submit" class="register_btn"><fmt:message key="login.submit.btn"/></button>
                </div>

                <div class="container sign_up">
                    <p><fmt:message key="login.have.no.acc"/> <a
                            href="${pageContext.request.contextPath}/registration">
                        <fmt:message key="login.sign.up"/></a>.</p>
                </div>
                <div style="height: 60px"></div>
            </form:form>
        </div>

    </div>
</div>
</body>
</html>