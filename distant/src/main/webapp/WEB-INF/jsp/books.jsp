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
        <fmt:message key="con.book.free.web" var="conbookfreeweb"/>
        <fmt:message key="con.book.video.lessons" var="conbookvideolessons"/>
        <fmt:message key="con.book.basic.prog" var="conbookbasicprog"/>
        <fmt:message key="con.book.garvard" var="conbookgarvard"/>
        <fmt:message key="con.book.algo" var="conbookalgo"/>
        <fmt:message key="con.book.basic.window" var="conbookbasicwindow"/>
        <fmt:message key="con.book.window.servlet" var="conbookwindowservlet"/>
        <fmt:message key="con.book.basic.linux" var="conbookbasiclinux"/>
        <fmt:message key="con.book.modern.linux" var="conbookmodernlinux"/>
        <fmt:message key="con.book.admin" var="conbookadmin"/>

        <div class="parent-container">
            <div class="child-container">
                <h1>${conbooktitle}</h1>
                <p><b>${conbookcompweb}</b></p>

                <p><a href="https://www.bsuir.by/m/12_100229_1_85460.pdf">https://www.bsuir.by/m/12<em>100229</em>1_85460.pdf</a> - ${conbookmainbook}.</p>

                <p><a href="https://www.ozon.ru/context/detail/id/7309924/">https://www.ozon.ru/context/detail/id/7309924/</a> - ${conbookmodernweb}.</p>

                <p><a href="https://www.asozykin.ru/courses/networks_online">https://www.asozykin.ru/courses/networks_online</a>  - ${conbookfreeweb}.</p>

                <p><a href="https://youtu.be/67d97GMXi0w">https://youtu.be/67d97GMXi0w</a> и <a href="https://youtu.be/LTZ8BVGRhH0">https://youtu.be/LTZ8BVGRhH0</a> - ${conbookvideolessons}. </p>

                <br/>

                <p><b>${conbookbasicprog}</b></p>

                <p><a href="https://www.youtube.com/playlist?list=PLawfWYMUziZqyUL5QDLVbe3j5BKWj42E5">https://www.youtube.com/playlist?list=PLawfWYMUziZqyUL5QDLVbe3j5BKWj42E5</a> - ${conbookgarvard}.</p>

                <p><a href="http://interactivepython.org/runestone/static/pythonds/index.html">http://interactivepython.org/runestone/static/pythonds/index.html</a> - ${conbookalgo}.</p>

                <br/>

                <p><b>${conbookbasicwindow}</b></p>

                <p><a href="https://www.youtube.com/watch?v=5g3NTecqaFQ&amp;list=PLMkZjopxSe7gnwcLPKDuuAKFVjjx3qMN">https://www.youtube.com/watch?v=5g3NTecqaFQ&amp;list=PLMkZjopxSe7gnwcLPKDuuAKFVjjx3qMNC</a> - ${conbookwindowservlet}.</p>

                <br/>

                <p><b>${conbookbasiclinux}</b></p>

                <p><a href="https://stepik.org/course/73/syllabus">https://stepik.org/course/73/syllabus</a> - ${conbookmodernlinux}</p>

                <p><a href="https://www.ozon.ru/context/detail/id/31924164/">https://www.ozon.ru/context/detail/id/31924164/</a> - ${conbookadmin}</p>

                <br/>
            </div>
        </div>
    </body>
</html>