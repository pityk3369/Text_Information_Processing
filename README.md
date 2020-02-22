# Text_Information_Processing
 
# 文本信息管理系统：

管理员登录、文本添加删除、批量处理、分词、关键词处理、自动摘要生成！

![image-20200222160226316](https://github.com/pityk3369/Text_Information_Processing/blob/master/md_photos/image-20200222160226316.png)



![image-20200222160253882](D:\md_photos\image-20200222160253882.png)



![image-20200222160320009](D:\md_photos\image-20200222160320009.png)









java的JDBC数据库连接池

```java
package util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * JDBC工具类 使用Durid连接池
 */
public class JDBCUtils {

    private static DataSource ds ;

    static {

        try {
            //1.加载配置文件
            Properties pro = new Properties();
            //使用ClassLoader加载配置文件，获取字节输入流
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            pro.load(is);

            //2.初始化连接池对象
            ds = DruidDataSourceFactory.createDataSource(pro);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接池对象
     */
    public static DataSource getDataSource(){
        return ds;
    }


    /**
     * 获取连接Connection对象
     */
    public static Connection getConnection() throws SQLException {
        return  ds.getConnection();
    }
}

```



新建WebApplication应用，将CSS配置文件加入到工程项目中。

## 1-修改index.jsp文件

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$文本信息管理$</title>
  </head>
  <body>
  $END$
  </body>
</html>


<%--加入CSS配置文件--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
	<!-- 增加文本链接 -->
    <a
            href="list.html" style="text-decoration:none;font-size:33px">查询所有文本信息
    </a>
</div>
</body>
</html>


```



## 2- 新增list.html

在https://v3.bootcss.com/css/#tables 学习常见表单：

```html
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
            border-spacing: ;
        }
    </style>
</head>
<body>
<div class="container">
    <!-- 设置布局容器为container：固定宽度并支持响应式布局的容器-->
    <h3 style="text-align: center;">文本信息列表</h3>
    <!-- 内联表单靠左，添加删除靠右 -->
    <div style="float: left;">
        <form class="form-inline">
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
        <a class="btn btn-primary" href="">添加文本</a>
        <a class="btn btn-danger" href="">删除选中</a>
    </div>

    <table border="3" class="table table-bordered table-hover">
        <tr class="success">
            <th><input type="checkbox"></th>
            <th>编号</th>
            <th>类别</th>
            <th>标题</th>
            <th>时间</th>
            <th>内容</th>
            <th>链接</th>
            <th>操作</th>
            <th>文本处理</th>
        </tr>

        <tr>
            <th><input type="checkbox"></th>
            <td>1</td>
            <td>固定资产</td>
            <td>固定资产投资增长较快</td>
            <td>2019-10-26</td>
            <td>累计完成交通运输固定资产投资15亿元，为年度目标任务的1.5%，同比增长1.5%，实现良好开局。主要指标基本达到预期目标，但还存在投资结构不平衡的问题。</td>
            <td>www.baidu.com</td>
            <td>
                <a class="btn btn-default btn-sm" href="">修改</a>
                <a class="btn btn-danger btn-sm" href="">删除</a>
            </td>
            <td>
                <a class="btn btn-default btn-sm" href="">分词处理</a>
            </td>
        </tr>
        ......
    </table>
