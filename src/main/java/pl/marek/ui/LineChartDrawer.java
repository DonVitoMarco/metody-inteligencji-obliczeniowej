package pl.marek.ui;

import io.vavr.Tuple3;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LineChartDrawer extends JFrame {

    public LineChartDrawer(List<Tuple3<Integer, Double, Double>> values) throws HeadlessException {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        setResizable(false);
        setSize(1000, 600);
        setTitle("PSO - Convergence graph");

        DefaultXYDataset dateSet = createDateSet(values);
        JFreeChart chart = ChartFactory.createXYLineChart("Convergence graph",
                "Iteration", "Value", dateSet, PlotOrientation.VERTICAL, true, true,
                false);

        ChartPanel cp = new ChartPanel(chart);
        cp.setBounds(0, 0, 1000, 550);

        add(cp);
    }

    public DefaultXYDataset createDateSet(List<Tuple3<Integer, Double, Double>> values) {
        DefaultXYDataset ds = new DefaultXYDataset();
        double[][] globalBestValue = new double[2][values.size()];
        double[][] generationAvgValue = new double[2][values.size()];

        for (int i = 0; i < values.size(); i++) {
            Tuple3<Integer, Double, Double> value = values.get(i);

            globalBestValue[0][i] = value._1;
            globalBestValue[1][i] = value._2;

            generationAvgValue[0][i] = value._1;
            generationAvgValue[1][i] = value._3;
        }

        ds.addSeries("Global Best Value", globalBestValue);
        ds.addSeries("Generation Avg Value", generationAvgValue);

        return ds;
    }
}
