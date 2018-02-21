<%@ page import="com.tsyrulik.dmitry.model.entity.Client" %>
<%@ page import="com.tsyrulik.dmitry.model.entity.Trainer" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
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

<form name="adminPageClients" method="POST" action="/jsp/controller">
    <input type="hidden" name="command" value="adminClient"/>
    <h3>List of clients: </h3>
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
        <% List<Client> clients = new ArrayList<>();
            clients.add(new Client(2, "Pety", "Saplov", 23, "M",
                    "goodmail@gmail.com", "58bad6b697dff48f4927941962f23e90", "client", (long)1, (double)15, (long)2));
            clients.add(new Client(3, "Danila", "Letov", 21, "M",
                    "letov@gmail.com", "6982e45352af5526754d83df2d1635", "client",(long)2, 0.65, (long)4));

            clients.add(new Client(4,"Данила", "Куликов", 42, "M",
                    "kulikov42@gmail.com", "34cc93ece0ba9e3f6f235d4af979b16c", "client", (long)3, 30.0,(long) 4));
            session.setAttribute("clients", clients);

            //10	Алексей 	Артемьев	24	M	artemiev@yandex.ru	87e897e3b54a405da144968b2ca19b45

            List<Trainer> trainers = new ArrayList<>();
            trainers.add(new Trainer(5, "Алексей", "Артемьев", 24, "M",
                    "artemiev@yandex.ru", "87e897e3b54a405da144968b2ca19b45", "trainer", 2,"мсмк", new BigDecimal(12), 4 ));
            session.setAttribute("trainers", trainers);
        %>
        <c:forEach items="${clients}" var="clients">
            <tr>
                <td>${clients.idClient}</td>
                <td>${clients.name}</td>
                <td>${clients.surname}</td>
                <td>${clients.yearOld}</td>
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
    <input type="hidden" name="command" value="adminTrainer"/>
    <h3>List of trainers: </h3>
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
                <td>${trainers.idTrainer}</td>
                <td>${trainers.name}</td>
                <td>${trainers.surname}</td>
                <td>${trainers.yearOld}</td>
                <td>${trainers.sex}</td>
                <td>${trainers.email}</td>
                <td>${trainers.role}</td>
                <td>${trainers.education}</td>
                <td>${trainers.costPerHour}</td>
            </tr>
        </c:forEach>
    </table>
</form>
<br/><input type="submit" value="Update">
<br/><input type="submit" value="Remove">
</body>
</html>