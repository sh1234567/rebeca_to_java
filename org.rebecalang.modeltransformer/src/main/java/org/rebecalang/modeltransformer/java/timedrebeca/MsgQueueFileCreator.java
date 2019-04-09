package org.rebecalang.modeltransformer.java.timedrebeca;

import java.util.PriorityQueue;

public class MsgQueueFileCreator {

	public String getMsgQueueFileContent() {
		// TODO Auto-generated method stub
		String retValue = "";
		retValue += "import java.util.*;\r\n" + 
				"\r\n" + 
				"public class MessageQueue<Message> extends PriorityQueue<Message> implements Cloneable {\r\n" + 
				"	public MessageQueue<Message> clone() throws CloneNotSupportedException {\r\n" + 
				"		MessageQueue<Message> pq = new MessageQueue<Message>();\r\n" + 
				"		pq = (MessageQueue<Message>) super.clone();\r\n" + 
				"		return pq;\r\n" + 
				"	}\r\n" + 
				"}\r\n" + 
				"";
		return retValue;
	}

}
