<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../../css/app.css">
        <title>Distant exam</title>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <fmt:message key="con.book.title" var="conbooktitle"/>
        <fmt:message key="con.book.comp.web" var="conbookcompweb"/>
        <fmt:message key="con.book.main.book" var="conbookmainbook"/>
        <fmt:message key="con.book.modern.web" var="conbookmodernweb"/>
        <fmt:message key="con.book.basic.prog" var="conbookbasicprog"/>
        <fmt:message key="con.book.algo" var="conbookalgo"/>
        <fmt:message key="con.book.basic.window" var="conbookbasicwindow"/>
        <fmt:message key="con.book.basic.linux" var="conbookbasiclinux"/>
        <fmt:message key="con.book.modern.linux" var="conbookmodernlinux"/>
        <fmt:message key="con.book.admin" var="conbookadmin"/>

        <div class="parent-container">
            <div class="child-container">
                <h1>${conbooktitle}</h1>

                <p><b>${conbookcompweb}</b></p>

                <p class="book-container">

                    <a href="https://www.bsuir.by/m/12_100229_1_85460.pdf" target="_blank">
                        <img src="../../img/olipher1_.jpg" alt="${conbookmainbook}">
                        <span>${conbookmainbook}</span>
                    </a>
                </p>

                <hr>

                <p class="book-container">
                    <a href="https://www.ozon.ru/context/detail/id/7309924/" target="_blank">
                        <img src="../../img/tanenbaum2.jpg" alt="${conbookmodernweb}">
                        <span>${conbookmodernweb}</span>
                    </a>
                </p>

                <hr>

                <p>
                    <b>${conbookbasicprog}</b>
                </p>

                <p class="book-container">
                    <a href="http://interactivepython.org/runestone/static/pythonds/index.html" target="_blank">
                        <img src="../../img/PythonDScover.jpg" alt="${conbookalgo}">
                        <span>${conbookalgo}</span>
                    </a>
                </p>

                <hr>

                <p>
                    <b>${conbookbasiclinux}</b>
                </p>

                <p class="book-container">
                    <a href="https://stepik.org/course/73/syllabus" target="_blank">
                        <img src="../../img/linux3.jpg" alt="${conbookmodernlinux}">
                        <span>${conbookmodernlinux}</span>
                    </a>
                </p>

                <hr>

                <p class="book-container">
                    <a href="https://www.ozon.ru/context/detail/id/31924164/" target="_blank">
                        <img src="../../img/linux4.jpg" alt="${conbookadmin}">
                        <span>${conbookadmin}</span>
                    </a>
                </p>
            </div>
        </div>
    </body>
</html>