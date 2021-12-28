package charts;

import charts.statistics.NumberOfCorrectAnswers;
import charts.statistics.NumberOfSolvedTasks;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import services.TopicService;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class NumberOfCorrectAnswerCharts {
    public JPanel createDemoPanel(List<NumberOfCorrectAnswers> numberOfCorrectAnswer)
    {
        JFreeChart chart = createChart(createDataset(numberOfCorrectAnswer));
        chart.setPadding(new RectangleInsets(4, 8, 2, 2));
        ChartPanel panel = new ChartPanel(chart);
        panel.setFillZoomRectangle(true);
        panel.setMouseWheelEnabled(true);
        panel.setPreferredSize(new Dimension(600, 300));
        return panel;
    }

    public void painGraphics(String topicName) throws SQLException {
        var topicService = new TopicService();
        var topicId = topicService.getTopicByTopicName(topicName);
        var numberOfCorrectAnswers = topicService.getNumberOfCorrectAnswer(topicId.getId());
        var numberOfCorrectAnswerCharts = new NumberOfCorrectAnswerCharts();
        JPanel jPanel1 = numberOfCorrectAnswerCharts.createDemoPanel(numberOfCorrectAnswers);
        var jFrame3 = new JFrame();
        jFrame3.getContentPane().add(jPanel1);
        jFrame3.setSize(600, 600);
        jFrame3.setVisible(true);
    }

    private CategoryDataset createDataset(List<NumberOfCorrectAnswers> numberOfSolvedTasks)
    {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int i = 0;
        for (NumberOfCorrectAnswers c: numberOfSolvedTasks){
            i++;
            dataset.addValue(c.getNumberCorrectAnswer(),
                    "Количество правильных ответов",
                    String.format("%s №%d",c.getQuestionName(), i));
        }
        return dataset;
    }

    private JFreeChart createChart(CategoryDataset dataset)
    {
        JFreeChart chart = ChartFactory.createBarChart(
                "Количество правильных ответов в теме",
                "Название вопроса",                   // x-axis label
                "Число студентов ответивших правильно",     // y-axis label
                dataset);
        chart.setBackgroundPaint(Color.white);

        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        chart.getLegend().setFrame(BlockBorder.NONE);

        return chart;
    }
}
