package DAO;

import entities.Course;

import java.sql.SQLException;
import java.util.Collection;

public interface CourseDAO {
    public void addCourse(Course course) throws SQLException;
    public void updateCourse(Course course) throws SQLException;
    public Course getCourseById(int id_course) throws SQLException;
    public Collection getAllCourses() throws SQLException;
    public void deleteCourse(Course course) throws SQLException;
}
