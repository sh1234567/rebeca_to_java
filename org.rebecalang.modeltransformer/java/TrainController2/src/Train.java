public class Train{
private String name;
private boolean onTheBridge;
public Train(String n) {
this.name = n;
onTheBridge = false;
Message msg5 = new Message();
msg5.setMsgName("Passed");
msg5.setSender(this.name);
msg5.setReceiver(this.name);
msg5.setAfter(0);
msg5.setDeadline(100000);
MessageQueue.getMessageQueue().add(msg5);
}
public void YouMayPass ()
{
onTheBridge = true;
Message msg6 = new Message();
msg6.setMsgName("Passed");
msg6.setSender(this.name);
msg6.setReceiver(this.name);
msg6.setAfter(0);
msg6.setDeadline(100000);
MessageQueue.getMessageQueue().add(msg6);

}
public void Passed ()
{
onTheBridge = false;
Message msg7 = new Message();
msg7.setMsgName("Leave");
msg7.setSender(this.name);
msg7.setReceiver("theController");
msg7.setAfter(0);
msg7.setDeadline(100000);
MessageQueue.getMessageQueue().add(msg7);
Message msg8 = new Message();
msg8.setMsgName("ReachBridge");
msg8.setSender(this.name);
msg8.setReceiver(this.name);
msg8.setAfter(0);
msg8.setDeadline(100000);
MessageQueue.getMessageQueue().add(msg8);

}
public void ReachBridge ()
{
Message msg9 = new Message();
msg9.setMsgName("Arrive");
msg9.setSender(this.name);
msg9.setReceiver("theController");
msg9.setAfter(0);
msg9.setDeadline(100000);
MessageQueue.getMessageQueue().add(msg9);

}
}
