public class Train extends Actors {
	private String name;
	private boolean onTheBridge;

	public Train(String n) {
		float t = 0;
		this.name = n;
		onTheBridge = false;
		Message msg5 = new Message();
		msg5.setMsgName("Passed");
		msg5.setSender(this.name);
		msg5.setReceiver(this.name);
		msg5.setAfter(t);
		msg5.setDeadline(t + 100000);
		MessageQueue.getMessageQueue().add(msg5);
	}

	public void YouMayPass(float t) {
		onTheBridge = true;
		Message msg6 = new Message();
		msg6.setMsgName("Passed");
		msg6.setSender(this.name);
		msg6.setReceiver(this.name);
		msg6.setAfter(t);
		msg6.setDeadline(t + 100000);
		MessageQueue.getMessageQueue().add(msg6);

	}

	public void Passed(float t) {
		onTheBridge = false;
		Message msg7 = new Message();
		msg7.setMsgName("Leave");
		msg7.setSender(this.name);
		msg7.setReceiver("theController");
		msg7.setAfter(t);
		msg7.setDeadline(t + 100000);
		MessageQueue.getMessageQueue().add(msg7);
		Message msg8 = new Message();
		msg8.setMsgName("ReachBridge");
		msg8.setSender(this.name);
		msg8.setReceiver(this.name);
		msg8.setAfter(t);
		msg8.setDeadline(t + 100000);
		MessageQueue.getMessageQueue().add(msg8);

	}

	public void ReachBridge(float t) {
		Message msg9 = new Message();
		msg9.setMsgName("Arrive");
		msg9.setSender(this.name);
		msg9.setReceiver("theController");
		msg9.setAfter(t);
		msg9.setDeadline(t + 100000);
		MessageQueue.getMessageQueue().add(msg9);

	}

	public boolean equals(Train a) {
		if (!(this.name == a.name))
			return false;
		if (!(this.onTheBridge == a.onTheBridge))
			return false;
		return true;
	}
}
