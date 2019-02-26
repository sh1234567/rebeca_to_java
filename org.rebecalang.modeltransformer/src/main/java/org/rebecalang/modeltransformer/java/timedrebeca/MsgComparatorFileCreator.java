package org.rebecalang.modeltransformer.java.timedrebeca;

import java.util.Comparator;

public class MsgComparatorFileCreator {

	public String getMsgComparatorFileContent() {
		// TODO Auto-generated method stub
		String retValue = "";
		retValue += "import java.util.Comparator;\r\n" + 
				"\r\n" + 
				"public class MessageComparator implements Comparator<Message> {\r\n" + 
				"	public int compare(Message m1, Message m2) {\r\n" + 
				"		if (m1.getAfter() > m2.getAfter())\r\n" + 
				"			return 1;\r\n" + 
				"		else if (m1.getAfter() < m2.getAfter()) \r\n" + 
				"			return -1;\r\n" + 
				"		return 0;\r\n" + 
				"		\r\n" + 
				"	}\r\n" + 
				"}\r\n" + 
				"";
		return retValue;
	}

}
