package DAO.Impl;

import DAO.CityDAO;
import charts.CityStatistic;
import entities.City;
import entities.Person;
import entities.StudentRegistration;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaQuery;
import utils.HibernateUtil;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CityDAOImpl implements CityDAO {
    @Override
    public void addCity(City city) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(city);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        } finally {
            if (session != null && session.isOpen()) {
                session.clear();
            }
        }
    }

    public void connectPersonWithCity(Long id_city, Person person) throws SQLException {
        Session session = null;
        City city = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            city = session.get(City.class, id_city);
            city.addPerson(person);
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Collection<Person> getPeople(Long id_city){
        Session session = null;
        List<Person> people = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            var city = session.get(City.class, id_city);
            people = city.getPeople().stream().collect(Collectors.toList());
        }catch (Exception e){
            System.out.println(e.fillInStackTrace());
        }finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return people;
    }

    public List<CityStatistic> getCountStudentsOnCourseFromCities(Long id_course){
        Session session = null;
        List<CityStatistic> cityStatistics = new ArrayList<>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            var cq = cb.createQuery(City.class);
            Root<City> root = cq.from(City.class);
            cq.select(root);

            Query query = session.createQuery(cq);
            for(Object city: query.getResultList()){
                city = (City)city;
                var cityStatistic = new CityStatistic(((City) city).getCityName());
                for(Person person: ((City) city).getPeople()){
                    for (StudentRegistration registration: person.getStudent().getRegistrations()){
                        if (registration.getCourse().getId() == id_course){
                            cityStatistic.addStudent();
                            break;
                        }
                    }
                }
                cityStatistics.add(cityStatistic);
            }
        }catch (Exception e){
            System.out.println(e.fillInStackTrace());
        }finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return cityStatistics;
    }

    @Override
    public void updateCity(City city) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(city);
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
    public City getCityById(long id_city) throws SQLException {
        Session session = null;
        City city = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            city = session.get(City.class, id_city);
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return city;
    }

    public City getCityByCityName(String cityName) throws SQLException{
        Session session = null;
        City city = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            var cq = cb.createQuery(City.class);
            Root<City> root = cq.from(City.class);
            cq.where(cb.equal(root.get("cityName"), cityName));
            Query query = session.createQuery(cq);
            city = (City) query.getSingleResult();
        }catch (Exception e){
            System.out.println(e.fillInStackTrace());
        }finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return city;
    }

    @Override
    public Collection<City> getAllCities() throws SQLException {
        Session session = null;
        List<City> cities = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            var cq = cb.createQuery(City.class);
            Root<City> root = cq.from(City.class);
            cq.select(root);

            Query query = session.createQuery(cq);
            cities = query.getResultList();
        }catch (Exception e){
            System.out.println(e.fillInStackTrace());
        }finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return cities;
    }

    @Override
    public void deleteCity(City city) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(city);
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
