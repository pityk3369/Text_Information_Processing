package web.Servlet;

import domain.User;
import org.apache.commons.beanutils.BeanUtils;
import service.TextService;
import service.impl.TextServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

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
