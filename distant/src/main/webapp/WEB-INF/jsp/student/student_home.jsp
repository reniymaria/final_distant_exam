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
    <div class="child-container align-center">
        <fmt:message key="con.main.student"/>
        <form class="input-form" action="${pageContext.request.contextPath}/controller" method="Get">
            <input type="hidden" name="command" value="subject_list">
            <input type="hidden" name="page" value="1">
            <button><fmt:message key="con.available.exams"/></button>
        </form>
        <form class="input-form" action="${pageContext.request.contextPath}/controller" method="Get">
            <input type="hidden" name="command" value="exam_list">
            <input type="hidden" name="page" value="1">
            <button><fmt:message key="con.open.exam.results"/></button>
        </form>
    </div>
</div>
</body>
</html>
