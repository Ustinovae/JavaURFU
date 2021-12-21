package DAO.Impl;

import DAO.TaskDAO;
import entities.StudentRegistration;
import entities.Task;
import entities.TaskScore;
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

public class TaskDAOImpl implements TaskDAO {
    @Override
    public void addTask(Task task) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(task);
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
    public void updateTask(Task task) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(task);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void connectTaskScoreWithTask(Long id_task, TaskScore score) throws SQLException {
        Session session = null;
        Task task = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            task = session.get(Task.class, id_task);
            task.addTaskScore(score);
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public Task getTaskById(int id_task) throws SQLException {
        Session session = null;
        Task task = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            task = session.get(Task.class, id_task);
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return task;
    }

    public Task getTaskByTaskName(String taskName) throws SQLException{
        Session session = null;
        Task task = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            var cq = cb.createQuery(Task.class);
            Root<Task> root = cq.from(Task.class);
            cq.where(cb.equal(root.get("taskName"), taskName));
            Query query = session.createQuery(cq);
            task = (Task) query.getResultList().stream().findFirst().get();
        }catch (Exception e){
            System.out.println(e.fillInStackTrace());
        }finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return task;
    }

    @Override
    public Collection getAllTasks() throws SQLException {
        Session session = null;
        List<Task> tasks = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            var cq = cb.createQuery(Task.class);
            Root<Task> root = cq.from(Task.class);
            cq.select(root);

            Query query = session.createQuery(cq);
            tasks = query.getResultList();
        }catch (Exception e){
            System.out.println(e.fillInStackTrace());
        }finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return tasks;
    }

    @Override
    public void deleteTask(Task task) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(task);
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
