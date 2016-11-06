package fi.johannes.services;

import java.math.BigDecimal;
import java.math.BigInteger;


public interface MathService {

	BigInteger sum(BigInteger[] bigints);

	BigDecimal mean(BigInteger[] bigints);

	BigDecimal median(BigInteger[] bigints);

}