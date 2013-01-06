package de.fh_zwickau.mgdi.calc.algo.param;

public class IntegerParameter extends Parameter<Integer> 
{

	public IntegerParameter(String name, Integer value) {
		super(name, value);
	}

	@Override
	public Class<?> type() {
		return Integer.class;
	}

}
