package dataStructure;

public class Task {
    private String title;
    private final int score;

    public Task(int score, String title){
        this.score = score;
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public int getScore(){
        return score;
    }

    public String toString(){
        return String.format("%s: %d;", title, score);
    }
}
