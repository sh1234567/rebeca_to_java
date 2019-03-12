import java.util.LinkedList;

public class State implements Cloneable {
	private Message[] messages;
	private LinkedList<Actors> actorsList = new LinkedList<Actors>();

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
public boolean equalMessagesArray(Message[] m1, Message[] m2) {
		int n = 0;
		int m = 0;
		for (int i = 0; i < m1.length; i++)
			if (m1[i] != null)
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
				if (m1[i].equals(m2[j]) && b[j]!=1) {
					a[i] = 1;
					b[j]=1;
				}
			}
		}
		for (int i = 0; i < n; i++) {
			if (a[i] == 0)
				return false;
		}
		return true;
	}	}
}
