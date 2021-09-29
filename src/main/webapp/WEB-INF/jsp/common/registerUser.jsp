<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="${param.lang}">
<jsp:include page="/WEB-INF/jsp/parts/head_tag.jsp"/>

<body class="home_blog">
<div id="wrapper">
    <header id="header">
        <jsp:include page="/WEB-INF/jsp/parts/header.jsp"/>
    </header>
    <div class="center">
        <div class="register">
            <form:form method="POST" modelAttribute="regForm" action="${pageContext.request.contextPath}/registration">
                <div class="container">
                    <h1><fmt:message key="register.title"/></h1>
                    <p><fmt:message key="register.ask.to.fill"/></p>
                    <hr>

                    <label for="name"><b><fmt:message key="register.name"/></b></label>

                    <form:errors cssStyle="color:red" path="name"><fmt:message
                            key="register.name.error"/></form:errors>
                    <input id="name" type="text" placeholder="<fmt:message key="register.name"/>" name="name" required/>

                    <label for="email"><b><fmt:message key="register.email"/></b></label>
                    <form:errors cssStyle="color:red" path="email"><fmt:message
                            key="register.email.error"/></form:errors>
                    <input id="email" type="email" placeholder="<fmt:message key="register.email"/>" name="email"
                           required/>
                    <hr>

                    <label for="pass"><b><fmt:message key="register.password"/></b></label>
                    <form:errors cssStyle="color:red" path="password"><fmt:message
                            key="register.password.error"/></form:errors>
                    <input id="pass" type="password" placeholder="<fmt:message key="register.enter.password"/>"
                           name="password" required/>

                    <label for="repPass"><b><fmt:message key="register.repeat.password"/></b></label>
                    <form:errors cssStyle="color:red" path="confirmPassword"><fmt:message
                            key="register.password.error"/></form:errors>
                    <input id="repPass" type="password" placeholder="<fmt:message key="register.repeat.password"/>"
                           name="confirmPassword" required/>
                    <hr>

                    <p><fmt:message key="register.agree.creating"/><a href="#">
                        <fmt:message key="register.terms.privacy"/></a>.</p>
                    <button type="submit" class="register_btn"><fmt:message key="register.btn.submit"/></button>
                </div>

                <div class="container signin">
                    <p><fmt:message key="register.have.acc"/> <a href="${pageContext.request.contextPath}/login">
                        <fmt:message key="register.sign.in"/></a>.
                    </p>
                </div>
                <div style="height: 60px"></div>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>