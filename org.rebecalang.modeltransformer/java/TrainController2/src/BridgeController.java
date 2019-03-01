public class BridgeController{
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
Message msg1 = new Message();
msg1.setMsgName("YouMayPass");
msg1.setSender(this.name);
msg1.setReceiver("train1");
MessageQueue.getMessageQueue().add(msg1);
}
else {
isWaiting1 = true;
}
}
else {
if (signal1 == false) {
signal2 = true;
Message msg2 = new Message();
msg2.setMsgName("YouMayPass");
msg2.setSender(this.name);
msg2.setReceiver("train2");
MessageQueue.getMessageQueue().add(msg2);
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
Message msg3 = new Message();
msg3.setMsgName("YouMayPass");
msg3.setSender(this.name);
msg3.setReceiver("train2");
MessageQueue.getMessageQueue().add(msg3);
isWaiting2 = false;
}
}
else {
signal2 = false;
if (isWaiting1) {
signal1 = true;
Message msg4 = new Message();
msg4.setMsgName("YouMayPass");
msg4.setSender(this.name);
msg4.setReceiver("train1");
MessageQueue.getMessageQueue().add(msg4);
isWaiting1 = false;
}
}

}
}
