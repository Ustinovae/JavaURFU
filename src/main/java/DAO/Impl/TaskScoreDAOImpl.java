package DAO.Impl;

import DAO.TaskScoreDAO;
import entities.Task;
import entities.TaskScore;
import org.hibernate.Session;
import utils.HibernateUtil;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TaskScoreDAOImpl implements TaskScoreDAO {
    @Override
    public void addTaskScore(TaskScore taskScore) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(taskScore);
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
    public void updateTaskScore(TaskScore taskScore) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(taskScore);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public TaskScore getTaskScoreById(int id_task_score) throws SQLException {
        Session session = null;
        TaskScore taskScore = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            taskScore = session.get(TaskScore.class, id_task_score);
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return taskScore;
    }

    @Override
    public Collection getAllTasksScores() throws SQLException {
        Session session = null;
        List<TaskScore> tasksScores = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            var cq = cb.createQuery(TaskScore.class);
            Root<TaskScore> root = cq.from(TaskScore.class);
            cq.select(root);

            Query query = session.createQuery(cq);
            tasksScores = query.getResultList();
        }catch (Exception e){
            System.out.println(e.fillInStackTrace());
        }finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return tasksScores;
    }

    @Override
    public void deleteTaskScore(TaskScore taskScore) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(taskScore);
            session.getTransaction().commit();;
        }catch (Exception e){
            System.out.println(e.fillInStackTrace());
        }finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }
}
