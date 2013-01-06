package de.fh_zwickau.mgdi.calc.util;

public class BinaryUtil {

	public enum ShiftDirection
	{
		SHIFT_RIGHT,
		SHIFT_LEFT;
	}
	
	
	private static BinaryUtil instance;
	
	private BinaryUtil()
	{
		//
	}
	
	public static BinaryUtil instance()
	{
		if(instance == null)
		{
			instance = new BinaryUtil();
		}
		return instance;
	}
	
	
	public String toBinary(int i)
	{
		return toBinary(i, -1);
	}
	
	public String toBinary(int i, int minLen)
	{
		String bin = Integer.toBinaryString(i);
		for (int j = bin.length(); j < minLen; j++) {
			bin = "0" + bin;
		}
		return bin;
	}
	
	public String fill(String s, int n)
	{
		for (int i = s.length(); i < n; i++) {
			s = "0" + s;
		}
		return s;
	}
	
	public int[] stringToAscii(final String s)
	{
		int foo[] = new int[s.length()];
		for(int i = 0; i < s.length(); ++i)
		{
			foo[i] = (int) s.charAt(i);
		}
		return foo;		
	}
	
	public String XOR(String x1, String x2)
	{
		int ix1 = Integer.parseInt(x1, 2);
		int ix2 = Integer.parseInt(x2, 2);
		return toBinary((ix1 ^ ix2), x1.length());
	}
	
	public String shift(String s, ShiftDirection dir, int n)
	{
		if(dir == ShiftDirection.SHIFT_RIGHT)
		{
			for (int i = 0; i < n; i++) 
			{
				s = "0" + s;
				s = s.substring(0, s.length()-1);
			}
		}
		else
		{
			for (int i = 0; i < n; i++) 
			{
				s = s + 0;
				s = s.substring(1, s.length());
			}
		}
		return s;
	}
	
	
}
