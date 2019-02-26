package org.rebecalang.modeltransformer.java.timedrebeca;

import java.util.PriorityQueue;

public class MsgQueueFileCreator {

	public String getMsgQueueFileContent() {
		// TODO Auto-generated method stub
		String retValue = "";
		retValue += "import java.util.*;\r\n" + 
				"\r\n" + 
				"public class MessageQueue {\r\n" + 
				"	private static PriorityQueue<Message> messageQueue = new PriorityQueue<Message>(10, new MessageComparator());\r\n" + 
				"\r\n" + 
				"	public static PriorityQueue<Message> getMessageQueue() {\r\n" + 
				"		return messageQueue;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public static void setMessageQueue(PriorityQueue<Message> messageQueue) {\r\n" + 
				"		MessageQueue.messageQueue = messageQueue;\r\n" + 
				"	}\r\n" + 
				"}\r\n" + 
				"";
		return retValue;
	}

}
