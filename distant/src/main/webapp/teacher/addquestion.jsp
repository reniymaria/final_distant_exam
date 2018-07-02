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

<div class="parent-container">
    <div class="child-container">
        <form class="input-form" action="${pageContext.request.contextPath}/addquestion" method="Post">
            <input placeholder="<fmt:message key="con.question.title"/>" type="text" name="question"><br>
            <input placeholder="<fmt:message key="con.answer1"/>" type="text" name="answer1">
            <input placeholder="<fmt:message key="con.answer2"/>" type="text" name="answer2">
            <input placeholder="<fmt:message key="con.answer3"/>" type="text" name="answer3">
            <input placeholder="<fmt:message key="con.numbercoranswer"/>" type="text" name="correctAnswer">
            <input  type="hidden" name="subjectId" value ="${requestScope.subjectId}">
            <input  type="hidden" name="langId" value ="${requestScope.languageId}">
            <button><fmt:message key="con.buttonsubmit"/></button>
        </form>
    </div>
</div>
</body>
</html>
