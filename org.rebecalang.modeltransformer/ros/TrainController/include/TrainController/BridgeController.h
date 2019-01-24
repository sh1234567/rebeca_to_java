#include <ros/ros.h>
#include <TrainController\Arrive.h>
#include <TrainController\Leave.h>
#include <TrainController\YouMayPass.h>
#include <TrainController\Passed.h>
#include <TrainController\ReachBridge.h>
#include <string>
#include <bitset>
typedef std::bitset<8> byte;

class BridgeController{
public:
BridgeController(std::string _sender);
void ArriveCallback(const TrainController::Arrive & thisMsg);
void LeaveCallback(const TrainController::Leave & thisMsg);
private:
/*ROS Fields*/
ros::NodeHandle n;
ros::Publisher t2_YouMayPass_pub;
ros::Publisher t1_YouMayPass_pub;
ros::Subscriber Arrive_sub;
ros::Subscriber Leave_sub;
/* Reactive Class State Variables as Private Fields */
bool isWaiting1bool isWaiting2bool signal1bool signal2std::string sender;

/* the following fields needed to make the automatic transformation compilable */
std::string t1 = "t1";
std::string t2 = "t2";
};