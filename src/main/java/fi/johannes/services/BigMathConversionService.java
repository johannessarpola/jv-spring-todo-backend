package fi.johannes.services;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.springframework.stereotype.Service;

@Service
public class BigMathConversionService implements ConversionService{

	
	public BigMathConversionService() {
	}

	@Override
	public BigInteger StringToBigInt(String s) {
		return new BigInteger(s);
	}

	@Override
	public BigDecimal StringToBigDec(String s) {
		return new BigDecimal(s);
	}
	@Override
	public BigInteger[] StringsToBigInts(String[] s) {
		BigInteger[] bigints = new BigInteger[s.length];
		for(int i = 0; i<s.length; i++) {
			bigints[i] = StringToBigInt(s[i]);
		}
		return bigints;
	}

	@Override
	public BigDecimal[] StringsToBigDecs(String[] s) {
		BigDecimal[] bigdecs = new BigDecimal[s.length];
		for(int i = 0; i<s.length; i++) {
			bigdecs[i] = StringToBigDec(s[i]);
		}
		return bigdecs;
	}
	
	
}

