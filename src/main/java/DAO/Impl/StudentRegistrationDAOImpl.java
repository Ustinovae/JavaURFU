package DAO.Impl;

import DAO.StudentRegistrationDAO;
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

public class StudentRegistrationDAOImpl implements StudentRegistrationDAO {
    @Override
    public void addStudentStudentRegistration(StudentRegistration studentRegistration) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(studentRegistration);
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
    public void updateStudentRegistration(StudentRegistration studentRegistration) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(studentRegistration);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void connectTaskScoreWithStudentRegistration(Long id_registration, TaskScore score) throws SQLException {
        Session session = null;
        StudentRegistration registration = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            registration = session.get(StudentRegistration.class, id_registration);
            registration.addTaskScore(score);
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public StudentRegistration getStudentRegistrationOnCourse(Long id_student, Long id_course) throws SQLException {
        Session session = null;
        StudentRegistration studentRegistration = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            var cq = cb.createQuery(StudentRegistration.class);
            Root<StudentRegistration> studentRegistrationRoot = cq.from(StudentRegistration.class);

            cq.where(cb.and(cb.equal(studentRegistrationRoot.get("student"), id_student),
                    cb.equal(studentRegistrationRoot.get("course"), id_course)));
            Query query = session.createQuery(cq);
            studentRegistration = (StudentRegistration) query.getResultList().stream().findFirst().get();
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return studentRegistration;
    }

    @Override
    public StudentRegistration getStudentRegistrationById(int id_student_registration) throws SQLException {
        Session session = null;
        StudentRegistration registration = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            registration = session.get(StudentRegistration.class, id_student_registration);
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return registration;
    }

    @Override
    public Collection getAllStudentRegistration() throws SQLException {
        Session session = null;
        List<StudentRegistration> studentsRegistrations = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            var cq = cb.createQuery(StudentRegistration.class);
            Root<StudentRegistration> root = cq.from(StudentRegistration.class);
            cq.select(root);

            Query query = session.createQuery(cq);
            studentsRegistrations = query.getResultList();
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return studentsRegistrations;
    }

    @Override
    public void deleteStudentRegistration(StudentRegistration studentRegistration) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(studentRegistration);
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
