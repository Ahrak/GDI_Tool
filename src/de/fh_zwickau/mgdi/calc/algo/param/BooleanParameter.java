package de.fh_zwickau.mgdi.calc.algo.param;

public class BooleanParameter extends Parameter<Boolean> {

	public BooleanParameter(String name, Boolean value) {
		super(name, value);
	}

	@Override
	public Class<?> type() {
		return Boolean.class;
	}

}
