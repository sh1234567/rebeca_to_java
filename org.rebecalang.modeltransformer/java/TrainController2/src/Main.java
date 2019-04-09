import java.util.*;
public class Main {
public static void main(String[] args) throws CloneNotSupportedException {
Queue<State> queue = new LinkedList<State>();
MessageQueue<Message> mq = new MessageQueue<Message>();
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
Iterator i = queue.iterator();
while (i.hasNext()) {
State s_1 = new State();
State s_2 = new State();
s_1 = (State) i.next();
if (!s_1.getMessageQueue().isEmpty()) {
float after = s_1.getMessageQueue().peek().getAfter();
while (s_1.getMessageQueue().peek().getAfter() == after) {
a = s_1.getMessageQueue().poll();
if (a.getReceiver().equals("theController") && a.getMsgName().equals("Arrive")) {
s_2 = theController.Arrive(t, s_1);
if (!contains(queue, s_2)) {
queue.add(s_2);
}}
if (a.getReceiver().equals("theController") && a.getMsgName().equals("Leave")) {
s_2 = theController.Leave(t, s_1);
if (!contains(queue, s_2)) {
queue.add(s_2);
}}
if (a.getReceiver().equals("train1") && a.getMsgName().equals("YouMayPass")) {
s_2 = train1.YouMayPass(t, s_1);
if (!contains(queue, s_2)) {
queue.add(s_2);
}}
if (a.getReceiver().equals("train2") && a.getMsgName().equals("YouMayPass")) {
s_2 = train2.YouMayPass(t, s_1);
if (!contains(queue, s_2)) {
queue.add(s_2);
}}
if (a.getReceiver().equals("train1") && a.getMsgName().equals("Passed")) {
s_2 = train1.Passed(t, s_1);
if (!contains(queue, s_2)) {
queue.add(s_2);
}}
if (a.getReceiver().equals("train2") && a.getMsgName().equals("Passed")) {
s_2 = train2.Passed(t, s_1);
if (!contains(queue, s_2)) {
queue.add(s_2);
}}
if (a.getReceiver().equals("train1") && a.getMsgName().equals("ReachBridge")) {
s_2 = train1.ReachBridge(t, s_1);
if (!contains(queue, s_2)) {
queue.add(s_2);
}}
if (a.getReceiver().equals("train2") && a.getMsgName().equals("ReachBridge")) {
s_2 = train2.ReachBridge(t, s_1);
if (!contains(queue, s_2)) {
queue.add(s_2);
}}
}
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
}