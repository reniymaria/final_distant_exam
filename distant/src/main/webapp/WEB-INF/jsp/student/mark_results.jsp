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


<fmt:message key="con.subject" var="consubject"/>
<fmt:message key="con.mark" var="conmark"/>
<fmt:message key="con.date" var="condate"/>

<c:choose>
    <c:when test="${requestScope.listEmpty != null}">
        <div class="parent-container">
            <div class="child-container">
                    <div class="input-form">
                        <c:out value="${requestScope.listEmpty}"/>
                        <br>
                        <br>
                    </div>
                <a class="button margin-top-20" href="${pageContext.request.contextPath}/student_home"><fmt:message
                        key="con.studentpage"/></a>
            </div>
        </div>
    </c:when>

    <c:otherwise>
        <div class="parent-container">
            <div class="child-container">
                <table cellpadding="5" cellspacing="5">
                    <tr>
                        <th>${consubject}</th>
                        <th>${conmark}</th>
                        <th>${condate}</th>
                    </tr>
                    <c:forEach var="examresult" items="${requestScope.ExamList}">

                        <tr>
                            <td>${examresult.subject}</td>
                            <td>${examresult.mark}</td>
                            <td>${examresult.date}</td>
                        </tr>
                    </c:forEach>
                </table>

                <table cellpadding="5" cellspacing="5">
                    <tr>
                        <c:if test="${requestScope.currentPage != 1}">
                            <td>
                                <form action="${pageContext.request.contextPath}/controller" method="Get">
                                    <input type="hidden" name="command" value="exam_list">
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
                                            <input type="hidden" name="command" value="exam_list">
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
                                    <input type="hidden" name="command" value="exam_list">
                                    <input type="hidden" name="page" value="${requestScope.currentPage + 1}">
                                    <button class="table-btn"><fmt:message key="con.next"/></button>
                                </form>
                            </td>
                        </c:if>
                    </tr>
                </table>
                <br>
                <a class="button" href="${pageContext.request.contextPath}/student_home"><fmt:message
                        key="con.studentpage"/></a>
            </div>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>

