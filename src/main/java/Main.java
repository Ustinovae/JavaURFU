import charts.CityStatisticChart;
import charts.NumberOfCorrectAnswerCharts;
import charts.NumberOfSolvedTasksCharts;
import charts.StudentAgeStatisticChart;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;

import services.CityService;
import services.PersonService;
import services.TopicService;

import javax.swing.*;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws ClientException, InterruptedException, ApiException, SQLException {
//        var parser = new Parser();
//        DataTable table = parser.parseFileToDataTable("java-rtf.csv");
//        var vkApi = new VkApi();
//        vkApi.getUserInfo("198188261", table);
//
//        SchemaGenerateDemo.startCreate();
//        var dbManager = new DBManager();
//        dbManager.loadDataInDB(table);


        CityStatisticChart cityStatisticChart = new CityStatisticChart();
        cityStatisticChart.paintGraphic();



        StudentAgeStatisticChart studentAgeStatisticChart = new StudentAgeStatisticChart();
        studentAgeStatisticChart.paintGraphics();

        var numberOfSolvedTasksCharts = new NumberOfSolvedTasksCharts();
        numberOfSolvedTasksCharts.painGraphics("2. Базовый синтаксис. Типы");

        var numberOfCorrectAnswer = new NumberOfCorrectAnswerCharts();
        numberOfCorrectAnswer.painGraphics("2. Базовый синтаксис. Типы");
    }
}