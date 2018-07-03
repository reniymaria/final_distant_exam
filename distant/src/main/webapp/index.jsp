<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="./css/app.css">
    <title>Distant exam</title>
</head>
<body>
<%@ include file="WEB-INF/jsp/header.jsp" %>
<div class="parent-container">
    <div class="child-container align-center">
        <div class="header-text"><fmt:message key="con.welcomtext"/></div>

        <c:choose>
            <c:when test="${sessionScope.role=='student'}">
                <a class="button margin-top-20" href="${pageContext.request.contextPath}/student_home"><fmt:message
                        key="con.studentpage"/></a>
            </c:when>
            <c:when test="${sessionScope.role=='teacher'}">
                <a class="button margin-top-20" href="${pageContext.request.contextPath}/teacher_home"><fmt:message
                        key="con.teacherpage"/></a>
            </c:when>
            <c:otherwise>
                <a class="button margin-top-20" href="${pageContext.request.contextPath}/login"><fmt:message
                        key="con.loginmessage"/></a>
            </c:otherwise>
        </c:choose>

    </div>
</div>
</body>
</html>



