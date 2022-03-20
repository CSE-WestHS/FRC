package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.controls.OI;

public class Elevator {
    // creates 1 elevator motor, which are CANSparkMax Neo brushless
    private static CANSparkMax elevatorMotor = new CANSparkMax(5, MotorType.kBrushless);
    private static int smartCurrentLimit = 40;
    //speed the elevator motors will run at
    private static double speed = 0.65;

    public Elevator() {
        elevatorMotor.clearFaults();
         //clearFaults clears any sticky faults that may occur in the CANSparkMaxes

        /*sticky faults are errors in the CANSparkMax hardware 
        not updating after the error is resolved
        */
        elevatorMotor.setSmartCurrentLimit(smartCurrentLimit);
        //turns off wheels
        motorPower(0);
    }
//puts information on the SmartDashboard 
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
    public void motorPower(double power) {
        elevatorMotor.set(power);
    }

    /**
     * Control elevator motors using joystick input.
     */
    public void elevatorButtonControl() {
        if (OI.elevatorButton.isPressedEvent()) {
            motorPower(speed);
        } else if (OI.elevatorSpitoutbutton.isPressedEvent()) {
            motorPower(-speed);
        } else {
            motorPower(0);
        }
    }
}
