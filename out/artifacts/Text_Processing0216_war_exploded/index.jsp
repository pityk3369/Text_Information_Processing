<%--
  Created by IntelliJ IDEA.
  User: 30480
  Date: 2020/2/16
  Time: 12:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>文本信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript">
    </script>
</head>
<body>
<div align="center">
    <a style="text-decoration:none;font-size:33px">欢迎！</a>
    <br>
    <a style="text-decoration:none;font-size:20px">5秒后自动跳转到界面</a>
<%--    <meta http-equiv="refresh" content="1;url=${pageContext.request.contextPath}/loginServlet">--%>
    <meta http-equiv="refresh" content="1;url=${pageContext.request.contextPath}/login.jsp">
</div>
</body>
</html>

<%--加入CSS配置文件--%>
