package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id", nullable = false)
    private Long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "group_number")
    private String groupNumber;

    @Column(name = "specialization")
    private String specialization;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<StudentRegistration> registrations = new ArrayList<>();

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private Person person;

    public Student() {
    }

    public void addRegistration(StudentRegistration registration) {
        registration.setStudent(this);
        registrations.add(registration);
    }

    public void removeRegistration(StudentRegistration registration) {
        registrations.remove(registration);
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setRegistrations(ArrayList<StudentRegistration> registrations) {
        this.registrations = registrations;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public String getSpecialization() {
        return specialization;
    }

    public List<StudentRegistration> getRegistrations() {
        return registrations;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", groupNumber='" + groupNumber + '\'' +
                ", specialization='" + specialization + '\'' +
                ", registrations=" + registrations +
                ", person=" + person +
                '}';
    }
}
