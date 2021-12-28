package charts;

import charts.statistics.StudentAgeStatistic;
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
import services.PersonService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StudentAgeStatisticChart {
    public JPanel createDemoPanel(List<StudentAgeStatistic> studentAgeStatistics)
    {
        JFreeChart chart = createChart(createDataset(studentAgeStatistics));
        chart.setPadding(new RectangleInsets(4, 8, 2, 2));
        ChartPanel panel = new ChartPanel(chart);
        panel.setFillZoomRectangle(true);
        panel.setMouseWheelEnabled(true);
        panel.setPreferredSize(new Dimension(600, 300));
        return panel;
    }

    public void paintGraphics(){
        var t = new PersonService();
        var studentAgeStatistics = t.getNumberStudentsOnCourseByAge(1l);
        StudentAgeStatisticChart studentAgeStatisticChart = new StudentAgeStatisticChart();
        JPanel jPanel = studentAgeStatisticChart.createDemoPanel(studentAgeStatistics);
        var jFrame2 = new JFrame();
        jFrame2.getContentPane().add(jPanel);
        jFrame2.setSize(600, 600);
        jFrame2.setVisible(true);
    }

    private CategoryDataset createDataset(List<StudentAgeStatistic> studentAgeStatistics)
    {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (StudentAgeStatistic c: studentAgeStatistics){
            dataset.addValue(c.getCountAge(), "Число студентов", String.valueOf(c.getAge()));
        }
        return dataset;
    }

    private JFreeChart createChart(CategoryDataset dataset)
    {
        JFreeChart chart = ChartFactory.createBarChart(
                "Число студентов по возрастам",
                "Возраст",                   // x-axis label
                "Число студентов",                // y-axis label
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
