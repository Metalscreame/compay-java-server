<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE-edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>ComPay</title>
    <link href="/pages/css/bootstrap.css " rel="stylesheet">
    <link href="/pages/css/jumbotron-narrow.css " rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<%--<body background="https://steamuserimages-a.akamaihd.net/ugc/481147707246687394/F2F921D39F3B8EB94067E73F0C4FE74BB8A354CF/">--%>


<body>
<div class="container" style="width: 300px;">
    <c:url value="/j_spring_security_check" var="loginUrl" />
    <form action="<c:url value="/j_spring_security_check"></c:url>" method="post" role="form">
        <h1 class="form-signin-heading">Авторизация</h1>
        <input type="text" class="form-control" name="j_username" placeholder="Email address" required autofocus value="">
        <input type="password" class="form-control" name="j_password" placeholder="Password" required value="">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>

     </form>
    <sec:authorize access="!isAuthenticated()">
        <p>

        <p><a class="" href="<c:url value="/help" />" role="button">Забыли пароль?</a></p>

        <p><a class="" href="<c:url value="/registration_form"/>" role="button">Регистрация</a></p>
        </p>
    </sec:authorize>
</div>
</body>
</html>