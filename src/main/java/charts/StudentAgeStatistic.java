package charts;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class StudentAgeStatistic {
    private int age;
    private int countAge;

    public StudentAgeStatistic(int age) {
        this.age = age;
        countAge = 1;
    }

    public int getAge() {
        return age;
    }

    public int getCountAge() {
        return countAge;
    }

    public void addStudent(){
        countAge++;
    }

    public static int calculatesAge(LocalDate birthDate) {
        if (birthDate != null) {
            return Period.between(birthDate, LocalDate.now()).getYears();
        } else {
            return 0;
        }
    }

    public static LocalDate checkCorrectFormat(String bDate) {
        if (bDate == null)
            return null;

        var bDateArr = bDate.split("[.]");
        if (bDateArr.length != 3)
            return null;

        var day = Integer.valueOf(bDateArr[0]);
        var month = Integer.valueOf(bDateArr[1]);
        var year = Integer.valueOf(bDateArr[2]);

        return LocalDate.of(year, month, day);
    }

    @Override
    public String toString() {
        return "StudentAgeStatistic{" +
                "age=" + age +
                ", countAge=" + countAge +
                '}';
    }
}
