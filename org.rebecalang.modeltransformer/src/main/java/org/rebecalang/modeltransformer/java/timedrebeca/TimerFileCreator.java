package org.rebecalang.modeltransformer.java.timedrebeca;

public class TimerFileCreator {

	public String getTimerFileContent() {
		// TODO Auto-generated method stub
		String retValue = "";
		retValue += "public class TimeClass {\r\n" + 
				"	private static long startTime = System.currentTimeMillis();\r\n" + 
				"	public static long getTime() {\r\n" + 
				"		return (System.currentTimeMillis()-startTime);\r\n" + 
				"	}\r\n" + 
				"}";
		return retValue;
	}
	

}
