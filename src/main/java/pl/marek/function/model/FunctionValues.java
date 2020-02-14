package pl.marek.function.model;

import io.vavr.Tuple5;

public class FunctionValues {

    public static Tuple5<Double, Double, Double, Integer, Integer> getBestValues(String name) {
        if (name.equals(FunctionName.SPHERE.getName())) {
            return new Tuple5<>(0.6, 2.0, 2.0, 30, 1600);
        } else if (name.equals(FunctionName.ACKLEY.getName())) {
            return new Tuple5<>(0.6, 2.0, 2.0, 90, 1800);
        } else if (name.equals(FunctionName.MICHALEWICZ.getName())) {
            return new Tuple5<>(0.6, 2.0, 4.0, 70, 1850);
        } else if (name.equals(FunctionName.RASTRIGIN.getName())) {
            return new Tuple5<>(0.6, 2.0, 2.0, 30, 1950);
        } else if (name.equals(FunctionName.SCHWEFEL.getName())) {
            return new Tuple5<>(0.6, 2.0, 2.0, 70, 1950);
        } else {
            return new Tuple5<>(0.6, 2.0, 2.0, 30, 1600);
        }
    }
}
