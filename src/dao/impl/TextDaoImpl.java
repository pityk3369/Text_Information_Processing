package dao.impl;


import dao.TextDao;
import domain.Text;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;


import java.util.List;

public class TextDaoImpl implements TextDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Text> findAll() {
        //使用JDBC操作数据库...
        //1.定义sql
        String sql = "select * from texts";
        List<Text> texts = template.query(sql, new BeanPropertyRowMapper<Text>(Text.class));
        return texts;
    }

    @Override
    public void addText(Text text) {
        //1.定义sql，ID为null自增长；其余属性为？，由text类属性完成
        String sql = "insert into texts values(null,?,?,?,?,?)";
        //2.执行sql
        template.update(sql, text.getType(), text.getTitle(), text.getTime(), text.getContent(), text.getUrl());

    }

    @Override
    public Text findTextById(String id) {
        String sql = "select * from texts where id=?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<Text>(Text.class), id);
    }

    @Override
    public void updateText(Text text) {
        String sql="update texts set type = ?,title = ? ,time = ? , content = ? , url = ? where id = ?";
        template.update(sql, text.getType(), text.getTitle(), text.getTime(), text.getContent(), text.getUrl(), text.getId());

    }

    @Override
    public void deleteText(String id) {
        String sql = "delete from texts where id = ?";
        template.update(sql, id);
    }

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
}
