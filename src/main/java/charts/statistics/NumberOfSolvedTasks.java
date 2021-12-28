package charts.statistics;

import java.util.regex.Pattern;

public class NumberOfSolvedTasks {
    private String nameTask;
    private int numberSolvedTasks;

    public NumberOfSolvedTasks(String nameTask) {
        this.nameTask = nameTask;
    }

    public String getNameTask() {
        return nameTask;
    }
    public void addSolvedTasks(){
        numberSolvedTasks++;
    }

    public int getNumberSolvedTasks() {
        return numberSolvedTasks;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    public void setNumberSolvedTasks(int numberSolvedTasks) {
        this.numberSolvedTasks = numberSolvedTasks;
    }

    public static boolean isTask(String nameTask){
        Pattern pattern = Pattern.compile(".*Контрольны[йе]? вопрос[ы]?.*");
        return !pattern.matcher(nameTask).find();
    }

    @Override
    public String toString() {
        return "NumberOfSolvedTasks{" +
                "nameTask='" + nameTask + '\'' +
                ", numberSolvedTasks=" + numberSolvedTasks +
                '}';
    }
}
