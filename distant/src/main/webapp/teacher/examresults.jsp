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

<%@ include file="/header.jsp" %>

<div class="parent-container">
    <div class="child-container">
        <fmt:message key="con.name" var="conname"/>
        <fmt:message key="con.surname" var="consurname"/>
        <fmt:message key="con.subject" var="consubject"/>
        <fmt:message key="con.mark" var="conmark"/>
        <fmt:message key="con.action" var="conaction"/>
        <fmt:message key="con.delete" var="deletebutton"/>
        <fmt:message key="con.next" var="nextbutton"/>
        <fmt:message key="con.prev" var="previousbutton"/>

        <table border="1" cellpadding="5" cellspacing="5">
            <div class="error-message">
                <c:if test="${requestScope.errMsg != null}">
                    <c:out value="${requestScope.errMsg}"/>
                </c:if>
            </div>
            <tr>
                <th>${conname}</th>
                <th>${consurname}</th>
                <th>${consubject}</th>
                <th>${conmark}</th>
                <th>${conaction}</th>
            </tr>
            <c:forEach var="examresult" items="${requestScope.MarkList}">

                <tr>
                    <!-- create cells of row -->
                    <td>${examresult.name}</td>
                    <td>${examresult.surname}</td>
                    <td>${examresult.subject}</td>
                    <td>${examresult.mark}</td>
                    <td>
                        <form class="input-form" action="${pageContext.request.contextPath}/results" method="Post">
                            <button>${deletebutton}</button>
                            <input type="hidden" name="subjectID" value="${examresult.subjectID}"/>
                            <input type="hidden" name="userID" value="${examresult.userID}"/>
                        </form>
                    </td>
                    <!-- close row -->
                </tr>
                <!-- close the loop -->
            </c:forEach>
        </table>

        <%--For displaying Page numbers.
        The when condition does not display a link for the current page--%>
        <table border="1" cellpadding="5" cellspacing="5">
            <tr>
                <c:if test="${requestScope.currentPage != 1}">
                    <td><a href="results?page=${requestScope.currentPage - 1}">${previousbutton}</a></td>
                </c:if>

                <c:forEach begin="1" end="${requestScope.noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${requestScope.currentPage eq i}">
                            <td>${i}</td>
                        </c:when>
                        <c:otherwise>
                            <td><a href="results?page=${i}">${i}</a></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${requestScope.currentPage lt requestScope.noOfPages}">
                    <td><a href="results?page=${requestScope.currentPage + 1}">${nextbutton}</a></td>
                </c:if>
            </tr>
        </table>
    </div>
</div>

</body>
</html>
