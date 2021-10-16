package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX; 
import edu.wpi.first.wpilibj.SpeedController; 

import frc.robot.controls.OI; 

public class ShooterSystem
{
    //initates motor controls 
    private static final SpeedController spin1 = new PWMVictorSPX(5); //bottom wheels 
    private static final SpeedController spin2 = new PWMVictorSPX(4); //top wheels 

    public static void update()
    {
        

        if(OI.SHOOT_BUTTON_LOW.isHold()) 
        {
            //Activates shooter at low power (button 4 on left stick)
        spin2.set(.453); //.435 Good at ~5 feet
        spin1.set(-.453); //.435

        }
        else if(OI.SHOOT_BUTTON_HIGH.isHold())
        {
          //Activates shooter at high power (button 3 on right stick)
          spin2.set(.666);
          spin1.set(-.666);
        }
         else 
        {

           spin1.stopMotor();
           spin2.stopMotor();
        }
    } 
}
