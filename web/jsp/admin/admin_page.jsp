<%@ page import="com.tsyrulik.dmitry.model.entity.Client" %>
<%@ page import="com.tsyrulik.dmitry.model.entity.Trainer" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
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
</head>
<body>
<h1>Admin page</h1>

<form name="adminPageClients" method="POST" action="/jsp/controller">
    <input type="hidden" name="command" value="update_client"/>
    <h3>List of clients: </h3>
    <table border="1" width="100%" cellpadding="5">
        <tr>
            <th><fmt:message key="jsp.select" bundle="${var}"/></th>
            <th><fmt:message key="jsp.id" bundle="${var}"/></th>
            <th><fmt:message key="jsp.name" bundle="${var}"/></th>
            <th><fmt:message key="jsp.surname" bundle="${var}"/></th>
            <th><fmt:message key="jsp.yearsOld" bundle="${var}"/></th>
            <th><fmt:message key="jsp.sex" bundle="${var}"/></th>
            <th><fmt:message key="jsp.email" bundle="${var}"/></th>
        </tr>
        <% List<Client> clients = new ArrayList<>();
            clients.add(new Client(2, "Pety", "Saplov", 23, "M",
                    "goodmail@gmail.com", "58bad6b697dff48f4927941962f23e90", "client", (long)1, (double)15, (long)2));
            clients.add(new Client(3, "Danila", "Letov", 21, "M",
                    "letov@gmail.com", "6982e45352af5526754d83df2d1635", "client",(long)2, 0.65, (long)4));

            //8	Евгений	Соболев	33	M	sobolev@gamil.com	b25ef06be3b6948c0bc431da46c2c738	3

            clients.add(new Client(8,"Евгений", "Соболев", 33, "M",
                    "sobolev@gamil.com", "b25ef06be3b6948c0bc431da46c2c738", "client", (long)7, 0.0,(long) 8));
            session.setAttribute("clients", clients);

            //10	Алексей 	Артемьев	24	M	artemiev@yandex.ru	87e897e3b54a405da144968b2ca19b45

            List<Trainer> trainers = new ArrayList<>();
            trainers.add(new Trainer(5, "Алексей", "Артемьев", 24, "M",
                    "artemiev@yandex.ru", "87e897e3b54a405da144968b2ca19b45", "trainer", 2,"мсмк", new BigDecimal(12), 4 ));

            trainers.add(new Trainer(9, "Федор", "Макаров", 27, "M",
                    "makarov@gmail.com", "5d69dd95ac183c9643780ed7027d128a", "trainer", 1, "кмс", new BigDecimal(10), 9));
            //18	Станислав	Федотов	32	М	fedotov@yandex.ru	80b8bdceb474b5127b6aca386bb8ce14	2
            trainers.add(new Trainer(18, "Станислав", "Федотов", 32, "M",
                    "fedotov@yandex.ru", "80b8bdceb474b5127b6aca386bb8ce14", "trainer", 10, "мсмк", new BigDecimal(25), 18));
            session.setAttribute("trainers", trainers);
        %>
        <c:forEach items="${clients}" var="clients">
            <tr>
                <td><input type="checkbox" name="selectClient" value="${clients.email}" id="idClient"/></td>
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
    <strong><fmt:message key = "jsp.email" bundle="${var}"/></strong>
    <label>
        <input type="text" name="emailClient" value=""/>
    </label>
    <br/><input type="submit" value="Update" name="UpdateClientButton"/>
</form>


<form name="adminPageTrainers" method="POST" action="/jsp/controller">
    <input type="hidden" name="command" value="update_trainer"/>
    <h3>List of trainers: </h3>
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
            <th><fmr:message key="jsp.costPerLesson" bundle="${var}"/></th>
        </tr>
        <c:forEach items="${trainers}" var="trainers">
            <tr>
                <td><input type="checkbox" name="selectTrainer" value="${trainers.email}" id="idTrainer"/></td>
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
    <%--<div class="main">--%>

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


    <strong><fmt:message key = "jsp.email" bundle="${var}" /></strong>
    <label>
        <input type="text" name="emailTrainer" value="" /><br/>
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
</html>