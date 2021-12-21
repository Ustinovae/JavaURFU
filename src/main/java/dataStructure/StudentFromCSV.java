package dataStructure;

import java.net.URL;

public class StudentFromCSV extends Person
        implements StudentInterface{

    private final String specialization;
    private final String academicGroup;
    private final Course course;

    public StudentFromCSV(String username, String specialization, String academicGroup, Course course){
        super(username);
        this.specialization = specialization;
        this.academicGroup = academicGroup;
        this.course = course;
    }

    public void convertUsernameToNameLastname(){
        if (getLastname() == null){
            var usernameArr = getUsername().split(" ");
            var firstname = new StringBuilder();
            var lastname = new StringBuilder();
            for (int i = 0; i < usernameArr.length / 2; i++){
                firstname.append(usernameArr[i]).append(" ");
            }
            firstname.setLength(firstname.length() - 1);
            for (int i = usernameArr.length / 2; i < usernameArr.length; i++){
                lastname.append(usernameArr[i]).append(" ");
            }
            lastname.setLength(lastname.length() - 1);

            setFirstname(firstname.toString());
            setLastname(lastname.toString());
        }
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getAcademicGroup() {
        return academicGroup;
    }

    public int getGrade() {
        return 0;
    }

    public Course getCourse() {
        return course;
    }

    public String toString(){
        var sb = new StringBuilder();
        sb.append(String.format("ФИ: %s; ", getUsername()));
        sb.append(String.format("Фамилия студента: %s; ", getLastname()));
        sb.append(String.format("Фото студента: %s; ", getLinkPhoto()));
        sb.append(String.format("Имя студента: %s; ", getFirstname()));
        sb.append(String.format("id студента: %s; ", getUserVkId()));
        sb.append(String.format("день рождения: %s; ", getBirthDay()));
        sb.append(String.format("Группа студента: %s; ",academicGroup));
        sb.append(String.format("Пол: %s; ", getGender()));
        sb.append(course.toString());

        return sb.toString();
    }
}
