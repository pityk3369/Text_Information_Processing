package service;

import domain.PageBean;
import domain.Text;
import domain.User;

import java.util.List;
import java.util.Map;

/**
 * 文本管理的业务接口
 */
public interface TextService {
    /**
     * 查询所有文本
     * @return
     */
    public List<Text> findAll();

    /**
     * 增加本文
     * @param text
     */
    void addText(Text text);

    /**
     * 根据文本id查找文本对象
     * @param id
     * @return
     */
    Text findTextById(String id);
    /**
     * 修改文本
     * @param text
     */
    void updateText(Text text);


    /**
     * 根据文本id删除
     * @param id
     */
    void deleteText(String id);

    /**
     * 删除选中的文本
     * @param ids
     */
    void delSerlectedText(String[] ids);

    /**
     * 将特定文本分词
     * @param id
     * @return
     */
    String fenCiById(String id);

    /**
     * 从数据库中检索出文本，进行关键词提取！
     * @param id
     * @return
     */
    String keyWordsById(String id);

    /**
     * 文本自动摘要
     * @param id
     * @return
     */
    String summaryById(String id);

    /**
     *
     * 文本列表分页显示控制对象
     * @param currentPage
     * @param rows
     * @return
     */
    PageBean<Text> findTextByPage(String currentPage, String rows);

    /**
     * 用户登录
     * @param user
     * @return
     */
    User login(User user);
}
