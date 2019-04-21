package TestCase1;
public abstract class Actors implements Cloneable {
	public abstract boolean equals (Actors a);
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
