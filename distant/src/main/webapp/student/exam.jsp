<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="../css/app.css">
    <title>Distant exam</title>
</head>
<body>
<%@ include file="/header.jsp" %>

<div class="parent-container">
    <div class="child-container">
        <form class="input-form" action="${pageContext.request.contextPath}/controller" method="Post">
            <input type = "hidden" name = "command" value = "examanswer" >
            <c:forEach var="question" items="${sessionScope.examQuestions}">
                <p><b>${question.question}</b></p>
                <p><input name="${question.questionId}" type="radio" value="1">${question.answer1}</p>
                <p><input name="${question.questionId}" type="radio" value="2">${question.answer2}</p>
                <p><input name="${question.questionId}" type="radio" value="3" checked>${question.answer3}</p>
            </c:forEach>
            <button><fmt:message key="con.buttonsubmit"/></button>
        </form>

    </div>
</div>
</body>
</html>

