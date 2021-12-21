package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "max_score")
    private int maxScore;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<TaskScore> scores = new ArrayList<>();

    public Task(){
    }

    public void addTaskScore(TaskScore score){
        score.setTask(this);
        scores.add(score);
    }

    public void removeTaskScore(TaskScore score){
        scores.remove(score);
    }

    public void setTopic(Topic topic){
        this.topic = topic;
    }

    public void setTaskName(String taskName){
        this.taskName = taskName;
    }

    public void setMaxScore(int maxScore){
        this.maxScore = maxScore;
    }

    public void setScores(ArrayList<TaskScore> scores){
        this.scores = scores;
    }

    public Long getId(){
        return id;
    }

    public Topic getTopic(){
        return topic;
    }

    public String getTaskName(){
        return taskName;
    }

    public int getMaxScore(){
        return maxScore;
    }

    public List<TaskScore> getScores(){
        return scores;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", topic=" + topic +
                ", taskName='" + taskName + '\'' +
                ", maxScore=" + maxScore +
                ", scores=" + scores +
                '}';
    }
}
