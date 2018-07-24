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

            <c:if test="${requestScope.examDeletedMsg != null}">
                <c:out value="${requestScope.examDeletedMsg}"/>
                <form class="input-form" action="${pageContext.request.contextPath}/controller" method="Get">
                    <input type="hidden" name="command" value="results">
                    <input type="hidden" name="page" value="1">
                    <button><fmt:message key="con.exam.results"/></button>
                </form>
            </c:if>
        </div>

        <div class="input-form">
            <c:if test="${requestScope.msgdeletesubject != null}">
                <c:out value="${requestScope.msgdeletesubject}"/>
                <br>
                <br>
                <form class="input-form" action="${pageContext.request.contextPath}/controller" method="Get">
                    <input type="hidden" name="command" value="subjects">
                    <input type="hidden" name="page" value="1">
                    <button><fmt:message key="con.subjects"/></button>
                </form>
            </c:if>
        </div>


        <div class="input-form">
            <c:if test="${requestScope.msgaddsubject != null}">
                <c:out value="${requestScope.msgaddsubject}"/>
                <br>
                <br>
                <form class="input-form" action="${pageContext.request.contextPath}/controller" method="Get">
                    <input type="hidden" name="command" value="subjects">
                    <input type="hidden" name="page" value="1">
                    <button><fmt:message key="con.subjects"/></button>
                </form>
            </c:if>
        </div>

        <div class="input-form">
            <c:if test="${requestScope.msgeditsubject != null}">
                <c:out value="${requestScope.msgeditsubject}"/>
                <br>
                <br>
                <form class="input-form" action="${pageContext.request.contextPath}/controller" method="Get">
                    <input type="hidden" name="command" value="subjects">
                    <input type="hidden" name="page" value="1">
                    <button><fmt:message key="con.subjects"/></button>
                </form>
            </c:if>
        </div>

        <div class="input-form">
            <c:if test="${requestScope.msgaddquestion != null}">
                <c:out value="${requestScope.msgaddquestion}"/>
                <br>
                <br>
                <form class="input-form" action="${pageContext.request.contextPath}/controller" method="Get">
                    <button><fmt:message key="con.openquestions"/></button>
                    <input type="hidden" name="command" value="questions"/>
                    <input type="hidden" name="page" value="1">
                    <input type="hidden" name="subjectId" value="${sessionScope.subjectId}"/>
                    <input type="hidden" name="lang" value="${sessionScope.lang}"/>
                </form>
            </c:if>
        </div>


        <div class="error-message">
            <c:if test="${requestScope.errorUpload != null}">
                <c:out value="${requestScope.errorUpload}"/>
                <br>
                <br>
                <form class="input-form" action="${pageContext.request.contextPath}/controller" method="Get">
                    <button><fmt:message key="con.openquestions"/></button>
                    <input type="hidden" name="command" value="questions"/>
                    <input type="hidden" name="page" value="1">
                    <input type="hidden" name="subjectId" value="${sessionScope.subjectId}"/>
                    <input type="hidden" name="lang" value="${sessionScope.lang}"/>
                </form>
            </c:if>
        </div>

        <div class="input-form">
            <c:if test="${requestScope.uploadSuccess != null}">
                <c:out value="${requestScope.uploadSuccess}"/>
                <br>
                <br>
                <form class="input-form" action="${pageContext.request.contextPath}/controller" method="Get">
                    <button><fmt:message key="con.openquestions"/></button>
                    <input type="hidden" name="command" value="questions"/>
                    <input type="hidden" name="page" value="1">
                    <input type="hidden" name="subjectId" value="${sessionScope.subjectId}"/>
                    <input type="hidden" name="lang" value="${sessionScope.lang}"/>
                </form>
            </c:if>
        </div>

        <div class="input-form">
            <c:if test="${requestScope.msgeditquestion != null}">
                <c:out value="${requestScope.msgeditquestion}"/>
                <br>
                <br>
                <form class="input-form" action="${pageContext.request.contextPath}/controller" method="Get">
                    <button><fmt:message key="con.openquestions"/></button>
                    <input type="hidden" name="command" value="questions"/>
                    <input type="hidden" name="page" value="1">
                    <input type="hidden" name="subjectId" value="${sessionScope.subjectId}"/>
                    <input type="hidden" name="lang" value="${sessionScope.lang}"/>
                </form>
            </c:if>
        </div>

        <div class="input-form">
            <c:if test="${requestScope.msgdeletequestion != null}">
                <c:out value="${requestScope.msgdeletequestion}"/>
                <br>
                <br>
                <form class="input-form" action="${pageContext.request.contextPath}/controller" method="Get">
                    <button><fmt:message key="con.openquestions"/></button>
                    <input type="hidden" name="command" value="questions"/>
                    <input type="hidden" name="page" value="1">
                    <input type="hidden" name="subjectId" value="${sessionScope.subjectId}"/>
                    <input type="hidden" name="lang" value="${sessionScope.lang}"/>
                </form>
            </c:if>
        </div>

          </div>
        </div>
      </div>
</body>
</html>
