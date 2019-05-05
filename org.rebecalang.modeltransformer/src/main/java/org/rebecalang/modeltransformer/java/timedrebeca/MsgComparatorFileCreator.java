package org.rebecalang.modeltransformer.java.timedrebeca;

import java.util.Comparator;

public class MsgComparatorFileCreator {
	private String modelName;

	public MsgComparatorFileCreator(String modelName) {
		// TODO Auto-generated constructor stub
		this.modelName = modelName;
	}

	public String getMsgComparatorFileContent() {
		// TODO Auto-generated method stub
		String retValue = "";
		retValue += "package " + modelName + ";\r\n" + 
				"import java.util.Comparator;\r\n" + 
				"\r\n" + 
				"public class MessageComparator implements Comparator<Message> {\r\n" + 
				"	public int compare(Message m1, Message m2) {\r\n" + 
				"		if (m1.getAfter_1() > m2.getAfter_1())\r\n" + 
				"			return 1;\r\n" + 
				"		else if (m1.getAfter_1() < m2.getAfter_1()) \r\n" + 
				"			return -1;\r\n" + 
				"		return 0;\r\n" + 
				"		\r\n" + 
				"	}\r\n" + 
				"}\r\n" + 
				"";
		return retValue;
	}

}
