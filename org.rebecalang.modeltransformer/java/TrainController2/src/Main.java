public class Main {
	public static void main(String[] args) throws CloneNotSupportedException {
		float t = 0;
		State s_1 = new State();
		Message a = new Message();
		Train train1 = new Train("train1");
		Train train2 = new Train("train2");
		BridgeController theController = new BridgeController("theController");
		while (!MessageQueue.getMessageQueue().isEmpty()) {
			a = MessageQueue.getMessageQueue().poll();
			if (a.getReceiver().equals("theController") && a.getMsgName().equals("Arrive")) {
				theController.Arrive(t, s_1);
			}
			if (a.getReceiver().equals("theController") && a.getMsgName().equals("Leave")) {
				theController.Leave(t, s_1);
			}
			if (a.getReceiver().equals("train1") && a.getMsgName().equals("YouMayPass")) {
				train1.YouMayPass(t, s_1);
			}
			if (a.getReceiver().equals("train2") && a.getMsgName().equals("YouMayPass")) {
				train2.YouMayPass(t, s_1);
			}
			if (a.getReceiver().equals("train1") && a.getMsgName().equals("Passed")) {
				train1.Passed(t, s_1);
			}
			if (a.getReceiver().equals("train2") && a.getMsgName().equals("Passed")) {
				train2.Passed(t, s_1);
			}
			if (a.getReceiver().equals("train1") && a.getMsgName().equals("ReachBridge")) {
				train1.ReachBridge(t, s_1);
			}
			if (a.getReceiver().equals("train2") && a.getMsgName().equals("ReachBridge")) {
				train2.ReachBridge(t, s_1);
			}
		}	
	}
}