package org.rebecalang.modeltransformer.java.timedrebeca;

import java.util.LinkedList;

public class StateFileCreator {

	public String getStateFileContent() {
		// TODO Auto-generated method stub
		String retValue = "";
		retValue += "import java.util.*;\r\n" + 
				"\r\n" + 
				"public class State implements Cloneable {\r\n" + 
				"	private MessageQueue<Message> messageQueue = new MessageQueue<Message>();\r\n" + 
				"	private Message[] messages;\r\n" + 
				"	private Actors[] actors;\r\n" + 
				"\r\n" + 
				"	public Object clone() throws CloneNotSupportedException {\r\n" + 
				"		return super.clone();\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public State() {\r\n" + 
				"		messages = (Message[]) messageQueue.toArray();\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public boolean equalMessagesArray(Message[] m2) {\r\n" + 
				"		int n = 0;\r\n" + 
				"		int m = 0;\r\n" + 
				"		for (int i = 0; i < messages.length; i++)\r\n" + 
				"			if (messages[i] != null)\r\n" + 
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
				"				if (messages[i].equals(m2[j]) && b[j] != 1) {\r\n" + 
				"					a[i] = 1;\r\n" + 
				"					b[j] = 1;\r\n" + 
				"				}\r\n" + 
				"			}\r\n" + 
				"		}\r\n" + 
				"		for (int i = 0; i < n; i++) {\r\n" + 
				"			if (a[i] == 0)\r\n" + 
				"				return false;\r\n" + 
				"		}\r\n" + 
				"		return true;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public boolean equalActorsArray(Actors[] a2) {\r\n" + 
				"		int n = 0;\r\n" + 
				"		int m = 0;\r\n" + 
				"		for (int i = 0; i < actors.length; i++)\r\n" + 
				"			if (actors[i] != null)\r\n" + 
				"				n++;\r\n" + 
				"		for (int i = 0; i < a2.length; i++)\r\n" + 
				"			if (a2[i] != null)\r\n" + 
				"				m++;\r\n" + 
				"		if (n != m)\r\n" + 
				"			return false;\r\n" + 
				"		int a[] = new int[n];\r\n" + 
				"		int b[] = new int[n];\r\n" + 
				"		for (int i = 0; i < n; i++) {\r\n" + 
				"			for (int j = 0; i < n; i++) {\r\n" + 
				"				if (actors[i].equals(a2[j]) && b[j] != 1) {\r\n" + 
				"					a[i] = 1;\r\n" + 
				"					b[j] = 1;\r\n" + 
				"				}\r\n" + 
				"			}\r\n" + 
				"		}\r\n" + 
				"		for (int i = 0; i < n; i++) {\r\n" + 
				"			if (a[i] == 0)\r\n" + 
				"				return false;\r\n" + 
				"		}\r\n" + 
				"		return true;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public boolean equals(State a) {\r\n" + 
				"		if (!(this.equalMessagesArray(a.getMessages())))\r\n" + 
				"			return false;\r\n" + 
				"		if (!(this.equalActorsArray(a.getActors())))\r\n" + 
				"			return false;\r\n" + 
				"		return true;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public MessageQueue<Message> getMessageQueue() {\r\n" + 
				"		return messageQueue;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public void setMessageQueue(MessageQueue<Message> messageQueue) {\r\n" + 
				"		this.messageQueue = messageQueue;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public Message[] getMessages() {\r\n" + 
				"		return messages;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public void setMessages(Message[] messages) {\r\n" + 
				"		this.messages = messages;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public Actors[] getActors() {\r\n" + 
				"		return actors;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public void setActors(Actors[] actors) {\r\n" + 
				"		this.actors = actors;\r\n" + 
				"	}\r\n" + 
				"}\r\n" + 
				"";
		return retValue;
	}

}
