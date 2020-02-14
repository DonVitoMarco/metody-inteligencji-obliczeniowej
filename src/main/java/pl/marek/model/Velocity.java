package pl.marek.model;

public class Velocity {

    private final double X;
    private final double Y;

    public Velocity(double X, double Y) {
        this.X = X;
        this.Y = Y;
    }

    public double X() {
        return X;
    }

    public double Y() {
        return Y;
    }

    public Velocity multiply(double scalar) {
        double valueX = X * scalar;
        double valueY = Y * scalar;

        return new Velocity(valueX, valueY);
    }
}
