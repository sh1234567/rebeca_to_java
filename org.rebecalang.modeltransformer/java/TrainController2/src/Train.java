package TrainController2;
import java.util.*;
import com.rits.cloning.Cloner;
public class Train extends Actors {
private String name;
private int id;
private boolean onTheBridge;

public Train(String n, int actorId, MessageQueue<Message> mq) {
float t = 0;
this.name = n;
this.id = actorId;
onTheBridge = false;
Message msg5 = new Message();
msg5.setMsgName("Passed");
msg5.setSender(this.name);
msg5.setReceiver(this.name);
msg5.setAfter(t);
msg5.setDeadline(t + 100000);
mq.add(msg5);
}

public State YouMayPass(float t, State s_1) throws CloneNotSupportedException {
Cloner cloner = new Cloner();
State s_2 = cloner.deepClone(s_1);
Train a = (Train) s_2.getActors()[id];
a.setonTheBridge(true);
Message msg6 = new Message();
msg6.setMsgName("Passed");
msg6.setSender(this.name);
msg6.setReceiver(this.name);
msg6.setAfter(t);
msg6.setDeadline(t + 100000);
s_2.getMessageQueue().add(msg6);
return s_2;
}

public State Passed(float t, State s_1) throws CloneNotSupportedException {
Cloner cloner = new Cloner();
State s_2 = cloner.deepClone(s_1);
Train a = (Train) s_2.getActors()[id];
a.setonTheBridge(false);
Message msg7 = new Message();
msg7.setMsgName("Leave");
msg7.setSender(this.name);
msg7.setReceiver("theController");
msg7.setAfter(t);
msg7.setDeadline(t + 100000);
s_2.getMessageQueue().add(msg7);
Message msg8 = new Message();
msg8.setMsgName("ReachBridge");
msg8.setSender(this.name);
msg8.setReceiver(this.name);
msg8.setAfter(t);
msg8.setDeadline(t + 100000);
s_2.getMessageQueue().add(msg8);
return s_2;
}

public State ReachBridge(float t, State s_1) throws CloneNotSupportedException {
Cloner cloner = new Cloner();
State s_2 = cloner.deepClone(s_1);
Train a = (Train) s_2.getActors()[id];
Message msg9 = new Message();
msg9.setMsgName("Arrive");
msg9.setSender(this.name);
msg9.setReceiver("theController");
msg9.setAfter(t);
msg9.setDeadline(t + 100000);
s_2.getMessageQueue().add(msg9);
return s_2;
}

public boolean equals(Actors m) {
Train a = (Train) m;
if (!(this.name == a.name))
return false;
if (!(this.onTheBridge == a.onTheBridge))
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

	public boolean getonTheBridge() {
		return onTheBridge;
	}

	public void setonTheBridge(boolean onTheBridge) {
		this.onTheBridge = onTheBridge;
	}
}
