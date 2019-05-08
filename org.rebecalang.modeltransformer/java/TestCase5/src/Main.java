package TestCase5;
import java.io.IOException;
import java.util.*;
import com.rits.cloning.Cloner;
public class Main {
public static void main(String[] args) throws CloneNotSupportedException {
String mode = "i";
String programRetValue = "";
Queue<State> queue = new LinkedList<State>();
Queue<State> queue_2 = new LinkedList<State>();
MessageQueue<Message> mq = new MessageQueue<Message>();
Cloner cloner = new Cloner();
float t_1 = 0;
float t_2 = 0;
int id = 0;
int states_num = 0;
Message a = new Message();
Actors[] actors = new Actors[10];
String[] actorsNames = new String[10];
int actorId = 0;
Ping pi = new Ping("pi", actorId, mq);
actors[actorId] = pi;
actorsNames[actorId] = "pi";
actorId += 1;
Pong po = new Pong("po", actorId, mq);
actors[actorId] = po;
actorsNames[actorId] = "po";
actorId += 1;
State s_0 = new State();
s_0.setActors(actors);
s_0.setMessageQueue(mq);
queue.add(s_0);
states_num += 1;
System.out.println(printState(s_0, states_num));
programRetValue += printState(s_0, states_num);
int n = 0;
if (mode.equals("d")) {
while (!queue.isEmpty()) {
State s_1 = new State();
State s_2 = new State();
s_1 = queue.poll();
queue_2.add(s_1);
State s = cloner.deepClone(s_1);
if (!s.getMessageQueue().isEmpty() && s.getMessageQueue().peek() != null) {
float after = s.getMessageQueue().peek().getAfter_1();
t_1 = after;
t_2 = t_1;
int highPriority_num = 0;
MessageQueue<Message> mq_2 = new MessageQueue<Message>();
while (!s.getMessageQueue().isEmpty() && s.getMessageQueue().peek().getAfter_1() == after) {
mq_2.add(s.getMessageQueue().remove());
highPriority_num += 1;
}
Message[] equalPriorityMsgs = new Message[highPriority_num];
for (int i = 0; i < highPriority_num; i++) {
equalPriorityMsgs[i] = mq_2.remove();
}

for (int i = 0; i < equalPriorityMsgs.length; i++) {
State new_s = cloner.deepClone(s);
MessageQueue<Message> mq_3 = new MessageQueue<Message>();
for (int j = 0; j < equalPriorityMsgs.length; j++) {
if (i != j) {
new_s.getMessageQueue().add(equalPriorityMsgs[j]);
} else {
a = equalPriorityMsgs[j];
}
}
id = 0;
for (int j = 0; j < actorsNames.length; j++) {
if (actorsNames[j] != null && actorsNames[j].equals("pi")) {
id = j;
break;
}
}
if (a.getReceiver().equals("pi") && a.getMsgName().equals("ping") && actors[id].getClass().getSimpleName().equals("Ping")) {
s_2 = ((Ping) new_s.getActors()[id]).ping(t_1, t_2, new_s, mode);
if (!contains(queue, s_2, mode) && !contains(queue_2, s_2, mode)) {
queue.add(s_2);
states_num += 1;
System.out.println(printState(s_2, states_num));
programRetValue += printState(s_2, states_num);
}else {
System.out.println("equal:\r\n" + printState(s_2, 0));
programRetValue += "equal:\r\n" + printState(s_2, 0);
}
}
id = 0;
for (int j = 0; j < actorsNames.length; j++) {
if (actorsNames[j] != null && actorsNames[j].equals("po")) {
id = j;
break;
}
}
if (a.getReceiver().equals("po") && a.getMsgName().equals("pong") && actors[id].getClass().getSimpleName().equals("Pong")) {
s_2 = ((Pong) new_s.getActors()[id]).pong(t_1, t_2, new_s, mode);
if (!contains(queue, s_2, mode) && !contains(queue_2, s_2, mode)) {
queue.add(s_2);
states_num += 1;
System.out.println(printState(s_2, states_num));
programRetValue += printState(s_2, states_num);
}else {
System.out.println("equal:\r\n" + printState(s_2, 0));
programRetValue += "equal:\r\n" + printState(s_2, 0);
}
}
}
}
else {
queue.poll();
}
n += 1;
}
}
else if (mode.equals("i")) {
while (!queue.isEmpty()) {
State s_1 = new State();
State s_2 = new State();
s_1 = queue.poll();
queue_2.add(s_1);
State s = cloner.deepClone(s_1);
// ArrayList<Message> messages = new ArrayList<Message>();
Message m = new Message();
ArrayList<TimePoint> timePoints = new ArrayList<TimePoint>();
ArrayList<TimePoint> timePoints_2 = new ArrayList<TimePoint>();
while (!s.getMessageQueue().isEmpty()) {
TimePoint tp_1 = new TimePoint();
TimePoint tp_2 = new TimePoint();
m = s.getMessageQueue().remove();
tp_1.setTime(m.getAfter_1());
tp_1.setType("b");
timePoints.add(tp_1);
tp_2.setTime(m.getAfter_2());
tp_2.setType("e");
timePoints.add(tp_2);
}
timePoints_2 = cloner.deepClone(timePoints);
if (!timePoints_2.isEmpty()) {
float min_1 = timePoints_2.remove(0).getTime();
float min_2 = timePoints_2.remove(0).getTime();
while (min_2 == min_1 && !timePoints_2.isEmpty()) {
min_2 = timePoints_2.remove(0).getTime();
}
float r = 0;
if (min_2 < min_1) {
r = min_1;
min_1 = min_2;
min_2 = r;
}
int l = timePoints_2.toArray().length;
for (int i = 0; i < l; i++) {
float new_t = timePoints_2.remove(0).getTime();
if (new_t < min_1) {
min_2 = min_1;
min_1 = new_t;
} else if (new_t < min_2 && new_t != min_1) {
min_2 = new_t;
}
}
s = cloner.deepClone(s_1);
while (!s.getMessageQueue().isEmpty()) {
Message msg = s.getMessageQueue().remove();
if (msg.getAfter_1() == min_1 && msg.getAfter_2() == min_1) {
min_2 = min_1;
break;
}
}System.out.println("min_1: " + min_1);
programRetValue += "min_1: " + min_1 + "\r\n";
System.out.println("min_2: " + min_2);
programRetValue += "min_2: " + min_2  + "\r\n";
t_1 = min_1;
t_2 = min_2;
s = cloner.deepClone(s_1);
ArrayList<Message> m_1 = new ArrayList<Message>();
ArrayList<Message> m_2 = new ArrayList<Message>();
while (!s.getMessageQueue().isEmpty()) {
Message msg = s.getMessageQueue().remove();
if (msg.getAfter_1() == min_1) {
m_1.add(cloner.deepClone(msg));
}
if (msg.getAfter_2() == min_2) {
m_2.add(cloner.deepClone(msg));
}
}
s = cloner.deepClone(s_1);
State s_prime = cloner.deepClone(s_1);
if (!m_1.isEmpty()) {
for (int i = 0; i < m_1.toArray().length; i++) {
s = cloner.deepClone(s_1);
State new_s = cloner.deepClone(s_1);
Message new_m = new Message();
MessageQueue<Message> mq_3 = new MessageQueue<Message>();
while (!s.getMessageQueue().isEmpty()) {
new_m = s.getMessageQueue().remove();
if (!(new_m.equals(m_1.get(i)))) {
mq_3.add(new_m);
} else {
a = new_m;
}
}
System.out.println(printMessage(a));
programRetValue += printMessage(a) + "\r\n";
new_s.setMessageQueue(mq_3);
new_s.setState_time_1(min_1);
new_s.setState_time_2(min_2);
System.out.println(printState(new_s, 0));
programRetValue += printState(new_s, 0);
id = 0;
for (int j = 0; j < actorsNames.length; j++) {
if (actorsNames[j] != null && actorsNames[j].equals("pi")) {
id = j;
break;
}
}
if (a.getReceiver().equals("pi") && a.getMsgName().equals("ping") && actors[id].getClass().getSimpleName().equals("Ping")) {
s_2 = ((Ping) new_s.getActors()[id]).ping(t_1, t_2, new_s, mode);
if (!contains(queue, s_2, mode) && !contains(queue_2, s_2, mode)) {
queue.add(s_2);
states_num += 1;
System.out.println(printState(s_2, states_num));
programRetValue += printState(s_2, states_num);
}else {
System.out.println("equal:\r\n" + printState(s_2, 0));
programRetValue += "equal:\r\n" + printState(s_2, 0);
}
}
id = 0;
for (int j = 0; j < actorsNames.length; j++) {
if (actorsNames[j] != null && actorsNames[j].equals("po")) {
id = j;
break;
}
}
if (a.getReceiver().equals("po") && a.getMsgName().equals("pong") && actors[id].getClass().getSimpleName().equals("Pong")) {
s_2 = ((Pong) new_s.getActors()[id]).pong(t_1, t_2, new_s, mode);
if (!contains(queue, s_2, mode) && !contains(queue_2, s_2, mode)) {
queue.add(s_2);
states_num += 1;
System.out.println(printState(s_2, states_num));
programRetValue += printState(s_2, states_num);
}else {
System.out.println("equal:\r\n" + printState(s_2, 0));
programRetValue += "equal:\r\n" + printState(s_2, 0);
}
}

}
}
if (m_2.isEmpty()) {

System.out.println("time passing");
programRetValue += "time passing" + "\r\n";
s = cloner.deepClone(s_prime);
State new_s = cloner.deepClone(s_1);
MessageQueue<Message> mq_3 = new MessageQueue<Message>();
while (!s.getMessageQueue().isEmpty()) {
Message msg = s.getMessageQueue().remove();
if (msg.getAfter_1() < min_2) {
msg.setAfter_1(min_2);
}
mq_3.add(msg);
}
new_s.setMessageQueue(mq_3);
s = cloner.deepClone(new_s);
m = new Message();
timePoints = new ArrayList<TimePoint>();
timePoints_2 = new ArrayList<TimePoint>();
while (!s.getMessageQueue().isEmpty()) {
TimePoint tp_1 = new TimePoint();
TimePoint tp_2 = new TimePoint();
m = s.getMessageQueue().remove();
tp_1.setTime(m.getAfter_1());
tp_1.setType("b");
timePoints.add(tp_1);
tp_2.setTime(m.getAfter_2());
tp_2.setType("e");
timePoints.add(tp_2);
}
timePoints_2 = cloner.deepClone(timePoints);
if (!timePoints_2.isEmpty()) {
min_1 = timePoints_2.remove(0).getTime();
min_2 = timePoints_2.remove(0).getTime();
while (min_2 == min_1 && !timePoints_2.isEmpty()) {
min_2 = timePoints_2.remove(0).getTime();
}
r = 0;
if (min_2 < min_1) {
r = min_1;
min_1 = min_2;
min_2 = r;
}
l = timePoints_2.toArray().length;
for (int i = 0; i < l; i++) {
float new_t = timePoints_2.remove(0).getTime();
if (new_t < min_1) {
min_2 = min_1;
min_1 = new_t;
} else if (new_t < min_2 && new_t != min_1) {
min_2 = new_t;
}
}
s = cloner.deepClone(s_1);
while (!s.getMessageQueue().isEmpty()) {
Message msg = s.getMessageQueue().remove();
if (msg.getAfter_1() == min_1 && msg.getAfter_2() == min_1) {
min_2 = min_1;
break;
}
}
System.out.println("min_1: " + min_1);
programRetValue += "min_1: " + min_1 + "\r\n";
System.out.println("min_2: " + min_2);programRetValue += "min_2: " + min_2 + "\r\n";
new_s.setState_time_1(min_1);
new_s.setState_time_2(min_2);
t_1 = min_1;
t_2 = min_2;
if (!contains(queue, new_s, mode) && !contains(queue_2, new_s, mode)) {
queue.add(new_s);
states_num += 1;
System.out.println(printState(new_s, states_num));
programRetValue += printState(new_s, states_num);
} else {
System.out.println("equal:\r\n" + printState(new_s, 0));
programRetValue += "equal:\r\n" + printState(new_s, 0);
}
}
}
}
}
}

DirectoryCreator directoryCreator = new DirectoryCreator();
try {
directoryCreator.addFile("prog_notes.txt", programRetValue);
} catch (IOException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
}
private static boolean contains(Queue<State> queue, State s_1, String mode) {
// TODO Auto-generated method stub
Iterator i = queue.iterator();
while (i.hasNext()) {
State s = (State) i.next();
if (s_1.equals(s, mode)) {
System.out.println("equal_2: " + printState(s_1, 0));
System.out.println("equal_1: " + printState(s, 0));
return true;
}
}
return false;
}
private static String printMessage(Message msg) {
String retValue = "";
retValue += "MsgName:" + msg.getMsgName() + ", " + "MsgSender:" + msg.getSender() + ", "
+ "MsgReceiver:" + msg.getReceiver() + ", " + "MsgAfterIntervalBegin:" + msg.getAfter_1() + ", "
+ "MsgAfterIntervalEnd:" + msg.getAfter_2() + "\r\n";
return retValue;
}
private static String printState(State s, int state_number) {
String retValue = "";
retValue += "-------------------------------------------------------------------------\r\n";
retValue += "State number: " + state_number + "\r\n";
retValue += "State begin time: " + s.getState_time_1() + ", State end time: " + s.getState_time_2() + "\r\n";
MessageQueue<Message> mq = s.getMessageQueue();
Actors[] actors = s.getActors();
Iterator<Message> itr = mq.iterator();
retValue += "messageQueue contents: \r\n";
while (itr.hasNext()) {
Message msg = itr.next();
if (msg != null) {
retValue += "MsgName:" + msg.getMsgName() + ", " + "MsgSender:" + msg.getSender() + ", "
+ "MsgReceiver:" + msg.getReceiver() + ", " + "MsgAfterIntervalBegin:" + msg.getAfter_1() + ", "
+ "MsgAfterIntervalEnd:" + msg.getAfter_2() + "\r\n";
}
}
retValue += "actors variables: \r\n";

for (int i = 0; i < actors.length; i++) {
if (actors[i] != null) {
if (actors[i].getClass().getSimpleName().equals("Ping")) {
Ping a = (Ping) actors[i];
retValue += "Actor's Id:" + a.getId() + ", class:" + a.getClass().getSimpleName() + ", name:" + a.getName() + ", ";
retValue += "\r\n";
}
if (actors[i].getClass().getSimpleName().equals("Pong")) {
Pong a = (Pong) actors[i];
retValue += "Actor's Id:" + a.getId() + ", class:" + a.getClass().getSimpleName() + ", name:" + a.getName() + ", ";
retValue += "\r\n";
}
}
}
retValue += "-------------------------------------------------------------------------" + "\r\n";
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
int i = 1;
while (itr.hasNext()) {
retValue += printState(itr.next(), i);
i++;
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