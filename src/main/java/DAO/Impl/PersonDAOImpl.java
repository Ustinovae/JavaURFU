package DAO.Impl;

import DAO.PersonDAO;
import charts.statistics.StudentAgeStatistic;
import entities.Person;
import entities.StudentRegistration;
import org.hibernate.Session;
import utils.HibernateUtil;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PersonDAOImpl implements PersonDAO {
    @Override
    public void addPerson(Person person) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(person);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        } finally {
            if (session != null && session.isOpen()) {
                session.clear();
            }
        }
    }

    public List<StudentAgeStatistic> getNumberStudentsOnCourseByAge(Long id_course) {
        Session session = null;
        List<StudentAgeStatistic> studentAgeStatistics = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            var cq = cb.createQuery(Person.class);
            Root<Person> root = cq.from(Person.class);
            cq.select(root);

            Query query = session.createQuery(cq);
            for (Object person : query.getResultList()) {
                LocalDate bDate = StudentAgeStatistic.checkCorrectFormat(((Person) person).getBirthday());
                if (bDate == null)
                    continue;
                var age = StudentAgeStatistic.calculatesAge(bDate);
                var exists = false;
                for (StudentRegistration registration : ((Person) person).getStudent().getRegistrations()) {
                    if (registration.getCourse().getId() == id_course) {
                        exists = true;
                        break;
                    }
                }
                if (!exists)
                    continue;
                StudentAgeStatistic studentAgeStatistic = null;
                for (StudentAgeStatistic s: studentAgeStatistics){
                    if (s.getAge() == age){
                        studentAgeStatistic = s;
                        studentAgeStatistic.addStudent();
                    }

                }
                if (studentAgeStatistic == null)
                    studentAgeStatistics.add(new StudentAgeStatistic(age));
            }
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return studentAgeStatistics;
    }

    @Override
    public void updatePerson(Person person) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(person);
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
    public Person getPersonById(int id_person) throws SQLException {
        Session session = null;
        Person person = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            person = session.get(Person.class, id_person);
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return person;
    }

//    public Person getPersonByUsername(String firstname, String lastname) throws SQLException{
//        Session session = null;
//        Person person = null;
//        try {
//            session = HibernateUtil.getSessionFactory().openSession();
//            CriteriaBuilder cb = session.getCriteriaBuilder();
//            var cq = cb.createQuery(Person.class);
//            Root<Person> root = cq.from(Person.class);
//            cq.where(cb.equal(root.get("first"), cityName));
//            Query query = session.createQuery(cq);
//            person = (City) query.getResultList().stream().findFirst().get();
//        }catch (Exception e){
//            System.out.println(e.fillInStackTrace());
//        }finally {
//            if (session != null && session.isOpen()) {
//                session.close();
//            }
//        }
//        return person;
//    }

    @Override
    public Collection getAllPeople() throws SQLException {
        Session session = null;
        List<Person> people = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            var cq = cb.createQuery(Person.class);
            Root<Person> root = cq.from(Person.class);
            cq.select(root);

            Query query = session.createQuery(cq);
            people = query.getResultList();
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return people;
    }

    @Override
    public void deletePerson(Person person) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(person);
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
