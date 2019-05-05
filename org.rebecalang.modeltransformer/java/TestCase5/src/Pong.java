package TestCase5;
import java.util.*;
import com.rits.cloning.Cloner;
public class Pong extends Actors {
private String name;
private int id;

public Pong(String n, int actorId, MessageQueue<Message> mq) {
float t_1 = 0;
float t_2 = 0;
this.name = n;
this.id = actorId;
Message msg3 = new Message();
msg3.setMsgName("pong");
msg3.setSender(this.name);
msg3.setReceiver(this.name);
msg3.setAfter_1(t_1);
msg3.setAfter_2(t_2);
msg3.setDeadline(t_1 + 100000);
mq.add(msg3);
}

public State pong(float t_1, float t_2, State s_1, String mode) throws CloneNotSupportedException {
Cloner cloner = new Cloner();
State s_2 = cloner.deepClone(s_1);
Pong a = (Pong) s_2.getActors()[id];
Message msg4 = new Message();
msg4.setMsgName("ping");
msg4.setSender(this.name);
msg4.setReceiver("pi");
msg4.setAfter_1(t_1 + 1);
msg4.setAfter_2(t_2 + 1);
msg4.setDeadline(t_1 + 100000);
s_2.getMessageQueue().add(msg4);
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
