package DAO;

import entities.Topic;

import java.sql.SQLException;
import java.util.Collection;

public interface TopicDAO {
    public void addTopic(Topic topic) throws SQLException;
    public void updateTopic(Topic topic) throws SQLException;
    public Topic getTopicById(int id_topic) throws SQLException;
    public Collection getAllThemes() throws SQLException;
    public void deleteTopic(Topic topic) throws SQLException;
}
