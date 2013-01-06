package de.fh_zwickau.mgdi.calc.algo;

import de.fh_zwickau.mgdi.calc.algo.param.IntegerParameter;

public class Dec2Hex extends Algorithm 
{

	public Dec2Hex() {
		super();
		registerParam(new IntegerParameter("decimal number", 0));
	}
	
	static String numberAlign(int z, int n)
	{
		String s = "" + z;
		for (int i = s.length(); i < n; i++) 
		{
			s = " " + s;
		}
		return s;
	}
	
	@Override
	public ReturnType calc(String[] args) 
	{
		StringBuffer buf = new StringBuffer();

		int zahl = new Integer(args[0]);
		buf.append(zahl + "_(10) = " 
			+ Integer.toHexString(zahl).toUpperCase() + "_(16) \n");
		int maxWidth = ("" + zahl).length();
		buf.append(du.line(30) + "\n");
		
		while(zahl > 0)
		{
			buf.append(numberAlign(zahl, maxWidth) + " : 16 = " 
				+ numberAlign((zahl / 16), maxWidth)
				+ "\t Rest: " + Integer.toHexString(zahl % 16).toUpperCase() 
				+ "\n");
			zahl = (int) zahl / 16;
		}
		return new ReturnType(true, buf.toString());
	}

	@Override
	public String getHelpText() {
		return "converts decimal number into binary one.";
	}

}
