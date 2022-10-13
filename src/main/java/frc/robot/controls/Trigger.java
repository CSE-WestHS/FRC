package frc.robot.controls;
import frc.robot.controls.OperatorInput;
public class Trigger {
String axisName;
    public Trigger(String newAxisName){
        axisName = newAxisName;
    }
    public Boolean getIsPressed(){
        
        if (axisName.equalsIgnoreCase("rightTrigger")){
            

        }
        return false;
    }
}
