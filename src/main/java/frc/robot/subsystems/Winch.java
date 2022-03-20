package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.controls.OI;

public class Winch {
    private static CANSparkMax winchMotor = new CANSparkMax(8, MotorType.kBrushless);
    private int smartCurrentLimit = 40;

    public Winch() {
        winchMotor.clearFaults();
        // clearFaults clears any sticky faults that may occur in the CANSparkMaxes

        /*
         * sticky faults are errors in the CANSparkMax hardware
         * not updating after the error is resolved
         */
        winchMotor.setSmartCurrentLimit(smartCurrentLimit);
        // turns off wheels
        winchMotor.set(0);
    }

    public void smartdashboard() {
        SmartDashboard.putNumber("Elevator/Voltage", winchMotor.getBusVoltage());
        SmartDashboard.putNumber("Elevator/Temperature", winchMotor.getMotorTemperature());
        SmartDashboard.putNumber("Elevator/Output", winchMotor.getAppliedOutput());
    }

    public void buttonWinch() {
        if (OI.liftButton.isPressed()) {
            winchMotor.set(0.5);
        } else {
            winchMotor.set(0);
        }
    }
}
