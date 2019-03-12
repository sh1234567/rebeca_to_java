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
				"public boolean equalMessagesArray(Message[] m1, Message[] m2) {\r\n" + 
				"		int n = 0;\r\n" + 
				"		int m = 0;\r\n" + 
				"		for (int i = 0; i < m1.length; i++)\r\n" + 
				"			if (m1[i] != null)\r\n" + 
				"				n++;\r\n" + 
				"		for (int i = 0; i < m2.length; i++)\r\n" + 
				"			if (m2[i] != null)\r\n" + 
				"				m++;\r\n" + 
				"		if (n != m)\r\n" + 
				"			return false;\r\n" + 
				"		int a[] = new int[n];\r\n" + 
				"		int b[] = new int[n];\r\n" + 
				"		for (int i = 0; i < n; i++) {\r\n" + 
				"			for (int j = 0; i < n; i++) {\r\n" + 
				"				if (m1[i].equals(m2[j]) && b[j]!=1) {\r\n" + 
				"					a[i] = 1;\r\n" + 
				"					b[j]=1;\r\n" + 
				"				}\r\n" + 
				"			}\r\n" + 
				"		}\r\n" + 
				"		for (int i = 0; i < n; i++) {\r\n" + 
				"			if (a[i] == 0)\r\n" + 
				"				return false;\r\n" + 
				"		}\r\n" + 
				"		return true;\r\n" + 
				"	}" +
				"	}\r\n" + 
				"}\r\n" + 
				"";
		return retValue;
	}

}
