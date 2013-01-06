package de.fh_zwickau.mgdi.calc.algo.param;

import java.io.Serializable;


public abstract class Parameter<T extends Serializable>
{
	
	public Parameter(String name, T value) 
	{
		super();
		this.name = name;
		this.value = value;
	}

	/** parameter name */
	private String name;
	
	/** parameter value */
	private T value;
	
	/**
	 * get parameter name
	 * 
	 * @return name
	 */
	public String name()
	{
		return name;
	}
	
	/**
	 * get parameter value
	 * 
	 * @return value
	 */
	public T value()
	{
		return value;
	}
	
	public void setValue(T value)
	{
		this.value = value;
	}
	
	public abstract Class<?> type();
	
}
