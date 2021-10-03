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
                    <div class="grid-containerTable">
                        <div class="grid-itemTable">
                            <h2><c:out value="${movieListNow.get(0).date}"/></h2>
                            <h4>
                                <c:forEach items="${movieListNow}" var="timeTableDto">
                                    <strong style="color: #a71d2a"><c:out value="${timeTableDto.time}"/></strong> : <c:out value="${timeTableDto.movieTitle}"/>
                                    <br>
                                </c:forEach>
                            </h4>
                        </div>

                        <div class="grid-itemTable">
                            <h2><c:out value="${movieListTomorrow.get(0).date}"/></h2>
                            <h4>
                                <c:forEach items="${movieListTomorrow}" var="timeTableDto">
                                    <strong style="color: #a71d2a"><c:out value="${timeTableDto.time}"/></strong> : <c:out value="${timeTableDto.movieTitle}"/>
                                    <br>
                                </c:forEach>
                            </h4>
                        </div>

                        <div class="grid-itemTable">
                            <h2><c:out value="${movieListNowPlTwo.get(0).date}"/></h2>
                            <h4>
                                <c:forEach items="${movieListNowPlTwo}" var="timeTableDto">
                                    <strong style="color: #a71d2a"><c:out value="${timeTableDto.time}"/></strong> : <c:out value="${timeTableDto.movieTitle}"/>
                                    <br>
                                </c:forEach>
                            </h4>
                        </div>

                        <div class="grid-itemTable">
                            <h2><c:out value="${movieListNowPlThree.get(0).date}"/></h2>
                            <h4>
                                <c:forEach items="${movieListNowPlThree}" var="timeTableDto">
                                    <strong style="color: #a71d2a"><c:out value="${timeTableDto.time}"/></strong> : <c:out value="${timeTableDto.movieTitle}"/>
                                    <br>
                                </c:forEach>
                            </h4>
                        </div>

                        <div class="grid-itemTable">
                            <h2><c:out value="${movieListNowPlFour.get(0).date}"/></h2>
                            <h4>
                                <c:forEach items="${movieListNowPlFour}" var="timeTableDto">
                                    <strong style="color: #a71d2a"><c:out value="${timeTableDto.time}"/></strong> : <c:out value="${timeTableDto.movieTitle}"/>
                                    <br>
                                </c:forEach>
                            </h4>
                        </div>

                        <div class="grid-itemTable">
                            <h2><c:out value="${movieListNowPlFive.get(0).date}"/></h2>
                            <h4>
                                <c:forEach items="${movieListNowPlFive}" var="timeTableDto">
                                    <strong style="color: #a71d2a"><c:out value="${timeTableDto.time}"/></strong> : <c:out value="${timeTableDto.movieTitle}"/>
                                    <br>
                                </c:forEach>
                            </h4>
                        </div>

                        <div class="grid-itemTable">
                            <h2><c:out value="${movieListNowPlSix.get(0).date}"/></h2>
                            <h4>
                                <c:forEach items="${movieListNowPlSix}" var="timeTableDto">
                                    <strong style="color: #a71d2a"><c:out value="${timeTableDto.time}"/></strong> : <c:out value="${timeTableDto.movieTitle}"/>
                                    <br>
                                </c:forEach>
                            </h4>
                        </div>
                    </div>
                    <img src="/resources/img/multiplex_main.jpg" class="desktop-slide" width="1680"
                         height="1050" alt="main_picture">
                </li>
            </ul>
        </section>
    </header>
</div>
<jsp:include page="/WEB-INF/jsp/parts/footer.jsp"/>
</body>
</html>