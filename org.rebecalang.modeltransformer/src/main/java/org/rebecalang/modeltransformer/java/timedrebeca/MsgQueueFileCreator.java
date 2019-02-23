package org.rebecalang.modeltransformer.java.timedrebeca;

public class MsgQueueFileCreator {

	public String getMsgQueueFileContent() {
		// TODO Auto-generated method stub
		String retValue = "";
		retValue += "import java.util.LinkedList;\r\n" + 
				"import java.util.Queue;\r\n" + 
				"\r\n" + 
				"public class MessageQueue {\r\n" + 
				"	private static Queue<Message> messageQueue = new LinkedList<Message>();\r\n" + 
				"\r\n" + 
				"	public static Queue<Message> getMessageQueue() {\r\n" + 
				"		return messageQueue;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public static void setMessageQueue(Queue<Message> messageQueue) {\r\n" + 
				"		MessageQueue.messageQueue = messageQueue;\r\n" + 
				"	}	\r\n" + 
				"}\r\n" + 
				"";
		return retValue;
	}

}
