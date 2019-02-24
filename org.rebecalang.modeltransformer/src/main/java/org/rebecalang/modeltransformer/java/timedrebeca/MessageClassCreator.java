package org.rebecalang.modeltransformer.java.timedrebeca;

public class MessageClassCreator {

	public String getMessageClassContent() {
		// TODO Auto-generated method stub
		String retValue = "";
		retValue +=  "public class Message {\r\n" + 
				"	private String msgName;\r\n" + 
				"	private String sender;\r\n" + 
				"	private String receiver;\r\n" + 
				"	private float after;\r\n" + 
				"	private float deadline;\r\n" + 
				"	public String getMsgName() {\r\n" + 
				"		return msgName;\r\n" + 
				"	}\r\n" + 
				"	public void setMsgName(String msgName) {\r\n" + 
				"		this.msgName = msgName;\r\n" + 
				"	}\r\n" + 
				"	public String getSender() {\r\n" + 
				"		return sender;\r\n" + 
				"	}\r\n" + 
				"	public void setSender(String sender) {\r\n" + 
				"		this.sender = sender;\r\n" + 
				"	}\r\n" + 
				"	public String getReceiver() {\r\n" + 
				"		return receiver;\r\n" + 
				"	}\r\n" + 
				"	public void setReceiver(String receiver) {\r\n" + 
				"		this.receiver = receiver;\r\n" + 
				"	}\r\n" + 
				"	public float getAfter() {\r\n" + 
				"		return after;\r\n" + 
				"	}\r\n" + 
				"	public void setAfter(float after) {\r\n" + 
				"		this.after = after;\r\n" + 
				"	}\r\n" + 
				"	public float getDeadline() {\r\n" + 
				"		return deadline;\r\n" + 
				"	}\r\n" + 
				"	public void setDeadline(float deadline) {\r\n" + 
				"		this.deadline = deadline;\r\n" + 
				"	}\r\n" + 
				"	\r\n" + 
				"}\r\n" + 
				"";
		return retValue;
	}

}
