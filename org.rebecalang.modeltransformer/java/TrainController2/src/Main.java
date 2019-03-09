public class Main {
	public static void main(String[] args) {
		float t = 0;
		Message a = new Message();
		Train train1 = new Train("train1");
		Train train2 = new Train("train2");
		BridgeController theController = new BridgeController("theController");
		while (!MessageQueue.getMessageQueue().isEmpty()) {
			a = MessageQueue.getMessageQueue().poll();
			if (a.getReceiver().equals("theController") && a.getMsgName().equals("Arrive")) {
				theController.Arrive(t);
			}
			if (a.getReceiver().equals("theController") && a.getMsgName().equals("Leave")) {
				theController.Leave(t);
			}
			if (a.getReceiver().equals("train1") && a.getMsgName().equals("YouMayPass")) {
				train1.YouMayPass(t);
			}
			if (a.getReceiver().equals("train2") && a.getMsgName().equals("YouMayPass")) {
				train2.YouMayPass(t);
			}
			if (a.getReceiver().equals("train1") && a.getMsgName().equals("Passed")) {
				train1.Passed(t);
			}
			if (a.getReceiver().equals("train2") && a.getMsgName().equals("Passed")) {
				train2.Passed(t);
			}
			if (a.getReceiver().equals("train1") && a.getMsgName().equals("ReachBridge")) {
				train1.ReachBridge(t);
			}
			if (a.getReceiver().equals("train2") && a.getMsgName().equals("ReachBridge")) {
				train2.ReachBridge(t);
			}
		}	
	}
}