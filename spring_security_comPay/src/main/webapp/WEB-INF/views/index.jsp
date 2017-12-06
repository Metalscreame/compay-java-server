<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE-edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Spring Security</title>
    <link href="/pages/css/bootstrap.css " rel="stylesheet">
    <link href="/pages/css/jumbotron-narrow.css " rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>

<%--<body background="https://steamuserimages-a.akamaihd.net/ugc/481147707246687394/F2F921D39F3B8EB94067E73F0C4FE74BB8A354CF/">--%>
<div class="container">

    <div class="jumbotron" style="margin-top :20px;">
        <h1>Welcom to ComPay</h1>
        <p class="lead">
            Войдите в свой кабинет или заполните форму регистрации
        <p>//эту страницу можно убрать будет</p>
            _____________________________________________
        </p>

        <sec:authorize access="!isAuthenticated()">
            <p><a class="btn btn-lg btn-success" href="<c:url value="/login" />" role="button">Войти</a></p>
        </sec:authorize>



        <sec:authorize access="isAuthenticated()">
            <p>Ваш логин: <sec:authentication property="principal.username" /></p>
            <p> <a class="btn btn-lg btn-danger" href="<c:url value="/logout" />" role="button">Выйти</a></p>
        </sec:authorize>


    </div>
    <div class="footer">
        <p> ComPay </p>
    </div>
</div>
</body>
</html>
