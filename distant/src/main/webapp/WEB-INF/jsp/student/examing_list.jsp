<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="../../../css/app.css">
    <title>Distant exam</title>
</head>
<body>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

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
                        <form class="input-form" action="${pageContext.request.contextPath}/controller" method="Post">
                            <button><fmt:message key="con.pass.in.en"/></button>
                            <input type="hidden" name="command" value="studentexam"/>
                            <input type="hidden" name="subject" value="${subject.subject}"/>
                            <input type="hidden" name="examlang" value="en"/>
                        </form>
                        <br>
                        <form class="input-form" action="${pageContext.request.contextPath}/controller" method="Post">
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
                    <td>
                        <form action="${pageContext.request.contextPath}/controller" method="Get">
                            <input type="hidden" name="command" value="subject_list">
                            <input type="hidden" name="page" value="${requestScope.currentPage - 1}">
                            <button class="table-btn"><fmt:message key="con.prev"/></button>
                        </form>
                    </td>
                </c:if>

                <c:forEach begin="1" end="${requestScope.noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${requestScope.currentPage eq i}">
                            <td>
                                <button disabled="true" class="table-btn table-btn-current">${i}</button>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td>
                                <form action="${pageContext.request.contextPath}/controller" method="Get">
                                    <input type="hidden" name="command" value="subject_list">
                                    <input type="hidden" name="page" value="${i}">
                                    <button class="table-btn">${i}</button>
                                </form>
                            </td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${requestScope.currentPage lt requestScope.noOfPages}">
                    <td>
                        <form action="${pageContext.request.contextPath}/controller" method="Get">
                            <input type="hidden" name="command" value="subject_list">
                            <input type="hidden" name="page" value="${requestScope.currentPage + 1}">
                            <button class="table-btn"><fmt:message
                                    key="con.next"/></button>
                        </form>
                    </td>
                </c:if>
            </tr>
        </table>

    </div>
</div>
</body>
</html>
