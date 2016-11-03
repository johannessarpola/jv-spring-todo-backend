package fi.johannes.crapgen;

import java.util.Random;

public class StringGen {

	/**
	 * @param length
	 * @return random alphabetical string
	 */
	public static String randomAlphaNumericString(int length)
	{		
		char[] cbuf = new char[length];
		char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		int n = chars.length;
		Random rand = new Random(System.currentTimeMillis() - ((long)length * 1000));
		for(int i = 0; i < length; i++)
		{
			cbuf[i] = chars[rand.nextInt(n)];
		}
		return String.valueOf(cbuf);
	}
}
