public class Train{
private String name;
private boolean onTheBridge;
public Train(String n) {
	this.name = n;
}
public void YouMayPass ()
{
onTheBridge = true;
Message msg = new Message();
msg.setMsgName("Passed");
msg.setSender(this.name);
msg.setReceiver(this.name);

pubMsg9.sender=sender;
self_Passed_pub.publish(pubMsg9);
;


}

public void Passed ()
{
onTheBridge = false;
Message msg = new Message();
msg.setMsgName("Leave");
msg.setSender(this.name);
msg.setReceiver(controller);

pubMsg11.sender=sender;
controller_Leave_pub.publish(pubMsg11);
;
Message msg = new Message();
msg.setMsgName("ReachBridge");
msg.setSender(this.name);
msg.setReceiver(this.name);

pubMsg13.sender=sender;
self_ReachBridge_pub.publish(pubMsg13);
;


}

public void ReachBridge ()
{
Message msg = new Message();
msg.setMsgName("Arrive");
msg.setSender(this.name);
msg.setReceiver(controller);

pubMsg15.sender=sender;
controller_Arrive_pub.publish(pubMsg15);
;


}

}

