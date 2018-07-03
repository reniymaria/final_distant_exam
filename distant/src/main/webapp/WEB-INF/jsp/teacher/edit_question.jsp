<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="../css/app.css">
</head>
<body>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="parent-container">
    <div class="child-container">
        <form class="input-form" action="${pageContext.request.contextPath}/update" method="Post">
            <input type="hidden" name="questionId" value="${requestScope.question.questionId}">
            <input type="text" name="question" value="${requestScope.question.question}"><br>
            <input type="text" name="answer1" value="${requestScope.question.answer1}">
            <input type="text" name="answer2" value="${requestScope.question.answer2}">
            <input type="text" name="answer3" value="${requestScope.question.answer3}">
            <input type="text" name="correctAnswer" value="${requestScope.question.correctAnswer}">
            <input type="hidden" name="subjectId" value="${requestScope.question.subjectId}">
            <input type="hidden" name="langId" value="${requestScope.question.languageId}">
            <button><fmt:message key="con.buttonsubmit"/></button>
        </form>
    </div>
</div>
</body>
</html>
