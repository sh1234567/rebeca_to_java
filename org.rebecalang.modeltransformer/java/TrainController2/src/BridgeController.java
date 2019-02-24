public class BridgeController{
private String name;
private boolean isWaiting1;
private boolean isWaiting2;
private boolean signal1;
private boolean signal2;
private boolean var = true;
public BridgeController(String n) {
	this.name = n;
}
public void Arrive ()
{
if (var == true) {
if (signal2 == false) {
signal1 = true;
Message msg = new Message();
msg.setMsgName("YouMayPass");
msg.setSender(this.name);
msg.setReceiver(t1);

pubMsg1.sender=sender;
t1_YouMayPass_pub.publish(pubMsg1);
;

}
else {
isWaiting1 = true;

}

}
else {
if (signal1 == false) {
signal2 = true;
Message msg = new Message();
msg.setMsgName("YouMayPass");
msg.setSender(this.name);
msg.setReceiver(t2);

pubMsg3.sender=sender;
t2_YouMayPass_pub.publish(pubMsg3);
;

}
else {
isWaiting2 = true;

}

}


}

public void Leave ()
{
if (var == true) {
signal1 = false;
if (isWaiting2) {
signal2 = true;
Message msg = new Message();
msg.setMsgName("YouMayPass");
msg.setSender(this.name);
msg.setReceiver(t2);

pubMsg5.sender=sender;
t2_YouMayPass_pub.publish(pubMsg5);
;
isWaiting2 = false;

}

}
else {
signal2 = false;
if (isWaiting1) {
signal1 = true;
Message msg = new Message();
msg.setMsgName("YouMayPass");
msg.setSender(this.name);
msg.setReceiver(t1);

pubMsg7.sender=sender;
t1_YouMayPass_pub.publish(pubMsg7);
;
isWaiting1 = false;

}

}


}

}

