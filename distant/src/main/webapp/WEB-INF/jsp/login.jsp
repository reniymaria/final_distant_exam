<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="../../css/app.css">
    <title>Login page</title>
</head>
<body>

<%@ include file="header.jsp" %>

<div class="parent-container">
    <div class="child-container align-center">
        <h2 class="form-title"><fmt:message key="con.loginmessage"/></h2>

        <div class="error-message">
            <c:if test="${requestScope.authorizeError != null}">
                <c:out value="${requestScope.authorizeError}"/>
            </c:if>
        </div>

        <form class="input-form" action="${pageContext.request.contextPath}/controller" method="Post">
            <input type="hidden" name="command" value="login">
            <div class="error-message">
                <c:if test="${requestScope.emptyMess1 != null}">
                    <c:out value="${requestScope.emptyMess1}"/>
                </c:if>
            </div>
            <input placeholder="<fmt:message key="con.usernamelabel"/>" type="text" name="login" required/><br/>
            <div class="error-message">
                <c:if test="${requestScope.emptyMess2 != null}">
                    <c:out value="${requestScope.emptyMess2}"/>
                </c:if>
            </div>
            <input placeholder="<fmt:message key="con.passwordlabel"/>" type="password" name="password" required/><br/>
            <button><fmt:message key="con.buttonsubmit"/></button>
        </form>

        <a class="form-link" href="${pageContext.request.contextPath}/register"><fmt:message key="con.register"/></a>
    </div>
</div>
</body>
</html>