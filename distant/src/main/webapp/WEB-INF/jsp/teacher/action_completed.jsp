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
    <div class="child-container">
        <div class="error-message">


            <c:if test="${requestScope.msgdeletesubject != null}">
                <c:out value="${requestScope.msgdeletesubject}"/>
                <br>
                <br>
                <a class="button" href="${pageContext.request.contextPath}/subjects"><fmt:message
                        key="con.open.subjects"/></a>
            </c:if>


            <c:if test="${requestScope.msgaddsubject != null}">
                <c:out value="${requestScope.msgaddsubject}"/>
                <br>
                <br>
                <a class="button" href="${pageContext.request.contextPath}/subjects"><fmt:message
                        key="con.open.subjects"/></a>
            </c:if>

            <c:if test="${requestScope.msgeditsubject != null}">
                <c:out value="${requestScope.msgeditsubject}"/>
                <br>
                <br>
                <a class="button" href="${pageContext.request.contextPath}/subjects"><fmt:message
                        key="con.open.subjects"/></a>
            </c:if>

            <c:if test="${requestScope.msgaddquestion != null}">
                <c:out value="${requestScope.msgaddquestion}"/>
                <br>
                <br>
                <form class="input-form" action="${pageContext.request.contextPath}/questions" method="Post">
                    <button><fmt:message key="con.open.questions"/></button>
                    <input type="hidden" name="subjectId" value="${requestScope.subjectId}"/>
                    <input type="hidden" name="langId" value="${requestScope.langId}"/>
                </form>
            </c:if>

            <c:if test="${requestScope.msgeditquestion != null}">
                <c:out value="${requestScope.msgeditquestion}"/>
                <br>
                <br>
                <form class="input-form" action="${pageContext.request.contextPath}/questions" method="Post">
                    <button><fmt:message key="con.open.questions"/></button>
                    <input type="hidden" name="subjectId" value="${requestScope.subjectId}"/>
                    <input type="hidden" name="langId" value="${requestScope.langId}"/>
                </form>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
