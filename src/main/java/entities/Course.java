package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id", nullable = false)
    private Long id;

    @Column(name = "max_score")
    private int maxScore;

    @Column(name = "course_name")
    private String courseName;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<StudentRegistration> registrations = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Topic> themes = new ArrayList<>();

    public Course() {
    }

    public void addTopic(Topic topic) {
        topic.setCourse(this);
        themes.add(topic);
    }

    public void removeTopic(Topic topic) {
        themes.remove(topic);
    }

    public void addRegistration(StudentRegistration registration) {
        registration.setCourse(this);
        registrations.add(registration);
    }

    public void removeRegistration(StudentRegistration registration) {
        registrations.remove(registration);
    }

    public void setRegistrations(List<StudentRegistration> registrations) {
        this.registrations = registrations;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setThemes(List<Topic> themes) {
        this.themes = themes;
    }

    public Long getId() {
        return id;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public String getCourseName() {
        return courseName;
    }

    public List<Topic> getThemes() {
        return themes;
    }

    public List<StudentRegistration> getRegistrations() {
        return registrations;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", maxScore=" + maxScore +
                ", courseName='" + courseName + '\'' +
                ", registrations=" + registrations +
                ", themes=" + themes +
                '}';
    }
}
