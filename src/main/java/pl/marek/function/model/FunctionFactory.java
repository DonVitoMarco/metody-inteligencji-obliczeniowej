package pl.marek.function.model;

import pl.marek.function.*;

public class FunctionFactory {

    public static Function getFunction(String name) {
        if (name.equals(FunctionName.SPHERE.getName())) {
            return new SphereFunction();
        } else if (name.equals(FunctionName.ACKLEY.getName())) {
            return new AckleyFunction();
        } else if (name.equals(FunctionName.MICHALEWICZ.getName())) {
            return new MichalewiczFunction();
        } else if (name.equals(FunctionName.RASTRIGIN.getName())) {
            return new RastriginFunction();
        } else if (name.equals(FunctionName.SCHWEFEL.getName())) {
            return new SchwefelFunction();
        } else {
            return new SphereFunction();
        }
    }
}
