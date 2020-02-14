package pl.marek.function;

import io.vavr.Tuple2;

public interface Function {

    double fitness(double X, double Y);

    Tuple2<Double, Double> getFunctionRestriction();
}
