<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="locale" var="var"/>
<html>
<head>
    <title>Send Result</title>
    <style>
        @import "/css/mail.css";
    </style>
    <%@ include file="/jsp/common/header.jsp" %>
</head>
<body>
    <p><fmt:message key="label.sendingInProgress"  bundle="${var}"/></p>
    <a href="${pageContext.request.contextPath}/jsp/admin/mail.jsp"><fmt:message key="label.returnToNewPage" bundle="${var}"/></a>
    <c:import url="/jsp/common/footer.jsp" />
</body>
</html>
