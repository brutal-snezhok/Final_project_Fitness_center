<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="var"/>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<fmt:setLocale value="${changeLanguage}"/>
<html>
<head>
    <title>Order</title>
</head>
<%@ include file="/jsp/common/header.jsp" %>
<body>
<h1><fmt:message key="label.orderPage" bundle="${var}"/></h1>
<form name="orderPage" method="POST" action="${pageContext.request.contextPath}/jsp/controller">
    <input type="hidden" name="command" value="order"/>
    <h3><fmt:message key="jsp.listOfTrainers"  bundle="${var}"/></h3>
    <table border="1" width="100%" cellpadding="5">
        <tr>
            <th><fmt:message key="jsp.select" bundle="${var}"/></th>
            <th><fmt:message key="jsp.id" bundle="${var}"/></th>
            <th><fmt:message key="jsp.name" bundle="${var}"/></th>
            <th><fmt:message key="jsp.surname" bundle="${var}"/></th>
            <th><fmt:message key="jsp.yearsOld" bundle="${var}"/></th>
            <th><fmt:message key="jsp.sex" bundle="${var}"/></th>
            <th><fmt:message key="jsp.email" bundle="${var}"/></th>
            <th><fmt:message key="jsp.education" bundle="${var}"/></th>
            <th><fmt:message key="jsp.costPerLesson" bundle="${var}"/></th>
        </tr>

        <c:forEach items="${trainers}" var="trainers">
            <tr>
                <td><input type="radio" name="selectTrainer" value="${trainers.email}" id="idTrainer"/></td>
                <td>${trainers.idTrainer}</td>
                <td>${trainers.name}</td>
                <td>${trainers.surname}</td>
                <td>${trainers.yearOld}</td>
                <td>${trainers.sex}</td>
                <td>${trainers.email}</td>
                <td>${trainers.education}</td>
                <td>${trainers.costPerHour}</td>
            </tr>
        </c:forEach>
    </table>

    <h3><fmt:message key="jsp.typeOfTraining" bundle="${var}"/></h3>
    <select name="listOfModes">
        <option value="norm" selected><fmt:message key="jsp.typeOfTraining.normal"  bundle="${var}"/></option>
        <option value="sport"><fmt:message key="jsp.typeOfTraining.sport" bundle="${var}"/></option>
        <option value="sparing"><fmt:message key="jsp.typeOfTraining.sparing" bundle="${var}"/></option>
    </select>
    <br/><br/>

    <strong><fmt:message key = "jsp.numberOfTraining" bundle="${var}"/></strong><br/>
    <label>
        <input type="number" value="1" name="countOfTraining">
    </label>

    <br/><input type="submit" value=<fmt:message key="button.makeOrder" bundle="${var}"/> name="makeOrder">


</form>
<c:import url="/jsp/common/footer.jsp" />
</body>
</html>
