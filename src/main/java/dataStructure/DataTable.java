package dataStructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataTable {
    private Course maxScores;
    private final ArrayList<StudentFromCSV> students;
    private final Map<String, StudentFromCSV> studentsMap;
    private ArrayList<String> topicsNames;
    private ArrayList<String> tasksNames;
    private ArrayList<Integer> countTasksInTopic;
    private String courseName;

    public DataTable(){
        students = new ArrayList<>();
        topicsNames = new ArrayList<>();
        countTasksInTopic = new ArrayList<>();
        tasksNames = new ArrayList<>();
        studentsMap = new HashMap<>();
    }

    public void setCourseName(String courseName){
        this.courseName = courseName;
    }

    public String getCourseName(){
        return courseName;
    }

    public void addNameTopic(String topicName){
        topicsNames.add(topicName);
    }

    public void addSizeTopic(int size) {
        countTasksInTopic.add(size);
    }

    public void addNameTask(String name){
        tasksNames.add(name);
    }

    public ArrayList<String> getTopicsNames(){
        return topicsNames;
    }

    public ArrayList<String> getTasksNames() {
        return tasksNames;
    }

    public ArrayList<Integer> getCountTasksInTopic() {
        return countTasksInTopic;
    }

    public void addStudent(StudentFromCSV student){
        studentsMap.put(student.getUsername(), student);
        students.add(student);
    }

    public StudentFromCSV getStudent(String userName){
        return studentsMap.get(userName);
    }

    public boolean containStudent(String userName){
        return studentsMap.containsKey(userName);
    }

    public ArrayList<StudentFromCSV> getStudents(){
        return students;
    }

    public Course getMaxScores(){
        return maxScores;
    }

    public void setMaxScores(Course course){
        maxScores = course;
    }

    public String toString(){
        var sb = new StringBuilder();
        for(String topicName: getTopicsNames()){
            sb.append(topicName + ";");
        }
        sb.append("\n");
        for(String taskName: getTasksNames())
            System.out.print(taskName + ";");
        sb.append('\n');
        sb.append(maxScores);
        sb.append('\n');
        for(StudentFromCSV student: students){
            sb.append(student.toString()).append('\n');
        }
        return sb.toString();
    }
}
