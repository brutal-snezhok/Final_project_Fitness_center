<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<%@ page isELIgnored="false" %>
<fmt:setBundle basename="locale" var="var"/>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
    <h3>Welcome</h3>
    <hr/> ${user}, hello! <hr/>


    <div class="centerTable">
        <h1><fmt:message key="jsp.myprofile" bundle="${var}"/></h1>
        <p>
            <b><fmt:message key="jsp.name" bundle="${var}"/>:</b>     ${user.name}
        </p>
        <p>
            <b><fmt:message key="jsp.surname" bundle="${var}"/>:</b>    ${user.surname}
        </p>
        <p>
            <b><fmt:message key="jsp.yearsOld" bundle="${var}"/>:</b>   ${user.yearOld}
        </p>
        <p>
            <b><fmt:message key="jsp.sex" bundle="${var}"/>:</b>   ${user.sex}
        </p>
        <p>
            <b><fmt:message key="jsp.email" bundle="${var}"/>:</b>    ${user.email}
        </p>
        <%--<p>--%>
            <%--<b><fmt:message key="jsp.role" bundle="${var}"/>:</b>     ${user.role}--%>
        <%--</p>--%>


        <table>
            <form name="loginForm" method="POST" action="${pageContext.request.contextPath}/jsp/controller">
                <input type="hidden" name="command" id="logout"/>
                <br/>
                <input type="submit" value="Logout"/>
            </form>
        </table>
    </div>


</body>
</html>
