cmake_minimum_required(VERSION 2.8.3)
project(TrainController)
find_package(catkin REQUIRED COMPONENTS
  roscpp
  rospy
  std_msgs
  message_generation
${MESSAGE_DEPENDENCIES})

add_message_files(
FILES
Arrive.msg
Leave.msg
YouMayPass.msg
Passed.msg
ReachBridge.msg
)

generate_messages(
DEPENDENCIES
std_msgs
)

catkin_package(
CATKIN_DEPENDS roscpp rospy std_msgs message_runtime
)

include_directories(
include
${catkin_INCLUDE_DIRS}
)

add_executable(BridgeController_node src/BridgeController.cpp)
add_dependencies(BridgeController_node TrainController_generate_messages_cpp)
target_link_libraries(BridgeController_node ${catkin_LIBRARIES})
add_executable(Train_node src/Train.cpp)
add_dependencies(Train_node TrainController_generate_messages_cpp)
target_link_libraries(Train_node ${catkin_LIBRARIES})
