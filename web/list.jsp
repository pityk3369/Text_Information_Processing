<%--
  Created by IntelliJ IDEA.
  User: 30480
  Date: 2020/2/16
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>文本信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
    <script language="JavaScript">
        function delSelected() {
            if (confirm("您确定要删除选中条目吗？")) {
                var flag = false;
                //判断是否有条目被选中
                var cbs = document.getElementsByName("tid");
                for (var i = 0; i < cbs.length; i++)
                    if (cbs[i].checked) {
                        //存在条目被选中
                        flag = true;
                        break;
                    }
                if (flag)
                    //存在被选中的条目，表单提交
                    document.getElementById("checkbox_form").submit();
            }
        }

        function deleteText(id) {
            if (confirm("确认删除吗？")) {
                location.href = "${pageContext.request.contextPath}/deleteTextServlet?id=" + id;
            }
        }


        //等待页面事件加载完毕后运行该事件
        window.onload = function () {


            //1.获取第一个cb
            document.getElementById("firstCb").onclick = function () {
                //2.获取下边列表中的所有cb
                var cbs = document.getElementsByName("tid");
                //3.遍历
                for (var i = 0; i < cbs.length; i++) {
                    //4.为这些cbs[i]设置checked状态
                    cbs[i].checked = this.checked;
                }
            };
        }
    </script>
</head>

<body>
<div class="container">
    <!-- 设置布局容器为container：固定宽度并支持响应式布局的容器-->
    <h3 style="text-align: center;">文本信息列表${user.username}</h3>

    <!-- 内联表单靠左，添加删除靠右 -->
    <div style="float: left;">
        <form class="form-inline" action="${pageContext.request.contextPath}/findTextByPageServlet" method="post">
            <div class="form-group">
                <label for="exampleInputName1">类别</label>
                <input type="text" name="type" class="form-control" id="exampleInputName1" placeholder="固定资产">
            </div>
            <div class="form-group">
                <label for="exampleInputName2">标题</label>
                <input type="text" name="title" class="form-control" id="exampleInputName2" placeholder="固定资产投资稳定增长">
            </div>
            <div class="form-group">
                <label for="exampleInputName3">时间</label>
                <input type="text" name="time" class="form-control" id="exampleInputName3" placeholder="2019-12">
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>
    </div>
    <div style="float: right;">
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/add.jsp">添加文本</a>
        <a class="btn btn-danger" id="delSelected" onclick="delSelected()">删除选中</a>
    </div>

    <form id="checkbox_form" action="${pageContext.request.contextPath}/delSelectedServlet" method="post">
        <table border="3" class="table table-bordered table-hover">
            <tr class="success">
                <th><input type="checkbox" id="firstCb"></th>
                <th>编号</th>
                <th>类别</th>
                <th>标题</th>
                <th>时间</th>
                <th>内容</th>
                <th>链接</th>
                <th>操作</th>
                <th>文本处理</th>
            </tr>

            <c:forEach items="${pb.list}" var="text" varStatus="s">
                <tr>
                    <th><input type="checkbox" name="tid" value="${text.id}"></th>
                    <td>${s.count}</td>
                    <td>${text.type}</td>
                    <td>${text.title}</td>
                    <td>${text.time}</td>
                    <td>${text.content}</td>
                    <td>${text.url}</td>
                    <td>
                        <a class="btn btn-default btn-sm"
                           href="${pageContext.request.contextPath}/findTextServlet?id=${text.id}&pro=${"update"}">修改</a>
                            <%--                        <a class="btn btn-danger btn-sm" href="javascript:deleteText(${text.id});">删除</a>--%>
                        <a class="btn btn-danger btn-sm" onclick="delSelected(${text.id});">删除</a>
                    </td>
                    <td>
                        <a class="btn btn-default btn-sm"
                           href="${pageContext.request.contextPath}/findTextServlet?id=${text.id}&pro=${"fenci"}">分词处理</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form>

    <div>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <%--当前为第1页，点击上一页，还是跳转第一页--%>
                <c:if test="${pb.currentPage==1}">
                    <li class="disabled">
                        <a href="${pageContext.request.contextPath}/findTextByPageServlet?currentPage=${1}&rows=${5}"
                           aria-label="Previous">
                            <span aria-hidden="true"></span>${"上一页"}
                        </a>
                    </li>
                </c:if>
                <%--                当前不为第1页，上一页跳转到页面-1--%>
                <c:if test="${pb.currentPage!=1}">
                    <li class="disabled">
                        <a href="${pageContext.request.contextPath}/findTextByPageServlet?currentPage=${pb.currentPage-1}&rows=${5}"
                           aria-label="Previous">
                            <span aria-hidden="true"></span>
                                ${"上一页"}
                        </a>
                    </li>
                </c:if>
                <%--遍历显示页面数字--%>
                <c:forEach begin="1" end="${pb.totalPage}" var="i">
                    <c:if test="${pb.currentPage==i}">
                        <li class="active">
                            <a href="${pageContext.request.contextPath}/findTextByPageServlet?currentPage=${i}&rows=5">${i}</a>
                        </li>
                    </c:if>
                    <c:if test="${pb.currentPage!=i}">
                        <li>
                            <a href="${pageContext.request.contextPath}/findTextByPageServlet?currentPage=${i}&rows=5">${i}</a>
                        </li>
                    </c:if>
                </c:forEach>
                <%--最后一页，点击下一页还是跳转到最后一页--%>
                <c:if test="${pb.currentPage==pb.totalPage}">
                    <li class="disabled">
                        <a href="${pageContext.request.contextPath}/findTextByPageServlet?currentPage=${pb.currentPage}&rows=${5}"
                           aria-label="Next">
                            <span aria-hidden="true"></span>
                                ${"下一页"}
                        </a>
                    </li>
                </c:if>
                <%--不为最后一页，下一页跳转到当前页面+1页--%>
                <c:if test="${pb.currentPage!=pb.totalPage}">
                    <li>
                        <a href="${pageContext.request.contextPath}/findTextByPageServlet?currentPage=${pb.currentPage+1}&rows=${5}"
                           aria-label="Next">
                            <span aria-hidden="true"></span>
                                ${"下一页"}
                        </a>
                    </li>
                </c:if>

                <span style="font-size: 25px;margin-left: 5px;">
                    共${pb.totalCount}条记录，共${pb.totalPage}页
                </span>
            </ul>
        </nav>
    </div>

</div>
</body>
</html>

