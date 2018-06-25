<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Distant exam</title>
    <link rel="stylesheet" href="../css/app.css">
</head>
<body>
<%@ include file="/header.jsp" %>
<br>
<div>------------------------------------------------------------------------------</div>
<br>

<fmt:message key="con.hellolabel"/> <c:out value="${sessionScope['loginUser']}"/>
<br>
<a href="../index.jsp"><fmt:message key="con.logoutLabel"/></a>
<br>

<p>
    Add question:
</p>
<form action="${pageContext.request.contextPath}/update" method="Post">
    Question:<br>
    <input type=hidden name="subjectID" value="${requestScope.question.subjectId}">
    <input type="text" name="question" value="${requestScope.question.question}"><br>
    First answer:<br>
    <input type="text" name="answer1" value="${requestScope.question.answer1}">
    Second answer:<br>
    <input type="text" name="answer2" value="${requestScope.question.answer2}">
    Third answer:<br>
    <input type="text" name="answer3" value="${requestScope.question.answer3}">
    Number of correct answer:<br>
    <input type="text" name="correctAnswer" value="${requestScope.question.correctAnswer}">
    <input type=hidden name="subject" value="${requestScope.question.subject}">
    <button><fmt:message key="con.buttonsubmit"/></button>
</form>

</body>
</html>

