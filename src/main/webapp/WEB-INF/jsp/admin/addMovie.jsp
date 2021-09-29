<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="${param.lang}">
<jsp:include page="/WEB-INF/jsp/parts/head_tag.jsp"/>

<form:form id="form-edit-icon" modelAttribute="movie" class="form-horizontal" method="POST"
      action="${pageContext.request.contextPath}/admin/addMovie">
    <fieldset>
        <!-- Form Name -->
        <legend><fmt:message key="add.movie.table.title"/></legend>

        <!-- Title input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="title"><fmt:message key="show.movie.title"/></label>
            <form:errors cssStyle="color:red" path="title"><fmt:message
                    key="add.movie.button.bad.input"/></form:errors>
            <div class="col-md-5">
                <input
                        id="title"
                        name="title"
                        type="text"
                        class="form-control input-md"
                        required=""
                >
            </div>
        </div>

        <!-- Description -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="description"><fmt:message key="show.movie.description"/></label>
            <form:errors cssStyle="color:red" path="description"><fmt:message
                    key="add.movie.button.bad.input"/></form:errors>
            <div class="col-md-4">
                <textarea class="form-control" id="description" name="description"></textarea>
            </div>
        </div>

        <!-- Photo URL-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="photo"><fmt:message key="show.movie.photoUrl"/></label>
            <form:errors cssStyle="color:red" path="photoUrl"><fmt:message
                    key="add.movie.button.bad.input"/></form:errors>
            <div class="col-md-5">
                <input
                        id="photo"
                        name="photo"
                        type="text"
                        class="form-control input-md"
                        required=""
                />
            </div>
        </div>

        <!-- Director-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="director"><fmt:message key="show.movie.director"/></label>
            <form:errors cssStyle="color:red" path="director"><fmt:message
                    key="add.movie.button.bad.input"/></form:errors>
            <div class="col-md-5">
                <input
                        id="director"
                        name="director"
                        type="text"
                        class="form-control input-md"
                        required=""
                />
            </div>
        </div>

        <!-- Country-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="country"><fmt:message key="show.movie.country"/></label>
            <form:errors cssStyle="color:red" path="country"><fmt:message
                    key="add.movie.button.bad.input"/></form:errors>
            <div class="col-md-5">
                <input
                        id="country"
                        name="country"
                        type="text"
                        class="form-control input-md"
                        required=""
                />
            </div>
        </div>

        <!-- Year-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="year"><fmt:message key="show.movie.year"/></label>
            <form:errors cssStyle="color:red" path="year"><fmt:message
                    key="add.movie.button.bad.input"/></form:errors>
            <div class="col-md-5">
                <input
                        id="year"
                        name="year"
                        type="text"
                        class="form-control input-md"
                        required=""
                />
            </div>
        </div>

    </fieldset>
    <fieldset>
        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="save"></label>
            <div class="col-md-4">
                <button id="save" type="submit" name="save" class="btn btn-primary">
                    <fmt:message key="add.movie.button.save"/></button>
            </div>
        </div>
    </fieldset>
</form:form>
<!-- Button -->
<div class="form-group">
    <label class="col-md-4 control-label" for="close"></label>
    <div class="col-md-4">
        <a href="${pageContext.request.contextPath}/cinema/manageMovie">
            <button id="close" name="close" data-dismiss="modal" aria-label="Close" class="btn btn-info"><fmt:message
                    key="add.movie.button.close"/></button>
        </a>
    </div>
</div>
