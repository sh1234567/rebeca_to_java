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
msg.setSender();
msg.setReceiver();

pubMsg9.sender=sender;
self_Passed_pub.publish(pubMsg9);
;


}

public void Passed ()
{
onTheBridge = false;
Message msg = new Message();
msg.setMsgName("Leave");
msg.setSender();
msg.setReceiver();

pubMsg11.sender=sender;
controller_Leave_pub.publish(pubMsg11);
;
Message msg = new Message();
msg.setMsgName("ReachBridge");
msg.setSender();
msg.setReceiver();

pubMsg13.sender=sender;
self_ReachBridge_pub.publish(pubMsg13);
;


}

public void ReachBridge ()
{
Message msg = new Message();
msg.setMsgName("Arrive");
msg.setSender();
msg.setReceiver();

pubMsg15.sender=sender;
controller_Arrive_pub.publish(pubMsg15);
;


}

}

