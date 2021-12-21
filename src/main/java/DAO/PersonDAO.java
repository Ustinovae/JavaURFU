package DAO;

import entities.Person;

import java.sql.SQLException;
import java.util.Collection;

public interface PersonDAO {
    public void addPerson(Person person) throws SQLException;
    public void updatePerson(Person person) throws SQLException;
    public Person getPersonById(int id_person) throws SQLException;
    public Collection getAllPeople() throws SQLException;
    public void deletePerson(Person person) throws SQLException;
}
