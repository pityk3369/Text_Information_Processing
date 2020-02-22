package web.Servlet;

import com.hankcs.hanlp.seg.common.Term;
import domain.Text;
import service.TextService;
import service.impl.TextServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/processTextServlet")
public class ProcessTextServlet extends HttpServlet {
    String fenciList="";
    String keywordsList="";
    String summaryList = "";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入processTextServlet");
        //1.获取id
        String id = request.getParameter("id");//request域好像只能为String类型，所以下面需要强制类型转换
        String pro = request.getParameter("pro");

        //2.调用Servlet查询
        TextService service = new TextServiceImpl();
        Text text = service.findTextById(id);

        //3.将user存入request
        request.setAttribute("text", text);

        //4.转发到update.jsp
        if(pro.equals("fenci")) {
            System.out.println("分词！！！");
            fenciList=service.fenCiById(id);
            System.out.println(fenciList);

        }
        else if (pro.equals("keywords")) {
            System.out.println("关键词！！！");
            keywordsList=service.keyWordsById(id);
            System.out.println(keywordsList);

        } else if (pro.equals("summary")) {
            System.out.println("文本摘要！！！");
            summaryList=service.summaryById(id);
            System.out.println(summaryList);
        }
        request.setAttribute("keywordsList", keywordsList);
        request.setAttribute("fenciList", fenciList);
        request.setAttribute("summaryList", summaryList);
        request.getRequestDispatcher("/Process.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
