<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<%@ page isELIgnored="false" %>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/login.css"/>">
    <title>Login</title>
    <%@ include file="/jsp/common/header.jsp" %>
</head>
<body>
<%--<header>--%>
    <%--<c:import url="/jsp/common/header.jsp" />--%>
<%--</header>--%>
<form name="loginForm" method="POST" action="${pageContext.request.contextPath}/jsp/controller">
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

    <br/> ${successMessage} <br/> ${errorLoginPassMessage} <br/> ${wrongAction} <br/> ${nullPage} <br/>
</form>
<c:import url="/jsp/common/footer.jsp" />
</body>
</html>
