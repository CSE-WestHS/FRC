package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;

import frc.robot.controls.OI;

public class ConveyorSystem
{
    //initiates motor controls
    // private static final SpeedController spin1 = new PWMVictorSPX(5);
    private static final SpeedController conveyMotor = new PWMVictorSPX(6);
    private static final SpeedController motor = new PWMVictorSPX(7);
 
 public static void update()
    {
    if (OI.BALL_BUTTON.isHold())
        {

    motor.set(1);
         }
    else if(OI.BALLREVERSE_BUTTON.isHold())
     {
      motor.set(-.5);
       }
    else
       {
        motor.set(0);
         }
         
         //activates shooter when trigger pulled
        if(OI.BALLREVERSE_BUTTON.isHold())
        {
             //turn on conveyor motor
             conveyMotor.set(1);

        } 
        else if(OI.BALLREVERSE_BUTTON.isHold())
         {
             conveyMotor.set(-1);
         } else {
             //dont turn motor on
             conveyMotor.set(0);
         }
    }
}