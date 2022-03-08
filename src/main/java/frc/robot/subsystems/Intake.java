package frc.robot.subsystems;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.controls.OI;

/**
 * The Intake class is responsible for controlling the intake system of the
 * robot this includes both the piece that brings the ball in, the pice that
 * moves it to th.
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
        intake1.clearFaults();
        intake2.clearFaults();

        intake1.setSmartCurrentLimit(smartCurrentLimit);
        intake2.setSmartCurrentLimit(smartCurrentLimit);

        intake2.setInverted(true);
    }

    public void smartdashboard() {
        SmartDashboard.putNumber("Intake/Mecanum/Current", intake1.getOutputCurrent());
        SmartDashboard.putNumber("Intake/LowerBelt/Current", intake2.getOutputCurrent());

        SmartDashboard.putNumber("Intake/Mecanum/Voltage", intake1.getBusVoltage());
        SmartDashboard.putNumber("Intake/LowerBelt/Voltage", intake2.getBusVoltage());

        SmartDashboard.putNumber("Intake/Mecanum/Speed", intake1.getEncoder().getVelocity());
        SmartDashboard.putNumber("Intake/LowerBelt/Speed", intake2.getEncoder().getVelocity());
    }

    /**
     * Set intake motor speed to 0.
     */
    public void stopMotors() {
        intake1.set(0);
        intake2.set(0);
    }

    /**
     * Set intake motor speeds.
     * 
     * @param motorSpeed Speed of intake motors from -1 to 1.
     */
    public void runLowerMotors(double motorSpeed) {

        intake1.set(motorSpeed);
        intake2.set(motorSpeed);
    }

    /**
     * Use buttons to activate intake system.
     */
    public void intakeButtonControl() {
        if (OI.intakeButton.isPressed()) {
            runLowerMotors(0.45);
        } else if (OI.spitoutButton.isPressed()) {
            runLowerMotors(-0.45);
        } else {
            stopMotors();
        } 
    }
}
