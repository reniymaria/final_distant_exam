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
                <fmt:message key ="con.main.teacher"/>
                <br>
                <br>
                <a class="button" href="/results"><fmt:message key="con.exam.results" /></a>
                <br>
                <br>
                <a class="button" href="/subjects"><fmt:message key="con.subjects" /></a>
                <br>
            </div>
        </div>
    </body>
</html>



