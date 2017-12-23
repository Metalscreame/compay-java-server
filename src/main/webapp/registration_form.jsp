<%--
  Created by IntelliJ IDEA.
  User: Alex_D
  Date: 03.12.2017
  Time: 23:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Regidtration</title>
    <link href='style.css' rel='stylesheet' type='text/css'>
</head>
<body>

<form method='post' class='reg-form'>

    <div class=''>
        <label for='form_fname'>First Name: </label>
        <input type='text' id='form_fname' name='first_name'>
    </div>

    <div class=''>
        <label for='form_sname'>Second Name: </label>
        <input type='text' id='form_sname' name='second_name'>
    </div>

    <div class=''>
        <label for='form_email'>Email: </label>
        <input type='email' id='form_email' name='email'>
    </div>

    <div class=''>
        <label for='form_phone'>Phone: </label>
        <input type='text' id='form_phone' name='phone'>
    </div>

    <div class="">
        <input type="submit" value='Go'>
    </div>

</form>

</body>
</html>
