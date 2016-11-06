package fi.johannes.services;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface ConversionService {

	
	public BigInteger StringToBigInt(String s);
	public BigDecimal StringToBigDec(String s);
	public BigInteger[] StringsToBigInts(String[] s);
	public BigDecimal[] StringsToBigDecs(String[] s);
}
