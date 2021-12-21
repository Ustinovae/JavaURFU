package dataStructure;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Topic {
    private int number;
    private String name;
    private int totalScore;
    private ArrayList<Task> tasks;

    public Topic(String name){
        tasks = new ArrayList<>();
        this.name = name;
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    public ArrayList<Task> getTasks(){
        return tasks;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setNumber(int number){
        this.number = number;
    }

    public void setTotalScore(int score){
        totalScore = score;
    }

    public String getName(){
        return name;
    }

    public int getTotalScore(){
        return totalScore;
    }

    public String toString(){
        var sb = new StringBuilder();
        sb.append(String.format("Оценка за тему: %d; ", totalScore));
        sb.append(String.format("Название темы: %s; ", name));
        for(Task task: tasks)
            sb.append(task.toString());
        return sb.toString();
    }
}
