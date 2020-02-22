package web.Servlet;

import domain.Text;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.jdbc.support.CustomSQLErrorCodesTranslation;
import service.TextService;
import service.impl.TextServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/updateTextServlet")
public class UpdateTextServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
