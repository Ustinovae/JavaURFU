package entities;

import javax.persistence.*;

@Entity
@Table(name = "tasks_scores")
public class TaskScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_score_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_registration_id")
    private StudentRegistration studentRegistration;

    @Column(name = "score")
    private int score;

    public TaskScore(){
    }

    public void setTask(Task task){
        this.task = task;
    }

    public void setStudentRegistration(StudentRegistration studentRegistration){
        this.studentRegistration = studentRegistration;
    }

    public void setScore(int score){
        this.score = score;
    }

    public Long getId(){
        return id;
    }

    public Task getTask(){
        return task;
    }

    public StudentRegistration getStudentRegistration(){
        return studentRegistration;
    }

    public int getScore(){
        return score;
    }

    @Override
    public String toString() {
        return "TaskScore{" +
                "id=" + id +
                ", task=" + task +
                ", studentRegistration=" + studentRegistration +
                ", score=" + score +
                '}';
    }
}
