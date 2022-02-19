package frc.robot.subsystems;

import frc.robot.controls.OI;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Intake {
    /*
     * this is a proof of concept. we will most likely
     * change speeds, the number of motors, types of motors, etc
     * when the build is done enough to where we can know what
     * we need to change, we will update the code to represent
     * the motors used on the conveyor system
     */
    // these are the motors for the intake
    // Update when we finalize what motors need to be used
    public static CANSparkMax upFeed = new CANSparkMax(7, MotorType.kBrushless);
    public static CANSparkMax upFeed2 = new CANSparkMax(9, MotorType.kBrushless);

    public Intake() {
        upFeed.clearFaults();
        upFeed2.clearFaults();

        upFeed.set(0);
        upFeed2.set(0);
        upFeed2.setInverted(true);
    }

    // turns off all motors
    public void stopMotors() {
        upFeed.set(0);
        upFeed2.set(0);
    }

    // turns on all motors to a custom value
    public void runMotors(double motorSpeed) {

        upFeed.set(motorSpeed);
        upFeed2.set(motorSpeed);
    }

    public void buttonIntake() {
        if (OI.UPFEED_BUTTON.isHold()) { // activates the upfeed motors when pressed
            /*
             * these are mock up speeds. Change these when
             * we know what motors and speeds and such we
             * need to use
             */

            upFeed.set(0.45);
            upFeed2.set(-0.45);

        } else if (OI.SPITOUT_BUTTON.isHold())
        // puts the upfeed motors on reverse
        // this spits out the ball(s)
        {
            /*
             * these are mock up speeds. Change these when
             * we know what motors and speeds and such we
             * need to use
             */

            upFeed.set(-0.45);
            upFeed2.set(0.45);
        } else
        // this is the default state where all the motors are off
        {

            upFeed.set(0);
            upFeed2.set(0);
        }
    }
}
