package web.Servlet;

import domain.Text;
import service.TextService;
import service.impl.TextServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findTextServlet")
public class FindTextServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");//request域好像只能为String类型，所以下面需要强制类型转换
        String pro = request.getParameter("pro");

        System.out.println("进行操作："+pro);

        //2.调用Servlet查询
        TextService service = new TextServiceImpl();
        Text text = service.findTextById(id);

        //3.将user存入request
        request.setAttribute("text", text);
        //4.转发到update.jsp
        if (pro.equals("fenci")) {
            request.getRequestDispatcher("/Process.jsp").forward(request, response);
        } else if (pro.equals("update")) {
            request.getRequestDispatcher("/update.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
