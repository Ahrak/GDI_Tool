package de.fh_zwickau.mgdi.calc.algo;

import de.fh_zwickau.mgdi.calc.algo.param.IntegerParameter;


public class BinaryDiv extends Algorithm
{

	public BinaryDiv() 
	{
		super();
		registerParam(new IntegerParameter("divident", 0));
		registerParam(new IntegerParameter("divisor", 0));
	}
	
	@Override
	public ReturnType calc(String[] args) 
	{
		//GET PARAMS
		int divident = new Integer(args[0]);
		int	divisor = new Integer(args[1]);
		
		if(divident < divisor)
		{
			return new ReturnType(false, "Divident < Divisor!");
		}
		else if(divisor <= 0)
		{
			return new ReturnType(false, "Div 0!");
		}
	
		// OUTPUT BUFFER
		StringBuffer outBuf = new StringBuffer();
		
		// BINARY STRINGS
		String sDivD = bu.toBinary(divident, 0);
		String sDivI = bu.toBinary(divisor, 0);
		
		// INIT VARS
		String erg = "";
		String rest = "";
		String space = "";
		int dSize = sDivI.length();
		
		// INTEGER VALUES
		int x1 = Integer.parseInt(sDivD.substring(0, dSize), 2);
		int x2 = Integer.parseInt(sDivI, 2);
		
		// INIT DIVISION LINE AND CALCULATION OUTPUT BUFFER
		String firstLine =  " " + sDivD + " / " + sDivI + " = "; 
		StringBuffer sb = new StringBuffer();
		sb.append(space + "\n-------" + "\n");
		
		// DO THE CALCULATION
		for(int i = 0; i < sDivD.length() - dSize+1; i++)
		{
			// FLAG IF SUBSTRAKTION IS VALID
			boolean error = false;
			
			// CALCULATE SPACES 
			String bin = bu.toBinary(x1, 0);
			String foo = "";
			for (int j = sDivI.length(); j < bin.length(); j++) 
			{
				foo = "0" + foo;
			}
			
			//OPERATION OUT
			sb.append(space + " " + bu.toBinary(x1, dSize) + "\n");
			sb.append(space + "-" + foo + sDivI + " " + "\n");
			
			//SUB
			int sub = x1 - x2;
			int lineSize = bu.toBinary(x1, -1).length();
			
			//CHECK CASES
			if(sub >= 0) // SUB  IS VALID
			{
				erg += "1";
				x1 = sub;
			}
			else  // NOT VALID
			{
				erg += "0";
				error = true;
			}
			
			//OUT ERG
			sb.append(space + "-------" + "\n");
			sb.append(space + " " + (error ? "ERROR" : bu.toBinary(sub, lineSize)) + "\n");
		
			
			//GET NEXT		
			if(i+dSize < sDivD.length())
			{
				x1 = (x1 << 1) + (sDivD.charAt(i+dSize) == '0' ? 0 : 1);
				sb.append(space + "-------" + "\n");
			}
			else
			{
				if(sub >= 0)
				{
					rest = Integer.toBinaryString(sub);
				}
				else 
				{
					rest = "0";
				}
				sb.append(space + "-------" + "\n");
				sb.append(space + " " + bu.fill(rest, sDivI.length()) + "\n");
				sb.append(space + "=====" + "\n");
			}
			space += " ";
		}
		
		firstLine += erg + " (" + (divident / divisor) + ")" + 
			" REST: " + rest ;
		outBuf.append(firstLine);
		outBuf.append(sb.toString());
		
		return new ReturnType(true, outBuf.toString());
		
		
	}


	@Override
	public String getHelpText() {
		return "Show binary division algorithm step by step.";
	}
	
}
