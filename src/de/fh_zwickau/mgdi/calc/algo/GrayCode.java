package de.fh_zwickau.mgdi.calc.algo;

import de.fh_zwickau.mgdi.calc.algo.param.StringParameter;
import de.fh_zwickau.mgdi.calc.util.BinaryUtil.ShiftDirection;

public class GrayCode extends Algorithm
{
	
	public GrayCode() 
	{
		//REGISTER PARAM
		registerParam(new StringParameter("ascii chars", "null"));
	}

	public ReturnType calc(String[] args) 
	{
		
		if(args.length == 0 || args[0].isEmpty())
		{
			return new ReturnType(false, "No input param");
		}
		
		//OUTPUT BUFFER
		StringBuffer sb = new StringBuffer();
		
		//PRINT HEADER
		sb.append("ASCII \t\t  BIN \t\t RSHIFT \t  XOR\n");
		sb.append(du.line(65) + "\n");
		
		//GET WHOLE STRING
		String s = "";
		for(int i = 0; i < args.length; ++i)
		{
			s += args[i] + ((i == args.length-1) ? "" : " " ) ;
		}
		//STRING TO ASCII ARRAY
		int[] ascii = bu.stringToAscii(s);
		
		//DO CALC
		for (int i = 0; i < ascii.length; i++) {
			
			//GET ASCII
			sb.append("" + s.charAt(i) + " = "  
					+ ((ascii[i] < 100) ? "0" : "") 
					+ ascii[i] + "\t");
			
			//GET BINARAY
			String bin = bu.toBinary(ascii[i], 8);
			sb.append("\t" + bin);
			
			//RIGHT SHIFT
			String shift = bu.shift(bin, ShiftDirection.SHIFT_RIGHT, 1);
			sb.append("\t" + shift);
			
			//XOR
			String xor = bu.XOR(bin, shift);
			sb.append("\t" + xor);
			
			//NEWLINE
			sb.append("\n");
		}
		
		//OUT
		return new ReturnType(true, sb.toString());
	}



	@Override
	public String getHelpText() {
		return "Codes an ascii string into graycode (8Bit).";
	}
	
	
}
