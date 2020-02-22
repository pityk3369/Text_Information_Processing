<%--
  Created by IntelliJ IDEA.
  User: 30480
  Date: 2020/2/17
  Time: 16:43
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
    <title>分词处理</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-2.1.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <style type="text/css">

    </style>
    <script>
        function fenCiPro() {
            document.getElementById("fencitext").value = text.content;
        }
        function returnlist(){
            window.location.href = "${pageContext.request.contextPath}/findTextByPageServlet";
        }
    </script>
</head>

<body>
<div class="container">
    <!-- 设置布局容器为container：固定宽度并支持响应式布局的容器-->
    <h2 style="text-align: center;">分词处理</h2>
    <table border="5" class="table table-bordered" style="text-align: center;">
        <tr class="success">
            <th class="col-lg-3 col-md-3 col-sm-3 col-xs-3">文本信息</th>
            <th class="col-lg-2 col-md-2 col-sm-2 col-xs-2">操作</th>
            <th class="col-lg-7 col-md-7 col-sm-7 col-xs-7">结果</th>
        </tr>
        <%--        <th class="col-lg-3 col-md-3 col-sm-3 col-xs-3">文本信息</th>--%>
        <%--        <th class="col-lg-2 col-md-2 col-sm-2 col-xs-2">操作</th>--%>
        <%--        <th class="col-lg-7 col-md-7 col-sm-7 col-xs-7">结果</th>--%>
        <tr>
            <td rowspan="3">${text.content}</td>
            <td><a class="btn btn-info btn-sm"
                   href="${pageContext.request.contextPath}/processTextServlet?id=${text.id}&pro=${"fenci"}">分词</a>
            </td>
            <td>
                <div><%=(request.getAttribute("fenciList") == null) ? "" : request.getAttribute("fenciList")%>
                </div>
            </td>
        </tr>
        <tr>
            <td><a class="btn btn-info btn-sm"
                   href="${pageContext.request.contextPath}/processTextServlet?id=${text.id}&pro=${"keywords"}">关键词提取</a>
            </td>
            <td>
                <div><%=(request.getAttribute("keywordsList") == null) ? "" : request.getAttribute("keywordsList")%>
                </div>
            </td>
        </tr>
        <tr>
            <td><a class="btn btn-info btn-sm"
                   href="${pageContext.request.contextPath}/processTextServlet?id=${text.id}&pro=${"summary"}">文本摘要</a>
            </td>
            <td>
                <div><%=(request.getAttribute("summaryList") == null) ? "" : request.getAttribute("summaryList")%>
                </div>
            </td>
        </tr>

    </table>

    <div>
        <input class="btn btn-primary" type="button" value="返回" onclick="javascript:returnlist()"/>
    </div>


</div>
</body>
