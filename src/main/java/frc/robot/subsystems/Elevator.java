package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.controls.OI;

public class Elevator {
    private static CANSparkMax elevatorMotor = new CANSparkMax(5, MotorType.kBrushless);
    private static int smartCurrentLimit = 40;
    private static double speed = 0.65;

    public Elevator() {
        elevatorMotor.clearFaults();
        elevatorMotor.setSmartCurrentLimit(smartCurrentLimit);
    }

    public void smartdashboard() {
        SmartDashboard.putNumber("Elevator/Voltage", elevatorMotor.getBusVoltage());
        SmartDashboard.putNumber("Elevator/Temperature", elevatorMotor.getMotorTemperature());
        SmartDashboard.putNumber("Elevator/Output", elevatorMotor.getAppliedOutput());
    }

    /**
     * Set elevator motor speed.
     * 
     * @param power Speed of elevator motors from -1 to 1.
     */
    public void motorPower(double m_power) {
        elevatorMotor.set(m_power);
    }

    /**
     * Control elevator motors using joystick input.
     */
    public void elevatorButtonControl() {
        if (OI.elevatorButton.isPressed()) {
            motorPower(0.65);
        } else if (OI.elevatorSpitoutbutton.isPressed()) {
            motorPower(-speed);
        } else {
            motorPower(0);
        }
    }
}
