#include <TrainController\BridgeController.h>

int main(int argc, char** argv){
	ROS_INFO("BridgeController node started");
	ros::init(argc, argv, "BridgeController_node");
	ros::NodeHandle nh("~");
	std::string sender;
	 nh.getParam("sender", sender);
	BridgeController _bridgecontroller(sender);
}


BridgeController::BridgeController(std::string _sender){
Arrive_sub = n.subscribe("BridgeController/Arrive", 30, &BridgeController::ArriveCallback, this);
Leave_sub = n.subscribe("BridgeController/Leave", 30, &BridgeController::LeaveCallback, this);
t2_YouMayPass_pub = n.advertise<TrainController::YouMayPass>("t2/YouMayPass", 30);
t1_YouMayPass_pub = n.advertise<TrainController::YouMayPass>("t1/YouMayPass", 30);
sender = _sender;
signal1 = false;
signal2 = false;
isWaiting1 = false;
isWaiting2 = false;

ros::spin();
}

void BridgeController::ArriveCallback(const TrainController::Arrive & thisMsg){
if (sender == t1) {if (signal2 == false) {signal1 = true;
TrainController::YouMayPass pubMsg4;
pubMsg4.sender=sender;
t1_YouMayPass_pub.publish(pubMsg4);
;

}
else {
isWaiting1 = true;

};

}
else {
if (signal1 == false) {signal2 = true;
TrainController::YouMayPass pubMsg5;
pubMsg5.sender=sender;
t2_YouMayPass_pub.publish(pubMsg5);
;

}
else {
isWaiting2 = true;

};

};

}

void BridgeController::LeaveCallback(const TrainController::Leave & thisMsg){
if (sender == t1) {signal1 = false;
if (isWaiting2) {signal2 = true;
TrainController::YouMayPass pubMsg6;
pubMsg6.sender=sender;
t2_YouMayPass_pub.publish(pubMsg6);
;
isWaiting2 = false;

};

}
else {
signal2 = false;
if (isWaiting1) {signal1 = true;
TrainController::YouMayPass pubMsg7;
pubMsg7.sender=sender;
t1_YouMayPass_pub.publish(pubMsg7);
;
isWaiting1 = false;

};

};

}

