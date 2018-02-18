<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="Content-Type" >
    <link rel="shortcut icon" href="/document/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrap.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/header.css"/>">
    <title>header</title>
</head>
<body>
<nav class="navbar navbar-inverse" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/jsp/login.jsp">Main Page</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="/jsp/login.jsp">Trainers</a></li>
            <li><a href="/main">Fitness directions</a></li>
            <li><a href="/main">Contact</a></li>
        </ul>
    </div>
</nav>
</body>
</html>
