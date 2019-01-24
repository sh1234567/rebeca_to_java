#include <TrainController\Train.h>

int main(int argc, char** argv){
	ROS_INFO("Train node started");
	ros::init(argc, argv, "Train_node");
	ros::NodeHandle nh("~");
	std::string sender;
	 nh.getParam("sender", sender);
	Train _train(sender);
}


Train::Train(std::string _sender){
YouMayPass_sub = n.subscribe("Train/YouMayPass", 30, &Train::YouMayPassCallback, this);
Passed_sub = n.subscribe("Train/Passed", 30, &Train::PassedCallback, this);
ReachBridge_sub = n.subscribe("Train/ReachBridge", 30, &Train::ReachBridgeCallback, this);
self_Passed_pub = n.advertise<TrainController::Passed>("Train/Passed", 30);
controller_Arrive_pub = n.advertise<TrainController::Arrive>("controller/Arrive", 30);
self_ReachBridge_pub = n.advertise<TrainController::ReachBridge>("Train/ReachBridge", 30);
controller_Leave_pub = n.advertise<TrainController::Leave>("controller/Leave", 30);
sender = _sender;
onTheBridge = false;
TrainController::Passed pubMsg13;
pubMsg13.sender=sender;
self_Passed_pub.publish(pubMsg13);
;

ros::spin();
}

void Train::YouMayPassCallback(const TrainController::YouMayPass & thisMsg){
onTheBridge = true;
TrainController::Passed pubMsg14;
pubMsg14.sender=sender;
self_Passed_pub.publish(pubMsg14);
;

}

void Train::PassedCallback(const TrainController::Passed & thisMsg){
onTheBridge = false;
TrainController::Leave pubMsg15;
pubMsg15.sender=sender;
controller_Leave_pub.publish(pubMsg15);
;
TrainController::ReachBridge pubMsg16;
pubMsg16.sender=sender;
self_ReachBridge_pub.publish(pubMsg16);
;

}

void Train::ReachBridgeCallback(const TrainController::ReachBridge & thisMsg){
TrainController::Arrive pubMsg17;
pubMsg17.sender=sender;
controller_Arrive_pub.publish(pubMsg17);
;

}

