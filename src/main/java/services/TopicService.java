package services;

import DAO.Impl.TopicDAOImpl;
import entities.Task;
import entities.Topic;
import org.hibernate.Session;
import utils.HibernateUtil;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TopicService {

    private TopicDAOImpl topicDAO;

    public TopicService(){
        topicDAO = new TopicDAOImpl();
    }

    public void addTopic(Topic topic) throws SQLException {
        topicDAO.addTopic(topic);
    }

    public void updateTopic(Topic topic) throws SQLException {
        topicDAO.updateTopic(topic);
    }

    public void connectTaskWithTopic(Long id_topic, Task task) throws SQLException {
        topicDAO.connectTaskWithTopic(id_topic, task);
    }

    public Collection<Task> getTasksFromTopic(Long id_Topic){
        return topicDAO.getTasksFromTopic(id_Topic);
    }

    public Topic getTopicById(int id_topic) throws SQLException {
        return topicDAO.getTopicById(id_topic);
    }

    public Topic getTopicByTopicName(String topicName) throws SQLException{
        return topicDAO.getTopicByTopicName(topicName);
    }

    public Collection getAllThemes() throws SQLException {
        return topicDAO.getAllThemes();
    }

    public void deleteTopic(Topic topic) throws SQLException {
        topicDAO.deleteTopic(topic);
    }
}
