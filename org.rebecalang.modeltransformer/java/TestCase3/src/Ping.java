package TestCase3;
import java.util.*;
import com.rits.cloning.Cloner;
public class Ping extends Actors {
private String name;
private int id;

public Ping(String n, int actorId, MessageQueue<Message> mq) {
float t = 0;
this.name = n;
this.id = actorId;
Message msg1 = new Message();
msg1.setMsgName("ping");
msg1.setSender(this.name);
msg1.setReceiver(this.name);
msg1.setAfter(t);
msg1.setDeadline(t + 100000);
mq.add(msg1);
}

public State ping(float t, State s_1) throws CloneNotSupportedException {
Cloner cloner = new Cloner();
State s_2 = cloner.deepClone(s_1);
Ping a = (Ping) s_2.getActors()[id];
Message msg2 = new Message();
msg2.setMsgName("pong");
msg2.setSender(this.name);
msg2.setReceiver("po");
msg2.setAfter(t + 1);
msg2.setDeadline(t + 100000);
s_2.getMessageQueue().add(msg2);
Message msg3 = new Message();
msg3.setMsgName("pong2");
msg3.setSender(this.name);
msg3.setReceiver("po");
msg3.setAfter(t + 1);
msg3.setDeadline(t + 100000);
s_2.getMessageQueue().add(msg3);
return s_2;
}

public boolean equals(Actors m) {
Ping a = (Ping) m;
if (!(this.name == a.name))
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

}
