package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.util.Debug;
import frc.robot.controls.OI;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Intake {
    private CANSparkMax inMotor1;
    private CANSparkMax inMotor2;

    public Intake() {
        inMotor1 = new CANSparkMax(2, CANSparkMaxLowLevel.MotorType.kBrushless);
        inMotor2 = new CANSparkMax(3, CANSparkMaxLowLevel.MotorType.kBrushless);
       
        inMotor1.set(0);
        inMotor2.set(0);

        inMotor1.restoreFactoryDefaults();
        inMotor2.restoreFactoryDefaults();
    }

    public void update() {
        //activates intake when pressed
        if (OI.INTAKE_BUTTON.isHold()) {
            inMotor1.set(.15);
            inMotor2.set(.15);
            System.out.println("intake button pressed");
        } else {
            inMotor1.set(0);
            inMotor2.set(0);
        }
    }
}