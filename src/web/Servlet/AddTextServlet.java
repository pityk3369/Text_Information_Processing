package web.Servlet;

import domain.Text;
import org.apache.commons.beanutils.BeanUtils;
import service.TextService;
import service.impl.TextServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Map;

@WebServlet("/addTextServlet")
public class AddTextServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取参数
        Map<String, String[]> map = request.getParameterMap();
        System.out.println(map);
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + Arrays.toString(entry.getValue()));
        }
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
