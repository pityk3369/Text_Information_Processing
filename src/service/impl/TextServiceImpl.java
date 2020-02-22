package service.impl;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import dao.TextDao;
import dao.UserDao;
import dao.impl.TextDaoImpl;
import dao.impl.UserDaoImpl;
import domain.PageBean;
import domain.Text;
import domain.User;
import service.TextService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TextServiceImpl implements TextService {


    //声明数据库操作对象，
    private TextDao dao = new TextDaoImpl();
    private UserDao userdao = new UserDaoImpl();

    @Override
    public List<Text> findAll() {
        //调用dao完成查询
        return dao.findAll();
    }

    @Override
    public void addText(Text text) {
        //调用dao完成文本增加
        dao.addText(text);
    }

    @Override
    public Text findTextById(String id) {

        return dao.findTextById(id);
    }

    @Override
    public void updateText(Text text) {
        //调用dao完成文本修改
        dao.updateText(text);
    }

    @Override
    public void deleteText(String id) {
        dao.deleteText(id);

    }

    @Override
    public void delSerlectedText(String[] ids) {
        for (String id : ids) {
            dao.deleteText(id);
        }
    }

    @Override
    public String fenCiById(String id) {
        Text text = dao.findTextById(id);
        List<String> fenciList = new ArrayList<>();
        System.out.println(text.getContent().toString());
        System.out.println(NLPTokenizer.segment(text.getContent().toString()));
        List<Term> termList = NLPTokenizer.segment(text.getContent().toString());
        for (Term term : termList) {
            if (term.word.length() > 1) {
                fenciList.add(term.word);
            }
            //ystem.out.println(term.word);
        }
        return fenciList.toString();
    }

    @Override
    public String keyWordsById(String id) {
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
    }

    @Override
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


     /* *
     *  根据两个参数来获取本页的文本显示列表
     * @param _currentPage
     * @param _rows
     * @param condition
     * @return
     */
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

    @Override
    public User login(User user) {
        return userdao.findUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }
}
