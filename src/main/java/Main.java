import DAO.Impl.CityDAOImpl;
import DAO.Impl.PersonDAOImpl;
import DBManager.DBManager;
import Parser.Parser;
import VlApi.VkApi;
import charts.CityStatistic;
import charts.StudentAgeStatistic;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;

import services.CityService;
import services.PersonService;
import services.StudentService;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

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

        var cityService = new CityService();
        CityStatisticChart cityStatisticChart = new CityStatisticChart();
        var cityStatistics = cityService.getCountStudentsOnCourseFromCities(1l);
        JPanel panel = cityStatisticChart.createDemoPanel(cityStatistics);
        var jFrame = new JFrame();
        jFrame.getContentPane().add(panel);
        jFrame.setSize(600, 600);
        jFrame.setVisible(true);

        var t = new PersonService();
        var studentAgeStatistics = t.getNumberStudentsOnCourseByAge(1l);
        StudentAgeStatisticChart studentAgeStatisticChart = new StudentAgeStatisticChart();
        JPanel jPanel = studentAgeStatisticChart.createDemoPanel(studentAgeStatistics);
        var jFrame2 = new JFrame();
        jFrame2.getContentPane().add(jPanel);
        jFrame2.setSize(600, 600);
        jFrame2.setVisible(true);
    }
}