package pl.marek.model;

import java.text.DecimalFormat;

public class Particle {

    private Position position;
    private Position bestPosition;
    private double bestValue;
    private Velocity velocity;

    public Particle(double initPositionX, double initPositionY) {
        position = new Position(initPositionX, initPositionY);
        bestPosition = position;
        bestValue = Double.MAX_VALUE;
        velocity = new Velocity(0, 0);
    }

    public void setNewBestValue(Position position, double fitnessValue) {
        bestPosition = new Position(position.X(), position.Y());
        bestValue = fitnessValue;
    }

    public void move() {
        position.X(position.X() + velocity.X());
        position.Y(position.Y() + velocity.Y());
    }

    public void moveAndSet(Velocity velocity) {
        this.velocity = velocity;
        move();
    }

    public Position getBestPosition() {
        return bestPosition;
    }

    public void printBestPosition() {
        DecimalFormat df = new DecimalFormat("0.00000");

        System.out.printf("Particle : X=%s Y=%s Value=%s %n",
                df.format(bestPosition.X()),
                df.format(bestPosition.Y()),
                df.format(bestValue)
        );
    }

    public Position getPosition() {
        return position;
    }

    public void printPosition() {
        DecimalFormat df = new DecimalFormat("0.00000");

        System.out.printf("Particle : X=%s Y=%s %n",
                df.format(position.X()),
                df.format(position.Y())
        );
    }

    public double getBestValue() {
        return bestValue;
    }

    public Velocity getVelocity() {
        return velocity;
    }
}
