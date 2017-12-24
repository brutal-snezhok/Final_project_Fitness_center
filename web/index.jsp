<%--
  Created by IntelliJ IDEA.
  User: admindi
  Date: 23.12.2017
  Time: 12:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Final_project_Fitness_center_Tsyrulik</title>
    <h5>Счетчик времени от запуска приложения до нажатия кнопки</h5>

  </head>
  <body>
  <jsp:useBean id="calendar" class="java.util.GregorianCalendar"/>
  <form name="Simple" action="timeaction" method="POST">
    <input type="hidden" name="time" value="${calendar.timeInMillis}"/>
    <input type="submit" name="button" value="Посчитать время"/>
  </form>

  </body>


</html>
