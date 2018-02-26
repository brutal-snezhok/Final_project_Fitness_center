<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmr" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="var"/>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/admin_page.css"/>">
    <title>Admin page</title>
    <%@ include file="/jsp/common/header.jsp" %>
</head>

<body>
<h1>Admin page</h1>
<a href="/jsp/admin/mail.jsp"><fmt:message key="label.sendMessageTo"  bundle="${var}"/></a><br/>
<a href="/jsp/admin/adminReview.jsp"><fmt:message key="label.updateReview"  bundle="${var}"/></a>

<form name="adminPageClients" method="POST" action="/jsp/controller">
    <input type="hidden" name="command" value="update_client"/>
    <h3>List of clients: </h3>
    <table border="1" width="90%" cellpadding="5">
        <tr>
            <th><fmt:message key="jsp.select" bundle="${var}"/></th>
            <th><fmt:message key="jsp.id" bundle="${var}"/></th>
            <th><fmt:message key="jsp.name" bundle="${var}"/></th>
            <th><fmt:message key="jsp.surname" bundle="${var}"/></th>
            <th><fmt:message key="jsp.yearsOld" bundle="${var}"/></th>
            <th><fmt:message key="jsp.sex" bundle="${var}"/></th>
            <th><fmt:message key="jsp.email" bundle="${var}"/></th>
        </tr>

        <c:forEach items="${clients}" var="clients">
            <tr>
                <td><div style="text-align: center"><input type="checkbox" name="selectClient" value="${clients.email}" id="idClient" style="text-align: center"/>
                </div></td>
                <td>${clients.idClient}</td>
                <td>${clients.name}</td>
                <td>${clients.surname}</td>
                <td>${clients.yearOld}</td>
                <td>${clients.sex}</td>
                <td>${clients.email}</td>
            </tr>
        </c:forEach>
    </table>
    <br/><input type="submit" value="Remove" name="RemoveClientButton"><br/><br/>
    <strong><fmt:message key="jsp.id" bundle="${var}"/></strong>
    <label>
        <input type="number" name="idClient" value=""/>
    </label>
    <strong><fmt:message key = "jsp.name" bundle="${var}"/></strong>
    <label>
        <input type="text" name="nameClient" value=""/>
    </label>
    <strong><fmt:message key = "jsp.surname" bundle="${var}"/></strong>
    <label>
        <input type="text" name="surnameClient" value=""/>
    </label>
    <strong><fmt:message key = "jsp.yearsOld" bundle="${var}"/></strong>
    <label>
        <input type="text" name="yearOldClient" value=""/>
    </label>
    <strong><fmt:message key = "jsp.sex" bundle="${var}"/></strong>
    <label>
        <input type="text" name="sexClient" value=""/>
    </label>
    <br/><input type="submit" value="Update" name="UpdateClientButton"/>
</form>


<form name="adminPageTrainers" method="POST" action="/jsp/controller">
    <input type="hidden" name="command" value="update_trainer"/>
    <h3>List of trainers: </h3>
    <table border="1" width="90%" cellpadding="5">
        <tr>
            <th><fmt:message key="jsp.select" bundle="${var}"/></th>
            <th><fmt:message key="jsp.id" bundle="${var}"/></th>
            <th><fmt:message key="jsp.name" bundle="${var}"/></th>
            <th><fmt:message key="jsp.surname" bundle="${var}"/></th>
            <th><fmt:message key="jsp.yearsOld" bundle="${var}"/></th>
            <th><fmt:message key="jsp.sex" bundle="${var}"/></th>
            <th><fmt:message key="jsp.email" bundle="${var}"/></th>
            <th><fmt:message key="jsp.education" bundle="${var}"/></th>
            <th><fmr:message key="jsp.costPerLesson" bundle="${var}"/></th>
        </tr>
        <c:forEach items="${trainers}" var="trainers">
            <tr>
                <td><div style="text-align: center"><input type="checkbox" name="selectTrainer" value="${trainers.email}" id="idTrainer"/>
                </div></td>
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
    <br/><input type="submit" value="Remove" name="RemoveTrainerButton"><br/><br/>

    <strong><fmt:message key="jsp.id" bundle="${var}"/></strong>
    <label>
        <input type="text" name="idTrainer" value="" /><br/>
    </label>


    <strong><fmt:message key = "jsp.name" bundle="${var}"/></strong>
    <label>
        <input type="text" name="nameTrainer" value="" /><br/>
    </label>

    <strong><fmt:message key = "jsp.surname" bundle="${var}" /></strong>
    <label>
        <input type="text" name="surnameTrainer" value="" /><br/>
    </label>

    <strong><fmt:message key = "jsp.yearsOld" bundle="${var}"/></strong>
    <label>
        <input type="text" name="yearOldTrainer" value="" /><br/>
    </label>

    <strong><fmt:message key = "jsp.sex" bundle="${var}"/></strong>
    <label>
        <input type="text" name="sexTrainer" value=""/><br/>
    </label>

    <strong><fmt:message key = "jsp.education" bundle="${var}" /></strong>
    <label>
        <input type="text" name="education" value="" /><br/>
    </label>

    <strong><fmt:message key = "jsp.costPerLesson" bundle="${var}" /></strong>
    <label>
        <input type="text" name="costPerLesson" value="" /><br/>
    </label>

    <br/><input type="submit" value="Update" name="UpdateTrainerButton">
</form>

</body>
<%--<c:import url="/jsp/common/footer.jsp" />--%>
</html>