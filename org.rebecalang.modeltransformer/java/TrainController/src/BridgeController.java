public class BridgeController{
private boolean isWaiting1;
private boolean isWaiting2;
private boolean signal1;
private boolean signal2;
public void Arrive ()
{
if (sender == t1) {
if (signal2 == false) {
signal1 = true;
;

}
else {
isWaiting1 = true;

}

}
else {
if (signal1 == false) {
signal2 = true;
;

}
else {
isWaiting2 = true;

}

}


}

public void Leave ()
{
if (sender == t1) {
signal1 = false;
if (isWaiting2) {
signal2 = true;
;
isWaiting2 = false;

}

}
else {
signal2 = false;
if (isWaiting1) {
signal1 = true;
;
isWaiting1 = false;

}

}


}

}

