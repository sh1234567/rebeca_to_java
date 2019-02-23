import java.util.LinkedList;
import java.util.Queue;

public class MessageQueue {
	private static Queue<Message> messageQueue = new LinkedList<Message>();

	public static Queue<Message> getMessageQueue() {
		return messageQueue;
	}

	public static void setMessageQueue(Queue<Message> messageQueue) {
		MessageQueue.messageQueue = messageQueue;
	}	
}