</div>
</body>
</html>
```



![image-20200216162559618](D:\md_photos\image-20200216162559618.png)



## 3-新增Text.java

新建domain包》Text.java文件，该类中数据声明完全对应数据库：

```java
public class Text {
    private int id;//编号
    private String type;//类别
    private String title;//标题
    private String time;//时间
    private String content;//内容
    private String url;//链接
    //生成各自的get、set和toString方法
}
```

- 新建数据库表“texts”，

  ```mysql
  -- 操作文本信息库
  CREATE TABLE TEXTS(   	-- 创建表
  	id 	INT PRIMARY KEY AUTO_INCREMENT,
  	TYPE 	VARCHAR(15),
  	title 	VARCHAR(50),
  	TIME 	VARCHAR(15),
  	content VARCHAR(100),
  	url	VARCHAR(50)
  );
  
  INSERT INTO texts VALUES(NULL,'固定资产','固定资产投资增长较快'
  	,'2019-10-26','累计完成交通运输固定资产投资15亿元，为年度目标任务的1.5%，同比增长1.5%，实现良好开局。主要指标基本达到预期目标，但还存在投资结构不平衡的问题。'
  	,'www.baidu.com');
  ```

- 新建接口TextDao，并实现TextDaoImpl。

  ```java
  public interface TextDao {
      public List<Text> findAll();
  
  //    void add(Text text);
  }
  
  public class TextDaoImpl implements TextDao {
  
      private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
      @Override
      public List<Text> findAll() {
          //使用JDBC操作数据库...
          //1.定义sql
          String sql = "select * from texts";
          List<Text> texts=template.query(sql, new BeanPropertyRowMapper<Text>(Text.class))
          return texts;
      }
  }
  ```

  将druid.properties导入到项目src文件下，修改：url=jdbc:mysql:///texts；
  在web包下新建WEB-INF：将jar导入到web包--》WEB-INF--》lib文件夹下，并add as library。
  新建util包：将JDBCUtils工具类添加到src--》util文件夹下。
  新建service包：新建TextService，及其实现类TextServiceImpl。

  ```java
  /**
   * 文本管理的业务接口
   */
  public interface TextService {
      /**
       * 查询所有文本
       * @return
       */
      public List<Text> findAll();
  }
  
  public class TextServiceImpl implements TextService {
      private TextDao dao = new TextDaoImpl();
  
      @Override
      public List<Text> findAll() {
          //调用dao完成查询
          return dao.findAll();
      }
  }
  ```

### 3.1数据库JDBC链接部分：


## 4- index.jsp跳转链接》textListServlet

在textListServlet调用sql查询数据库中所有文本，并存入到textlist中，而后页面转到list.jsp，textlist作为参数传递到list中进行显示。

```jsp
<div align="center">
    <a style="text-decoration:none;font-size:33px">欢迎！</a>
    <br>
    <a style="text-decoration:none;font-size:20px">5秒后自动跳转到界面</a>
    <meta http-equiv="refresh" content="5;url=${pageContext.request.contextPath}/textListServlet">
</div>
```



跳转成功：

![image-20200217111610447](D:\md_photos\image-20200217111610447.png)



## 5-增加添加文本功能

### 5-1新增add.jsp：

action="${pageContext.request.contextPath}/addTextServlet" method="post"
主要的跳转链接到addTextServlet，

```jsp
<%--
  Created by IntelliJ IDEA.
  User: 30480
  Date: 2020/2/17
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
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
    <title>添加文本</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <center><h3>添加文本页面</h3></center>
    <form action="${pageContext.request.contextPath}/addUserServlet" method="post">
        <div class="form-group">
            <label for="type">类别：</label>
            <input type="text" class="form-control" id="type" name="type" placeholder="请输入类别">
        </div>
        <div class="form-group">
            <label for="title">标题：</label>
            <input type="text" class="form-control" id="title" name="title" placeholder="请输入标题">
        </div>
        <div class="form-group">
            <label for="time">时间：</label>
            <input type="text" class="form-control" id="time" name="time" placeholder="请输入类别">
        </div>
        <div class="form-group">
            <label for="content">内容：</label>
            <input type="text" class="form-control" id="content" name="content" placeholder="请输入内容">
        </div>
        <div class="form-group">
            <label for="url">链接：</label>
            <input type="text" class="form-control" id="url" name="url" placeholder="请输入来源">
        </div>
        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交" />
            <input class="btn btn-default" type="reset" value="重置" />
            <input class="btn btn-default" type="button" value="返回" />
        </div>
    </form>
</div>

</body>
</html>


```



### 5-2新增AddTextServlet

```java
package web.Servlet;

import domain.Text;
import org.apache.commons.beanutils.BeanUtils;
import service.TextService;
import service.impl.TextServiceImpl;

