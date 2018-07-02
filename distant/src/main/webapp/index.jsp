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
<%@ include file="header.jsp" %>
<div class="parent-container">
    <div class="child-container">
        <div class="header-text"><fmt:message key="con.welcomtext"/></div>

        <br>
        <br>

        <c:choose>
            <c:when test="${sessionScope.role=='student'}">
                <a class="button" href="/student/studenthome.jsp"><fmt:message key="con.studentpage"/></a>
            </c:when>
            <c:when test="${sessionScope.role=='teacher'}">
                <a class="button" href="/teacher/teacherhome.jsp"><fmt:message key="con.teacherpage"/></a>
            </c:when>
            <c:otherwise>
                <a class="button" href="login.jsp"><fmt:message key="con.loginmessage"/></a>
            </c:otherwise>
        </c:choose>

    </div>
</div>
</body>
</html>



