package services;

import DAO.Impl.CourseDAOImpl;
import entities.Course;
import entities.StudentRegistration;
import entities.Topic;

import java.sql.SQLException;
import java.util.Collection;

public class CourseService {

    private CourseDAOImpl courseDAO;

    public CourseService() {
        courseDAO = new CourseDAOImpl();
    }

    public void addCourse(Course course) throws SQLException {
        courseDAO.addCourse(course);
    }

    public void updateCourse(Course course) throws SQLException {
        courseDAO.updateCourse(course);
    }

    public Course getCourseById(int id_course) throws SQLException {
        return courseDAO.getCourseById(id_course);
    }

    public void connectStudentRegistrationWithCourse(Long id_course, StudentRegistration registration) throws SQLException {
        courseDAO.connectStudentRegistrationWithCourse(id_course, registration);
    }

    public void connectTopicWithCourse(Long id_course, Topic topic) throws SQLException {
        courseDAO.connectTopicWithCourse(id_course, topic);
    }

    public Course getCourseByCourseName(String courseName) throws SQLException {
        return courseDAO.getCourseByCourseName(courseName);
    }

    public Collection getAllCourses() throws SQLException {
        return courseDAO.getAllCourses();
    }

    public void deleteCourse(Course course) throws SQLException {
        courseDAO.deleteCourse(course);
    }
}
