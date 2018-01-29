<%--
  Created by IntelliJ IDEA.
  User: admindi
  Date: 27.01.2018
  Time: 18:02
  To change this template use File | Settings | File Templates.
--%>
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
<form name="signForm" method="POST" action="controller">
    <input type="hidden" name="command" value="signup"/>

    <fmt:message key="jsp.register.firstname" /></fmt><br/>
    <label>
        <input type="text" name="first_name" value=""/>
    </label>

    <br/> <fmt:message key="jsp.register.secondname"/><br/>
    <label>
        <input type="text" name="second_name" value=""/>
    </label>

    <br/><fmt:message key="jsp.register.years_old"/><br/>
    <label>
        <input type="text" name="years_old" value=""/>
    </label>

    <br/><fmt:message key="jsp.register.sex"/><br/>
    <label>
        <%--<input type="text" name="sex" value=""/>--%>
            <input type="radio" name="radio-sex" value="Male" checked>
            M
            <input type="radio" name="radio-sex" value="Female">
            F
    </label>

    <br/><fmt:message key="jsp.register.email"/><br/>
    <label>
        <input type="text" name="e-mail" value=""/>
    </label>

    <br/><fmt:message key="jsp.register.password"/><br/>
    <label>
        <input type="password" name="password" value=""/>
    </label>
    <br/>
    <label>
        <input type="radio" name="radio-role" value="Client" checked>
        Client
        <input type="radio" name="radio-role" value="Trainer">
        Trainer
    </label>
    <br>



    <input type="submit" value="<fmt:message key = "jsp.login.submit" />">
    <br/>
    ${errorLoginPassMessage} <br/> ${wrongAction} <br/> ${nullPage} <br/>
</form>
</body>
</html>