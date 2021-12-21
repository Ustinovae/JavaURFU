package services;

import DAO.Impl.StudentRegistrationDAOImpl;
import entities.StudentRegistration;
import entities.TaskScore;
import org.hibernate.Session;
import utils.HibernateUtil;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StudentRegistrationService {

    private StudentRegistrationDAOImpl studentRegistrationDAO;

    public StudentRegistrationService(){
        studentRegistrationDAO = new StudentRegistrationDAOImpl();
    }

    public void addStudentStudentRegistration(StudentRegistration studentRegistration) throws SQLException {
        studentRegistrationDAO.addStudentStudentRegistration(studentRegistration);
    }

    public void updateStudentRegistration(StudentRegistration studentRegistration) throws SQLException {
        studentRegistrationDAO.updateStudentRegistration(studentRegistration);
    }

    public StudentRegistration getStudentRegistrationOnCourse(Long id_student, Long id_course) throws SQLException {
        return studentRegistrationDAO.getStudentRegistrationOnCourse(id_student, id_course);
    }

    public void connectTaskScoreWithStudentRegistration(Long id_registration, TaskScore score) throws SQLException {
        studentRegistrationDAO.connectTaskScoreWithStudentRegistration(id_registration, score);
    }

    public StudentRegistration getStudentRegistrationById(int id_student_registration) throws SQLException {
        return studentRegistrationDAO.getStudentRegistrationById(id_student_registration);
    }

    public Collection getAllStudentRegistration() throws SQLException {
        return studentRegistrationDAO.getAllStudentRegistration();
    }

    public void deleteStudentRegistration(StudentRegistration studentRegistration) throws SQLException {
        studentRegistrationDAO.deleteStudentRegistration(studentRegistration);
    }
}
