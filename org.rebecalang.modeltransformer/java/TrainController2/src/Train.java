public class Train{
private String name;
private boolean onTheBridge;
public Train(String n) {
	this.name = n;
}
public void YouMayPass ()
{
onTheBridge = true;
Message msg = new Message();
msg.setMsgName("Passed");
msg.setSender(this.name);
msg.setReceiver(this.name);
MessageQueue.getMessageQueue().add(msg);

}
public void Passed ()
{
onTheBridge = false;
Message msg = new Message();
msg.setMsgName("Leave");
msg.setSender(this.name);
msg.setReceiver("theController");
MessageQueue.getMessageQueue().add(msg);
Message msg = new Message();
msg.setMsgName("ReachBridge");
msg.setSender(this.name);
msg.setReceiver(this.name);
MessageQueue.getMessageQueue().add(msg);

}
public void ReachBridge ()
{
Message msg = new Message();
msg.setMsgName("Arrive");
msg.setSender(this.name);
msg.setReceiver("theController");
MessageQueue.getMessageQueue().add(msg);

}
}