@WebServlet("/addTextServlet")
public class AddTextServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取参数
        Map<String, String[]> map = request.getParameterMap();
        //3.封装对象
        Text text = new Text();
        try {
            BeanUtils.populate(text, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //4.调用service保存
        TextServiceImpl service = new TextServiceImpl();
        service.addText(text);

        //5.跳转到textListServlet, getContextPath获取虚拟路径
        response.sendRedirect(request.getContextPath() + "/textListServlet");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}


```

### 5-3增加服务层接口，重写方法

在TextService接口中定义”新增文本“接口，并在TextServiceImpl中重写方法：

```java
/**
* 增加本文
* @param text
*/
void addText(Text text);

@Override
public void addText(Text text) {
    //调用dao完成文本增加
    dao.addText(text);
}


```



### 5-4增加操作层接口，重写方法

```java
//TextDao接口中增加方法：addText
void addText(Text text);

//TextDaoImpl实现类中重写方法：
@Override
public void addText(Text text) {
    //1.定义sql，ID为null自增长；其余属性为？，由text类属性完成
    String sql = "insert into texts values(null,?,?,?,?,?)";
    //2.执行sql
    template.update(sql, text.getType(), text.getTitle(), text.getTime(), text.getContent(), text.getUrl());

}

```



### 5-5测试

![image-20200217123710544](D:\md_photos\image-20200217123710544.png)



![image-20200217123728263](D:\md_photos\image-20200217123728263.png)



### 5-6思考

对于网页前端add.jsp的输入表单内容，如何传入到addTextServlet中？

前端代码中：
![image-20200217124256839](D:\md_photos\image-20200217124256839.png)

使用post方法，input这些数据内容后；在后台的addTextServlet代码中，在post方法体内通过request获取前端输入的内容：

![image-20200217124437755](D:\md_photos\image-20200217124437755.png)



![image-20200217125739478](D:\md_photos\image-20200217125739478.png)



![image-20200217125720121](D:\md_photos\image-20200217125720121.png)





## 6-增加修改文本功能

> 文本信息的修改，针对于单个特殊文本进行，需要绑定该文本的id号
>
> 1-在list.jsp中，将text.id作为参数传入到findTextServlet中
> <href="${pageContext.request.contextPath}/findTextServlet?id=${text.id}">修改
>
> 2-findTextServlet：获取文本id；根据id查询文本信息Text；将Text对象存到request内；将request转发到update.jsp页面。
>
> 3-update.jsp：根据隐藏域内文本id进行回显信息；进行文本信息修改；提交信息到request域，跳转UpdateUserServlet
>
> 4-UpdateUserServlet：获取request域表单数据map；封装Text对象；调用Service》Dao完成数据库文本修改；修改完毕后跳转到TextListServlet进行列表更新显示。

### 6-1新增findTextServlet

在post方法内：根据

```java
//1.获取id
String id = request.getParameter("id");//request域好像只能为String类型，所以下面需要强制类型转换
//2.调用Servlet查询
TextService service = new TextServiceImpl();
Text text = service.findTextById(id);

//3.将user存入request
request.setAttribute("text", text);
//4.转发到update.jsp
request.getRequestDispatcher("/update.jsp").forward(request, response);

```



### 6-2根据id查询文本接口功能实现

```java
/*
页面上某个文本的修改按钮，点击后调用/findTextServlet?id=${user.id}，
在findTextServlet类中调用dao.findTextById(id)，返回文本Text对象到findTextServlet中
而后调用updat.jsp，text对象传入页面进行回显，并进行信息修改，提交后转到updateTextServlet
在updateTextServlet中，从request中获取text对象，然后调用dao.updateText(text)进行修改
修改完毕后，跳转到textListServlet，文本列表更新现实
*/

接口层：
/**
 * 根据文本id查找文本对象
 * @param id
 * @return
 */
Text findTextById(String id);

@Override
public Text findTextById(String id) {
    return dao.findTextById(id);
}

dao层：
Text findTextById(String id);

@Override
public Text findTextById(String id) {
    String sql = "select * from texts where id=?";
    return template.queryForObject(sql, new BeanPropertyRowMapper<Text>(Text.class), id);
}


```



### 6-1新增update.jsp



```jsp
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
    <!-- 中间回显信息，通过text对象，前提就是获取到该对象 -->
    <form action="${pageContext.request.contextPath}/updateTextServlet" method="post">
        <!-- 隐藏域 -->
        <input type="hidden" name="id" value="${text.id}">
        <div class="form-group">
            <label for="type">类别：</label>
            <input type="text" class="form-control" id="type" value="${user.type}" name="type"
                   placeholder="请输入类别"/>
        </div>
        ......
        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交"/>
            <input class="btn btn-default" type="reset" value="重置"/>
            <input class="btn btn-primary" type="button" value="返回" onclick="javascript:history.go(-1)"/>

        </div>
    </form>
</div>
</body>
</html>


```



### 6-2新增UpdateTextServlet

在其post方法内：

```java
//1.设置编码
request.setCharacterEncoding("utf-8");
//2.获取map
Map<String, String[]> map = request.getParameterMap();
//3.封装对象
Text text = new Text();
try {
    BeanUtils.populate(text, map);
} catch (IllegalAccessException e) {
    e.printStackTrace();
} catch (InvocationTargetException e) {
    e.printStackTrace();
}
//4.调用Service修改
TextService service = new TextServiceImpl();
service.updateText(text);
//5.跳转到查询所有Servlet
response.sendRedirect(request.getContextPath() + "/textListServlet");

```

修改其get方法为：

```java
this.doPost(request, response);

```



### 6-3增加服务层接口，重写方法

在TextService接口中，定义updateText方法；并在其实现类中重写。

```java
/**
 * 修改文本
 * @param text
 */
void updateText(Text text);

@Override
public void updateText(Text text) {
    //调用dao完成文本修改
    dao.updateText(text);
}

```



### 6-4增加操作层接口，重写方法

在TextDao接口中定义updateText方法；并在其实现类中重写。

```java
void updateText(Text text);

@Override
public void updateText(Text text) {
    String sql="update text set type = ?,title = ? ,time = ? , content = ? , url = ? where id = ?";
    template.update(sql, text.getType(), text.getTitle(), text.getTime(), text.getContent(), text.getUrl(), text.getId());

}

```



## 7-增加删除文本功能

### 7-1新增DeleteTextServlet

还是根据文本id来计算，doPost方法：

```java
request.setCharacterEncoding("utf-8");
String id = request.getParameter("id");
TextService service = new TextServiceImpl();
service.deleteText(id);
response.sendRedirect(request.getContextPath() + "textListServlet");

```



### 7-2新增服务层、操作层接口，重写方法

```java
/**TextService：
 * 根据文本id删除
 * @param id
 */
void deleteText(String id);

//TextServicelmpl：
@Override
public void deleteText(String id) {
    dao.deleteText(id);
}

//操作层：TextDao
void deleteText(String id);
//TextDaolmpl：
@Override
public void deleteText(String id) {
    String sql = "delete from texts where id = ?";
    template.update(sql, id);
}


```



### 7-3测试

点击删除按钮后，立马就消失了，觉得应该存在一个弹窗来确认删除！

```jsp
修改删除按钮响应为JavaScript事件：
<a class="btn btn-danger btn-sm" href="javascript:deleteText(${text.id});">删除</a>
并在list.jsp的head标签内定义：
<script language="JavaScript">
    function deleteText(id) {
        if (confirm("确认删除吗？")) {
           location.href="${pageContext.request.contextPath}/deleteTextServlet?id=" + id;
        }
    }
</script>


```



## 8-增加选中删除文本功能

### 8-1新增选中表单

对于选中的文本，我们需要建立一个表单form：checkbox_form；在此表单内，勾选的checkbox作为input输入内容；该表单范围是整个table；

![image-20200218151327575](D:\md_photos\image-20200218151327575.png)



![image-20200218112212387](D:\md_photos\image-20200218112212387.png)



并对“删除选中”按钮进行功能设置：

![image-20200218154337492](D:\md_photos\image-20200218154337492.png)



list.jsp中新增以下JavaScript代码：

```js
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

```



check_form表单提交至DelSelectedServlet

### 8-2新增DelSelectedServlet

```java
//1.获取所有id
String[] ids = request.getParameterValues("tid");
//2.调用service
TextService service = new TextServiceImpl();
service.delSerlectedText(ids);

//3.跳转查询所有的Servlet
//因为网页之间不存在数据共享，故可使用重定向。
response.sendRedirect(request.getContextPath() + "/textListServlet");

```



### 8-3新增服务层、操作层，并重写方法

实现service.delServletedText()方法：

```java
/**
 * 删除选中的文本
 * @param ids
 */
void delSerlectedText(String[] ids);

@Override
public void delSerlectedText(String[] ids) {
    for (String id : ids) {
        dao.deleteText(id);
    }
}

```



### 8-4 测试成功！

![image-20200218154712421](D:\md_photos\image-20200218154712421.png)



### 8-5新增全部选中功能

![image-20200218172443563](D:\md_photos\image-20200218172443563.png)



checkbox复选框，将选择的结果提交到“checkbox_form"。

根据id="firstCb"，复选框的状态来 对 确定列表中文本中复选框id="tid"的状态。

![image-20200218172729932](D:\md_photos\image-20200218172729932.png)

在list.jsp文件中，增加下面的js事件：

```js
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

```





## 9-新增文本处理功能

将从Hanlp的github官网，下载到的jar包，放入lib中。

![image-20200219142325800](D:\md_photos\image-20200219142325800.png)

```text
#本配置文件中的路径的根目录，根目录+其他路径=完整路径（支持相对路径，请参考：https://github.com/hankcs/HanLP/pull/254）
#Windows用户请注意，路径分隔符统一使用/
#root=D:/JavaProjects/HanLP/
root=G:/IDEA_java_code/files/data-for-1.7.5/

#好了，以上为唯一需要修改的部分，以下配置项按需反注释编辑。

```



### 9-1分词按钮增加链接

在list.jsp中修改分词按钮；href里将pro参数设置为不同的操作，后面的分词、关键词提取等根据该参数进行。

```jsp
<td>
    <a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/findTextServlet?id=${text.id}&pro=${"fenci"}">分词处理</a>
</td>

```



findTextServlet.java，doPost方法：

```java
String id = request.getParameter("id");//request域好像只能为String类型，所以下面需要强制类型转换
String pro = request.getParameter("pro");

System.out.println("进行操作："+pro);

//2.调用Servlet查询
TextService service = new TextServiceImpl();
Text text = service.findTextById(id);

//3.将text存入request
request.setAttribute("text", text);
//4.根据pro参数，跳转到不同页面
if (pro.equals("fenci")) {
    request.getRequestDispatcher("/Process.jsp").forward(request, response);
} else if (pro.equals("update")) {
    request.getRequestDispatcher("/update.jsp").forward(request, response);
}


```



process.jsp

<div><%=(request.getAttribute("fenciList") == null) ? "" : request.getAttribute("fenciList")%>，这行代码是要注意的地方！！！


```jsp
<body>
<div class="container-fluid">
    <!-- 设置布局容器为container：固定宽度并支持响应式布局的容器-->
    <h2 style="text-align: center;">分词处理</h2>
    <table border="5" class="table table-bordered" style="text-align: center;" >
        <th class="col-lg-2 col-md-2 col-sm-2 col-xs-2">操作</th>
        <th class="col-lg-5 col-md-5 col-sm-5 col-xs-5">文本信息</th>
        <th class="col-lg-5 col-md-5 col-sm-5 col-xs-5">结果</th>
        <tr>
            <th>
                <a class="btn btn-default btn-sm"
                        href="${pageContext.request.contextPath}/processTextServlet?id=${text.id}&pro=${"fenci"}">分词</a>
            </th>
            <th>${text.content}</th>
            <th>
                <div><%=(request.getAttribute("fenciList") == null) ? "" : request.getAttribute("fenciList")%>
                </div>
            </th>
        </tr>
    </table>
</div>
</body>

```

页面布局有点丑，重新规划：表格第一列进行合并三行的操作：

```jsp
<tr>
    <td rowspan="3">${text.content}</td>
    <td><a class="btn btn-default btn-sm"
           href="${pageContext.request.contextPath}/processTextServlet?id=${text.id}&pro=${"fenci"}">分词</a>
    </td>
    <td>
        <div><%=(request.getAttribute("fenciList") == null) ? "" : request.getAttribute("fenciList")%>
        </div>
    </td>
</tr>

```



![image-20200221115933859](D:\md_photos\image-20200221115933859.png)



### 9-2关键词按钮增加链接

```jsp
<tr>
    <td><a class="btn btn-info btn-sm"
           href="${pageContext.request.contextPath}/processTextServlet?id=${text.id}&pro=${"keywords"}">关键词提取</a>
    </td>
    <td>
        <div><%=(request.getAttribute("keywordsList") == null) ? "" : request.getAttribute("keywordsList")%>
        </div>
    </td>
</tr>

```

- processtextservlet中，在判断fenci操作的基础上，增加关键词提取的操作：

  ```
  else if (pro.equals("keywords")) {
      System.out.println("关键词！！！");
      String termList=service.keyWordsById(id);
      System.out.println(termList);
      request.setAttribute("keywordsList", termList);
      request.getRequestDispatcher("/Process.jsp").forward(request, response);
  }
  
  ```

  



- TextService增加service.keyWordsById(id);接口，并实现！

  ```java
  Text text = dao.findTextById(id);
      List<String> keywordsList = new ArrayList<>();
      System.out.println(text.getContent().toString());
      System.out.println(HanLP.extractKeyword(text.getContent().toString(), 5));
      List<String> termList = HanLP.extractKeyword(text.getContent().toString(), 5);
      for (String term : termList) {
          if (term.length() > 1) {
              keywordsList.add(term);
          }
          //ystem.out.println(term.word);
      }
      return keywordsList.toString();
  
  ```



### 9-3文本摘要按钮增加链接

- 方法同上

  ```jsp
  <tr>
      <td><a class="btn btn-info btn-sm"
             href="${pageContext.request.contextPath}/processTextServlet?id=${text.id}&pro=${"summary"}">文本摘要</a>
      </td>
      <td>
          <div><%=(request.getAttribute("summaryList") == null) ? "" : request.getAttribute("summaryList")%>
          </div>
      </td>
  </tr>
  
  ```

  

  ```java
  processtextservlet：
  else if (pro.equals("summary")) {
      System.out.println("文本摘要！！！");
      summaryList=service.summaryById(id);
      System.out.println(summaryList);
  }
  
  textserviceimpl：
  public String summaryById(String id) {
      Text text = dao.findTextById(id);
      List<String> summaryList = new ArrayList<>();
      System.out.println(text.getContent().toString());
      System.out.println(HanLP.extractSummary(text.getContent().toString(), 3));
      List<String> termList = HanLP.extractSummary(text.getContent().toString(), 5);
      for (String term : termList) {
          if (term.length() > 1) {
              summaryList.add(term);
          }
          //ystem.out.println(term.word);
      }
      return summaryList.toString();
      //return HanLP.getSummary(text.getContent().toString(), 15);
  }
  
  ```

  

### 9-4遇到了问题：

点击分词后，分词显示！点关键词，关键词显示，但是分词的结果却不显示了。

将这些处理结果类型声明为类成员，进行不同的操作，对不同成员赋值，但是在post方法结尾处进行都统一的属性设置。

![image-20200221135555399](D:\md_photos\image-20200221135555399.png)



![image-20200221135613146](D:\md_photos\image-20200221135613146.png)

- ```jsp
  <td>
      <div><%=(request.getAttribute("summaryList") == null) ? "" : request.getAttribute("summaryList")%>
      </div>
  </td>
  
  ```



## 10-文本列表信息分页及页码

### 服务器端：

```java
/**在domain包内定义PageBean分页对象，作为输出给客户端。
 * 分页对象，这里的分页，在许多地方都有应用，故加上泛型增加通用性！使用时，初始化泛型即可！
 */
public class PageBean<T> {
    private int totalCount; 	// 总记录数
    private int totalPage ; 	// 总页码
    private List<T> list ; 		// 每页的数据
    private int currentPage ; 	//当前页码
    private int rows;			//每页显示的记录数
    //生成get、set、toString方法
}

```



### 客户端：

> 定义SQL语句：totalCount = select count(*) from texts;
> totalPage = 提供每页显示条数给服务器：rows
> list = select * from texts limit ?,?;
> 		//第1个？：开始查询的索引；
> 		//第2个?：rows每页显示的条数。
> currentPage = 提供当前页码给服务器
> 开始查询的索引 = ( currentPage - 1 ) * rows;



### 前端后台调用：

根据网页链接URL中当前页码current和每页显示的条目数rows
--》调用FindUserByPageServlet；
		1.接受两个请求参数；2.使用service查询PageBean；3.将PageBean存入request；4.转发到list.jsp显示。
--》在TextService中：
		1.新建PageBean对象；2.设置当前页面currentPage属性和rows属性并计算总页码；
		3.调用dao查询总文本数；4.查询list集合。
--》在TextDao中操作数据库：
		查询总文本数findTotalCount()、
		计算分页的显示文本列表list，findByPage(start , rows)；返回给TextService。



### 10-1新增PageBean对象

- 在domain包内，新建

  ```java
  public class PageBean<T> {
      private int totalCount; // 总记录数
      private int totalPage ; // 总页码
      private List<T> list ; // 每页的数据
      private int currentPage ; //当前页码
      private int rows;//每页显示的记录数
      //生成get、set、toString方法
      }
  
  ```

  

### 10-2新增FindTextByPageServlet：

- ```java
  //1.获取参数
  String currentPage = request.getParameter("currentPage");
  String rows = request.getParameter("rows");
  //默认参数：默认打开第一页，设置参数
  if (currentPage == null || "".equals(currentPage)) {
      currentPage = "1";
  }
  if (rows == null || "".equals(rows)) {
      rows = "5";
  }
  
  System.out.println(currentPage);
  System.out.println(rows);
  
  //2.调用Service查询
  TextServiceImpl service = new TextServiceImpl();
  PageBean<Text> pb = service.findTextByPage(currentPage, rows);//泛型实例化
  
  //3.将PageBean存入request
  request.setAttribute("pb", pb);
  System.out.println(pb.toString());
  //4.转发到list.jsp
  request.getRequestDispatcher("/list.jsp").forward(request, response);
  
  ```



### 10-3新增接口service.findTextByPage(currentPage, rows)；并实现TextService以及Dao接口服务！

```java
@Override
public PageBean<Text> findTextByPage(String _currentPage, String _rows) {
    int currentPage = Integer.parseInt(_currentPage);
    int rows = Integer.parseInt(_rows);
    if (currentPage <= 0) {
        currentPage = 1;
    }
    //1.创建空的PageBean对象
    PageBean<Text> pb = new PageBean<Text>();
    //2.设置参数
    pb.setCurrentPage(currentPage);
    pb.setRows(rows);
    //3.调用dao查询总记录数
    int totalCount = dao.findTotalCount();
    pb.setTotalCount(totalCount);
    //4.调用dao查询list集合
    //计算本页开始的记录索引
    System.out.println("text服务，寻找最大页码和全部用户！");
    int start = (currentPage - 1) * rows;
    List<Text> list = dao.findByPage(start, rows);
    pb.setList(list);
    //5.计算总页码
    int totalPage = (totalCount % rows) == 0 ? totalCount / rows : (totalCount / rows) + 1;
    pb.setTotalPage(totalPage);
    if (currentPage >= totalPage) {
        pb.setCurrentPage(totalPage);
    }
    return pb;
}

//数据库中dao操作代码
@Override
public int findTotalCount() {
    String sql = "select count(*) from texts";
    System.out.println(sql);

    return template.queryForObject(sql, Integer.class);
}

@Override
public List<Text> findByPage(int start, int rows) {
    String sql = "select * from texts limit ?,?";
    System.out.println(sql);

    return template.query(sql, new BeanPropertyRowMapper<Text>(Text.class), start, rows);
}

```







### 10-4list.jsp修改分页列表显示

```
<c:forEach items="${texts}" var="text" varStatus="s">
    <tr>
        <th><input type="checkbox" name="tid" value="${text.id}"></th>
        <td>${s.count}</td>
        <td>${text.type}</td>
        <td>${text.title}</td>
        <td>${text.time}</td>
        <td>${text.content}</td>
        <td>${text.url}</td>
    </tr>
</c:forEach>

上面代码修改为：将texts修改为本页应该显示的文本信息列表：主要是items="${pb.list}"，
<c:forEach items="${pb.list}" var="text" varStatus="s">
    <tr>
	...
    </tr>
</c:forEach>

```



- 记得@WebServlet("/findTextByPageServlet")，前面的/不许得有！要不连tomcat都启动不了！

- ```jsp
  <meta http-equiv="refresh" content="1;url=${pageContext.request.contextPath}/findTextByPageServlet">
  记得在index.jsp在修改这个链接！要不然什么也没有！
  
  index,jsp主页界面：自动跳转到findTextByPageServlet。
  findTextByPageServlet：默认currentpage和rows赋值，调用dao数据库查询，返回页面pb对象，将该对象赋值到request内，然后跳转到list,jsp，列表进行显示。
  
  ```

  

- 修改页码显示

  ```jsp
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
              <%--当前不为第1页，上一页跳转到页面-1页--%>
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
  
  
  ```



### 10-5修改主页跳转链接

content="1;url=${pageContext.request.contextPath}/findTextByPageServlet"

```jsp
<div align="center">
    <a style="text-decoration:none;font-size:33px">欢迎！</a>
    <br>
    <a style="text-decoration:none;font-size:20px">5秒后自动跳转到界面</a>
    <meta http-equiv="refresh" content="1;url=${pageContext.request.contextPath}/findTextByPageServlet">
</div>

```



## 11-管理员登陆

### 11-1新增login.jsp

根据checkCodeServlet来获取到验证码，新增id=“vcode”，其src属性为获取到的验证码照片。

```jsp
<%-- login.jsp
  Created by IntelliJ IDEA.
  User: PitYk
  Date: 2020/2/22
  Time: 11:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>管理员登录</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript">
        //切换验证码
        function refreshCode() {
            //1.获取验证码图片对象
            document.getElementById("vcode");
            //2.设置其src属性，加时间戳
            vcode.src = "${pageContext.request.contextPath}/checkCodeServlet?time=" + new Date().getTime();

        }
    </script>
</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">管理员登录</h3>
    <form action="${pageContext.request.contextPath}/loginServlet" method="post">
        <div class="form-group">
            <label for="user">用户名：</label>
            <input type="text" name="username" class="form-control" id="user" placeholder="请输入用户名"/>
        </div>

        <div class="form-group">
            <label for="password">密码：</label>
            <input type="password" name="password" class="form-control" id="password" placeholder="请输入密码"/>
        </div>

        <div class="form-inline">
            <label for="vcode">验证码：</label>
            <input type="text" name="verifycode" class="form-control" id="verifycode" placeholder="请输入验证码"
                   style="width: 120px;"/>
            <a href="javascript:refreshCode()">
                <img src="${pageContext.request.contextPath}/checkCodeServlet" title="看不清点击刷新" id="vcode"/>
            </a>
        </div>
        <hr/>
        <div class="form-group" style="text-align: center;">
            <input class="btn btn btn-primary" type="submit" value="登录">
        </div>
    </form>

    <!-- 出错显示的信息框 -->
    <div class="alert alert-warning alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert">
            <span>&times;</span>
        </button>
        <strong>${login_msg}</strong>
    </div>
</div>
</body>
</html>

```



登录用户名和密码：表单提交到loginServlet，

```jsp
<form action="${pageContext.request.contextPath}/loginServlet" method="post">
    用户名、密码、验证码、登陆
</form>

```



验证码图片获取：

```jsp
js事件
<script type="text/javascript">
    //切换验证码
    function refreshCode() {
        //1.获取验证码图片对象
        document.getElementById("vcode");
        //2.设置其src属性，加时间戳
        vcode.src = "${pageContext.request.contextPath}/checkCodeServlet?time=" + new Date().getTime();

    }
</script>

......

<div class="form-inline">
    <label for="vcode">验证码：</label>
    <input type="text" name="verifycode" class="form-control" id="verifycode" placeholder="请输入验证码"
           style="width: 120px;"/>
    <a href="javascript:refreshCode()">
        <img src="${pageContext.request.contextPath}/checkCodeServlet" title="看不清点击刷新" id="vcode"/>
    </a>
</div>

```



### 11-2新增loginServlet

根据提交的表单，获取用户输入的验证码，与后台生成的验证码进行比较；
将表单内的用户名和密码封装成user对象，调用service的login方法，调用UserDao方法操作数据库进行登录信息匹配。

```java
package web.Servlet;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取数据
        //2.1根据post提交的表单获取用户输入的验证码
        String verifycode = request.getParameter("verifycode");

        //3.验证码校验
        HttpSession session = request.getSession();
        //CheckCodeServlet中将生成的验证码，保存为“CHECKCODE_SERVER"属性
        String checkcode_server = (String)session.getAttribute("CHECKCODE_SERVER");
        //保证验证码的一次性
        session.removeAttribute("CHECKCODE_SERVER");
        if (!checkcode_server.equalsIgnoreCase(verifycode)) {
            //验证码不正确//提示信息
            request.setAttribute("login_msg", "验证码错误！");
            //跳转登录页面
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return ;

        }
        //验证码正确的情况下，检验用户名是否正确
        Map<String, String[]> map = request.getParameterMap();
        //4.封装User对象
        User user=new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //5.调用Service查询
        TextService service = new TextServiceImpl();
        User loginUser = service.login(user);
        //6.判断是否登录成功
        if (loginUser != null) {
            //登陆成功
            //将用户存入session
            session.setAttribute("user", loginUser);
            //response对象是《客户端》跳转页面
            response.sendRedirect(request.getContextPath() + "/findTextByPageServlet");
        } else {
            //登陆失败
            //提示信息
            request.setAttribute("login_msg", "用户名或密码错误！");
            //getRequestDispatcher是《服务器》跳转登陆页面
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}


```



### 11-3domain中新增User对象、dao层增加UserDao方法

```java
public class User {
    private String username;
    private String password;
    //get、set、tostring方法
}
//textserviceimpl中增加登录，调用userdao的数据库查询方法
@Override
public User login(User user) {
    return userdao.findUserByUsernameAndPassword(user.getUsername(),user.getPassword());
}
    
//UserDaoImpl，实现了数据库操作
public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public User findUserByUsernameAndPassword(String username, String password) {

        try {
            String sql = "select * from adminuser where username=? and password=?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

```



### 11-4页面跳转更改

index.jsp --》 login.jsp --》 loginservlet -验证成功-》findtextbypageservlet --》 list.jsp

```
《《index.jsp
<div align="center">
    <a style="text-decoration:none;font-size:33px">欢迎！</a>
    <br>
    <a style="text-decoration:none;font-size:20px">5秒后自动跳转到界面</a>
<%--    <meta http-equiv="refresh" content="1;url=${pageContext.request.contextPath}/loginServlet">--%>
    <meta http-equiv="refresh" content="1;url=${pageContext.request.contextPath}/login.jsp">
</div>

《《login.jsp
<form action="${pageContext.request.contextPath}/loginServlet" method="post">
    ...提交表单到loginservlet
</form>

《《loginServlet
//1.设置编码
request.setCharacterEncoding("utf-8");
//2.获取数据
//2.1根据post提交的表单获取用户输入的验证码
String verifycode = request.getParameter("verifycode");

//3.验证码校验
HttpSession session = request.getSession();
//CheckCodeServlet中将生成的验证码，保存为“CHECKCODE_SERVER"属性
String checkcode_server = (String)session.getAttribute("CHECKCODE_SERVER");
//保证验证码的一次性
session.removeAttribute("CHECKCODE_SERVER");
if (!checkcode_server.equalsIgnoreCase(verifycode)) {
    //验证码不正确//提示信息
    request.setAttribute("login_msg", "验证码错误！");
    //跳转登录页面
    request.getRequestDispatcher("/login.jsp").forward(request, response);
    return ;

}
//验证码正确的情况下，检验用户名是否正确
Map<String, String[]> map = request.getParameterMap();
//4.封装User对象
User user=new User();
try {
    BeanUtils.populate(user, map);
} catch (IllegalAccessException e) {
    e.printStackTrace();
} catch (InvocationTargetException e) {
    e.printStackTrace();
}

//5.调用Service查询
TextService service = new TextServiceImpl();
User loginUser = service.login(user);
//6.判断是否登录成功
if (loginUser != null) {
    //登陆成功
    //将用户存入session
    session.setAttribute("user", loginUser);
    //response对象是《客户端》跳转页面
    response.sendRedirect(request.getContextPath() + "/findTextByPageServlet");
} else {
    //登陆失败
    //提示信息
    request.setAttribute("login_msg", "用户名或密码错误！");
    //getRequestDispatcher是《服务器》跳转登陆页面
    request.getRequestDispatcher("/login.jsp").forward(request, response);
}

```

