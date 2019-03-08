import java.util.LinkedList;
import java.util.PriorityQueue;

public class State implements Cloneable {
	private PriorityQueue<Message> messageQueue;
	private LinkedList<Actors> actorsList = new LinkedList<Actors>();
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}
}
