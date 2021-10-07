package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;

import frc.robot.controls.OI;

public class ConveyorSystem
{
    //initiates motor controls
    // private static final SpeedController spin1 = new PWMVictorSPX(5);
    private static final SpeedController conveyMotor = new PWMVictorSPX(6);
 
 public static void update()
{
    if (OI.BALL_BUTTON.isHold()){
        System.out.println("pushed flapper button");
conveyMotor.set(1);
    }
    else if(OI.BALLREVERSE.isHold())
    {
        conveyMotor.set(-.5);
    }
    else{
        conveyMotor.set(0);
    }
}