package fi.johannes.interop;

import fi.johannes.calculator.Calculator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Johannes on 16.3.2017.
 */
public class CalculatorTests {

    @Test
    public void plus(){
        Calculator calculator = new Calculator();
        scala.math.BigDecimal result = calculator.calculate("1+1");
        assertEquals(2.0, result.doubleValue(),0.);
    }
    @Test
    public void minus(){
        Calculator calculator = new Calculator();
        scala.math.BigDecimal result = calculator.calculate("2-1");
        assertEquals(1.0, result.doubleValue(),0.);
    }
}
