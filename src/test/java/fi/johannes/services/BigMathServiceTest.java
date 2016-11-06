package fi.johannes.services;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;

public class BigMathServiceTest {

	@Test
	public void test() {
		BigInteger[] bigints = {new BigInteger("10"), new BigInteger("100"), new BigInteger("1000")}; 
		BigMathService service = new BigMathService();
		BigDecimal avg = service.mean(bigints);
		BigDecimal median = service.median(bigints);
		BigInteger total = service.sum(bigints);
		assertEquals((10+100+1000)/3., avg.doubleValue(), 0.);
		assertEquals(100., median.doubleValue(), 0);
		assertEquals(1110., total.doubleValue(), 0 );
	}

}
