<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="../../../css/app.css">
    <title>Distant exam</title>
</head>
<body>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<c:choose>
    <c:when test="${requestScope.result != null}">
        <div class="parent-container">
            <div class="child-container align-center">
                <div class="input-form">
                    <c:out value="${requestScope.result}"/>
                </div>
                <form class="input-form" action="${pageContext.request.contextPath}/controller" method="Get">
                    <input type="hidden" name="command" value="exam_list">
                    <input type="hidden" name="page" value="1">
                    <button><fmt:message key="con.open.exam.results" /></button>
                </form>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <div class="parent-container">
            <div class="child-container align-center">
                <div class="error-message">
                    <c:if test="${requestScope.resultFailed != null}">
                        <c:out value="${requestScope.resultFailed}"/>
                    </c:if>
                </div>
                <form class="input-form" action="${pageContext.request.contextPath}/controller" method="Get">
                    <input type="hidden" name="command" value="subject_list">
                    <input type="hidden" name="page" value="1">
                    <button><fmt:message key="con.available.exams" /></button>
                </form>
            </div>
        </div>
    </c:otherwise>
</c:choose>

</body>
</html>


