package charts.statistics;

public class CityStatistic {
    private String cityName;
    private int countStudent;

    public CityStatistic(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName(){
        return cityName;
    }

    public int getCountStudent(){
        return countStudent;
    }

    public void addStudent(){
        countStudent++;
    }

    @Override
    public String toString() {
        return "CityStatistic{" +
                "cityName='" + cityName + '\'' +
                ", countStudent=" + countStudent +
                '}';
    }
}
