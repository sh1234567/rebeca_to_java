public class Train{
Message msg = new Message();
private String name;
private boolean onTheBridge;
public Train(String n) {
	this.name = n;
}
public void YouMayPass ()
{
onTheBridge = true;
msg = null;
msg.setMsgName("Passed");
msg.setSender(this.name);
msg.setReceiver(this.name);
MessageQueue.getMessageQueue().add(msg);

}
public void Passed ()
{
onTheBridge = false;
msg = null;
msg.setMsgName("Leave");
msg.setSender(this.name);
msg.setReceiver("theController");
MessageQueue.getMessageQueue().add(msg);
msg = null;
msg.setMsgName("ReachBridge");
msg.setSender(this.name);
msg.setReceiver(this.name);
MessageQueue.getMessageQueue().add(msg);

}
public void ReachBridge ()
{
msg = null;
msg.setMsgName("Arrive");
msg.setSender(this.name);
msg.setReceiver("theController");
MessageQueue.getMessageQueue().add(msg);

}
}
