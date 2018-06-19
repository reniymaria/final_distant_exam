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
                Главная страница учителя
                <br>
                Учитель может редактировать отметки сдавших экзамен
                <br>
                Открыть предметы
                <br>
                Вопросы по математике на английском языке:
                <a href="/questions?subject=math&lang=en"><fmt:message key="con.openquestions" /></a>
            </div>
        </div>
    </body>
</html>



