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
                        <c:if test="${requestScope.passwordError != null}">
                            <c:out value="${requestScope.passwordError}"/>
                        </c:if>
                    </div>

                    <div class="error-message">
                        <c:if test="${requestScope.loginError != null}">
                            <c:out value="${requestScope.loginError}"/>
                        </c:if>
                    </div>

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
                    <div class="error-message">
                        <c:if test="${requestScope.emptyMess3 != null}">
                            <c:out value="${requestScope.emptyMess3}"/>
                        </c:if>
                    </div>
                    <input placeholder="<fmt:message key="con.passwordlabel"/>" type="password" name="repassword" required/><br/>
                    <div class="error-message">
                        <c:if test="${requestScope.emptyMess4 != null}">
                            <c:out value="${requestScope.emptyMess4}"/>
                        </c:if>
                    </div>
                    <input placeholder="<fmt:message key="con.namelabel"/>" type="text" name="name" required/><br/>
                    <div class="error-message">
                        <c:if test="${requestScope.emptyMess5 != null}">
                            <c:out value="${requestScope.emptyMess5}"/>
                        </c:if>
                    </div>

                    <input placeholder="<fmt:message key="con.surnamelabel"/>" type="text" name="surname" required/><br/>

                    <button><fmt:message key="con.buttonsubmit"/></button>
                </form>
            </div>
        </div>
    </body>
</html>