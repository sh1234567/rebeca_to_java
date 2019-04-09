import java.util.*;

public class State implements Cloneable {
	private MessageQueue<Message> messageQueue = new MessageQueue<Message>();
	private Message[] messages;
	private Actors[] actors;

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public State() {
		messages = (Message[]) messageQueue.toArray();
	}

	public boolean equalMessagesArray(Message[] m2) {
		int n = 0;
		int m = 0;
		for (int i = 0; i < messages.length; i++)
			if (messages[i] != null)
				n++;
		for (int i = 0; i < m2.length; i++)
			if (m2[i] != null)
				m++;
		if (n != m)
			return false;
		int a[] = new int[n];
		int b[] = new int[n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; i < n; i++) {
				if (messages[i].equals(m2[j]) && b[j] != 1) {
					a[i] = 1;
					b[j] = 1;
				}
			}
		}
		for (int i = 0; i < n; i++) {
			if (a[i] == 0)
				return false;
		}
		return true;
	}

	public boolean equalActorsArray(Actors[] a2) {
		int n = 0;
		int m = 0;
		for (int i = 0; i < actors.length; i++)
			if (actors[i] != null)
				n++;
		for (int i = 0; i < a2.length; i++)
			if (a2[i] != null)
				m++;
		if (n != m)
			return false;
		int a[] = new int[n];
		int b[] = new int[n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; i < n; i++) {
				if (actors[i].equals(a2[j]) && b[j] != 1) {
					a[i] = 1;
					b[j] = 1;
				}
			}
		}
		for (int i = 0; i < n; i++) {
			if (a[i] == 0)
				return false;
		}
		return true;
	}

	public boolean equals(State a) {
		if (!(this.equalMessagesArray(a.getMessages())))
			return false;
		if (!(this.equalActorsArray(a.getActors())))
			return false;
		return true;
	}

	public MessageQueue<Message> getMessageQueue() {
		return messageQueue;
	}

	public void setMessageQueue(MessageQueue<Message> messageQueue) {
		this.messageQueue = messageQueue;
	}

	public Message[] getMessages() {
		return messages;
	}

	public void setMessages(Message[] messages) {
		this.messages = messages;
	}

	public Actors[] getActors() {
		return actors;
	}

	public void setActors(Actors[] actors) {
		this.actors = actors;
	}
}
