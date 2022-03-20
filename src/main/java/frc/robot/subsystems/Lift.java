package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.controls.OI;

public class Lift {
    private static CANSparkMax liftMotor = new CANSparkMax(8, MotorType.kBrushless);
    private int smartCurrentLimit = 40;

    public Lift() {
        liftMotor.clearFaults();
        // clearFaults clears any sticky faults that may occur in the CANSparkMaxes

        /*
         * sticky faults are errors in the CANSparkMax hardware
         * not updating after the error is resolved
         */
        liftMotor.setSmartCurrentLimit(smartCurrentLimit);
        // turns off wheels
        liftMotor.set(0);
    }

    public void smartdashboard() {
        SmartDashboard.putNumber("Elevator/Voltage", liftMotor.getBusVoltage());
        SmartDashboard.putNumber("Elevator/Temperature", liftMotor.getMotorTemperature());
        SmartDashboard.putNumber("Elevator/Output", liftMotor.getAppliedOutput());
    }

    public void buttonLift() {
        if (OI.liftButton.isPressedEvent()) {
            liftMotor.set(0.5);
        } else {
            liftMotor.set(0);
        }
    }
}
