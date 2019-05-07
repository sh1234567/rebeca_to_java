package org.rebecalang.modeltransformer.java.timedrebeca;

public class TimePointFileCreator {
	private String modelName;

	public TimePointFileCreator(String modelName) {
		// TODO Auto-generated constructor stub
		this.modelName = modelName;
	}

	public String getTimePointFileContent() {
		// TODO Auto-generated method stub
		String retValue = "";
		retValue += "package " + modelName + ";\r\n";
		retValue += "public class TimePoint {\r\n" + 
				"	float time = 0;\r\n" + 
				"	String type = new String();\r\n" + 
				"	public float getTime() {\r\n" + 
				"		return time;\r\n" + 
				"	}\r\n" + 
				"	public void setTime(float time) {\r\n" + 
				"		this.time = time;\r\n" + 
				"	}\r\n" + 
				"	public String getType() {\r\n" + 
				"		return type;\r\n" + 
				"	}\r\n" + 
				"	public void setType(String type) {\r\n" + 
				"		this.type = type;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"}";
		return retValue;
	}
	

}
