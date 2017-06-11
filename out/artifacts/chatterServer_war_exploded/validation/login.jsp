<%--
  User: sourcecodes.cn
  Date: 2017/6/7
  Time: 21:24
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>

<form action="<%=request.getContextPath()%>/validation" method="post">
    用户名: <input type="text" name="account"/>
    密码: <input type="password" name="password"/>
    <input type="submit" value="submit"/>
</form>


</body>
</html>
