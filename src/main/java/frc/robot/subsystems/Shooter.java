package frc.robot.subsystems;

import frc.robot.controls.OI;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/*
This is a skeleton of the shooter system
We plan to update this once we get the limelight
to work. We will put in calculations for distance
 to power for the shooting. We will also add and
 rename motors as needed
*/
public class Shooter {
    public static CANSparkMax shootMotor1 = new CANSparkMax(5, MotorType.kBrushless);;
    public static CANSparkMax shootMotor2 = new CANSparkMax(6, MotorType.kBrushless);;
    public static double limeToPower = 0.5;

    public Shooter() {
        shootMotor1.setInverted(true);
        shootMotor1.set(0);
        shootMotor2.set(0);
    }

    /**
     * Activates motors at a set power
     * 
     * @param power = -100 to 100
     */
    public static void setPower(double power) {
        // Distance will be a numebr given to us by the limelight
        // the equation given will be updated to make parameters from -1 to 1
        // The mins and maxes will not be -1 and 1, this is too much power on the motors

        double motorSpeed = power / 100.0;
        if (OI.SHOOT_BUTTON.isHold()) {
            shootMotor1.set(motorSpeed);
            shootMotor2.set(motorSpeed);
        } else {
            shootMotor1.set(0);
            shootMotor2.set(0);
        }
    }

    public static void limelightAdjust() {
        // TODO be implemennted after limelight is implemented
    }
}
