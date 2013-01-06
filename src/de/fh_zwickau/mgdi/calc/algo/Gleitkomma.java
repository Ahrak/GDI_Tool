package de.fh_zwickau.mgdi.calc.algo;

import de.fh_zwickau.mgdi.calc.algo.param.BooleanParameter;
import de.fh_zwickau.mgdi.calc.algo.param.IntegerParameter;

public class Gleitkomma extends Algorithm {

	public Gleitkomma() 
	{
		super();
		//registerParam(new IntegerParameter("base", 2));
		registerParam(new IntegerParameter("mantisse", 2));
		registerParam(new IntegerParameter("min. exponent", -1));
		registerParam(new IntegerParameter("max. exponent", 1));
		registerParam(new BooleanParameter("denorm", false));
	}
	
	@Override
	public ReturnType calc(String[] args) 
	{
		//OUT BUFFER
		StringBuffer buf = new StringBuffer();
		
		//PARAMS
		//int B = new Integer(args[0]);
		int B = 2;
		int p = new Integer(args[0]);
		int eMin = new Integer(args[1]);
		int eMax = new Integer(args[2]);
		Boolean denorm = new Boolean(args[3]);
		
		//ERRORS
		if(eMin >= eMax)
		{
			return new ReturnType(false, "eMax >= eMin");
		}
		if(p < 1)
		{
			return new ReturnType(false, "wrong mantisse size");
		}
		
		//
		buf.append(("F(%1, %2, %3, %4, %5) \n")
				.replace("%1", "" + B)
				.replace("%2", "" + p)
				.replace("%3", "" + eMin)
				.replace("%4", "" + eMax)
				.replace("%5", denorm.toString())
		);
		//
		buf.append(du.line(35) + "\n");
		//
		buf.append("B     \t = " + B + "\n");
		buf.append("p     \t = " + p + "\n");
		buf.append("eMin  \t = " + eMin + "\n");
		buf.append("eMax  \t = " + eMax + "\n");
		buf.append("denorm \t = " + denorm + "\n");
		//
		buf.append(du.line(35) + "\n");
		//
		
		//CALC PROPS
		//------------
		
		//COUNT
		double count = 0;
		if(!denorm)
		{
			count = (1 + 2 * (B - 1) * Math.pow(B, p - 1 ) * (eMax - eMin +1));
		}
		else
		{		
			count = (2 * ( Math.pow(B, p - 1) - 1 ));
		}
		buf.append("Count \t = " + (int)count + "\n");
		
		//MIN/MAX
		double max = B * ( 1 - Math.pow(B, -p)) * Math.pow(B, eMax);
		buf.append("Max \t = " + max + "\n");
		double min = Math.pow(B, eMin);
		buf.append("Min \t = " + min + "\n");
		
		//DELTA-X
		for (int e = eMin; e <= eMax; e++) 
		{
			double dX = Math.pow(B, (e - p + 1));
			buf.append("dx(" + e + ")\t = " + dX + "\n");
		}
		buf.append(du.line(35) + "\n");
		
		//MEM-SIZE
		int neg = 1;
		buf.append("+/- \t = " + neg + "\t Bit \n");
		buf.append("Mant \t = " + p + "\t Bit \n");
		int eCnt = Math.abs(eMin) + Math.abs(eMax) + 1;
		Double eSp = Math.log(eCnt) / Math.log(B);
		int eI = (eSp.doubleValue() / eSp.intValue() > 1) 
				? eSp.intValue() + 1
				: eSp.intValue();
		buf.append("Exp \t = " + eI + "\t Bit \n");
		buf.append("\t" + du.line(15) + " \n");
		buf.append("Total \t = " + (int)(neg+p+eSp) + "\t Bit \n" );
		
		
		return new ReturnType(true, buf.toString());
	}

	@Override
	public String getHelpText() 
	{
		return "Calculates some properties of an 'gleitkomma'-system";
	}

}
