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
        //Activates shooter when trigger pulled
       // System.out.println(OI.COLOR_STICK.getY());
      //  System.out.println(OI.SHOOT_BUTTON.isHold());
        if(OI.SHOOT_BUTTON_LOW.isHold() ) {//If the button to turn on the shooter is held and the far left stick is pressed
        spin2.set(.453); //.435 Good at ~5 feet
        spin1.set(-.453); //.435
      //  System.out.println("Shoot Button Pressed");
        } else {
     //       System.out.println(-(OI.COLOR_STICK.getY()));
           spin1.stopMotor();
           spin2.stopMotor();
       }
    } 
}
