package pl.marek.function;

import org.junit.Assert;
import org.junit.Test;

import java.text.DecimalFormat;

import static org.junit.Assert.*;

public class SchwefelFunctionTest {

    @Test
    public void fitness() {
        SchwefelFunction schwefelFunction = new SchwefelFunction();

        double result = schwefelFunction.fitness(420.9687, 420.9687);

        DecimalFormat df = new DecimalFormat("0.000");
        System.out.println(df.format(result));

        Assert.assertEquals(0.00, result, 1e-3);
    }
}