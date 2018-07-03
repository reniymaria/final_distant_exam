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
                <div class="error-message">
                    <c:out value="${requestScope.result}"/>
                </div>
                <a class="button margin-top-20" href="${pageContext.request.contextPath}/exam_list"><fmt:message
                        key="con.exam.results"/></a>
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
                <a class="button margin-top-20" href="${pageContext.request.contextPath}/subject_list"><fmt:message
                        key="con.available.exams"/></a>
            </div>
        </div>
    </c:otherwise>
</c:choose>

</body>
</html>


