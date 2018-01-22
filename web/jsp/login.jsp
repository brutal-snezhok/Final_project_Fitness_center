<%--
  Created by IntelliJ IDEA.
  User: admindi
  Date: 24.12.2017
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form name = "loginForm" method="POST" action="controller">
    <input type="hidden" name="command" value="login" />
    <b>Login:</b><br/>
    <input type="text" name="login" value=""/>
    <br/><b>Password:</b><br/>
    <input type="password" name="password" value=""/>
    <br/>
    ${errorLoginPassMessage}
    <br/>
    ${wrongAction}
    <br/>
    ${nullPage}
    <br/>
    <input type="submit" value="Log in"/>
</form>

<%--<h1><span>Contact Me</span></h1>--%>
<%--<form>--%>
    <%--<input name="name" type="text" /><br/>--%>
    <%--<textarea name="message"></textarea>--%>
    <%--<input type="submit" value="SEND" class="submit" />--%>
<%--</form>--%>
<hr/>
</body>


</html>
