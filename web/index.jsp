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
<div id="viewList">
    <hr/>
    <span>view-list</span>
    <br/>
</div>
</body>
<script type="module">
    import {getViewList} from "./js/request.js";

    await getViewList().then((list) => {
        let viewList = document.getElementById("viewList");
        list.forEach((viewName, index) => {
            if (viewName !== "") {
                let link = "/simple/view/" + viewName;
                let a = document.createElement('a');
                a.setAttribute("href", link);
                a.innerText = viewName;
                viewList.appendChild(a);
                if (index < list.length - 1) {
                    let br = document.createElement('br');
                    viewList.appendChild(br);
                }
            }
        })
    }).catch((res) => {
        console.log(res);
    });

</script>
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
