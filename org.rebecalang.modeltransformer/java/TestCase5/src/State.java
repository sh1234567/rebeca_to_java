package TestCase5;

public class State implements Cloneable {
	private MessageQueue<Message> messageQueue = new MessageQueue<Message>();
	private Actors[] actors;
	private float state_time_1;
	private float state_time_2;

	public float getState_time_1() {
		return state_time_1;
	}

	public void setState_time_1(float state_time_1) {
		this.state_time_1 = state_time_1;
	}

	public float getState_time_2() {
		return state_time_2;
	}

	public void setState_time_2(float state_time_2) {
		this.state_time_2 = state_time_2;
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public State() {

	}

	public boolean equalMessageQueue(MessageQueue m2, String mode, float a_1, float b_1) {
		Message[] messages_1 = new Message[50];
		Message[] messages_2 = new Message[50];
		Object[] array = messageQueue.toArray();
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null)
				messages_1[i] = (Message) array[i];
		}
		Object[] array_2 = m2.toArray();
		for (int i = 0; i < array_2.length; i++) {
			if (array_2[i] != null)
				messages_2[i] = (Message) array_2[i];
		}
		int n = 0;
		int m = 0;
		for (int i = 0; i < messages_1.length; i++)
			if (messages_1[i] != null) {
				n++;
			}
		for (int i = 0; i < messages_2.length; i++)
			if (messages_2[i] != null) {
				m++;
			}
		if (n != m) {
			return false;
		}
		if (mode.equals("d")) {
			float d = messages_1[0].getAfter_1() - messages_2[0].getAfter_1();
			int a[] = new int[n];
			int b[] = new int[n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (messages_1[i].equals_2(messages_2[j])
							&& (messages_1[i].getAfter_1() - messages_2[j].getAfter_1() == d) && a[i] != 1
							&& b[j] != 1) {
						a[i] = 1;
						b[j] = 1;
					}
				}
			}
			for (int i = 0; i < n; i++) {
				if (a[i] == 0) {
					return false;
				}
			}
			return true;
		} else {
			int a[] = new int[n];
			int b[] = new int[n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					float a_2 = (messages_2[j].getAfter_2() - messages_2[j].getAfter_1())
							/ (messages_1[j].getAfter_2() - messages_1[j].getAfter_1());
					float b_2 = messages_2[j].getAfter_2() - (a_2 * messages_1[j].getAfter_2());
					if (messages_1[i].equals_2(messages_2[j]) && (a_1 == a_2) && (b_1 == b_2) && a[i] != 1
							&& b[j] != 1) {
						a[i] = 1;
						b[j] = 1;
					}
				}
			}
			for (int i = 0; i < n; i++) {
				if (a[i] == 0) {
					return false;
				}
			}
			return true;
		}

	}

	public boolean equalActorsArray(Actors[] a2, String mode) {

		for (int i = 0; i < actors.length; i++) {
			if (actors[i] != null && !actors[i].equals(a2[i])) {
				return false;
			}
		}
		return true;
	}

	public boolean equals(State a, String mode) {
		float a_1 = (a.getState_time_2() - a.getState_time_1()) / (this.getState_time_2() - this.getState_time_1());
		float b_1 = a.getState_time_2() - (a_1 * this.getState_time_2());
		if (!(this.equalMessageQueue(a.getMessageQueue(), mode, a_1, b_1)))
			return false;
		if (!(this.equalActorsArray(a.getActors(), mode)))
			return false;
		return true;
	}

	public MessageQueue<Message> getMessageQueue() {
		return messageQueue;
	}

	public void setMessageQueue(MessageQueue<Message> messageQueue) {
		this.messageQueue = messageQueue;
	}

	public Actors[] getActors() {
		return actors;
	}

	public void setActors(Actors[] actors) {
		this.actors = actors;
	}
}
