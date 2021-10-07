package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;

import frc.robot.controls.OI;

public class ConveyorSystem
{
    //initiates motor controls
    // private static final SpeedController spin1 = new PWMVictorSPX(5);
    private static final SpeedController conveyMotor = new PWMVictorSPX(6);
    private static final SpeedController conveyMotor = new PWMVictorSPX(7);
 
 public static void update()
{
    if (OI.BALLPUSH.isHold()){
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
    public static void update()
    {
        //actives shooter when trigger pulled
        if(OI.BALL_BUTTON.isHold()) 
        {
            // turn on conveyor motor
            conveyMotor.set(1);
            System.out.println("Pushed conveyor button");
        }  
        else if (OI.BALLREVERSE_BUTTON.isHold())
        {
        conveyMotor.set(-1);
        }
        else
        {
            // dont turn on motor
            conveyMotor.set(0);
            System.out.println("not pressed");
        }
    }
}

