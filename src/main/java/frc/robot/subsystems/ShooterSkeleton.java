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
public class ShooterSkeleton {
    public static CANSparkMax shootMotor1 = new CANSparkMax(5, MotorType.kBrushless);;
    public static CANSparkMax shootMotor2 = new CANSparkMax(6, MotorType.kBrushless);;

    public ShooterSkeleton() {
        shootMotor1.set(0);
        shootMotor2.set(0);
    }

    public static void update() {
        if (OI.SHOOT_BUTTON.isHold()) {
            shootMotor1.set(50);
            shootMotor2.set(50);
        } else {
            shootMotor1.set(0);
            shootMotor2.set(0);
        }
    }
}
