package pl.marek.function;

import io.vavr.Tuple2;

public class RastriginFunction implements Function {

    @Override
    public double fitness(double X, double Y) {
        double valueX = Math.pow(X, 2) - 10 * Math.cos(2 * Math.PI * X);
        double valueY = Math.pow(Y, 2) - 10 * Math.cos(2 * Math.PI * Y);

        return 10 * 2 + valueX + valueY;
    }

    @Override
    public Tuple2<Double, Double> getFunctionRestriction() {
        return new Tuple2<>(-5.12, 5.12);
    }
}
