package frc.robot.subsystems;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.controls.OI;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * The Intake class is responsible for controlling the intake system of the
 * robot.
 */
public class Intake {
    private static CANSparkMax intake1 = new CANSparkMax(7, MotorType.kBrushless);
    private static CANSparkMax Elevator = new CANSparkMax(5, MotorType.kBrushless);
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
        Elevator.clearFaults();

        intake1.setSmartCurrentLimit(smartCurrentLimit);
        intake2.setSmartCurrentLimit(smartCurrentLimit);
        Elevator.setSmartCurrentLimit(smartCurrentLimit);

        intake2.setInverted(true);
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
    public void runMotors(double motorSpeed) {
        final double xSpeed = -m_linearVelocityLimiter.calculate(motorSpeed) * 2;

        intake1.set(motorSpeed);
        intake2.set(xSpeed);
    }

    /**
     * Use buttons to activate intake system.
     */
    public void buttonIntake() {
        SmartDashboard.putNumber("Intake1/Voltage", intake1.getBusVoltage());
        SmartDashboard.putNumber("Intake1/Temperature", intake1.getMotorTemperature());
        SmartDashboard.putNumber("Intake1/Output", intake1.getAppliedOutput());

        SmartDashboard.putNumber("Intake2/Voltage", intake2.getBusVoltage());
        SmartDashboard.putNumber("Intake2/Temperature", intake2.getMotorTemperature());
        SmartDashboard.putNumber("Intake2/Output", intake2.getAppliedOutput());

        if (OI.intakeButton.isPressed()) {
            runMotors(0.45);
        } else if (OI.spitoutButton.isPressed()) {
            runMotors(-0.45);
        } else {
            stopMotors();
        }
        if(OI.ElevatorButton.isPressed()){
            Elevator.set(0.45);
        }
            else{
                Elevator.set(0);
            }
        }
    }
