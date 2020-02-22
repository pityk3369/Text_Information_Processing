<%--
  Created by IntelliJ IDEA.
  User: 30480
  Date: 2020/2/17
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改文本</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-2.1.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">修改文本信息</h3>
    <form action="${pageContext.request.contextPath}/updateTextServlet" method="post">
        <!-- 隐藏域 -->
        <input type="hidden" name="id" value="${text.id}">
        <div class="form-group">
            <label for="type">类别：</label>
            <input type="text" class="form-control" id="type" value="${text.type}" name="type"
                   placeholder="请输入类别"/>
        </div>
        <div class="form-group">
            <label for="title">标题：</label>
            <input type="text" class="form-control" id="title" value="${text.title}" name="title"
                   placeholder="请输入标题"/>
        </div>
        <div class="form-group">
            <label for="time">时间：</label>
            <input type="text" class="form-control" id="time" value="${text.type}" name="time"
                   placeholder="请输入时间"/>
        </div>
        <div class="form-group">
            <label for="content">内容：</label>
            <input type="text" class="form-control" id="content" value="${text.content}" name="content"
                   placeholder="请输入内容"/>
        </div>
        <div class="form-group">
            <label for="url">链接：</label>
            <input type="text" class="form-control" id="url" value="${text.url}" name="url"
                   placeholder="请输入链接"/>
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交"/>
            <input class="btn btn-default" type="reset" value="重置"/>
            <input class="btn btn-primary" type="button" value="返回" onclick="javascript:history.go(-1)"/>

        </div>

    </form>
</div>
</body>
</html>
