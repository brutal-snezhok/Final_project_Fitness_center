<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<%@ page isELIgnored="false" %>
<fmt:setBundle basename="locale"/>
<!DOCTYPE html>
<html>
<head>
    <title>Sign up</title>
</head>
<body>
<h4><b><fmt:message key="jsp.register.registration" /> </b></h4>
<hr/>
<form name="signForm" method="POST" action = "<c:url value="/jsp/controller"/>">
    <input type="hidden" name="command" value="sign_up"/>
    <fmt:message key="jsp.register.firstname" /><br/>
    <label>
        <input type="text" name="first_name" value="" required pattern="^([A-Z][a-z]{0,15})|([А-Я][а-я]{0,20})$"/>
    </label>

    <br/> <fmt:message key="jsp.register.secondname" /><br/>
    <label>
        <input type="text" name="second_name" value="" required pattern="^([A-Z][a-z]{0,15})|([А-Я][а-я]{0,15})$"/>
    </label>

    <br/><fmt:message key="jsp.register.years_old"/><br/>
    <label>
        <input type="text" name="years_old" value="" />
    </label>

    <br/><fmt:message key="jsp.register.sex"/><br/>
    <label>
            <input type="radio" name="radio-sex" value="M" checked>
            M
            <input type="radio" name="radio-sex" value="F">
            F
    </label>

    <br/><fmt:message key="jsp.register.email"/><br/>
    <label>
        <input type="text" name="email" value="" required pattern="^([a-z0-9_.-]+)@([a-z0-9_.-]+)\.([a-z.]{2,6})$"/>
    </label>

    <br/><fmt:message key="jsp.register.password"/><br/>
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
    <input type="submit" value="<fmt:message key = "jsp.login.submit" />">
    </lable>
    <br/>
    ${errorLoginPassMessage} <br/> ${wrongAction} <br/> ${nullPage} <br/>
</form>
</body>
</html>