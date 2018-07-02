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
        <fmt:message key="con.exam.title" var="conexamtitle"/>
        <fmt:message key="con.action" var="conaction"/>

        <table border="1" cellpadding="5" cellspacing="5">
            <tr>
                <th>${conexamtitle}</th>
                <th>${conaction}</th>
            </tr>
            <c:forEach var="subject" items="${requestScope.SubjectList}">

                <tr>
                    <td>${subject.subject}</td>
                    <td>
                        <form class="input-form" action="${pageContext.request.contextPath}/controller" method="get">
                            <button><fmt:message key="con.pass.in.en"/></button>
                            <input type="hidden" name="command" value="studentexam"/>
                            <input type="hidden" name="subject" value="${subject.subject}"/>
                            <input type="hidden" name="examlang" value="en"/>
                        </form>
                        <br>
                        <form class="input-form" action="${pageContext.request.contextPath}/controller" method="get">
                            <button><fmt:message key="con.pass.in.ru"/></button>
                            <input type="hidden" name="command" value="studentexam"/>
                            <input type="hidden" name="subject" value="${subject.subject}"/>
                            <input type="hidden" name="examlang" value="ru"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <table border="1" cellpadding="5" cellspacing="5">
            <tr>
                <c:if test="${requestScope.currentPage != 1}">
                    <td><a href="subjectlist?page=${requestScope.currentPage - 1}"><fmt:message key="con.prev"/></a></td>
                </c:if>

                <c:forEach begin="1" end="${requestScope.noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${requestScope.currentPage eq i}">
                            <td>${i}</td>
                        </c:when>
                        <c:otherwise>
                            <td><a href="subjectlist?page=${i}">${i}</a></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${requestScope.currentPage lt requestScope.noOfPages}">
                    <td><a href="subjectlist?page=${requestScope.currentPage + 1}"><fmt:message key="con.next"/></a></td>
                </c:if>
            </tr>
        </table>

    </div>
</div>
</body>
</html>
