<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <link rel="stylesheet" href="../css/app.css">
</head>
<body>

<%@ include file="/WEB-INF/jsp/header.jsp" %>

<fmt:message key="con.name" var="conname"/>
<fmt:message key="con.surname" var="consurname"/>
<fmt:message key="con.subject" var="consubject"/>
<fmt:message key="con.mark" var="conmark"/>
<fmt:message key="con.action" var="conaction"/>
<fmt:message key="con.delete" var="deletebutton"/>
<fmt:message key="con.next" var="nextbutton"/>
<fmt:message key="con.prev" var="previousbutton"/>
<fmt:message key="con.delete.message" var="condeletemessage"/>
<fmt:message key="con.teacherpage" var="conteacherpage"/>


<c:choose>
    <c:when test="${requestScope.listEmpty != null}">
        <div class="parent-container">
            <div class="child-container">
                <div class="error-message">
                    <c:out value="${requestScope.listEmpty}"/>
                </div>
                <br>
                <a class="button" href="${pageContext.request.contextPath}/teacher_home">${conteacherpage}</a>
            </div>
        </div>
    </c:when>

    <c:otherwise>
        <div class="parent-container">
            <div class="child-container">
                <table cellpadding="5" cellspacing="5">
                    <div class="error-message">
                    </div>
                    <tr>
                        <th>${conname}</th>
                        <th>${consurname}</th>
                        <th>${consubject}</th>
                        <th>${conmark}</th>
                        <th>${conaction}</th>
                    </tr>
                    <c:forEach var="examresult" items="${requestScope.MarkList}">

                        <tr>
                            <td>${examresult.name}</td>
                            <td>${examresult.surname}</td>
                            <td>${examresult.subject}</td>
                            <td>${examresult.mark}</td>
                            <td>
                                <form class="input-form" onsubmit="preventSubmit(event, '${condeletemessage}')"
                                      action="${pageContext.request.contextPath}/controller" method="POST">
                                    <button>${deletebutton}</button>
                                    <input type="hidden" name="command" value="delete_exam_result">
                                    <input type="hidden" name="subjectID" value="${examresult.subjectID}"/>
                                    <input type="hidden" name="userID" value="${examresult.userID}"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>

                <table cellpadding="5" cellspacing="5">
                    <tr>
                        <c:if test="${requestScope.currentPage != 1}">
                            <td>
                                <form class="input-form" action="${pageContext.request.contextPath}/controller"
                                      method="Get">
                                    <input type="hidden" name="command" value="results">
                                    <input type="hidden" name="page" value="${requestScope.currentPage - 1}">
                                    <button>${previousbutton}</button>
                                </form>
                            </td>
                        </c:if>

                        <c:forEach begin="1" end="${requestScope.noOfPages}" var="i">
                            <c:choose>
                                <c:when test="${requestScope.currentPage eq i}">
                                    <td>
                                        <button disabled="true" class="table-btn table-btn-current">${i}</button>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td>
                                        <form class="input-form" action="${pageContext.request.contextPath}/controller"
                                              method="Get">
                                            <input type="hidden" name="command" value="results">
                                            <input type="hidden" name="page" value="${i}">
                                            <button>${i}</button>
                                        </form>
                                    </td>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>

                        <c:if test="${requestScope.currentPage lt requestScope.noOfPages}">
                            <td>
                                <form class="input-form" action="${pageContext.request.contextPath}/controller"
                                      method="Get">
                                    <input type="hidden" name="command" value="results">
                                    <input type="hidden" name="page" value="${requestScope.currentPage + 1}">
                                    <button>${nextbutton}</button>
                                </form>
                            </td>
                        </c:if>
                    </tr>
                </table>
            </div>
        </div>
    </c:otherwise>
</c:choose>
<script src="../js/app.js"></script>
</body>
</html>
