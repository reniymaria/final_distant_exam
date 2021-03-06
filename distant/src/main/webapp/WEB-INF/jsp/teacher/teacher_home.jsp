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
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="parent-container">
    <div class="child-container align-center">
        <fmt:message key="con.main.teacher"/>

        <c:if test="${sessionScope.user.name == 'Olga'}">
            <img class="avatar" src="../../../img/smolyakova.jpg" alt="Teacher Photo">
        </c:if>

        <c:if test="${sessionScope.user.name == 'Leonid'}">
            <img class="avatar" src="../../../img/leonid.jpg" alt="Teacher Photo">
        </c:if>
        <form class="input-form" action="${pageContext.request.contextPath}/controller" method="Get">
            <input type="hidden" name="command" value="results">
            <input type="hidden" name="page" value="1">
            <button><fmt:message key="con.exam.results"/></button>
        </form>
        <form class="input-form" action="${pageContext.request.contextPath}/controller" method="Get">
            <input type="hidden" name="command" value="subjects">
            <input type="hidden" name="page" value="1">
            <button><fmt:message key="con.subjects"/></button>
        </form>
    </div>
</div>
</body>
</html>



