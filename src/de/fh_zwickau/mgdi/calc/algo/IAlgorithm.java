package de.fh_zwickau.mgdi.calc.algo;

public interface IAlgorithm {

	public class ReturnType
	{
		
		public ReturnType(boolean success, String output) {
			super();
			this.success = success;
			this.output = output;
		}

		public boolean success = true;
		
		public String output = "";
	}
	
	public ReturnType calc(String[] args);
	
	public String getHelpText();
	
}
