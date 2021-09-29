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
        <section class="slider_home">
            <ul id="main_slider">
                <li>
                    <img src="${pageContext.request.contextPath}resources/img/multiplex_main.jpg" class="desktop-slide" width="1680"
                         height="1050" alt="main_picture">
                </li>
            </ul>
        </section>
    </header>
</div>
<jsp:include page="/WEB-INF/jsp/parts/footer.jsp"/>
</body>
</html>