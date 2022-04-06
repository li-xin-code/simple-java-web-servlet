<%@ page import="java.time.LocalTime" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.time.format.DateTimeFormatter" %><%--
  Created by IntelliJ IDEA.
  User: lx
  Date: 2022/4/6
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>time</title>
</head>
<body>
<%
    LocalTime now = LocalTime.now();
    DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");
    String time = now.format(format);
%>

<%=time %>

</body>
</html>
