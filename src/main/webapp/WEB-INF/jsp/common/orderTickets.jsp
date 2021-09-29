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
            <c:if test="${param.bookedSeats == true}">
                <p class="errorsM"><fmt:message key="order.booked.seats.true"/></p>
            </c:if>
            <c:if test="${param.orderFalse == true}">
                <p class="errorsM"><fmt:message key="order.save.false"/></p>
            </c:if>
            <h1 style="color: #a71d2a"><c:out value="${movie.title}"/></h1>
            <h3><fmt:message key="show.movie.session.title"/>
                <c:out value="${movieSession.showDate}"/> --
                <c:out value="${movieSession.showTime}"/>
            </h3>

            <form method="POST" action="${pageContext.request.contextPath}/addOrder/${movieSession.id}">
                <div class="grid-container">

                    <c:forEach items="${allSeats}" var="seat">
                        <div class="grid-item">
                            <c:if test="${seat.status.name().equals('BOOKED')}">
                                <img src="${pageContext.request.contextPath}resources/img/seat_booked.jpg" alt="seat_booked" width="60px" height="60px" title="BOOKED">
                                <c:out value="${seat.number}"/>
                            </c:if>
                            <c:if test="${seat.status.name().equals('FREE')}">
                                <img src="${pageContext.request.contextPath}resources/img/seat_icon.jpg" alt="seat" width="60px" height="60px" title="FREE">
                                <input class="form-check-input" type="checkbox" id="inlineCheckbox1" name="seatId" value="${seat.id}">
                                <label class="form-check-label" for="inlineCheckbox1"></label>
                                <c:out value="${seat.number}"/>
                            </c:if>
                        </div>
                    </c:forEach>
                </div>
                <div class="order">
                    <div style="height: 10px"></div>
                    <div class="container" style="display: flex;">
                        <button type="submit" class="buttonSub"><fmt:message key="order.add.order"/></button>
                    </div>
                    <div style="height: 100px"></div>
                </div>

            </form>

        </div>
    </header>

</div>
</html>
