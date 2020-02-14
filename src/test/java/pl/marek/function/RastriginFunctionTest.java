package pl.marek.function;

import org.junit.Assert;
import org.junit.Test;

import java.text.DecimalFormat;

public class RastriginFunctionTest {

    @Test
    public void fitness() {
        RastriginFunction rastriginFunction = new RastriginFunction();

        double result = rastriginFunction.fitness(0.00, 0.00);

        DecimalFormat df = new DecimalFormat("0.000");
        System.out.println(df.format(result));

        Assert.assertEquals(0.00, result, 1e-3);
    }
}