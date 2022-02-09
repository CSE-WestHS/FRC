package frc.robot.subsystems;

import frc.robot.controls.Encoder;
import frc.robot.controls.OI;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
    /*
     * this is a proof of concept. we will most likely
     * change speeds, the number of motors, types of motors, etc
     * when the build is done enough to where we can know what
     * we need to change, we will update the code to represent
     * the motors used on the conveyor system
     */
    // these are the motors for the intake
    // Update when we finalize what motors need to be used
    public static CANSparkMax intakeMotor = new CANSparkMax(8, MotorType.kBrushless);;
    public static CANSparkMax sideFeed = new CANSparkMax(7, MotorType.kBrushless);;
    public static CANSparkMax upFeed = new CANSparkMax(5, MotorType.kBrushless);;
    public static CANSparkMax upFeed2 = new CANSparkMax(6, MotorType.kBrushless);;
    public final Encoder m_sideEncoder = new Encoder(sideFeed, 0.785);
    public final Encoder m_upEncoder = new Encoder(upFeed, 0.785);
    public Intake() {
        intakeMotor.set(0);
        sideFeed.set(0);
        upFeed.set(0);
        upFeed2.set(0);
        upFeed2.setInverted(true);
        intakeMotor.restoreFactoryDefaults();
        sideFeed.restoreFactoryDefaults();
        upFeed.restoreFactoryDefaults();
        upFeed2.restoreFactoryDefaults();
    }

    // turns off all motors
    public void stopMotors() {
        intakeMotor.set(0);
        sideFeed.set(0);
        upFeed.set(0);
        upFeed2.set(0);
    }

    // turns on all motors to a custom value
    public void runMotors(double motorSpeed) {
        intakeMotor.set(motorSpeed);
        sideFeed.set(motorSpeed);
        upFeed.set(motorSpeed);
        upFeed2.set(motorSpeed);
    }

    // turns on intakeMotor and sideFeed to 1 value, and upFeeds 1 and 2 to another
    // speed
    // useful if you just want to turn on one set of motors
    public void runMotors(double intakeSpeed, double upFeedSpeed) {
        intakeMotor.set(intakeSpeed);
        sideFeed.set(intakeSpeed);
        upFeed.set(upFeedSpeed);
        upFeed2.set(upFeedSpeed);
    }

    public static void buttonIntake() {
        if (OI.INTAKE_BUTTON.isHold()) { // activates the intake and conveyor motors when pressed
            /*
             * these are mock up speeds. Change these when
             * we know what motors and speeds and such we
             * need to use
             */
            intakeMotor.set(0.45);
            sideFeed.set(0.45);
            upFeed.set(0.45);
            upFeed2.set(-0.45);

        } else if (OI.SPITOUT_BUTTON.isHold())
        // puts the conveyor and intake motors on reverse
        // this spits out the ball(s)
        {
            /*
             * these are mock up speeds. Change these when
             * we know what motors and speeds and such we
             * need to use
             */
            intakeMotor.set(-0.45);
            sideFeed.set(-0.45);
            upFeed.set(-0.45);
            upFeed2.set(0.45);
        } else
        // this is the default state where all the motors are off
        {
            intakeMotor.set(0);
            sideFeed.set(0);
            upFeed.set(0);
            upFeed2.set(0);
        }
    }
}
