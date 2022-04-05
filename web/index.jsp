<%@ page import="com.lixin.model.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/3/3
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Simple</title>
</head>
<body>
<p>Author: Lixin</p>
<%
    String user = (String) request.getSession().getAttribute("user");
    user = user == null ? "未知" : user;
    out.println("当前用户:" + user);
%>
<p id="msg"></p>
</body>
<script type="text/javascript">
    let xhr = new XMLHttpRequest();
    let url = "/simple/message";
    xhr.open('GET', url);
    xhr.send();
    xhr.onreadystatechange = function () {
        if (xhr.status === 200 && xhr.readyState === 4) {
            let context = xhr.responseText;
            let msg = document.getElementById("msg");
            msg.innerText = context;
        }
    };
</script>
</html>
