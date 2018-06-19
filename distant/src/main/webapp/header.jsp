<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="i18n.content" scope="session"/>


<header>
    <nav class="header-container">
        <i class="header-item header-logo"></i>
        <div class="header-item">
            <a class="header-button" href="index.jsp"><span class="header-text"><fmt:message key="con.homepage" /></span></a>
        </div>
        <div class="header-item">
            <a class="header-button" href="books.jsp"><span class="header-text"><fmt:message key="con.bookspage" /></span></a>
        </div>

        <form class="header-item header-right-item language">
            <select class="header-language header-button header-text" subjectID="language" name="language" onchange="submit()">
                <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
                <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
            </select>
        </form>

        <c:choose>
            <c:when test="${sessionScope.role=='student'}">
                <div class="header-right-item">
                    <span class="header-text"><fmt:message key="con.student"/></span>
                    <form action="${pageContext.request.contextPath}/logout" method="Get">
                        <button class="header-button"><fmt:message key="con.logoutbutton"/></button>
                    </form>
                </div>
            </c:when>
            <c:when test="${sessionScope.role=='teacher'}">
                <div class="header-right-item">
                    <span class="header-text"><fmt:message key="con.teacher"/> </span>
                    <form action="${pageContext.request.contextPath}/logout" method="Get">
                        <button class="header-button"><fmt:message key="con.logoutbutton"/></button>
                    </form>
                </div>
            </c:when>
            <c:otherwise>
                <div class="header-right-item">
                    <span class="header-text header-hello"><fmt:message key="con.helloguest"/></span>
                    <a class="header-button" href="login.jsp"><span class="header-text"><fmt:message key="con.loginmessage" /></span></a>
                </div>
            </c:otherwise>
        </c:choose>

    </nav>
</header>




