package TestCase5;
import java.util.*;

public class MessageQueue<Message> extends PriorityQueue<Message> implements Cloneable {
	public MessageQueue<Message> clone() throws CloneNotSupportedException {
		MessageQueue<Message> pq = new MessageQueue<Message>();
		pq = (MessageQueue<Message>) super.clone();
		return pq;
	}
}
