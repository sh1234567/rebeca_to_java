import java.util.*;
public class BridgeController extends Actors {
private String name;
private int id;
private boolean isWaiting1;
private boolean isWaiting2;
private boolean signal1;
private boolean signal2;
private boolean var = true;

public BridgeController(String n, int actorId, MessageQueue<Message> mq) {
float t = 0;
this.name = n;
this.id = actorId;
signal1 = false;
signal2 = false;
isWaiting1 = false;
isWaiting2 = false;
}

public State Arrive(float t, State s_1) throws CloneNotSupportedException {
State s_2 = (State) s_1.clone();
MessageQueue<Message> mq = new MessageQueue<Message>();
mq = s_1.getMessageQueue().clone();
Actors[] actors = s_1.getActors().clone();
BridgeController a = (BridgeController) actors[id].clone();
if (var == true) {
if (signal2 == false) {
signal1 = true;
a.setsignal1(signal1);
Message msg1 = new Message();
msg1.setMsgName("YouMayPass");
msg1.setSender(this.name);
msg1.setReceiver("train1");
msg1.setAfter(t);
msg1.setDeadline(t + 100000);
mq.add(msg1);
}
else {
isWaiting1 = true;
a.setisWaiting1(isWaiting1);
}
}
else {
if (signal1 == false) {
signal2 = true;
a.setsignal2(signal2);
Message msg2 = new Message();
msg2.setMsgName("YouMayPass");
msg2.setSender(this.name);
msg2.setReceiver("train2");
msg2.setAfter(t);
msg2.setDeadline(t + 100000);
mq.add(msg2);
}
else {
isWaiting2 = true;
a.setisWaiting2(isWaiting2);
}
}
s_2.setMessageQueue(mq);
actors[id] = a;
s_2.setActors(actors);
return s_2;
}

public State Leave(float t, State s_1) throws CloneNotSupportedException {
State s_2 = (State) s_1.clone();
MessageQueue<Message> mq = new MessageQueue<Message>();
mq = s_1.getMessageQueue().clone();
Actors[] actors = s_1.getActors().clone();
BridgeController a = (BridgeController) actors[id].clone();
if (var == true) {
signal1 = false;
a.setsignal1(signal1);
if (isWaiting2) {
signal2 = true;
a.setsignal2(signal2);
Message msg3 = new Message();
msg3.setMsgName("YouMayPass");
msg3.setSender(this.name);
msg3.setReceiver("train2");
msg3.setAfter(t);
msg3.setDeadline(t + 100000);
mq.add(msg3);
isWaiting2 = false;
a.setisWaiting2(isWaiting2);
}
}
else {
signal2 = false;
a.setsignal2(signal2);
if (isWaiting1) {
signal1 = true;
a.setsignal1(signal1);
Message msg4 = new Message();
msg4.setMsgName("YouMayPass");
msg4.setSender(this.name);
msg4.setReceiver("train1");
msg4.setAfter(t);
msg4.setDeadline(t + 100000);
mq.add(msg4);
isWaiting1 = false;
a.setisWaiting1(isWaiting1);
}
}
s_2.setMessageQueue(mq);
actors[id] = a;
s_2.setActors(actors);
return s_2;
}

public boolean equals(Actors m) {
BridgeController a = (BridgeController) m;
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
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getisWaiting1() {
		return isWaiting1;
	}

	public void setisWaiting1(boolean isWaiting1) {
		this.isWaiting1 = isWaiting1;
	}
	public boolean getisWaiting2() {
		return isWaiting2;
	}

	public void setisWaiting2(boolean isWaiting2) {
		this.isWaiting2 = isWaiting2;
	}
	public boolean getsignal1() {
		return signal1;
	}

	public void setsignal1(boolean signal1) {
		this.signal1 = signal1;
	}
	public boolean getsignal2() {
		return signal2;
	}

	public void setsignal2(boolean signal2) {
		this.signal2 = signal2;
	}
	public boolean getvar() {
		return var;
	}

	public void setvar(boolean var) {
		this.var = var;
	}
}
