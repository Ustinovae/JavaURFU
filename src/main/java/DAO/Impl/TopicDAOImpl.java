package DAO.Impl;

import DAO.TopicDAO;
import charts.statistics.NumberOfCorrectAnswers;
import charts.statistics.NumberOfSolvedTasks;
import entities.*;
import org.hibernate.Session;
import utils.HibernateUtil;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TopicDAOImpl implements TopicDAO {
    @Override
    public void addTopic(Topic topic) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(topic);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        } finally {
            if (session != null && session.isOpen()) {
                session.clear();
            }
        }
    }

    @Override
    public void updateTopic(Topic topic) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(topic);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void connectTaskWithTopic(Long id_topic, Task task) throws SQLException {
        Session session = null;
        Topic topic = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            topic = session.get(Topic.class, id_topic);
            topic.addTask(task);
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public List<NumberOfCorrectAnswers> getNumberOfCorrectAnswer(Long id_topic) {
        Session session = null;
        List<NumberOfCorrectAnswers> numberOfCorrectAnswers = new ArrayList<>();
        Topic topic = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            topic = session.get(Topic.class, id_topic);

            for (Task task : topic.getTasks()) {
                if (!NumberOfCorrectAnswers.isQuestion(task.getTaskName()))
                    continue;
                numberOfCorrectAnswers.add(new NumberOfCorrectAnswers(task.getTaskName()));
                for (TaskScore score : task.getScores()) {
                    if (score.getScore() == task.getMaxScore())
                        numberOfCorrectAnswers.get(numberOfCorrectAnswers.size() - 1).addAnswer();
                }
            }

        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return numberOfCorrectAnswers;
    }

    public List<NumberOfSolvedTasks> getNumberOfSolvedTasksInTopic(Long id_topic) {
        Session session = null;
        List<NumberOfSolvedTasks> solvedTasks = new ArrayList<>();
        Topic topic = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            topic = session.get(Topic.class, id_topic);

            for (Task task : topic.getTasks()) {
                if (!NumberOfSolvedTasks.isTask(task.getTaskName()))
                    continue;
                solvedTasks.add(new NumberOfSolvedTasks(task.getTaskName()));
                for (TaskScore score : task.getScores()) {
                    if (score.getScore() == task.getMaxScore())
                        solvedTasks.get(solvedTasks.size() - 1).addSolvedTasks();
                }
            }

        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return solvedTasks;
    }

    public Collection<Task> getTasksFromTopic(Long id_Topic) {
        Session session = null;
        List<Task> tasks = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Topic topic = session.get(Topic.class, id_Topic);
            tasks = topic.getTasks().stream().collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return tasks;
    }

    public Topic getTopicByTopicName(String topicName) throws SQLException {
        Session session = null;
        Topic topic = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            var cq = cb.createQuery(Topic.class);
            Root<Topic> root = cq.from(Topic.class);
            cq.where(cb.equal(root.get("topicName"), topicName));
            Query query = session.createQuery(cq);
            topic = (Topic) query.getResultList().stream().findFirst().get();
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return topic;
    }

    @Override
    public Topic getTopicById(int id_topic) throws SQLException {
        Session session = null;
        Topic topic = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            topic = session.get(Topic.class, id_topic);
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return topic;
    }

    @Override
    public Collection getAllThemes() throws SQLException {
        Session session = null;
        List<Topic> topics = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            var cq = cb.createQuery(Topic.class);
            Root<Topic> root = cq.from(Topic.class);
            cq.select(root);

            Query query = session.createQuery(cq);
            topics = query.getResultList();
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return topics;
    }

    @Override
    public void deleteTopic(Topic topic) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(topic);
            session.getTransaction().commit();
            ;
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
