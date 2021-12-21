package services;

import DAO.Impl.StudentDAOImpl;
import entities.Student;
import entities.StudentRegistration;

import java.sql.SQLException;
import java.util.Collection;

public class StudentService {
    private StudentDAOImpl studentDAO;

    public StudentService(){
        studentDAO = new StudentDAOImpl();
    }

    public void addStudent(Student student) throws SQLException {
        studentDAO.addStudent(student);
    }

    public Student getStudentByUsername(String firstname, String lastname) throws SQLException {
        return studentDAO.getStudentByUsername(firstname, lastname);
    }

    public void connectStudentRegistrationWithStudent(Long id_student, StudentRegistration registration) throws SQLException {
        studentDAO.connectStudentRegistrationWithStudent(id_student, registration);
    }

    public void updateStudent(Student student) throws SQLException {
        studentDAO.updateStudent(student);
    }

    public Student getStudentById(int id_student) throws SQLException {
        return studentDAO.getStudentById(id_student);
    }

    public Collection getAllStudents() throws SQLException {
        return studentDAO.getAllStudents();
    }

    public void deleteStudent(Student student) throws SQLException {
        studentDAO.deleteStudent(student);
    }
}
