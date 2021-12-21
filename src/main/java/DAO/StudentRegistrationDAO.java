package DAO;

import entities.StudentRegistration;

import java.sql.SQLException;
import java.util.Collection;

public interface StudentRegistrationDAO {
    public void addStudentStudentRegistration(StudentRegistration studentRegistration) throws SQLException;
    public void updateStudentRegistration(StudentRegistration studentRegistration) throws SQLException;
    public StudentRegistration getStudentRegistrationById(int id_student_registration) throws SQLException;
    public Collection getAllStudentRegistration() throws SQLException;
    public void deleteStudentRegistration(StudentRegistration studentRegistration) throws SQLException;
}
