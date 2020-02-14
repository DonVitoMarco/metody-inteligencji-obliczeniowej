package pl.marek.function;

import io.vavr.Tuple2;

public class SchwefelFunction implements Function {

    @Override
    public double fitness(double X, double Y) {
        double outcome = 418.9828 * 2;

        double valueX = X * (Math.sin(Math.sqrt(Math.abs(X))));
        double valueY = Y * (Math.sin(Math.sqrt(Math.abs(Y))));

        double sum = (valueX + valueY);

        return outcome - sum;
    }

    @Override
    public Tuple2<Double, Double> getFunctionRestriction() {
        return new Tuple2<>(-500.0, 500.0);
    }
}
