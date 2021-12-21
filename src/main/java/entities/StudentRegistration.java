package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

@Entity
@Table(name = "students_registrations")
public class StudentRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_registration_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToMany(mappedBy = "studentRegistration", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskScore> scores = new ArrayList<>();

    public StudentRegistration() {
    }

    public void addTaskScore(TaskScore taskScore) {
        taskScore.setStudentRegistration(this);
        scores.add(taskScore);
    }

    public void removeTaskScore(TaskScore taskScore){
        scores.remove(taskScore);
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setScores(List<TaskScore> scores) {
        this.scores = scores;
    }

    public Long getId() {
        return id;
    }

    public Course getCourse() {
        return course;
    }

    public Student getStudent() {
        return student;
    }

    public List<TaskScore> getScores() {
        return scores;
    }

    @Override
    public String toString() {
        return "StudentRegistration{" +
                "id=" + id +
                ", course=" + course +
                ", student=" + student +
                ", scores=" + scores +
                '}';
    }
}
