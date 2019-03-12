package org.rebecalang.modeltransformer.java.timedrebeca;

import java.util.LinkedList;

public class StateFileCreator {

	public String getStateFileContent() {
		// TODO Auto-generated method stub
		String retValue = "";
		retValue += "import java.util.LinkedList;\r\n" + 
				"\r\n" + 
				"public class State implements Cloneable {\r\n" + 
				"	private Message[] messages;\r\n" + 
				"	private LinkedList<Actors> actorsList = new LinkedList<Actors>();\r\n" + 
				"\r\n" + 
				"	public Object clone() throws CloneNotSupportedException {\r\n" + 
				"		return super.clone();\r\n" + 
				"	}\r\n" + 
				"}\r\n" + 
				"";
		return retValue;
	}

}
