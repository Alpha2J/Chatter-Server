<%--
  User: sourcecodes.cn
  Date: 2017/5/24
  Time: 23:05
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>test sendMessage</title>
</head>
<body>

<form action="<%=request.getContextPath()%>/message/sendMessage/privateMessage" method="post" enctype="multipart/form-data">
    sendId<input type="text" name="sendId"/><br/>
    receiveId<input type="text" name="receiveId"/><br/>
    messageType<input type="text" name="messageType"/><br/>
    contentType<input type="text" name="contentType"/><br/>
    content<input type="file" name="content"/><br/>
    <input type="submit" value="submit"/>
</form>

</body>
</html>
