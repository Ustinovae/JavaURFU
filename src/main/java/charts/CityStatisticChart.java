package charts;

import charts.statistics.CityStatistic;
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
import services.CityService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CityStatisticChart {
    public JPanel createDemoPanel(List<CityStatistic> cityStatistics)
    {
        JFreeChart chart = createChart(createDataset(cityStatistics));
        chart.setPadding(new RectangleInsets(4, 8, 2, 2));
        ChartPanel panel = new ChartPanel(chart);
        panel.setFillZoomRectangle(true);
        panel.setMouseWheelEnabled(true);
        panel.setPreferredSize(new Dimension(600, 300));
        return panel;
    }

    public void paintGraphic(){
        var cityService = new CityService();
        CityStatisticChart cityStatisticChart = new CityStatisticChart();
        var cityStatistics = cityService.getCountStudentsOnCourseFromCities(1l);
        JPanel panel = cityStatisticChart.createDemoPanel(cityStatistics);
        var jFrame = new JFrame();
        jFrame.getContentPane().add(panel);
        jFrame.setSize(600, 600);
        jFrame.setVisible(true);
    }

    private CategoryDataset createDataset(List<CityStatistic> cityStatistics)
    {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (CityStatistic c: cityStatistics){
            dataset.addValue(c.getCountStudent(), "Число студентов", c.getCityName());
        }
        return dataset;
    }

    private JFreeChart createChart(CategoryDataset dataset)
    {
        JFreeChart chart = ChartFactory.createBarChart(
                "Число студентов по городам",
                "Город",                   // x-axis label
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
