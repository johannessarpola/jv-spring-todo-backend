package fi.johannes.services;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

import org.springframework.stereotype.Service;

@Service
public class BigMathService implements MathService {

	public BigMathService() {
	}
	
	/* (non-Javadoc)
	 * @see fi.johannes.services.MathService#sum(java.math.BigInteger[])
	 */
	@Override
	public BigInteger sum(BigInteger[] bigints){
		BigInteger sum = BigInteger.valueOf(0L);
		for(BigInteger bigint : bigints) {
			sum = sum.add(bigint);
		}
		return sum;
	}
	
	/* (non-Javadoc)
	 * @see fi.johannes.services.MathService#mean(java.math.BigInteger[])
	 */
	@Override
	public BigDecimal mean(BigInteger[] bigints){
		BigDecimal avg = new BigDecimal("0");
		for(BigInteger bigint : bigints) {
			avg = avg.add(new BigDecimal(bigint));
		}
		BigDecimal divider = BigDecimal.valueOf(bigints.length);
		return avg.divide(divider);
	}
	
	/* (non-Javadoc)
	 * @see fi.johannes.services.MathService#median(java.math.BigInteger[])
	 */
	@Override
	public BigDecimal median(BigInteger[] bigints) {
		BigDecimal median = new BigDecimal("0");
		Arrays.sort(bigints);
		if(bigints.length % 2 == 0) {
			BigInteger a = bigints[bigints.length/2];
			BigInteger b = bigints[(bigints.length-1)/2];
			median = new BigDecimal(a.add(b).divide(new BigInteger("2")));
			return median;
		}
		else {
			return new BigDecimal(bigints[(bigints.length-1)-1]);
		}
	}
	
	
}

