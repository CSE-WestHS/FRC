package frc.robot.subsystems;

//the imports are what this class needs from other classes to function properly
//these can come from other parts of the robot or the internet
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.controls.OI;

/**
 * The Intake class is responsible for controlling the intake system of the
 * robot this includes both the piece that brings the ball in
 * and the piece that moves the ball towards the elevator.
 */
public class Intake {
    private static CANSparkMax intake1 = new CANSparkMax(7, MotorType.kBrushless);
    private static CANSparkMax intake2 = new CANSparkMax(9, MotorType.kBrushless);
    private static int smartCurrentLimit = 40;

    // Slew rate limiters to make inputs more gentle; 1/3 sec from 0 to 1.
    private final SlewRateLimiter m_linearVelocityLimiter = new SlewRateLimiter(1 / 3.0);

    /**
     * Constructor for the intake system.
     */

    public Intake() {
        /*
         * sticky faults are errors in the CANSparkMax hardware
         * not updating after the error is resolved
         */
        // clearFaults clears any sticky faults that may occur in the CANSparkMaxes
        intake1.clearFaults();
        intake2.clearFaults();

        intake1.setSmartCurrentLimit(smartCurrentLimit);
        intake2.setSmartCurrentLimit(smartCurrentLimit);
        // invert make motors move in the opposite direction
        intake2.setInverted(true);
        /*
         * turn off the wheels at the start
         * this is done to prevent the motors from moving if they
         * were when the robot was turned off
         */
        stopMotors();
    }

    /**
     * puts information on the SmartDashboard
     */
    public void smartdashboard() {
        SmartDashboard.putNumber("Intake/Mecanum/Current", intake1.getOutputCurrent());
        SmartDashboard.putNumber("Intake/LowerBelt/Current", intake2.getOutputCurrent());

        SmartDashboard.putNumber("Intake/Mecanum/Voltage", intake1.getBusVoltage());
        SmartDashboard.putNumber("Intake/LowerBelt/Voltage", intake2.getBusVoltage());

        SmartDashboard.putNumber("Intake/Mecanum/Speed", intake1.getEncoder().getVelocity());
        SmartDashboard.putNumber("Intake/LowerBelt/Speed", intake2.getEncoder().getVelocity());
    }

    /**
     * Stop all intake motors.
     */
    public void stopMotors() {
        intake1.set(0);
        intake2.set(0);
    }

    /**
     * Set intake motor speeds.
     * 
     * @param motorSpeed Speed of intake motors from -1 to 1.
     *                   0 is no speed,
     *                   1 is full speed forewards,
     *                   -1 is full speed backwards.
     */
    public void runMotors(double motorSpeed) {
        final double xSpeed = -m_linearVelocityLimiter.calculate(motorSpeed) * 2;

        intake1.set(motorSpeed);
        intake2.set(xSpeed);
    }

    /**
     * Use buttons to activate intake system.
     */
    public void intakeButtonControl() {
        // if this button is pressed, run motors forewards (ball moves forewards)
        if (OI.intakeButton.isPressed()) {
            runMotors(0.45);

        }
        // if this button is pressed, run motors backwards(ball moves backwards)
        else if (OI.spitoutButton.isPressed()) {
            runMotors(-0.45);
        }
        // if neither button is pressed, stop the motors
        else {
            stopMotors();
        }
    }
}
