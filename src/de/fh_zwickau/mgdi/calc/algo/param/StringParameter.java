package de.fh_zwickau.mgdi.calc.algo.param;

public class StringParameter extends Parameter<String> 
{

	public StringParameter(String name, String value) {
		super(name, value);
	}

	@Override
	public Class<?> type() 
	{
		return String.class;
	}
	
}
