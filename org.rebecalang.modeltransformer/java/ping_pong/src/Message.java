package org.rebecalang.modeltransformer.java.timedrebeca;

public class Message {
	private String msgName;
	private String receiver;
	private float after;
	private float deadline;
	public String getMsgName() {
		return msgName;
	}
	public void setMsgName(String msgName) {
		this.msgName = msgName;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public float getAfter() {
		return after;
	}
	public void setAfter(float after) {
		this.after = after;
	}
	public float getDeadline() {
		return deadline;
	}
	public void setDeadline(float deadline) {
		this.deadline = deadline;
	}
	

}
