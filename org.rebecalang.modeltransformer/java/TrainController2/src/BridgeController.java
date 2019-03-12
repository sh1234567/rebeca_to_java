public class BridgeController extends Actors {
private String name;
private boolean isWaiting1;
private boolean isWaiting2;
private boolean signal1;
private boolean signal2;
private boolean var = true;

public BridgeController(String n) {
float t = 0;
this.name = n;
signal1 = false;
signal2 = false;
isWaiting1 = false;
isWaiting2 = false;
}

public State Arrive(float t, State s_1) throws CloneNotSupportedException {
State s_2 = (State) s_1.clone();
if (var == true) {
if (signal2 == false) {
signal1 = true;
Message msg1 = new Message();
msg1.setMsgName("YouMayPass");
msg1.setSender(this.name);
msg1.setReceiver("train1");
msg1.setAfter(t);
msg1.setDeadline(t + 100000);
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
msg2.setAfter(t);
msg2.setDeadline(t + 100000);
MessageQueue.getMessageQueue().add(msg2);
}
else {
isWaiting2 = true;
}
}
return s_2;
}

public State Leave(float t, State s_1) throws CloneNotSupportedException {
State s_2 = (State) s_1.clone();
if (var == true) {
signal1 = false;
if (isWaiting2) {
signal2 = true;
Message msg3 = new Message();
msg3.setMsgName("YouMayPass");
msg3.setSender(this.name);
msg3.setReceiver("train2");
msg3.setAfter(t);
msg3.setDeadline(t + 100000);
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
msg4.setAfter(t);
msg4.setDeadline(t + 100000);
MessageQueue.getMessageQueue().add(msg4);
isWaiting1 = false;
}
}
return s_2;
}

public boolean equals(BridgeController a) {
if (!(this.name == a.name))
return false;
if (!(this.isWaiting1 == a.isWaiting1))
return false;
if (!(this.isWaiting2 == a.isWaiting2))
return false;
if (!(this.signal1 == a.signal1))
return false;
if (!(this.signal2 == a.signal2))
return false;
if (!(this.var == a.var))
return false;
return true;
}
}
