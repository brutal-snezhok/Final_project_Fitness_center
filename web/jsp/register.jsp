<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<%@ page isELIgnored="false" %>
<fmt:setBundle basename="locale"  var="var"/>
<fmt:setLocale value="${changeLanguage}"/>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/register.css"/>">
    <title>Sign up</title>
    <%@ include file="common/header.jsp" %>
</head>
<body>

<hr/>
<form name="signForm" method="POST" action = "<c:url value="/jsp/controller"/>">
    <h4><b><fmt:message key="jsp.register.registration" bundle="${var}"/> </b></h4>
    <div class="centerTable">
    <input type="hidden" name="command" value="sign_up"/>
    <fmt:message key="jsp.register.firstname" bundle="${var}" /><br/>
    <label>
        <input type="text" name="first_name" value="" required pattern="^([A-Z][a-z]{0,15})|([А-Я][а-я]{0,20})$"/>
    </label>

    <br/> <fmt:message key="jsp.register.secondname" bundle="${var}"/><br/>
    <label>
        <input type="text" name="second_name" value="" required pattern="^([A-Z][a-z]{0,15})|([А-Я][а-я]{0,15})$"/>
    </label>

    <br/><fmt:message key="jsp.register.years_old" bundle="${var}"/><br/>
    <label>
        <input type="text" name="years_old" value="" />
    </label>

    <br/><fmt:message key="jsp.register.sex" bundle="${var}"/><br/>
    <label>
            <input type="radio" name="radio-sex" value="M" checked>
            M
            <input type="radio" name="radio-sex" value="F">
            F
    </label>

    <br/><fmt:message key="jsp.register.email" bundle="${var}"/><br/>
    <label>
        <input type="text" name="email" value="" required pattern="^([a-z0-9_.-]+)@([a-z0-9_.-]+)\.([a-z.]{2,6})$"/>
    </label>

    <br/><fmt:message key="jsp.register.password" bundle="${var}"/><br/>
    <label>
        <input type="password" name="password" value="" />
    </label>
    <br/>
    <label>
        <input type="radio" name="radio-role" value="Client" checked>
        Client
        <input type="radio" name="radio-role" value="Trainer">
        Trainer
    </label>
    <br/>
    <lable>
    <input type="submit" value="<fmt:message key = "jsp.login.submit" bundle="${var}"/>">
    </lable>
    <br/>
    ${errorLoginPassMessage} <br/> ${wrongAction} <br/> ${nullPage} <br/>
    </div>
</form>
<c:import url="/jsp/common/footer.jsp" />
</body>
</html>