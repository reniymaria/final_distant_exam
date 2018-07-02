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
        <div class="error-message">
            <c:if test="${requestScope.result != null}">
                <c:out value="${requestScope.result}"/>
            </c:if>
            <br>
            <a href="/examlist">All exam results</a>
        </div>
    </div>
</div>
</body>
</html>


