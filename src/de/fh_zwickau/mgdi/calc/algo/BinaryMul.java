package de.fh_zwickau.mgdi.calc.algo;

import de.fh_zwickau.mgdi.calc.algo.param.IntegerParameter;


public class BinaryMul extends Algorithm 
{

	public BinaryMul()
	{
		super();
		registerParam(new IntegerParameter("faktor1", 0));
		registerParam(new IntegerParameter("faktor2", 0));
	}
	
	@Override
	public ReturnType calc(String[] args) {
		
		//GET PARAMS
		int fak1 = 0, fak2 = 0;
		try 
		{
			fak1 = new Integer(args[0]);
			fak2 = new Integer(args[1]);
		}
		catch(Exception e)
		{
			return new ReturnType(false, "Invalid Params");
		}
	
		// OUTPUT BUFFER
		StringBuffer outBuf = new StringBuffer();
		
		// BINARY STRINGS
		String sMulFak1 = bu.toBinary(fak1, 0);
		String sMulFak2 = bu.toBinary(fak2, 0);	
		
		// INIT VARS
		String space = "";
		
		// INTEGER VALUES
		int x1 = Integer.parseInt(sMulFak1, 2);
		int x2 = Integer.parseInt(sMulFak2, 2);
		
		
		// INIT DIVISION LINE AND CALCULATION OUTPUT BUFFER
		String firstLine =  " " + sMulFak1 + " * " + sMulFak2 + " = "; 
		StringBuffer sb = new StringBuffer();
		sb.append(du.line(20) + "\n");
		
		//DO CALC
		for (int i = 0; i < sMulFak2.length(); ++i) 
		{
			sb.append(((i > 0) ? "+" : " ") + space);
			if(sMulFak2.charAt(i) == '1')
			{
				sb.append(sMulFak1);
			}
			else
			{
				sb.append(sMulFak1.replace('1', '0'));
			}
			sb.append("\n");
			space += " ";
		}
		
		int size = (sMulFak1.length() + sMulFak2.length());
		sb.append(du.line(20) + "\n");
		sb.append(bu.toBinary(x1 * x2, size) + "\n");
		sb.append(du.dline(20) + "\n");
		
		outBuf.append(firstLine + bu.toBinary(x1 * x2, -1) + " (" + x1 * x2 + ")" + "\n");
		outBuf.append(sb.toString());
		
		return new ReturnType(true, outBuf.toString());
	}

	@Override
	public String getHelpText() {
		// TODO Auto-generated method stub
		return "Multiplies two decimals with binary multiplikation.";
	}

}
