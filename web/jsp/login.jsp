<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<%@ page isELIgnored="false" %>
<fmt:setBundle basename="locale"/>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/login.css"/>">
    <title>Login</title>
    <%@ include file="header.jsp" %>
</head>
<body>

<form name="loginForm" method="POST" action="/jsp/controller">
    <input type="hidden" name="command" value="login"/>
    <fmt:message key = "jsp.login.login"/><br/>
    <label>
        <input type="text" name="login" value=""/>
    </label>
    <br/><fmt:message key = "jsp.login.password"/><br/>
    <label>
        <input type="password" name="password" value=""/>
    </label>
    <br/><input type="submit" value="<fmt:message key = "jsp.login.submit"/>">
    <lablel>
    <a href="/jsp/register.jsp"><fmt:message key = "jsp.login.signup"/></a>
    </lablel>
    <%--<select class="select-lang" name="locale_language" onchange="this.form.submit()">--%>
        <%--<option value="en_US" selected="">Language</option>--%>
        <%--<option value="en_US">English</option>--%>
        <%--<option value="ru_RU">Русский</option>--%>
    <%--</select>--%>

    <br/> ${successMessage} <br/> ${errorLoginPassMessage} <br/> ${wrongAction} <br/> ${nullPage} <br/>
</form>
    <c:import url="${pageContext.request.contextPath}/jsp/footer.jsp"/>
</body>
</html>
