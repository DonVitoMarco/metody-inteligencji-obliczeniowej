package pl.marek.config;

import java.util.Random;

public class RandomGenerator {

    private static final Random random = new Random();

    public static double random(double min, double max) {
        return min + random.nextDouble() * (max - min);
    }

    public static double random() {
        return random.nextDouble();
    }
}
