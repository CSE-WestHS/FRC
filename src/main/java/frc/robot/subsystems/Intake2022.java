package frc.robot.subsystems;

import frc.robot.controls.OI;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
public class Intake2022 {
 // create motors in the class
    //these are the motors for the intake 
    //Update when we finalize what motors need to be used
 public static CANSparkMax intakeMotor = new CANSparkMax(2, MotorType.kBrushless);;
 public static CANSparkMax conveyMotor1 = new CANSparkMax(3, MotorType.kBrushless);;
 public static CANSparkMax conveyMotor2 = new CANSparkMax(2, MotorType.kBrushless);;
 public Intake2022() {
    intakeMotor.set(0);
    conveyMotor1.set(0);
    conveyMotor2.set(0);

    intakeMotor.restoreFactoryDefaults();
    conveyMotor1.restoreFactoryDefaults();
    conveyMotor2.restoreFactoryDefaults();
}
 //make motors move on a press of a button
    //assign button(s)
    public static void update(){
if (OI.INTAKE2022_BUTTON.isHold())
{
        /*these are mock up speeds. Change these when 
    we know what motors and speeds and such we
    need to use
    */
    intakeMotor.set(0.45);
    conveyMotor1.set(0.45);
    conveyMotor2.set(0.45);
}
else if (OI.SPITOUT_BUTTON.isHold())
{
    /*these are mock up speeds. Change these when 
    we know what motors and speeds and such we
    need to use
    */
    intakeMotor.set(-0.45);
    conveyMotor1.set(-0.45);
    conveyMotor2.set(-0.45);
}
else
{
    intakeMotor.set(0);
    conveyMotor1.set(0);
    conveyMotor2.set(0);
}
    }

}
