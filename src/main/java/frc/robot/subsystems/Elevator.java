package frc.robot.subsystems;

//the imports are what this class needs from other classes to function properly
//these can come from other parts of the robot or the internet
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.controls.OperatorInput;

/**
 * The Elevator Class is responsible for controlling the elevator motors
 * which move the balls from the intake to the shooter
 */
public class Elevator {
    // creates 1 elevator motor, which is a CANSparkMax Neo brushless
    private static CANSparkMax elevatorMotor = new CANSparkMax(5, MotorType.kBrushless);
    private static int smartCurrentLimit = 40;
    // speed the elevator motors will run at
    private static double speed = 0.65;

    /**
     * constructor for Elevator class
     */
    public Elevator() {
        elevatorMotor.clearFaults();
        // clearFaults clears any sticky faults that may occur in the CANSparkMaxes

        /*
         * sticky faults are errors in the CANSparkMax hardware
         * not updating after the error is resolved
         */
        elevatorMotor.setSmartCurrentLimit(smartCurrentLimit);
        /*
         * turn off the wheels at the start
         * this is done to prevent the motors from moving if they
         * were when the robot was turned off
         */
        motorPower(0);
    }

    /**
     * puts information on the SmartDashboard
     */
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
        // if this button is pressed, run the motors forewards (ball goes up)
        if (OperatorInput.elevatorButton.isPressed() || (OperatorInput.controller.getLeftTriggerAxis() >= 0.75)) {
            motorPower(speed);

        }
        // if this button is pressed, run the motors backwards (ball goes down)
        else if (OperatorInput.elevatorSpitoutbutton.isPressed()) {
            motorPower(-speed);

        }
        // if neither buttons are pressed, turn off the motors
        else {
            motorPower(0);
        }
    }
}
