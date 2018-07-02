<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="../css/app.css">
</head>
<body>
<%@ include file="/header.jsp" %>

<div class="parent-container">
    <div class="child-container">

        <form class="input-form" action="${pageContext.request.contextPath}/controller" method="Post">
            <input type="hidden" name="command" value="editsubject">
            <input type="text" name="subject" value="${requestScope.subject}">
            <input type="hidden" name="subjectId" value="${requestScope.subjectId}">
            <button><fmt:message key="con.buttonsubmit"/></button>
        </form>

    </div>
</div>
</body>
</html>
