package TestCase5;

public class Message implements Comparable<Message>, Cloneable {
	private String msgName;
	private String sender;
	private String receiver;
	private float after_1;
	private float after_2;
	private float deadline;

	public float getAfter_1() {
		return after_1;
	}

	public void setAfter_1(float after_1) {
		this.after_1 = after_1;
	}

	public float getAfter_2() {
		return after_2;
	}

	public void setAfter_2(float after_2) {
		this.after_2 = after_2;
	}

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
		if (!(this.after_1 == a.after_1))
			return false;
		if (!(this.after_2 == a.after_2))
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
		if (this.after_1 > m.getAfter_1())
			return +1;
		if (this.after_1 < m.getAfter_1())
			return -1;
		return 0;
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
