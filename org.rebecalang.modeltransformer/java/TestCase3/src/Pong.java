package TestCase3;
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
Message msg4 = new Message();
msg4.setMsgName("ping");
msg4.setSender(this.name);
msg4.setReceiver("pi");
msg4.setAfter(t + 1);
msg4.setDeadline(t + 100000);
s_2.getMessageQueue().add(msg4);
return s_2;
}

public State pong2(float t, State s_1) throws CloneNotSupportedException {
Cloner cloner = new Cloner();
State s_2 = cloner.deepClone(s_1);
Pong a = (Pong) s_2.getActors()[id];
Message msg5 = new Message();
msg5.setMsgName("ping");
msg5.setSender(this.name);
msg5.setReceiver("pi");
msg5.setAfter(t + 1);
msg5.setDeadline(t + 100000);
s_2.getMessageQueue().add(msg5);
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
