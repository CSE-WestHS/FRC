package frc.robot.subsystems;

//the imports are what this class needs from other classes to function properly
//these can come from other parts of the robot or the internet
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.controls.OI;

/**
 * The DriveSystem class is responsible for controlling the drivetrain of the
 * robot.
 */
public class DriveSystem {
    public static final double kMaxSpeed = 1.0; // meters per second
    public static final double kMaxAngularSpeed = 2 * Math.PI; // one rotation per second
    public boolean autonomousFlag = false; // used to check if motors are currently being used
    private static int smartCurrentLimit = 40; // amps, current limit for the smart motor controllers
    // creates 2 left side motors, which are CANSparkMax Neo brushless
    public final CANSparkMax m_frontLeft = new CANSparkMax(2, MotorType.kBrushless);
    private final CANSparkMax m_rearLeft = new CANSparkMax(4, MotorType.kBrushless);
    // creates the left group, which uses the 2 left motors
    private final MotorControllerGroup m_leftGroup = new MotorControllerGroup(m_frontLeft, m_rearLeft);
    // creates 2 right side motors, which are CANSparkMax Neo brushless
    public final CANSparkMax m_frontRight = new CANSparkMax(1, MotorType.kBrushless);
    private final CANSparkMax m_rearRight = new CANSparkMax(3, MotorType.kBrushless);
    // creates the right group, which uses the 2 right motors
    private final MotorControllerGroup m_rightGroup = new MotorControllerGroup(m_frontRight, m_rearRight);
    // accesses the encoders that come from the motors
    private final RelativeEncoder m_encoder_frontLeft = m_frontLeft.getEncoder();
    private final RelativeEncoder m_encoder_rearLeft = m_rearLeft.getEncoder();
    private final RelativeEncoder m_encoder_frontRight = m_frontRight.getEncoder();
    private final RelativeEncoder m_encoder_rearRight = m_rearRight.getEncoder();

    // creates the Differential drive, which access both the left motors and the
    // right motors
    private final DifferentialDrive m_drive = new DifferentialDrive(m_leftGroup, m_rightGroup);

    /**
     * Constructor for the drive system.
     */
    public DriveSystem() {
        // clearFaults clears any sticky faults that may occur in the CANSparkMaxes

        /*
         * sticky faults are errors in the CANSparkMax hardware
         * not updating after the error is resolved
         */
        m_frontLeft.clearFaults();
        m_rearLeft.clearFaults();
        m_frontRight.clearFaults();
        m_rearRight.clearFaults();

        m_frontLeft.setSmartCurrentLimit(smartCurrentLimit);
        m_rearLeft.setSmartCurrentLimit(smartCurrentLimit);
        m_frontRight.setSmartCurrentLimit(smartCurrentLimit);
        m_rearRight.setSmartCurrentLimit(smartCurrentLimit);
        // invert makes motors move in the opposite direction
        m_leftGroup.setInverted(true); // invert the left side motors
        m_rightGroup.setInverted(false); // invert the right side motors
        /*
         * turn off the wheels at the start
         * this is done to prevent the motors from moving if they
         * were when the robot was turned off
         */
        stopWheels();
    }

    /**
     * puts information on the SmartDashboard
     */
    public void smartdashboard() {
        SmartDashboard.putNumber("Drive/FrontLeft/Velocity", m_encoder_frontLeft.getVelocity());
        SmartDashboard.putNumber("Drive/RearLeft/Velocity", m_encoder_rearLeft.getVelocity());
        SmartDashboard.putNumber("Drive/FrontRight/Velocity", m_encoder_frontRight.getVelocity());
        SmartDashboard.putNumber("Drive/RearRight/Velocity", m_encoder_rearRight.getVelocity());
    }

    /**
     * Sets the desired wheel speeds.
     * 
     * This can be used directly with joystick controls.
     * 
     * @param leftSpeed  The speed of the left side.
     * @param rightSpeed The speed of the right side.
     */
    public void setSpeed(double leftSpeed, double rightSpeed) {
        m_drive.tankDrive(leftSpeed, rightSpeed);
    }

    /**
     * Uses both the joysticks to drive the robot,
     * with the left joystick controlling the left side
     * and the right joystick controlling the right side
     */
    public void dual_joystick_drive() {
        // Get left wheel speed from the left joystick.
        final double leftSpeed = OI.rightJoystick.getY() * kMaxSpeed;

        // Get right wheel speed from the right joystick.
        final double rightSpeed = OI.leftJoystick.getY() * kMaxSpeed;
        // if motors aren't in use by another program
        if (!this.autonomousFlag) {
            setSpeed(leftSpeed, rightSpeed);
        }
    }

    /**
     * Stop the motors on the robot.
     */
    public void stopWheels() {
        m_frontLeft.stopMotor();
        m_frontRight.stopMotor();
        m_rearLeft.stopMotor();
        m_rearRight.stopMotor();
    }
}
