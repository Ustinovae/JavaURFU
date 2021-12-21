package dataStructure;

import java.util.ArrayList;

public class Course {
    private final int scoreForCourse;
    private final ArrayList<Topic> topics;

    public Course(int scoreForCourse){
        this.scoreForCourse = scoreForCourse;
        topics = new ArrayList<>();
    }

    public void addTopic(Topic topic){
        topics.add(topic);
    }

    public ArrayList<Topic> getTopics(){
        return topics;
    }

    public int getScoreForCourse(){
        return scoreForCourse;
    }

    public String toString(){
        var sb = new StringBuilder();
        sb.append(String.format("Оценка за курс: %d; ", scoreForCourse));
        for(Topic topic: topics)
            sb.append(topic.toString());

        return sb.toString();
    }
}
