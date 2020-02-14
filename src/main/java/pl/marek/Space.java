package pl.marek;

import pl.marek.config.Config;
import pl.marek.config.RandomGenerator;
import pl.marek.function.Function;
import pl.marek.model.Particle;
import pl.marek.model.Position;
import pl.marek.model.Velocity;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Space {

    private final Config config;
    private final Function function;
    private final int numberOfParticles;
    private List<Particle> particles;
    private double globalBestValue = Double.MAX_VALUE;
    private Position globalBestPosition;

    public Space(Config config, Function function, int numberOfParticles) {
        this.function = function;
        this.config = config;
        this.numberOfParticles = numberOfParticles;
        this.globalBestPosition = new Position(RandomGenerator.random(1, 6), RandomGenerator.random(1, 6));
        initParticles();
    }

    private void initParticles() {
        particles = IntStream
                .range(0, numberOfParticles)
                .mapToObj(i -> new Particle(generateRandomValueBetweenRestriction(), generateRandomValueBetweenRestriction()))
                .collect(Collectors.toList());
    }

    private double generateRandomValueBetweenRestriction() {
        return RandomGenerator.random(function.getFunctionRestriction()._1, function.getFunctionRestriction()._2);
    }

    private double fitness(double X, double Y) {
        return function.fitness(X, Y);
    }

    public void setGlobalBestPosition() {
        for (Particle p : particles) {
            double bestCandidateFitness = fitness(p.getPosition().X(), p.getPosition().Y());
            if (globalBestValue > bestCandidateFitness) {
                globalBestValue = bestCandidateFitness;
                globalBestPosition = new Position(p.getPosition().X(), p.getPosition().Y());
            }
        }
    }

    public void setParticlesBest() {
        for (Particle p : particles) {
            double bestCandidateFitness = fitness(p.getPosition().X(), p.getPosition().Y());
            if (p.getBestValue() > bestCandidateFitness) {
                p.setNewBestValue(p.getPosition(), bestCandidateFitness);
            }
        }
    }

    public void moveParticles() {
        for (Particle p : particles) {
            double random01 = config.C1() * RandomGenerator.random();
            double random02 = config.C2() * RandomGenerator.random();

            Position vector01 = p.getBestPosition().minus(p.getPosition());
            Position vector02 = globalBestPosition.minus(p.getPosition());

            Position p1 = vector01.multiply(random01);
            Position p2 = vector02.multiply(random02);

            Velocity v = p.getVelocity().multiply(config.W());

            double newVelocityX = p1.X() + p2.X() + v.X();
            double newVelocityY = p1.Y() + p2.Y() + v.Y();

            boolean outOfX = isOutOfFunctionRestriction(newVelocityX + p.getPosition().X());
            boolean outOfY = isOutOfFunctionRestriction(newVelocityY + p.getPosition().Y());
            boolean outOfFunctionRestrictions = outOfX || outOfY;

            if (!outOfFunctionRestrictions) {
                p.moveAndSet(new Velocity(newVelocityX, newVelocityY));
            }
        }
    }

    public boolean isOutOfFunctionRestriction(double value) {
        return function.getFunctionRestriction()._1 > value || function.getFunctionRestriction()._2 < value;
    }

    public double getGlobalBestValue() {
        return globalBestValue;
    }

    public double getGenerationAverageValue() {
        Double sum = particles
                .stream()
                .map(p -> fitness(p.getPosition().X(), p.getPosition().Y())).reduce(0.0, Double::sum);
        return sum / particles.size();
    }

    public Position getGlobalBestPosition() {
        return globalBestPosition;
    }

    public void printGlobalBestPosition() {
        DecimalFormat df = new DecimalFormat("0.00000");

        System.out.printf("Global Best Position : X=%s Y=%s Value=%s %n",
                df.format(globalBestPosition.X()),
                df.format(globalBestPosition.Y()),
                df.format(globalBestValue)
        );
    }

    public List<Particle> getParticles() {
        return particles;
    }

    public void printBestPositionParticles() {
        particles.forEach(Particle::printBestPosition);
    }

    public void printParticles() {
        particles.forEach(Particle::printPosition);
    }
}
