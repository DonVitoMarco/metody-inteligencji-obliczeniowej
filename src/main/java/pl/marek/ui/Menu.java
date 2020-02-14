package pl.marek.ui;

import io.vavr.Tuple5;
import pl.marek.PSO;
import pl.marek.config.Config;
import pl.marek.function.Function;
import pl.marek.function.model.FunctionFactory;
import pl.marek.function.model.FunctionName;
import pl.marek.function.model.FunctionValues;
import pl.marek.model.Position;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.text.DecimalFormat;

import static org.jzy3d.analysis.AnalysisLauncher.open;

public class Menu extends JFrame {

    private JComboBox functionComboBox;

    private JTextField W;
    private JTextField c1;
    private JTextField c2;
    private JTextField particle;
    private JTextField iteration;

    private JLabel positionX;
    private JLabel positionY;

    private JRadioButton withUITrue;
    private JRadioButton withUIFalse;
    private JRadioButton withDocsTrue;
    private JRadioButton withDocsFalse;

    public Menu() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        setResizable(false);
        setTitle("PSO");
        setSize(230, 500);

        createConfigCreator();
        createStartButton();
        setInitValue();
    }

    public void createConfigCreator() {
        JLabel functionLabel = new JLabel("Function: ");
        functionLabel.setBounds(10, 30, 200, 20);
        functionComboBox = new JComboBox();
        functionComboBox.addItem(FunctionName.SPHERE.getName());
        functionComboBox.addItem(FunctionName.ACKLEY.getName());
        functionComboBox.addItem(FunctionName.MICHALEWICZ.getName());
        functionComboBox.addItem(FunctionName.RASTRIGIN.getName());
        functionComboBox.addItem(FunctionName.SCHWEFEL.getName());
        functionComboBox.setBounds(10, 50, 200, 20);

        functionComboBox.addActionListener(e -> {
            Object selectedItem = functionComboBox.getSelectedItem();
            if (selectedItem != null) {
                updateValues();
            }
        });

        JLabel wLabel = new JLabel("W: ");
        wLabel.setBounds(10, 70, 200, 20);
        W = new JTextField();
        W.setBounds(10, 90, 200, 20);
        W.setEditable(Boolean.FALSE);

        JLabel c1Label = new JLabel("C1: ");
        c1Label.setBounds(10, 110, 200, 20);
        c1 = new JTextField();
        c1.setBounds(10, 130, 200, 20);

        JLabel c2Label = new JLabel("C2: ");
        c2Label.setBounds(10, 150, 200, 20);
        c2 = new JTextField();
        c2.setBounds(10, 170, 200, 20);

        JLabel particleLabel = new JLabel("PARTICLE: ");
        particleLabel.setBounds(10, 190, 200, 20);
        particle = new JTextField();
        particle.setBounds(10, 210, 200, 20);

        JLabel iterationLabel = new JLabel("ITERATION: ");
        iterationLabel.setBounds(10, 230, 200, 20);
        iteration = new JTextField();
        iteration.setBounds(10, 250, 200, 20);

        JLabel withUILabel = new JLabel("WITH UI: ");
        withUILabel.setBounds(10, 270, 200, 20);

        withUITrue = new JRadioButton("true");
        withUIFalse = new JRadioButton("false");

        withUITrue.setBounds(10, 290, 60, 20);
        withUIFalse.setBounds(80, 290, 60, 20);

        withUITrue.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                withUIFalse.setSelected(Boolean.FALSE);
            } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                withUIFalse.setSelected(Boolean.TRUE);
            }
        });

        withUIFalse.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.DESELECTED) {
                withUITrue.setSelected(Boolean.TRUE);
            }
            if (e.getStateChange() == ItemEvent.SELECTED) {
                withUITrue.setSelected(Boolean.FALSE);
            }
        });

        JLabel withDocsLabel = new JLabel("WITH DOCS: ");
        withDocsLabel.setBounds(10, 310, 200, 20);


        withDocsTrue = new JRadioButton("true");
        withDocsFalse = new JRadioButton("false");

        withDocsTrue.setBounds(10, 330, 60, 20);
        withDocsFalse.setBounds(80, 330, 60, 20);

        withDocsTrue.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                withDocsFalse.setSelected(Boolean.FALSE);
            } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                withDocsFalse.setSelected(Boolean.TRUE);
            }
        });

        withDocsFalse.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.DESELECTED) {
                withDocsTrue.setSelected(Boolean.TRUE);
            }
            if (e.getStateChange() == ItemEvent.SELECTED) {
                withDocsTrue.setSelected(Boolean.FALSE);
            }
        });

        JLabel resultLabel = new JLabel("RESULT: ");
        resultLabel.setBounds(10, 380, 200, 20);

        positionX = new JLabel("");
        positionX.setBounds(10, 400, 200, 20);

        positionY = new JLabel("");
        positionY.setBounds(10, 420, 200, 20);

        add(functionLabel);
        add(functionComboBox);
        add(wLabel);
        add(W);
        add(c1Label);
        add(c1);
        add(c2Label);
        add(c2);
        add(particleLabel);
        add(particle);
        add(iterationLabel);
        add(iteration);
        add(withUILabel);
        add(withUITrue);
        add(withUIFalse);
        add(withDocsLabel);
        add(withDocsTrue);
        add(withDocsFalse);
        add(resultLabel);
        add(positionX);
        add(positionY);
    }

    public void createStartButton() {
        JButton start = new JButton("START");
        start.setBounds(10, 360, 200, 20);

        start.addActionListener(e -> {
            String wText = W.getText();
            String c1Text = c1.getText();
            String c2Text = c2.getText();
            String particleText = particle.getText();
            String iterationText = iteration.getText();

            Double wValue = null;
            Double c1Value = null;
            Double c2Value = null;
            Integer particleValue = null;
            Integer iterationValue = null;
            Boolean withUI = withUITrue.isSelected();
            Boolean withDocs = withDocsTrue.isSelected();
            String functionName = null;

            try {
                wValue = Double.valueOf(wText);
                c1Value = Double.valueOf(c1Text);
                c2Value = Double.valueOf(c2Text);
                particleValue = Integer.valueOf(particleText);
                iterationValue = Integer.valueOf(iterationText);
                functionName = (String) functionComboBox.getSelectedItem();
            } catch (Exception exception) {
            }

            if (wValue != null && c1Value != null && c2Value != null && particleValue != null && iterationValue != null && functionName != null) {
                Config config = new Config(wValue, c1Value, c2Value, particleValue, iterationValue);
                Function function = FunctionFactory.getFunction(functionName);

                ChartDrawer chartDrawer = null;
                if (withUI) {
                    chartDrawer = new ChartDrawer(function);
                    chartDrawer.init();
                    try {
                        open(chartDrawer);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                PSO pso = new PSO(chartDrawer, config, function, withDocs);
                Position result = pso.run();

                DecimalFormat df = new DecimalFormat("0.0000000");
                positionX.setText(df.format(result.X()));
                positionY.setText(df.format(result.Y()));

                new LineChartDrawer(pso.getValues());
            }
        });

        add(start);
    }

    public void setInitValue() {
        Tuple5<Double, Double, Double, Integer, Integer> values = FunctionValues.getBestValues(FunctionName.SPHERE.getName());
        setValues(values);
    }

    public void updateValues() {
        String functionName = (String) functionComboBox.getSelectedItem();
        if (functionName != null) {
            Tuple5<Double, Double, Double, Integer, Integer> values = FunctionValues.getBestValues(functionName);
            setValues(values);
        }
    }

    public void setValues(Tuple5<Double, Double, Double, Integer, Integer> values) {
        W.setText(String.valueOf(values._1));
        c1.setText(String.valueOf(values._2));
        c2.setText(String.valueOf(values._3));
        particle.setText(String.valueOf(values._4));
        iteration.setText(String.valueOf(values._5));
        withUIFalse.setSelected(Boolean.TRUE);
        withDocsFalse.setSelected(Boolean.TRUE);
    }
}
