<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="../css/app.css">
</head>
<body>
<%@ include file="/header.jsp" %>
<br>
<div>------------------------------------------------------------------------------</div>
<br>
<p>
    Add question:
</p>
<form action="${pageContext.request.contextPath}/addquestion" method = "Post">
    Question:<br>
    <input type="text" name="question"><br>
    First answer:<br>
    <input type="text" name="answer1">
    Second answer:<br>
    <input type="text" name="answer2">
    Third answer:<br>
    <input type="text" name="answer3">
    Number of correct answer:<br>
    <input type="text" name="correctAnswer">
    Subject:<br>
    <input type="text" name="subject">
    <button><fmt:message key = "con.buttonsubmit"/></button>
</form>

</body>
</html>
