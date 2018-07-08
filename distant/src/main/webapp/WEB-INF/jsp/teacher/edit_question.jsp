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
    <div class="child-container">

        <h1 class="form-title"><fmt:message key="con.subject.title"/>${requestScope.subject}</h1>
        <h1 class="form-title"><fmt:message key="con.language"/>${requestScope.lang}</h1>

        <form class="input-form" action="${pageContext.request.contextPath}/controller" method="Post">
            <input type="hidden" name="questionId" value="${requestScope.question.questionId}">
            <input type="hidden" name="command" value="update_question">
            <div class="error-message">
                <c:if test="${requestScope.emptyMess1 != null}">
                    <c:out value="${requestScope.emptyMess1}"/>
                </c:if>
            </div>
            <textarea name="question">${requestScope.question.question}</textarea><br/>
            <div class="error-message">
                <c:if test="${requestScope.emptyMess2 != null}">
                    <c:out value="${requestScope.emptyMess2}"/>
                </c:if>
            </div>
            <input type="text" name="answer1" value="${requestScope.question.answer1}">
            <div class="error-message">
                <c:if test="${requestScope.emptyMess3 != null}">
                    <c:out value="${requestScope.emptyMess3}"/>
                </c:if>
            </div>
            <input type="text" name="answer2" value="${requestScope.question.answer2}">
            <div class="error-message">
                <c:if test="${requestScope.emptyMess4 != null}">
                    <c:out value="${requestScope.emptyMess4}"/>
                </c:if>
            </div>
            <input type="text" name="answer3" value="${requestScope.question.answer3}">
            <div class="error-message">
                <c:if test="${requestScope.emptyMess5 != null}">
                    <c:out value="${requestScope.emptyMess5}"/>
                </c:if>
            </div>
            <input type="number" name="correctAnswer" title="${contooltipanswer}" pattern="[1-3]{1}" value="${requestScope.question.correctAnswer}">
            <button><fmt:message key="con.buttonsubmit"/></button>
        </form>
    </div>
</div>
</body>
</html>
