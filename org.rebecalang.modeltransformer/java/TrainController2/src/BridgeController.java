public class BridgeController{
Message msg = new Message();
private String name;
private boolean isWaiting1;
private boolean isWaiting2;
private boolean signal1;
private boolean signal2;
private boolean var = true;
public BridgeController(String n) {
	this.name = n;
}
public void Arrive ()
{
if (var == true) {
if (signal2 == false) {
signal1 = true;
msg = null;
msg.setMsgName("YouMayPass");
msg.setSender(this.name);
msg.setReceiver("train1");
MessageQueue.getMessageQueue().add(msg);
}
else {
isWaiting1 = true;
}
}
else {
if (signal1 == false) {
signal2 = true;
msg = null;
msg.setMsgName("YouMayPass");
msg.setSender(this.name);
msg.setReceiver("train2");
MessageQueue.getMessageQueue().add(msg);
}
else {
isWaiting2 = true;
}
}

}
public void Leave ()
{
if (var == true) {
signal1 = false;
if (isWaiting2) {
signal2 = true;
msg = null;
msg.setMsgName("YouMayPass");
msg.setSender(this.name);
msg.setReceiver("train2");
MessageQueue.getMessageQueue().add(msg);
isWaiting2 = false;
}
}
else {
signal2 = false;
if (isWaiting1) {
signal1 = true;
msg = null;
msg.setMsgName("YouMayPass");
msg.setSender(this.name);
msg.setReceiver("train1");
MessageQueue.getMessageQueue().add(msg);
isWaiting1 = false;
}
}

}
}
