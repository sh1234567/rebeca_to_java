package TestCase3;
public class Message implements Comparable<Message>, Cloneable {
	private String msgName;
	private String sender;
	private String receiver;
	private float after;
	private float deadline;

	public String getMsgName() {
		return msgName;
	}

	public void setMsgName(String msgName) {
		this.msgName = msgName;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
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

	public boolean equals(Message a) {
		if (!(this.msgName == a.msgName))
			return false;
		if (!(this.sender == a.sender))
			return false;
		if (!(this.receiver == a.receiver))
			return false;
		if (!(this.after == a.after))
			return false;
		if (!(this.deadline == a.deadline))
			return false;
		return true;
	}

	public boolean equals_2(Message a) {
		if (!(this.msgName == a.msgName))
			return false;
		if (!(this.sender == a.sender))
			return false;
		if (!(this.receiver == a.receiver))
			return false;
		return true;
	}

	public int compareTo(Message m) {
		// TODO Auto-generated method stub
		if (m == null)
			return -1;
		if (this.after > m.getAfter())
			return +1;
		if (this.after < m.getAfter())
			return -1;
		return 0;
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
