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

<fmt:message key="con.question.title" var="conquestiontitle"/>
<fmt:message key="con.answer1" var="conanswer1"/>
<fmt:message key="con.answer2" var="conanswer2"/>
<fmt:message key="con.answer3" var="conanswer3"/>
<fmt:message key="con.numbercoranswer" var="connumbercoranswer"/>
<fmt:message key="con.action" var="conaction"/>
<fmt:message key="con.delete.message" var="condeletemessage"/>
<fmt:message key="con.file.error.type" var="confileerrortype"/>

<c:choose>
    <c:when test="${requestScope.listEmpty != null}">
        <div class="parent-container">
            <div class="child-container">
                <div class="error-message">
                    <c:out value="${requestScope.listEmpty}"/>
                </div>
                <form class="input-form upload-form" onsubmit="onFileSubmit(event, '${confileerrortype}')" action="${pageContext.request.contextPath}/upload" method="Post"
                      enctype="multipart/form-data">
                    <label for="uploading"><a class="button button-full margin-right-10"><fmt:message key="con.choose.excel"/></a></label>
                    <input type="file" size="50" name="file_upload" id="uploading">
                    <input type="hidden" name="subjectId" value="${sessionScope.subjectId}"/>
                    <input type="hidden" name="langId" value="${sessionScope.langId}"/>
                    <button><fmt:message key="con.upload"/></button>
                </form>
                <form class="input-form upload-form" action="${pageContext.request.contextPath}/controller" method="Get">
                    <input type="hidden" name="command" value="addquestion">
                    <button><fmt:message key="con.addquestionlabel"/></button>
                    <input type="hidden" name="subjectId" value="${sessionScope.subjectId}"/>
                    <input type="hidden" name="langId" value="${sessionScope.langId}"/>
                </form>
                <br>
            </div>
        </div>

    </c:when>

    <c:otherwise>
        <div class="parent-container">
            <div class="child-container">

                <form class="input-form upload-form" onsubmit="onFileSubmit(event, '${confileerrortype}')" action="${pageContext.request.contextPath}/upload" method="Post"
                      enctype="multipart/form-data">
                    <label for="uploading"><a class="button button-full margin-right-10"><fmt:message key="con.choose.excel"/></a></label>
                    <input type="file" size="50" name="file_upload" id="uploading">
                    <input type="hidden" name="subjectId" value="${sessionScope.subjectId}"/>
                    <input type="hidden" name="langId" value="${sessionScope.langId}"/>
                    <button><fmt:message key="con.upload"/></button>
                </form>

                <table cellpadding="5" cellspacing="5">
                    <tr>
                        <th>${conquestiontitle}</th>
                        <th>${conanswer1}</th>
                        <th>${conanswer2}</th>
                        <th>${conanswer3}</th>
                        <th>${connumbercoranswer}</th>
                        <th>${conaction}</th>
                    </tr>
                    <c:forEach var="question" items="${requestScope.QuestionList}">
                        <tr>
                            <td>${question.question}</td>
                            <td>${question.answer1}</td>
                            <td>${question.answer2}</td>
                            <td>${question.answer3}</td>
                            <td>${question.correctAnswer}</td>
                            <td>
                                <form class="input-form" action="${pageContext.request.contextPath}/controller"
                                      method="GET">
                                    <input type="hidden" name="command" value="update_question">
                                    <button><fmt:message key="con.edit"/></button>
                                    <input type="hidden" name="questionId" value="${question.questionId}"/>
                                </form>
                                <form class="input-form" onsubmit="preventSubmit(event, '${condeletemessage}')"
                                      action="${pageContext.request.contextPath}/controller"
                                      method="Post">
                                    <input type="hidden" name="command" value="delete_question">
                                    <button><fmt:message key="con.delete"/></button>
                                    <input type="hidden" name="questionId" value="${question.questionId}"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <table cellpadding="5" cellspacing="5">
                    <tr>
                        <c:if test="${requestScope.currentPage != 1}">
                            <td>
                                <form action="${pageContext.request.contextPath}/controller" method="Get">
                                    <input type="hidden" name="command" value="questions">
                                    <input type="hidden" name="subjectId" value="${sessionScope.subjectId}"/>
                                    <input type="hidden" name="langId" value="${sessionScope.langId}"/>
                                    <input type="hidden" name="page" value="${requestScope.currentPage - 1}">
                                    <button class="table-btn"><fmt:message
                                            key="con.prev"/></button>
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
                                        <form action="${pageContext.request.contextPath}/controller" method="Get">
                                            <input type="hidden" name="command" value="questions">
                                            <input type="hidden" name="subjectId" value="${sessionScope.subjectId}"/>
                                            <input type="hidden" name="langId" value="${sessionScope.langId}"/>
                                            <input type="hidden" name="page" value="${i}">
                                            <button class="table-btn">${i}</button>
                                        </form>
                                    </td>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:if test="${requestScope.currentPage lt requestScope.noOfPages}">
                            <td>
                                <form action="${pageContext.request.contextPath}/controller" method="Get">
                                    <input type="hidden" name="command" value="questions">
                                    <input type="hidden" name="subjectId" value="${sessionScope.subjectId}"/>
                                    <input type="hidden" name="langId" value="${sessionScope.langId}"/>
                                    <input type="hidden" name="page" value="${requestScope.currentPage + 1}">
                                    <button class="table-btn"><fmt:message
                                            key="con.next"/></button>
                                </form>
                            </td>
                        </c:if>
                    </tr>
                </table>
                <br>
                <form class="input-form upload-form" action="${pageContext.request.contextPath}/controller" method="Get">
                    <input type="hidden" name="command" value="addquestion">
                    <button><fmt:message key="con.addquestionlabel"/></button>
                    <input type="hidden" name="subjectId" value="${sessionScope.subjectId}"/>
                    <input type="hidden" name="langId" value="${sessionScope.langId}"/>
                </form>
                <br>
            </div>
        </div>
    </c:otherwise>
</c:choose>
<script src="../js/app.js"></script>
</body>
</html>