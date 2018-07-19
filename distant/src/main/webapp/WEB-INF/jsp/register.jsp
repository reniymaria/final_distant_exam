<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="../../css/app.css">
    <title>Register form</title>
</head>
<body>
<%@ include file="header.jsp" %>

<div class="parent-container">
    <div class="child-container">
        <h2 class="form-title"><fmt:message key="con.register"/></h2>
        <form class="input-form" action="${pageContext.request.contextPath}/controller" method="Post">
            <input type="hidden" name="command" value="register">

            <div class="error-message">
                <c:if test="${requestScope.errMess != null}">
                    <c:out value="${requestScope.errMess}"/>
                </c:if>
            </div>

            <input placeholder="<fmt:message key="con.usernamelabel"/>" type="text" name="login" required/><br/>
            <input placeholder="<fmt:message key="con.passwordlabel"/>" type="password" name="password" required/><br/>
            <input placeholder="<fmt:message key="con.passwordlabel"/>" type="password" name="repassword"
                   required/><br/>
            <input placeholder="<fmt:message key="con.namelabel"/>" type="text" name="name" required/><br/>
            <input placeholder="<fmt:message key="con.surnamelabel"/>" type="text" name="surname" required/><br/>

            <button><fmt:message key="con.buttonsubmit"/></button>
        </form>
    </div>
</div>
</body>
</html>