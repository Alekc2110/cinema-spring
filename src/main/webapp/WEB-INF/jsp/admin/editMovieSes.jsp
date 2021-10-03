<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="${param.lang}">
<jsp:include page="/WEB-INF/jsp/parts/head_tag.jsp"/>

<form id="form-edit-icon" class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/admin/${movieSesEdit.id}/editMovieSession">
    <fieldset>
        <!-- Form Name -->
        <legend><c:out value="${movieTitle}"/></legend>

        <!-- Show_date input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="showDate"><fmt:message key="update.movie.session.showtime"/></label>
            <div class="col-md-5">
                <input
                        id="showDate"
                        name="showDate"
                        type="date"
                        class="form-control input-md"
                        data-pattern-error="<fmt:message key='data.non-valid'/>"
                        data-required-error="<fmt:message key='data.required'/>"
                        required pattern="^[0-9]{4}-[0-9]{2}-[0-9]{2}$"
                        value="<c:out value="${movieSesEdit.showDate}"/>"
                >
            </div>
        </div>

        <!-- Show_time input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="showTime"><fmt:message key="update.movie.session.showtime"/></label>
            <div class="col-md-5">
                <input
                        id="showTime"
                        name="showTime"
                        type="time"
                        class="form-control input-md"
                        data-pattern-error="<fmt:message key='data.non-valid'/>"
                        data-required-error="<fmt:message key='data.required'/>"
                        required pattern="^[0-9]{2}:[0-9]{2}$"
                        value="<c:out value="${movieSesEdit.showTime}"/>"
                >
            </div>
        </div>

        <!-- Price-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="ticketPrice"><fmt:message key="update.movie.session.ticket.price"/></label>
            <div class="col-md-5">
                <input
                        id="ticketPrice"
                        name="ticketPrice"
                        type="text"
                        class="form-control input-md"
                        data-pattern-error="<fmt:message key='data.non-valid'/>"
                        data-required-error="<fmt:message key='data.required'/>"
                        required pattern="^[0-9]{1,3}$"

                        value="<c:out value="${movieSesEdit.ticketPrice}"/>"
                />
            </div>
        </div>
    </fieldset>
    <fieldset>
        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="save"></label>
            <div class="col-md-4">
                <button id="save" type="submit" class="btn btn-primary"><fmt:message key="update.movie.session.button.save"/></button>
            </div>
        </div>
    </fieldset>
</form>
<!-- Button -->
<div class="form-group">
    <label class="col-md-4 control-label" for="close"></label>
    <div class="col-md-4">
        <a href="${pageContext.request.contextPath}/admin/manageMovie">
            <button  id="close" name="close" data-dismiss="modal" aria-label="Close" class="btn btn-info"><fmt:message key="update.movie.session.button.close"/></button>
        </a>
    </div>
</div>
