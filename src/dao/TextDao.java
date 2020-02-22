package dao;

import domain.Text;

import java.util.List;

public interface TextDao {
    public List<Text> findAll();

    void addText(Text text);

    void updateText(Text text);

    Text findTextById(String id);

    void deleteText(String id);

    int findTotalCount();

    List<Text> findByPage(int start, int rows);
}
