package TestCase5;
import java.util.Comparator;

public class MessageComparator implements Comparator<Message> {
	public int compare(Message m1, Message m2) {
		if (m1.getAfter_1() > m2.getAfter_1())
			return 1;
		else if (m1.getAfter_1() < m2.getAfter_1()) 
			return -1;
		return 0;
		
	}
}
