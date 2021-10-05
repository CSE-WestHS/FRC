package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMViotorSPX; 
import edu.wpi.first.wpilibj.SpeedControler; 

import frc.robot.controls.OI; 

public class ShooterSystem
{
    //initates motor controls 
    private static final SpeedControler spin1 = new PWMVictorSPX(5); //bottom wheels 
    private static final SpeedContorler spin2 = new PWMVictorSPX(4); //top wheels 

    public static void update()
    {
        //Activates shooter when trigger pulled
        if(OI.SHOOT_BUTTON.isHold() & OI.FARLEFT_STICK.getThrottle()>0) {//If the button to turn on the shooter is held and the far left stick is pressed
        spin2.set((OI.FARLEFT_STICK.getThrottle())); //.435 Good at ~5 feet
        spin1.set(-(OI.FARLEFT.STICK.getThrottle())); //.435
        System.out.println("Shoot Button Pressed");
        } else {
            System.out.println(-(OI.FARLEFT_STICK.getThrottle()));
            spin1.stopMotor();
            spin2.stopMotor();
        }
    } 
}
