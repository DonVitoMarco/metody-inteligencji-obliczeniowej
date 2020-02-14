package pl.marek.model;

public class Position {

    private double X;
    private double Y;

    public Position(double X, double Y) {
        this.X = X;
        this.Y = Y;
    }

    public double X() {
        return X;
    }

    public double Y() {
        return Y;
    }

    public void X(double X) {
        this.X = X;
    }

    public void Y(double Y) {
        this.Y = Y;
    }

    public Position minus(Position p) {
        double valueX = X - p.X();
        double valueY = Y - p.Y();

        return new Position(valueX, valueY);
    }

    public Position multiply(double scalar) {
        double valueX = X * scalar;
        double valueY = Y * scalar;

        return new Position(valueX, valueY);
    }
}
