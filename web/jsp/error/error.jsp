<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<%@ page isELIgnored="false" %>
<fmt:setBundle basename="locale"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/error.css"/>">
    <title>Error</title>
</head>
<body>
<strong>Request from ${pageContext.errorData.requestURI} is failed</strong>
<br/>
<strong><Servlet> name of type: ${pageContext.errorData.servletName}</Servlet></strong>
<br/>
<strong>Status code: ${pageContext.errorData.statusCode}</strong>
<br/>
<strong>Exception: ${pageContext.errorData.throwable}</strong>
<br/>
<lablel>
    <a href="${pageContext.request.contextPath}/jsp/login.jsp"><fmt:message key = "jsp.login.submit" /></a>
</lablel>
<h1>Page Not Found!</h1>
<br/>

</body>
</html>
