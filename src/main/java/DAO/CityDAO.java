package DAO;

import entities.City;

import java.sql.SQLException;
import java.util.Collection;

public interface CityDAO {
    public void addCity(City city) throws SQLException;
    public void updateCity(City city) throws SQLException;
    public City getCityById(long id_city) throws SQLException;
    public Collection getAllCities() throws SQLException;
    public void deleteCity(City city) throws SQLException;
}
