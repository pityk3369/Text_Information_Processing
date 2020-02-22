package web.Servlet;

import service.TextService;
import service.impl.TextServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delSelectedServlet")
public class DelSelectedServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取所有id
        String[] ids = request.getParameterValues("tid");
        //2.调用service
        TextService service = new TextServiceImpl();
        service.delSerlectedText(ids);

        //3.跳转查询所有的Servlet
        //因为网页之间不存在数据共享，故可使用重定向。
        response.sendRedirect(request.getContextPath() + "/textListServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
