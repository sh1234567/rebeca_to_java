import java.util.LinkedList;

public class State implements Cloneable {
	private Message[] messages;
	private LinkedList<Actors> actorsList = new LinkedList<Actors>();

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
