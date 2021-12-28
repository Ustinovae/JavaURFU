package charts;

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

public class NumberOfSolvedTasksCharts {
    public JPanel createDemoPanel(List<NumberOfSolvedTasks> numberOfSolvedTasks)
    {
        JFreeChart chart = createChart(createDataset(numberOfSolvedTasks));
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
        var numberOfSolvedTasks = topicService.getNumberOfSolvedTasksInTopic(topicId.getId());
        var numberOfSolvedTasksCharts = new NumberOfSolvedTasksCharts();
        JPanel jPanel1 = numberOfSolvedTasksCharts.createDemoPanel(numberOfSolvedTasks);
        var jFrame3 = new JFrame();
        jFrame3.getContentPane().add(jPanel1);
        jFrame3.setSize(600, 600);
        jFrame3.setVisible(true);
    }

    private CategoryDataset createDataset(List<NumberOfSolvedTasks> numberOfSolvedTasks)
    {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (NumberOfSolvedTasks c: numberOfSolvedTasks){
            dataset.addValue(c.getNumberSolvedTasks(), "Количество решенных задач", c.getNameTask());
        }
        return dataset;
    }

    private JFreeChart createChart(CategoryDataset dataset)
    {
        JFreeChart chart = ChartFactory.createBarChart(
                "Количество решенных задач в теме",
                "Название задачи",                   // x-axis label
                "Число студентов решивших задачу",     // y-axis label
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
