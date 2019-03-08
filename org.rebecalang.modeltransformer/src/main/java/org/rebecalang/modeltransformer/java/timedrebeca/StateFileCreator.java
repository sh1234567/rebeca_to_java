package org.rebecalang.modeltransformer.java.timedrebeca;

public class StateFileCreator {

	public String getStateFileContent() {
		// TODO Auto-generated method stub
		String retValue = "";
		retValue += "import java.util.LinkedList;\r\n" + 
				"import java.util.PriorityQueue;\r\n" + 
				"\r\n" + 
				"public class State implements Cloneable {\r\n" + 
				"	private PriorityQueue<Message> messageQueue;\r\n" + 
				"	private LinkedList<Actors> actorsList = new LinkedList<Actors>();\r\n" + 
				"	public Object clone() throws CloneNotSupportedException{\r\n" + 
				"		return super.clone();\r\n" + 
				"	}\r\n" + 
				"}\r\n" + 
				"";
		return retValue;
	}

}
