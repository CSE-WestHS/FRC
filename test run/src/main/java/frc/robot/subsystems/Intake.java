package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.util.Debug;
import frc.robot.controls.OI;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class BrosiusIsAMotor {
    private CANSparkMax broMo;
    private CANSparkMax broMo2;

    public BrosiusIsAMotor() {
        broMo = new CANSparkMax(2, MotorType.kBrushless);
        broMo2 = new CANSparkMax(3, MotorType.kBrushless);
       
        broMo.set(0);
        broMo2.set(0);

        broMo.restoreFactoryDefaults();
        broMo2.restoreFactoryDefaults();
    }

    public void update() {
        //activates intake when pressed
        if (OI.INTAKE_BUTTON.isHold()) {
            broMo.set(.15);
            broMo2.set(.15);
            System.out.println("intake button pressed");
        } else {
            broMo.set(0);
            broMo2.set(0);
        }
    }
}