<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Admin page</title>
</head>
<body>
<h1>Admin page</h1>
<h3>List of clients: </h3>
<form name="adminPageClients" method="POST" action="/jsp/controller">
    <input type="hidden" name="command" value="adminclient"/>
    <table border="1" width="100%" cellpadding="5">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Surname</th>
            <th>Years Old</th>
            <th>Sex</th>
            <th>Email</th>
            <th>Role</th>
        </tr>
        <c:forEach items="${clients}" var="clients">
            <tr>
                <td>${clients.clientid}</td>
                <td>${clients.name}</td>
                <td>${clients.surname}</td>
                <td>${clients.yearsOld}</td>
                <td>${clients.sex}</td>
                <td>${clients.email}</td>
                <td>${clients.role}</td>
            </tr>
        </c:forEach>
    </table>
</form>
<br/><input type="submit" value="Update">
<br/><input type="submit" value="Remove">

<form name="adminPageTrainers" method="POST" action="/jsp/controller">
    <input type="hidden" name="command" value="admintrainer"/>
    <table border="1" width="100%" cellpadding="5">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Surname</th>
            <th>Years Old</th>
            <th>Sex</th>
            <th>Email</th>
            <th>Role</th>
            <th>Education</th>
            <th>Cost per lesson</th>
        </tr>
        <c:forEach items="${trainers}" var="trainers">
            <tr>
                <td>${trainers.clientid}</td>
                <td>${trainers.name}</td>
                <td>${trainers.surname}</td>
                <td>${trainers.yearsOld}</td>
                <td>${trainers.sex}</td>
                <td>${trainers.email}</td>
                <td>${trainers.role}</td>
                <td>${trainers.education}</td>
                <td>${trainers.costPerLesson}</td>
            </tr>
        </c:forEach>
    </table>
</form>
<br/><input type="submit" value="Update">
<br/><input type="submit" value="Remove">
</body>
</html>