package org.rebecalang.modeltransformer.java.timedrebeca;

public class StateFileCreator {
	private String modelName;

	public StateFileCreator(String modelName) {
		// TODO Auto-generated constructor stub
		this.modelName = modelName;
	}

	public String getStateFileContent() {
		// TODO Auto-generated method stub
		String retValue = "";
		retValue += "package " + modelName + ";\r\n\r\n";
		retValue += "public class State implements Cloneable {\r\n" + 
				"	private MessageQueue<Message> messageQueue = new MessageQueue<Message>();\r\n" + 
				"	private Actors[] actors;\r\n" + 
				"	private float state_time_1;\r\n" + 
				"	private float state_time_2;\r\n" + 
				"\r\n" + 
				"	public float getState_time_1() {\r\n" + 
				"		return state_time_1;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public void setState_time_1(float state_time_1) {\r\n" + 
				"		this.state_time_1 = state_time_1;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public float getState_time_2() {\r\n" + 
				"		return state_time_2;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public void setState_time_2(float state_time_2) {\r\n" + 
				"		this.state_time_2 = state_time_2;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public Object clone() throws CloneNotSupportedException {\r\n" + 
				"		return super.clone();\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public State() {\r\n" + 
				"\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public boolean equalMessageQueue(MessageQueue m2, String mode, float a_1, float b_1) {\r\n" + 
				"		Message[] messages_1 = new Message[50];\r\n" + 
				"		Message[] messages_2 = new Message[50];\r\n" + 
				"		Object[] array = messageQueue.toArray();\r\n" + 
				"		for (int i = 0; i < array.length; i++) {\r\n" + 
				"			if (array[i] != null)\r\n" + 
				"				messages_1[i] = (Message) array[i];\r\n" + 
				"		}\r\n" + 
				"		Object[] array_2 = m2.toArray();\r\n" + 
				"		for (int i = 0; i < array_2.length; i++) {\r\n" + 
				"			if (array_2[i] != null)\r\n" + 
				"				messages_2[i] = (Message) array_2[i];\r\n" + 
				"		}\r\n" + 
				"		int n = 0;\r\n" + 
				"		int m = 0;\r\n" + 
				"		for (int i = 0; i < messages_1.length; i++)\r\n" + 
				"			if (messages_1[i] != null) {\r\n" + 
				"				n++;\r\n" + 
				"			}\r\n" + 
				"		for (int i = 0; i < messages_2.length; i++)\r\n" + 
				"			if (messages_2[i] != null) {\r\n" + 
				"				m++;\r\n" + 
				"			}\r\n" + 
				"		if (n != m) {\r\n" + 
				"			return false;\r\n" + 
				"		}\r\n" + 
				"		if (mode.equals(\"d\")) {\r\n" + 
				"			float d = messages_1[0].getAfter_1() - messages_2[0].getAfter_1();\r\n" + 
				"			int a[] = new int[n];\r\n" + 
				"			int b[] = new int[n];\r\n" + 
				"			for (int i = 0; i < n; i++) {\r\n" + 
				"				for (int j = 0; j < n; j++) {\r\n" + 
				"					if (messages_1[i].equals_2(messages_2[j])\r\n" + 
				"							&& (messages_1[i].getAfter_1() - messages_2[j].getAfter_1() == d) && a[i] != 1\r\n" + 
				"							&& b[j] != 1) {\r\n" + 
				"						a[i] = 1;\r\n" + 
				"						b[j] = 1;\r\n" + 
				"					}\r\n" + 
				"				}\r\n" + 
				"			}\r\n" + 
				"			for (int i = 0; i < n; i++) {\r\n" + 
				"				if (a[i] == 0) {\r\n" + 
				"					return false;\r\n" + 
				"				}\r\n" + 
				"			}\r\n" + 
				"			return true;\r\n" + 
				"		} else {\r\n" + 
				"			int a[] = new int[n];\r\n" + 
				"			int b[] = new int[n];\r\n" + 
				"			for (int i = 0; i < n; i++) {\r\n" + 
				"				for (int j = 0; j < n; j++) {\r\n" + 
				"					float a_2 = (messages_2[j].getAfter_2() - messages_2[j].getAfter_1())\r\n" + 
				"							/ (messages_1[i].getAfter_2() - messages_1[i].getAfter_1());\r\n" + 
				"					float b_2 = messages_2[j].getAfter_2() - (a_2 * messages_1[i].getAfter_2());\r\n" + 
				"					if (messages_1[i].equals_2(messages_2[j]) && (a_1 == a_2) && (b_1 == b_2) && a[i] != 1\r\n" + 
				"							&& b[j] != 1) {\r\n" + 
				"						a[i] = 1;\r\n" + 
				"						b[j] = 1;\r\n" + 
				"					}\r\n" + 
				"				}\r\n" + 
				"			}\r\n" + 
				"			for (int i = 0; i < n; i++) {\r\n" + 
				"				if (a[i] == 0) {\r\n" + 
				"					return false;\r\n" + 
				"				}\r\n" + 
				"			}\r\n" + 
				"			return true;\r\n" + 
				"		}\r\n" + 
				"\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public boolean equalActorsArray(Actors[] a2, String mode) {\r\n" + 
				"\r\n" + 
				"		for (int i = 0; i < actors.length; i++) {\r\n" + 
				"			if (actors[i] != null && !actors[i].equals(a2[i])) {\r\n" + 
				"				return false;\r\n" + 
				"			}\r\n" + 
				"		}\r\n" + 
				"		return true;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	public boolean equals(State a, String mode) {\r\n" + 
				"		float a_1 = (a.getState_time_2() - a.getState_time_1()) / (this.getState_time_2() - this.getState_time_1());\r\n" + 
				"		float b_1 = a.getState_time_2() - (a_1 * this.getState_time_2());\r\n" + 
				"		if (!(this.equalMessageQueue(a.getMessageQueue(), mode, a_1, b_1)))\r\n" + 
				"			return false;\r\n" + 
				"		if (!(this.equalActorsArray(a.getActors(), mode)))\r\n" + 
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
