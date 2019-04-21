package org.rebecalang.modeltransformer.java.timedrebeca;

public class TimerFileCreator {
	private String modelName;

	public TimerFileCreator(String modelName) {
		// TODO Auto-generated constructor stub
		this.modelName = modelName;
	}

	public String getTimerFileContent() {
		// TODO Auto-generated method stub
		String retValue = "";
		retValue += "package " + modelName + ";\r\n" + 
				"public class TimeClass {\r\n" + 
				"	private static long startTime = System.currentTimeMillis();\r\n" + 
				"	public static long getTime() {\r\n" + 
				"		return (System.currentTimeMillis()-startTime);\r\n" + 
				"	}\r\n" + 
				"}";
		return retValue;
	}
	

}
