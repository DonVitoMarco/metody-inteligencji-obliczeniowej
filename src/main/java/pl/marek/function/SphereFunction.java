package pl.marek.function;

import io.vavr.Tuple2;

public class SphereFunction implements Function {

    @Override
    public double fitness(double X, double Y) {
        return Math.pow(X, 2) + Math.pow(Y, 2);
    }

    @Override
    public Tuple2<Double, Double> getFunctionRestriction() {
        return new Tuple2<>(-5.12, 5.12);
    }
}
