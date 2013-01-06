package de.fh_zwickau.mgdi.calc.algo;

import java.util.ArrayList;
import java.util.List;

import de.fh_zwickau.mgdi.calc.algo.param.Parameter;
import de.fh_zwickau.mgdi.calc.util.BinaryUtil;
import de.fh_zwickau.mgdi.calc.util.DrawUtil;

public abstract class Algorithm implements IAlgorithm 
{

	/** draw util instance */
	protected static DrawUtil du = DrawUtil.instance();
	
	/** binary util instance */
	protected static BinaryUtil bu = BinaryUtil.instance();
	
	/** param list */
	private List<Parameter<?>> params = new ArrayList<Parameter<?>>();
	
	public String params()
	{
		StringBuffer paramBuf = new StringBuffer();
		paramBuf.append("Params:" );
		for (int i = 0; i < params.size(); i++) 
		{
			Parameter<?> p = params.get(i);
			paramBuf.append("\n" + i + ": " + p.name() +
					" (" + p.type().getSimpleName() + ")");
		}
		return paramBuf.toString();
	}
	
	
	public void registerParam(final Parameter<?> param)
	{
		params.add(param);
	}
	
	public ReturnType checkParams(String[] args)
	{
		//check param size
		if(	args.length != params.size() || args.length == 0  )
		{
			return new ReturnType(false, "Invalid param count");
		}
		
		//check each param
		for (int i = 0; i < params.size(); i++) 
		{
			//GET PARAM
			Parameter<?> p = params.get(i);
			
			//VALIDATE PARAM
			try
			{
				String cn = p.type().getSimpleName();
				System.out.println("DEBUG: " + cn);
				if(cn.equals("Integer"))
				{
					Integer ii = new Integer(args[i]);
					//TODO SET PARAM
					ii.doubleValue();
				}
				else if(cn.equals("String"))
				{
					//NOOP
				}
				else if(cn.equals("Boolean"))
				{
					Boolean b = new Boolean(args[i]);
					//TODO SET PARAM
					b.toString();
				}
				else
				{
					return new ReturnType(false, "Unknown param (" + i + ") --> " + cn);
				}
			}
			catch(Exception e)
			{
				return new ReturnType(false, "Invalid param at " + i + "!");
			}
		}
		
		
		return new ReturnType(true, "OK");
		
	}
	
	
}
