package DAO;

import entities.Student;

import java.sql.SQLException;
import java.util.Collection;

public interface StudentDAO {
    public void addStudent(Student student) throws SQLException;
    public void updateStudent(Student student) throws SQLException;
    public Student getStudentById(int id_student) throws SQLException;
    public Collection getAllStudents() throws SQLException;
    public void deleteStudent(Student student) throws SQLException;
}
