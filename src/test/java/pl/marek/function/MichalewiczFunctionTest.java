package pl.marek.function;

import org.junit.Assert;
import org.junit.Test;

import java.text.DecimalFormat;

public class MichalewiczFunctionTest {

    @Test
    public void fitness() {
        MichalewiczFunction michalewiczFunction = new MichalewiczFunction();

        double result = michalewiczFunction.fitness(2.20, 1.57);

        DecimalFormat df = new DecimalFormat("0.000");
        System.out.println(df.format(result));

        Assert.assertEquals(-1.8013, result, 1e-3);
    }
}