package services;

import DAO.Impl.PersonDAOImpl;
import charts.statistics.StudentAgeStatistic;
import entities.Person;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class PersonService {

    private PersonDAOImpl personDAO;

    public PersonService(){
        personDAO = new PersonDAOImpl();
    }

    public void addPerson(Person person) throws SQLException {
        personDAO.addPerson(person);
    }

    public void updatePerson(Person person) throws SQLException {
        personDAO.updatePerson(person);
    }

    public List<StudentAgeStatistic> getNumberStudentsOnCourseByAge(Long id_course){
        return personDAO.getNumberStudentsOnCourseByAge(id_course);
    }

    public Person getPersonById(int id_person) throws SQLException {
        return personDAO.getPersonById(id_person);
    }

    public Collection getAllPeople() throws SQLException {
        return personDAO.getAllPeople();
    }

    public void deletePerson(Person person) throws SQLException {
        personDAO.deletePerson(person);
    }
}
