public class Train{
private String name;
private boolean onTheBridge;
public Train(String n) {
	this.name = n;
}
public void YouMayPass ()
{
onTheBridge = true;
Message msg5 = new Message();
msg5.setMsgName("Passed");
msg5.setSender(this.name);
msg5.setReceiver(this.name);
MessageQueue.getMessageQueue().add(msg5);

}
public void Passed ()
{
onTheBridge = false;
Message msg6 = new Message();
msg6.setMsgName("Leave");
msg6.setSender(this.name);
msg6.setReceiver("theController");
MessageQueue.getMessageQueue().add(msg6);
Message msg7 = new Message();
msg7.setMsgName("ReachBridge");
msg7.setSender(this.name);
msg7.setReceiver(this.name);
MessageQueue.getMessageQueue().add(msg7);

}
public void ReachBridge ()
{
Message msg8 = new Message();
msg8.setMsgName("Arrive");
msg8.setSender(this.name);
msg8.setReceiver("theController");
MessageQueue.getMessageQueue().add(msg8);

}
}
