import java.io.IOException;
import java.util.*;
import com.rits.cloning.Cloner;
public class Main {
public static void main(String[] args) throws CloneNotSupportedException {
Queue<State> queue = new LinkedList<State>();
Queue<State> queue_2 = new LinkedList<State>();
MessageQueue<Message> mq = new MessageQueue<Message>();
Cloner cloner = new Cloner();
float t = 0;
Message a = new Message();
Actors[] actors = new Actors[10];
int actorId = 0;
Train train1 = new Train("train1", actorId, mq);
actors[actorId] = train1;
actorId += 1;
Train train2 = new Train("train2", actorId, mq);
actors[actorId] = train2;
actorId += 1;
BridgeController theController = new BridgeController("theController", actorId, mq);
actors[actorId] = theController;
actorId += 1;
State s_0 = new State();
s_0.setActors(actors);
s_0.setMessageQueue(mq);
queue.add(s_0);
while (!queue.isEmpty()) {
State s_1 = new State();
State s_2 = new State();
s_1 = queue.poll();
queue_2.add(s_1);
State s = cloner.deepClone(s_1);
if (!s.getMessageQueue().isEmpty() && s.getMessageQueue().peek() != null) {
float after = s.getMessageQueue().peek().getAfter();
while (!s.getMessageQueue().isEmpty() && s.getMessageQueue().peek().getAfter() == after) {
a = s.getMessageQueue().remove();
if (a.getReceiver().equals("theController") && a.getMsgName().equals("Arrive")) {
s_2 = theController.Arrive(t, s_1);
if (!contains(queue, s_2) && !contains(queue_2, s_2)) {
queue.add(s_2);
}}
if (a.getReceiver().equals("theController") && a.getMsgName().equals("Leave")) {
s_2 = theController.Leave(t, s_1);
if (!contains(queue, s_2) && !contains(queue_2, s_2)) {
queue.add(s_2);
}}
if (a.getReceiver().equals("train1") && a.getMsgName().equals("YouMayPass")) {
s_2 = train1.YouMayPass(t, s_1);
if (!contains(queue, s_2) && !contains(queue_2, s_2)) {
queue.add(s_2);
}}
if (a.getReceiver().equals("train2") && a.getMsgName().equals("YouMayPass")) {
s_2 = train2.YouMayPass(t, s_1);
if (!contains(queue, s_2) && !contains(queue_2, s_2)) {
queue.add(s_2);
}}
if (a.getReceiver().equals("train1") && a.getMsgName().equals("Passed")) {
s_2 = train1.Passed(t, s_1);
if (!contains(queue, s_2) && !contains(queue_2, s_2)) {
queue.add(s_2);
}}
if (a.getReceiver().equals("train2") && a.getMsgName().equals("Passed")) {
s_2 = train2.Passed(t, s_1);
if (!contains(queue, s_2) && !contains(queue_2, s_2)) {
queue.add(s_2);
}}
if (a.getReceiver().equals("train1") && a.getMsgName().equals("ReachBridge")) {
s_2 = train1.ReachBridge(t, s_1);
if (!contains(queue, s_2) && !contains(queue_2, s_2)) {
queue.add(s_2);
}}
if (a.getReceiver().equals("train2") && a.getMsgName().equals("ReachBridge")) {
s_2 = train2.ReachBridge(t, s_1);
if (!contains(queue, s_2) && !contains(queue_2, s_2)) {
queue.add(s_2);
}}
}
}
else {
queue.poll();
}
}	
}
private static boolean contains(Queue<State> queue, State s_1) {
// TODO Auto-generated method stub
Iterator i = queue.iterator();
while (i.hasNext()) {
State s = (State) i.next();
if (s_1.equals(s))
return true;
}
return false;
}
private static String printState(State s) {
String retValue = "";
MessageQueue<Message> mq = s.getMessageQueue();
Actors[] actors = s.getActors();
Iterator<Message> itr = mq.iterator();
retValue += "messageQueue contents: \r\n";
while (itr.hasNext()) {
Message msg = itr.next();
if (msg != null) {
retValue += "MsgName:" + msg.getMsgName() + ", " + "MsgSender:" + msg.getSender() + ", "
+ "MsgReceiver:" + msg.getReceiver() + ", " + "MsgAfter:" + msg.getAfter() + "\r\n";
}
}
retValue += "actors variables: \r\n";

for (int i = 0; i < actors.length; i++) {
if (actors[i] != null) {
if (actors[i].getClass().getName().equals("BridgeController")) {
BridgeController a = (BridgeController) actors[i];
retValue += "Actor's Id:" + a.getId() + ", class:" + a.getClass().getName() + ", name:" + a.getName() + ", ";
retValue += "isWaiting1:" + a.getisWaiting1() + ", ";
retValue += "isWaiting2:" + a.getisWaiting2() + ", ";
retValue += "signal1:" + a.getsignal1() + ", ";
retValue += "signal2:" + a.getsignal2() + ", ";
retValue += "var:" + a.getvar() + ", ";
retValue += "\r\n";
}
if (actors[i].getClass().getName().equals("Train")) {
Train a = (Train) actors[i];
retValue += "Actor's Id:" + a.getId() + ", class:" + a.getClass().getName() + ", name:" + a.getName() + ", ";
retValue += "onTheBridge:" + a.getonTheBridge() + ", ";
retValue += "\r\n";
}
}
}
retValue += "-------------------------------------------------------------------------\r\n";
DirectoryCreator directoryCreator = new DirectoryCreator();
try {
directoryCreator.addFile("a.txt", retValue);
} catch (IOException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
return retValue;
}
private static String printStatesQueue(Queue<State> q) {
		String retValue = "";
		Iterator<State> itr = q.iterator();
		while (itr.hasNext()) {
			retValue += printState(itr.next());
		}
		DirectoryCreator directoryCreator = new DirectoryCreator();
		try {
			directoryCreator.addFile("a.txt", retValue);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retValue;
	}
}