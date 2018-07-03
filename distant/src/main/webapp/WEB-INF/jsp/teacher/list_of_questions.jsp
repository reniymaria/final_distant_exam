<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <link rel="stylesheet" href="../css/app.css">
</head>
<body>

<%@ include file="/WEB-INF/jsp/header.jsp" %>

<fmt:message key="con.question.title" var="conquestiontitle"/>
<fmt:message key="con.answer1" var="conanswer1"/>
<fmt:message key="con.answer2" var="conanswer2"/>
<fmt:message key="con.answer3" var="conanswer3"/>
<fmt:message key="con.numbercoranswer" var="connumbercoranswer"/>
<fmt:message key="con.action" var="conaction"/>

<c:choose>
    <c:when test="${requestScope.listEmpty != null}">
        <div class="parent-container">
            <div class="child-container">
                <div class="error-message">
                    <c:out value="${requestScope.listEmpty}"/>
                </div>
                <form class="input-form" action="${pageContext.request.contextPath}/addquestion" method="Get">
                    <button><fmt:message key="con.addquestionlabel"/></button>
                    <input type="hidden" name="subjectId" value="${requestScope.subjectId}"/>
                    <input type="hidden" name="langId" value="${requestScope.langId}"/>
                </form>
            </div>
        </div>
    </c:when>

    <c:otherwise>
        <div class="parent-container">
            <div class="child-container">
                <table border="1" cellpadding="5" cellspacing="5">
                    <tr>
                        <th>${conquestiontitle}</th>
                        <th>${conanswer1}</th>
                        <th>${conanswer2}</th>
                        <th>${conanswer3}</th>
                        <th>${connumbercoranswer}</th>
                        <th>${conaction}</th>
                    </tr>
                    <c:forEach var="question" items="${requestScope.QuestionList}">
                        <tr>
                            <td>${question.question}</td>
                            <td>${question.answer1}</td>
                            <td>${question.answer2}</td>
                            <td>${question.answer3}</td>
                            <td>${question.correctAnswer}</td>
                            <td>
                                <form class="input-form" action="${pageContext.request.contextPath}/update"
                                      method="get">
                                    <button><fmt:message key="con.edit"/></button>
                                    <input type="hidden" name="questionID" value="${question.questionId}"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <table border="1" cellpadding="5" cellspacing="5">
                    <tr>
                        <c:if test="${requestScope.currentPage != 1}">
                            <td>
                                <a href="${pageContext.request.contextPath}/questions?page=${requestScope.currentPage - 1}"><fmt:message
                                        key="con.prev"/></a></td>
                        </c:if>

                        <c:forEach begin="1" end="${requestScope.noOfPages}" var="i">
                            <c:choose>
                                <c:when test="${requestScope.currentPage eq i}">
                                    <td>${i}</td>
                                </c:when>
                                <c:otherwise>
                                    <td><a href="${pageContext.request.contextPath}/questions?page=${i}">${i}</a></td>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:if test="${requestScope.currentPage lt requestScope.noOfPages}">
                            <td>
                                <a href="${pageContext.request.contextPath}/questions?page=${requestScope.currentPage + 1}"><fmt:message
                                        key="con.next"/></a></td>
                        </c:if>
                    </tr>
                </table>
                <br>
                <form class="input-form" action="${pageContext.request.contextPath}/addquestion" method="Get">
                    <button><fmt:message key="con.addquestionlabel"/></button>
                    <input type="hidden" name="subjectId" value="${requestScope.subjectId}"/>
                    <input type="hidden" name="langId" value="${requestScope.langId}"/>
                </form>
            </div>
        </div>
    </c:otherwise>
</c:choose>

</body>
</html>