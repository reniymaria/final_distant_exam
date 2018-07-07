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

<div class="parent-container">
    <div class="child-container">
        <fmt:message key="con.subject" var="consubject"/>
        <fmt:message key="con.action" var="conaction"/>
        <fmt:message key="con.delete.message" var="condeletemessage"/>
        <div class="error-message">
            <c:if test="${requestScope.msgdeletesubject != null}">
                <c:out value="${requestScope.msgdeletesubject}"/>
            </c:if>
        </div>

        <table border="1" cellpadding="5" cellspacing="5">
            <tr>
                <th>${consubject}</th>
                <th>${conaction}</th>
            </tr>
            <c:forEach var="subject" items="${requestScope.SubjectList}">

                <tr>
                    <td>${subject.subject}</td>
                    <td>
                        <form class="input-form" onsubmit="preventSubmit(event, '${condeletemessage}')"
                              action="${pageContext.request.contextPath}/controller" method="post">
                            <button><fmt:message key="con.delete"/></button>
                            <input type="hidden" name="command" value="deletesubject"/>
                            <input type="hidden" name="subjectId" value="${subject.subjectID}"/>
                        </form>
                        <form class="input-form" action="${pageContext.request.contextPath}/controller" method="Post">
                            <button><fmt:message key="con.edit"/></button>
                            <input type="hidden" name="command" value="editsubject"/>
                            <input type="hidden" name="subjectId" value="${subject.subjectID}"/>
                            <input type="hidden" name="subject" value="${subject.subject}"/>
                        </form>
                        <form class="input-form" action="${pageContext.request.contextPath}/controller" method="Get">
                            <button><fmt:message key="con.openquestions.ru"/></button>
                            <input type="hidden" name="command" value="questions"/>
                            <input type="hidden" name="subjectId" value="${subject.subjectID}"/>
                            <input type="hidden" name="langId" value="2"/>
                        </form>
                        <form class="input-form" action="${pageContext.request.contextPath}/controller" method="Get">
                            <button><fmt:message key="con.openquestions.en"/></button>
                            <input type="hidden" name="command" value="questions"/>
                            <input type="hidden" name="subjectId" value="${subject.subjectID}"/>
                            <input type="hidden" name="langId" value="1"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>


        <table border="1" cellpadding="5" cellspacing="5">
            <tr>
                <c:if test="${requestScope.currentPage != 1}">
                    <td>
                        <form action="${pageContext.request.contextPath}/controller" method="Get">
                            <input type="hidden" name="command" value="subjects">
                            <input type="hidden" name="page" value="${requestScope.currentPage - 1}">
                            <button class="table-btn"><fmt:message key="con.prev"/></button>
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
                                <form action="${pageContext.request.contextPath}/controller"
                                      method="Get">
                                    <input type="hidden" name="command" value="subjects">
                                    <input type="hidden" name="page" value="${i}">
                                    <button class="table-btn">${i}</button>
                                </form>
                            </td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${requestScope.currentPage lt requestScope.noOfPages}">
                    <td>
                        <form action="${pageContext.request.contextPath}/controller"
                              method="Get">
                            <input type="hidden" name="command" value="subjects">
                            <input type="hidden" name="page" value="${requestScope.currentPage + 1}">
                            <button class="table-btn"><fmt:message key="con.next"/></button>
                        </form>
                    </td>
                </c:if>
            </tr>
        </table>

        <br>

        <a class="button" href="${pageContext.request.contextPath}/add_subject"><fmt:message key="con.add.subject"/></a>
    </div>
</div>
<script src="../js/app.js"></script>
</body>
</html>
