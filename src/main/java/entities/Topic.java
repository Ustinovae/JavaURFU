package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "themes")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "topic_name")
    private String topicName;

    @Column(name = "max_score")
    private int maxScore;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();

    public Topic() {
    }

    public void addTask(Task task) {
        task.setTopic(this);
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Long getId() {
        return id;
    }

    public Course getCourse() {
        return course;
    }

    public String getTopicName() {
        return topicName;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", course=" + course +
                ", topicName='" + topicName + '\'' +
                ", maxScore=" + maxScore +
                ", tasks=" + tasks +
                '}';
    }
}
