package pl.marek;

import org.junit.Test;
import pl.marek.config.RandomGenerator;

import java.util.stream.IntStream;

public class RandomGeneratorTest {

    @Test
    public void name() {
        IntStream.range(0, 1000).forEach(i -> System.out.println(RandomGenerator.random()));
    }
}