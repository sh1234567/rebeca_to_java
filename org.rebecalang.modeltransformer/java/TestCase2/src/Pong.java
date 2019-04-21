package TestCase2;
import java.util.*;
import com.rits.cloning.Cloner;
public class Pong extends Actors {
private String name;
private int id;

public Pong(String n, int actorId, MessageQueue<Message> mq) {
float t = 0;
this.name = n;
this.id = actorId;
}

public State pong(float t, State s_1) throws CloneNotSupportedException {
Cloner cloner = new Cloner();
State s_2 = cloner.deepClone(s_1);
Pong a = (Pong) s_2.getActors()[id];
Message msg3 = new Message();
msg3.setMsgName("ping");
msg3.setSender(this.name);
msg3.setReceiver("ping");
msg3.setAfter(t + 1);
msg3.setDeadline(t + 100000);
s_2.getMessageQueue().add(msg3);
return s_2;
}

public boolean equals(Actors m) {
Pong a = (Pong) m;
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
