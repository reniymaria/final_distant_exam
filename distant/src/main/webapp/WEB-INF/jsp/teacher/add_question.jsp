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
<fmt:message key="con.tooltip.answer" var="contooltipanswer"/>

<div class="parent-container">
    <div class="child-container align-center">
        <h1 class="form-title"><fmt:message key="con.subject.title"/>${requestScope.subject}</h1>
        <h1 class="form-title"><fmt:message key="con.language"/>${requestScope.lang}</h1>

        <form class="input-form" action="${pageContext.request.contextPath}/controller" method="Post">
            <input type="hidden" name="command" value="addquestion">
            <div class="error-message">
                <c:if test="${requestScope.errMsg != null}">
                    <c:out value="${requestScope.errMsg}"/>
                </c:if>
            </div>
            <textarea placeholder="<fmt:message key="con.question.title"/>" name="question" required></textarea><br>
            <input placeholder="<fmt:message key="con.answer1"/>" type="text" name="answer1" required>
            <input placeholder="<fmt:message key="con.answer2"/>" type="text" name="answer2" required>
            <input placeholder="<fmt:message key="con.answer3"/>" type="text" name="answer3" required>
            <input placeholder="<fmt:message key="con.numbercoranswer"/>" type="number" name="correctAnswer"
                   title="${contooltipanswer}" pattern="[1-3]{1}" required>

            <input type="hidden" name="subjectId" value="${requestScope.subjectId}">
            <input type="hidden" name="langId" value="${requestScope.langId}">
            <button><fmt:message key="con.buttonsubmit"/></button>
        </form>
    </div>
</div>
</body>
</html>
