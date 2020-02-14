package pl.marek.function;

import org.junit.Assert;
import org.junit.Test;

import java.text.DecimalFormat;

public class AckleyFunctionTest {

    @Test
    public void fitness() {
        AckleyFunction ackleyFunction = new AckleyFunction();

        double result = ackleyFunction.fitness(0, 0);

        DecimalFormat df = new DecimalFormat("0.000");
        System.out.println(df.format(result));

        Assert.assertEquals(0.00, result, 1e-3);
    }
}