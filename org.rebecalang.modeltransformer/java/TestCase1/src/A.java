package TestCase1;
import java.util.*;
import com.rits.cloning.Cloner;
public class A extends Actors {
private String name;
private int id;
private int i;

public A(String n, int actorId, MessageQueue<Message> mq) {
float t = 0;
this.name = n;
this.id = actorId;
Message msg1 = new Message();
msg1.setMsgName("B");
msg1.setSender(this.name);
msg1.setReceiver(this.name);
msg1.setAfter(t);
msg1.setDeadline(t + 100000);
mq.add(msg1);
i = 0;
}

public State B(float t, State s_1) throws CloneNotSupportedException {
Cloner cloner = new Cloner();
State s_2 = cloner.deepClone(s_1);
A a = (A) s_2.getActors()[id];
if (i < 3) {
a.seti(i + 1);
}
Message msg2 = new Message();
msg2.setMsgName("B");
msg2.setSender(this.name);
msg2.setReceiver(this.name);
msg2.setAfter(t + 1);
msg2.setDeadline(t + 100000);
s_2.getMessageQueue().add(msg2);
return s_2;
}

public boolean equals(Actors m) {
A a = (A) m;
if (!(this.name == a.name))
return false;
if (!(this.i == a.i))
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

	public int geti() {
		return i;
	}

	public void seti(int i) {
		this.i = i;
	}
}
