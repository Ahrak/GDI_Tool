package de.fh_zwickau.mgdi.calc.util;

public class DrawUtil {

	private static DrawUtil instance;
	
	private DrawUtil()
	{
		//
	}
	
	public static DrawUtil instance()
	{
		if(instance == null)
		{
			instance = new DrawUtil();
		}
		return instance;
	}
	
	public String line(int w)
	{
		String s = "";
		for (int i = 0; i < w; i++) 
		{
			s += "-";
		}
		return s;
	}
	
	public String dline(int w)
	{
		String s = "";
		for (int i = 0; i < w; i++) 
		{
			s += "=";
		}
		return s;
	}
	
}
