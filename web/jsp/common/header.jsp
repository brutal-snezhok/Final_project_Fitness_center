<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="locale" var="var"/>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${changeLanguage}"/>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="Content-Type"/>
    <link rel="shortcut icon" href="/document/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrap.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/header.css"/>">
    <title>header</title>
</head>
<body>
<nav class="navbar navbar-inverse" role="navigation">

    <div class="container-fluid">
        <div class="navbar-header">
                    <c:choose>
                        <c:when test="${user.role=='client'}">
                            <a class="navbar-brand" href="${pageContext.request.contextPath}/jsp/client/client_cabinet.jsp"><fmt:message key="label.MainPage" bundle="${var}"/></a>
                        </c:when>
                        <c:when test="${user.role=='trainer'}">
                            <a class="navbar-brand" href="${pageContext.request.contextPath}/jsp/trainer/trainer_cabinet.jsp"><fmt:message key="label.MainPage" bundle="${var}"/></a>
                        </c:when>
                        <c:when test="${user.role=='admin'}">
                            <a class="navbar-brand" href="${pageContext.request.contextPath}/jsp/admin/admin_page.jsp"><fmt:message key="label.MainPage" bundle="${var}"/></a>
                        </c:when>
                        <c:otherwise>
                            <a class="navbar-brand" href="${pageContext.request.contextPath}/jsp/login.jsp"><fmt:message key="label.MainPage" bundle="${var}"/></a>
                        </c:otherwise>
                    </c:choose>
        </div>
        <ul class="nav navbar-nav">
            <li>
        <c:choose>
                <c:when test="${user.role=='client'}">
                 <a class="navbar-brand" href="${pageContext.request.contextPath}/jsp/trainerList.jsp"><fmt:message key="label.trainers" bundle="${var}"/></a>
                 </c:when>
                <c:when test="${user.role=='trainer'}">
                     <a class="navbar-brand" href="${pageContext.request.contextPath}/jsp/trainerList.jsp"><fmt:message key="label.trainers" bundle="${var}"/></a>
                 </c:when>
                <c:when test="${user.role=='admin'}">
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/jsp/trainerList.jsp"><fmt:message key="label.trainers" bundle="${var}"/></a>
                </c:when>
                <c:otherwise>
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/jsp/login.jsp"><fmt:message key="label.trainers" bundle="${var}"/></a>
                </c:otherwise>
        </c:choose>
            </li>
            <li><a href="${pageContext.request.contextPath}/jsp/common/contacts.jsp"><fmt:message key="label.contact" bundle="${var}"/></a></li>
            <li><a href="${pageContext.request.contextPath}/jsp/common/about.jsp"><fmt:message key="label.about" bundle="${var}"/></a></li>
        </ul>

        <table align="right">
            <tr align="center">
                <form name="localeForm" method="POST" action="${pageContext.request.contextPath}/jsp/controller">
                    <input type="hidden" name="pagePath" value="${pageContext.request.requestURL}" />
                    <input type="hidden" name="command" value="locale"/>
                    <td><input type="image" src="<fmt:message key="label.pictureLocale" bundle="${var}"/>" height="30" width="40" alt="<fmt:message key="label.buttonLanguage" bundle="${var}"/>"> </td>
                </form>
            </tr>
            <tr><td><ctg:infoTimeTag/></td></tr>
            <%--<tr>--%>
            <%--<td align="center"><ctg:role user="${user}"/></td>--%>
            <%--</tr>--%>
        </table>
    </div>
</nav>
</body>
</html>
