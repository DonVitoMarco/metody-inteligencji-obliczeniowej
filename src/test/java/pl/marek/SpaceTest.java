package pl.marek;

import org.junit.Test;
import pl.marek.config.Config;
import pl.marek.function.SchwefelFunction;

public class SpaceTest {

    Config config = new Config(0.3, 1.0, 2.0, 50, 100);

    Space space = new Space(config, new SchwefelFunction(), 1);

    @Test
    public void moveParticles() {
        System.out.println(space.isOutOfFunctionRestriction(650));
    }
}
