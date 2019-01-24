#include <ros/ros.h>
#include <TrainController\Arrive.h>
#include <TrainController\Leave.h>
#include <TrainController\YouMayPass.h>
#include <TrainController\Passed.h>
#include <TrainController\ReachBridge.h>
#include <string>
#include <bitset>
typedef std::bitset<8> byte;

class Train{
public:
Train(std::string _sender);
void YouMayPassCallback(const TrainController::YouMayPass & thisMsg);
void PassedCallback(const TrainController::Passed & thisMsg);
void ReachBridgeCallback(const TrainController::ReachBridge & thisMsg);
private:
/*ROS Fields*/
ros::NodeHandle n;
ros::Publisher self_Passed_pub;
ros::Publisher controller_Arrive_pub;
ros::Publisher self_ReachBridge_pub;
ros::Publisher controller_Leave_pub;
ros::Subscriber YouMayPass_sub;
ros::Subscriber Passed_sub;
ros::Subscriber ReachBridge_sub;
/* Reactive Class State Variables as Private Fields */
bool onTheBridgestd::string sender;

/* the following fields needed to make the automatic transformation compilable */
std::string controller = "controller";
};