package charts.statistics;

import java.util.regex.Pattern;

public class NumberOfCorrectAnswers {
    private String questionName;
    private int numberCorrectAnswer;

    public NumberOfCorrectAnswers(String questionName) {
        this.questionName = questionName;
    }

    public String getQuestionName() {
        return questionName;
    }

    public int getNumberCorrectAnswer() {
        return numberCorrectAnswer;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public void setNumberCorrectAnswer(int numberCorrectAnswer) {
        this.numberCorrectAnswer = numberCorrectAnswer;
    }

    public void addAnswer(){
        numberCorrectAnswer++;
    }

    public static boolean isQuestion(String nameTask){
        Pattern pattern = Pattern.compile(".*Контрольны[йе]? вопрос[ы]?.*");
        return pattern.matcher(nameTask).find();
    }

    @Override
    public String toString() {
        return "NumberOfCorrectAnswers{" +
                "questionName='" + questionName + '\'' +
                ", numberCorrectAnswer=" + numberCorrectAnswer +
                '}';
    }
}
