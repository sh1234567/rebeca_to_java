import java.util.*;

public class MessageQueue {
	private static PriorityQueue<Message> messageQueue = new PriorityQueue<Message>(10, new MessageComparator());

	public static PriorityQueue<Message> getMessageQueue() {
		return messageQueue;
	}

	public static void setMessageQueue(PriorityQueue<Message> messageQueue) {
		MessageQueue.messageQueue = messageQueue;
	}
}
