package TestCase5;
import java.util.*;
import com.rits.cloning.Cloner;
public class Ping extends Actors {
private String name;
private int id;

public Ping(String n, int actorId, MessageQueue<Message> mq) {
float t_1 = 0;
float t_2 = 0;
this.name = n;
this.id = actorId;
Message msg1 = new Message();
msg1.setMsgName("ping");
msg1.setSender(this.name);
msg1.setReceiver(this.name);
msg1.setAfter_1(t_1);
msg1.setAfter_2(t_2);
msg1.setDeadline(t_1 + 100000);
mq.add(msg1);
}

public State ping(float t_1, float t_2, State s_1, String mode) throws CloneNotSupportedException {
Cloner cloner = new Cloner();
State s_2 = cloner.deepClone(s_1);
Ping a = (Ping) s_2.getActors()[id];
Message msg2 = new Message();
msg2.setMsgName("pong");
msg2.setSender(this.name);
msg2.setReceiver("po");
msg2.setAfter_1(t_1 + 1);
msg2.setAfter_2(t_2 + 1);
msg2.setDeadline(t_1 + 100000);
s_2.getMessageQueue().add(msg2);
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
