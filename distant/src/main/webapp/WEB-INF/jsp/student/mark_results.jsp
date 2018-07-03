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

<c:choose>
    <c:when test="${requestScope.listEmpty != null}">
        <div class="parent-container">
            <div class="child-container">
                <div class="error-message">
                    <c:out value="${requestScope.listEmpty}"/>
                </div>
                <a class="button margin-top-20" href="${pageContext.request.contextPath}/student_home"><fmt:message
                        key="con.studentpage"/></a>
            </div>
        </div>
    </c:when>

    <c:otherwise>
        <div class="parent-container">
            <div class="child-container">
                <table border="1" cellpadding="5" cellspacing="5">
                    <tr>
                        <th>${consubject}</th>
                        <th>${conmark}</th>
                    </tr>
                    <c:forEach var="examresult" items="${requestScope.ExamList}">

                        <tr>
                            <td>${examresult.subject}</td>
                            <td>${examresult.mark}</td>
                        </tr>
                    </c:forEach>
                </table>

                <table border="1" cellpadding="5" cellspacing="5">
                    <tr>
                        <c:if test="${requestScope.currentPage != 1}">
                            <td>
                                <a href="${pageContext.request.contextPath}/exam_list?page=${requestScope.currentPage - 1}">Previous</a>
                            </td>
                        </c:if>

                        <c:forEach begin="1" end="${requestScope.noOfPages}" var="i">
                            <c:choose>
                                <c:when test="${requestScope.currentPage eq i}">
                                    <td>${i}</td>
                                </c:when>
                                <c:otherwise>
                                    <td><a href="${pageContext.request.contextPath}/exam_list?page=${i}">${i}</a></td>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>

                        <c:if test="${requestScope.currentPage lt requestScope.noOfPages}">
                            <td>
                                <a href="${pageContext.request.contextPath}/exam_list?page=${requestScope.currentPage + 1}">Next</a>
                            </td>
                        </c:if>
                    </tr>
                </table>
            </div>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>

