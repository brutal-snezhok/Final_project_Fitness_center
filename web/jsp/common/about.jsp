<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="locale" var="var"/>
<fmt:setLocale value="${changeLanguage}"/>
<html>
<head>
    <title>About</title>
    <style>
        @import "/css/about.css";
    </style>

    <%@ include file="/jsp/common/header.jsp" %>
    <link rel="icon" href="/document/edit_background.jpeg" type="images/jpg">
</head>
<body>
<div class="centerTable">
    <h1>Fitness center</h1>
    <p>
        <fmt:message key="label.informationA" bundle="${var}"/>
    </p>
    <p>
        <fmt:message key="label.informationB" bundle="${var}"/>
    </p>
    <p>
        <fmt:message key="label.informationC" bundle="${var}"/>
    </p>

</div>
<c:import url="/jsp/common/footer.jsp" />
</body>
</html>