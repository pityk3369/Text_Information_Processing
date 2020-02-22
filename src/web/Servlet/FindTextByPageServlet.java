package web.Servlet;

import domain.PageBean;
import domain.Text;
import service.impl.TextServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findTextByPageServlet")
public class FindTextByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取参数
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");
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
        //获取list.jsp中条件查询参数
//        Map<String, String[]> condition = request.getParameterMap();
//        for (String s : condition.keySet()) {
//            String[] values = (String[]) condition.get(s);
//            String value = Arrays.toString(values);
//            System.out.println("name:" + s + " ,value:" + value);
//        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
