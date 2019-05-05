package TestCase5;
public class TimeClass {
	private static long startTime = System.currentTimeMillis();
	public static long getTime() {
		return (System.currentTimeMillis()-startTime);
	}
}