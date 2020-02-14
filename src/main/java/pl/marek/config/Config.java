package pl.marek.config;

public class Config {

    private final double W;
    private final double c1;
    private final double c2;
    private final int particles;
    private final int iteration;

    public Config(double W, double c1, double c2, int particles, int iteration) {
        this.W = W;
        this.c1 = c1;
        this.c2 = c2;
        this.particles = particles;
        this.iteration = iteration;
    }

    public double W() {
        return W;
    }

    public double C1() {
        return c1;
    }

    public double C2() {
        return c2;
    }

    public int particles() {
        return particles;
    }

    public int iteration() {
        return iteration;
    }
}
