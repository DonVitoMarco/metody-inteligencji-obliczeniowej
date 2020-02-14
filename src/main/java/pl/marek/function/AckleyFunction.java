package pl.marek.function;

import io.vavr.Tuple2;

public class AckleyFunction implements Function {

    @Override
    public double fitness(double X, double Y) {
        double a = 20;
        double b = 0.2;
        double c = 2 * Math.PI;

        double sum1 = Math.pow(X, 2) + Math.pow(Y, 2);
        double sum2 = Math.cos(c * X) + Math.cos(c * Y);

        return a + Math.exp(1) - a * Math.exp(-b * Math.sqrt(sum1 / 2)) - Math.exp(sum2 / 2);
    }

    @Override
    public Tuple2<Double, Double> getFunctionRestriction() {
        return new Tuple2<>(-32.768, 32.768);
    }
}
