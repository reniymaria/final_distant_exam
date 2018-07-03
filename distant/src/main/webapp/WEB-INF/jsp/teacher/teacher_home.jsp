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
                <fmt:message key ="con.main.teacher"/>
                <a class="button margin-top-20" href="${pageContext.request.contextPath}/results"><fmt:message key="con.exam.results" /></a>
                <a class="button margin-top-20" href="${pageContext.request.contextPath}/subjects"><fmt:message key="con.subjects" /></a>
            </div>
        </div>
    </body>
</html>



