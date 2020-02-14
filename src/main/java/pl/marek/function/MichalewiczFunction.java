package pl.marek.function;

import io.vavr.Tuple2;

public class MichalewiczFunction implements Function {

    @Override
    public double fitness(double X, double Y) {
        double sum;
        double m = 10;

        double sumX = Math.sin(X) * Math.pow(
                Math.sin(1 * Math.pow(X, 2) / Math.PI), 2 * m
        );

        double sumY = Math.sin(Y) * Math.pow(
                Math.sin(2 * Math.pow(Y, 2) / Math.PI), 2 * m
        );

        sum = sumX + sumY;
        return  (-1) * sum;
    }

    @Override
    public Tuple2<Double, Double> getFunctionRestriction() {
        return new Tuple2<>(0.0, 3.16);
    }
}
