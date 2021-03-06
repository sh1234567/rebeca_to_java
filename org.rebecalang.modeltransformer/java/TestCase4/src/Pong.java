package TestCase4;
import java.util.*;
import com.rits.cloning.Cloner;
public class Pong extends Actors {
private String name;
private int id;
private int i;

public Pong(String n, int actorId, MessageQueue<Message> mq) {
float t_1 = 0;
float t_2 = 0;
this.name = n;
this.id = actorId;
i = 0;
}

public State pong(float t_1, float t_2, State s_1, String mode) throws CloneNotSupportedException {
Cloner cloner = new Cloner();
State s_2 = cloner.deepClone(s_1);
Pong a = (Pong) s_2.getActors()[id];
if (i < 3) {
a.seti(i + 1);
}
else {
a.seti(0);
}
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

public State pong2(float t_1, float t_2, State s_1, String mode) throws CloneNotSupportedException {
Cloner cloner = new Cloner();
State s_2 = cloner.deepClone(s_1);
Pong a = (Pong) s_2.getActors()[id];
Message msg5 = new Message();
msg5.setMsgName("ping");
msg5.setSender(this.name);
msg5.setReceiver("pi");
msg5.setAfter_1(t_1 + 1);
msg5.setAfter_2(t_2 + 1);
msg5.setDeadline(t_1 + 100000);
s_2.getMessageQueue().add(msg5);
return s_2;
}

public boolean equals(Actors m) {
Pong a = (Pong) m;
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
