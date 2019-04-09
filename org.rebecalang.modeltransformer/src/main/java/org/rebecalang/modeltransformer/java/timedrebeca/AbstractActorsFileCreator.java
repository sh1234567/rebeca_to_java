package org.rebecalang.modeltransformer.java.timedrebeca;

public class AbstractActorsFileCreator {

	public String getAbstractActorsFileContent() {
		// TODO Auto-generated method stub
		String retValue = "";
		retValue += "public abstract class Actors implements Cloneable {\r\n" + 
				"	public abstract boolean equals (Actors a);\r\n" + 
				"	public Object clone() throws CloneNotSupportedException {\r\n" + 
				"		return super.clone();\r\n" + 
				"	}\r\n" + 
				"}\r\n" + 
				"";
		return retValue;
	}

}
