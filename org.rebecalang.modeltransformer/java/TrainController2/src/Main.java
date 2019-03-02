public class Main{
public static void main(String[] args){
Message a = new Message();
a = MessageQueue.getMessageQueue().poll();
Train train1 = new Train("train1");
Train train2 = new Train("train2");
BridgeController theController = new BridgeController("theController");
if (a.getReceiver().equals("theController") && a.getMsgName().equals("Arrive")) {
	theController.Arrive();
}
if (a.getReceiver().equals("theController") && a.getMsgName().equals("Leave")) {
	theController.Leave();
}
if (a.getReceiver().equals("train1") && a.getMsgName().equals("YouMayPass")) {
	train1.YouMayPass();
}
if (a.getReceiver().equals("train2") && a.getMsgName().equals("YouMayPass")) {
	train2.YouMayPass();
}
if (a.getReceiver().equals("train1") && a.getMsgName().equals("Passed")) {
	train1.Passed();
}
if (a.getReceiver().equals("train2") && a.getMsgName().equals("Passed")) {
	train2.Passed();
}
if (a.getReceiver().equals("train1") && a.getMsgName().equals("ReachBridge")) {
	train1.ReachBridge();
}
if (a.getReceiver().equals("train2") && a.getMsgName().equals("ReachBridge")) {
	train2.ReachBridge();
}
	
}
}