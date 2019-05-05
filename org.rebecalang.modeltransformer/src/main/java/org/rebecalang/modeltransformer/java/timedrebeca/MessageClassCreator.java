package org.rebecalang.modeltransformer.java.timedrebeca;

public class MessageClassCreator {
	private String modelName;

	public MessageClassCreator(String modelName) {
		// TODO Auto-generated constructor stub
		this.modelName = modelName;
	}

	public String getMessageClassContent() {
		// TODO Auto-generated method stub
		String retValue = "";
		retValue +=  "package " + modelName + ";\r\n" +  
				"\r\n" + 
				"public class Message implements Comparable<Message>, Cloneable {\r\n" + 
				"	private String msgName;\r\n" + 
				"	private String sender;\r\n" + 
				"	private String receiver;\r\n" + 
				"	private float after_1;\r\n" + 
				"	private float after_2;\r\n" + 
				"	private float deadline;\r\n" + 
				"\r\n" + 
				"	public float getAfter_1() {\r\n" + 
				"		return after_1;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public void setAfter_1(float after_1) {\r\n" + 
				"		this.after_1 = after_1;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public float getAfter_2() {\r\n" + 
				"		return after_2;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public void setAfter_2(float after_2) {\r\n" + 
				"		this.after_2 = after_2;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public String getMsgName() {\r\n" + 
				"		return msgName;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public void setMsgName(String msgName) {\r\n" + 
				"		this.msgName = msgName;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public String getSender() {\r\n" + 
				"		return sender;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public void setSender(String sender) {\r\n" + 
				"		this.sender = sender;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public String getReceiver() {\r\n" + 
				"		return receiver;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public void setReceiver(String receiver) {\r\n" + 
				"		this.receiver = receiver;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public float getDeadline() {\r\n" + 
				"		return deadline;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public void setDeadline(float deadline) {\r\n" + 
				"		this.deadline = deadline;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public boolean equals(Message a) {\r\n" + 
				"		if (!(this.msgName == a.msgName))\r\n" + 
				"			return false;\r\n" + 
				"		if (!(this.sender == a.sender))\r\n" + 
				"			return false;\r\n" + 
				"		if (!(this.receiver == a.receiver))\r\n" + 
				"			return false;\r\n" + 
				"		if (!(this.after_1 == a.after_1))\r\n" + 
				"			return false;\r\n" + 
				"		if (!(this.after_2 == a.after_2))\r\n" + 
				"			return false;\r\n" + 
				"		if (!(this.deadline == a.deadline))\r\n" + 
				"			return false;\r\n" + 
				"		return true;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public boolean equals_2(Message a) {\r\n" + 
				"		if (!(this.msgName == a.msgName))\r\n" + 
				"			return false;\r\n" + 
				"		if (!(this.sender == a.sender))\r\n" + 
				"			return false;\r\n" + 
				"		if (!(this.receiver == a.receiver))\r\n" + 
				"			return false;\r\n" + 
				"		return true;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public int compareTo(Message m) {\r\n" + 
				"		// TODO Auto-generated method stub\r\n" + 
				"		if (m == null)\r\n" + 
				"			return -1;\r\n" + 
				"		if (this.after_1 > m.getAfter_1())\r\n" + 
				"			return +1;\r\n" + 
				"		if (this.after_1 < m.getAfter_1())\r\n" + 
				"			return -1;\r\n" + 
				"		return 0;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public Object clone() throws CloneNotSupportedException {\r\n" + 
				"		return super.clone();\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"}\r\n" + 
				"";
		return retValue;
	}

}
