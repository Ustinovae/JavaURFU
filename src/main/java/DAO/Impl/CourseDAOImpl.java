package DAO.Impl;

import DAO.CourseDAO;
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

public class CourseDAOImpl implements CourseDAO {
    @Override
    public void addCourse(Course course) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(course);
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
    public void updateCourse(Course course) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(course);
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
    public Course getCourseById(int id_course) throws SQLException {
        Session session = null;
        Course course = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            course = session.get(Course.class, id_course);
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return course;
    }

    public void connectStudentRegistrationWithCourse(Long id_course, StudentRegistration registration) throws SQLException {
        Session session = null;
        Course course = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            course = session.get(Course.class, id_course);
            course.addRegistration(registration);
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void connectTopicWithCourse(Long id_course, Topic topic) throws SQLException {
        Session session = null;
        Course course = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            course = session.get(Course.class, id_course);
            course.addTopic(topic);
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Course getCourseByCourseName(String courseName) throws SQLException{
        Session session = null;
        Course course = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            var cq = cb.createQuery(Course.class);
            Root<Course> root = cq.from(Course.class);
            cq.where(cb.equal(root.get("courseName"), courseName));
            Query query = session.createQuery(cq);
            course = (Course) query.getResultList().stream().findFirst().get();
        }catch (Exception e){
            System.out.println(e.fillInStackTrace());
        }finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return course;
    }

    @Override
    public Collection getAllCourses() throws SQLException {
        Session session = null;
        List<Course> courses = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            var cq = cb.createQuery(Course.class);
            Root<Course> root = cq.from(Course.class);
            cq.select(root);

            Query query = session.createQuery(cq);
            courses = query.getResultList();
        }catch (Exception e){
            System.out.println(e.fillInStackTrace());
        }finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return courses;
    }

    @Override
    public void deleteCourse(Course course) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(course);
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
