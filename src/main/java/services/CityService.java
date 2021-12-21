package services;

import DAO.Impl.CityDAOImpl;
import charts.CityStatistic;
import entities.City;
import entities.Person;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class CityService {
    private CityDAOImpl cityDAO;

    public CityService(){
        cityDAO = new CityDAOImpl();
    }

    public void addCity(City city) throws SQLException {
        cityDAO.addCity(city);
    }

    public void connectPersonWithWith(Long id_city, Person person) throws SQLException{
        cityDAO.connectPersonWithCity(id_city, person);
    }

    public Collection<Person> getPeople(Long id_city){
        return cityDAO.getPeople(id_city);
    }

    public void updateCity(City city) throws SQLException {
        cityDAO.updateCity(city);
    }

    public List<CityStatistic> getCountStudentsOnCourseFromCities(Long id_course){
        return cityDAO.getCountStudentsOnCourseFromCities(id_course);
    }

    public City getCityById(int id_city) throws SQLException {
        return cityDAO.getCityById(id_city);
    }

    public Collection<City> getAllCities() throws SQLException {
        return cityDAO.getAllCities();
    }

    public void deleteCity(City city) throws SQLException {
        cityDAO.deleteCity(city);
    }

    public City getCityByCityName(String cityName) throws SQLException {
        return cityDAO.getCityByCityName(cityName);
    }
}
