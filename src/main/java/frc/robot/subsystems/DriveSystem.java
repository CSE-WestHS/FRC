package frc.robot.subsystems;
//the imports are what this class needs from other classes to function properly
//these can come from other parts of the robot or the internet
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.XboxController;

import frc.robot.controls.OI;

/**
 * The DriveSystem class is responsible for controlling the drivetrain of the
 * robot.
 */
public class DriveSystem {
    public static final double kMaxSpeed = 0.75; // meters per second
    public static final double kMaxAngularSpeed = 2 * Math.PI; // one rotation per second

    private static int smartCurrentLimit = 40; // amps, current limit for the smart motor controllers
    private static final double kTrackWidth = 0.381 * 2; // meters
    //creates 2 left side motors, which are CANSparkMax Neo brushless 
    public final CANSparkMax m_frontLeft = new CANSparkMax(2, MotorType.kBrushless);
    private final CANSparkMax m_rearLeft = new CANSparkMax(4, MotorType.kBrushless);
    //creates the left group, which uses the 2 left motors
    private final MotorControllerGroup m_leftGroup = new MotorControllerGroup(m_frontLeft, m_rearLeft);
    //creates 2 right side motors, which are CANSparkMax Neo brushless 
    public final CANSparkMax m_frontRight = new CANSparkMax(1, MotorType.kBrushless);
    private final CANSparkMax m_rearRight = new CANSparkMax(3, MotorType.kBrushless);
    //creates the right group, which uses the 2 right motors
    private final MotorControllerGroup m_rightGroup = new MotorControllerGroup(m_frontRight, m_rearRight);
    //accesses the encoders that come from the Neo brushless motors
    private final RelativeEncoder m_encoder_frontLeft = m_frontLeft.getEncoder();
    private final RelativeEncoder m_encoder_rearLeft = m_rearLeft.getEncoder();
    private final RelativeEncoder m_encoder_frontRight = m_frontRight.getEncoder();
    private final RelativeEncoder m_encoder_rearRight = m_rearRight.getEncoder();

    //creates the Differential drive, which access both the left motors and the right motors
    private final DifferentialDrive m_drive = new DifferentialDrive(m_leftGroup, m_rightGroup);
    private final DifferentialDriveKinematics m_kinematics = new DifferentialDriveKinematics(kTrackWidth);

    private final XboxController m_controller = new XboxController(0);
    // Slew rate limiters to make joystick inputs more gentle; 1/3 sec from 0 to 1.
    private final SlewRateLimiter m_linearVelocityLimiter = new SlewRateLimiter(1 / 3.0);
    private final SlewRateLimiter m_angularVelocityLimiter = new SlewRateLimiter(1 / 3.0);

    /**
     * Constructor for the drive system.
     */
    public DriveSystem() {
        m_frontLeft.clearFaults();
        m_rearLeft.clearFaults();
        m_frontRight.clearFaults();
        m_rearRight.clearFaults();

        m_frontLeft.setSmartCurrentLimit(smartCurrentLimit);
        m_rearLeft.setSmartCurrentLimit(smartCurrentLimit);
        m_frontRight.setSmartCurrentLimit(smartCurrentLimit);
        m_rearRight.setSmartCurrentLimit(smartCurrentLimit);

        m_leftGroup.setInverted(true); // invert the left side motors
        m_rightGroup.setInverted(false); // invert the right side motors
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
        //puts the speed at which each motor is currently moving on the Smart Dashboard
        //the SmartDashboard is a tool you can use with the frc Drivers station
        SmartDashboard.putNumber("Drive/FrontLeft/Velocity", m_encoder_frontLeft.getVelocity());
        SmartDashboard.putNumber("Drive/RearLeft/Velocity", m_encoder_rearLeft.getVelocity());
        SmartDashboard.putNumber("Drive/FrontRight/Velocity", m_encoder_frontRight.getVelocity());
        SmartDashboard.putNumber("Drive/RearRight/Velocity", m_encoder_rearRight.getVelocity());

        //double error = m_frontLeft.getEncoder().getPosition() - m_frontRight.getEncoder().getPosition();
        
        m_drive.tankDrive(leftSpeed, rightSpeed);
    }

    /**
     * Drives the robot with given linear velocity and angular velocity.
     * 
     * Can be used with the xbox controller.
     * 
     * @param linearVelocity  The desired linear velocity in m/s.
     * @param angularVelocity The desired angular velocity in rad/s.
     */

    public void drive(double linearVelocity, double angularVelocity) {
        ChassisSpeeds speeds = new ChassisSpeeds(linearVelocity, 0.0, angularVelocity);
        DifferentialDriveWheelSpeeds wheelSpeeds = m_kinematics.toWheelSpeeds(speeds);
        setSpeed(wheelSpeeds.leftMetersPerSecond , wheelSpeeds.rightMetersPerSecond );
    }

    public void xbox_drive() {
        // Get the x speed from the left analog stick.
        // This is negative because Xbox controllers return negative values when pushed
        // forward.
        final double xSpeed = -m_linearVelocityLimiter.calculate(m_controller.getLeftY()) * kMaxSpeed;

        // Get the rate of angular velocity.
        // We are inverting because we want a positive value when we pull to the left.
        // (CCW rotation is positive in mathematics, but Xbox controllers return
        // positive when you pull right.)
        final double angularVelocity = -m_angularVelocityLimiter.calculate(m_controller.getRightX())
                * kMaxAngularSpeed;

        drive(xSpeed, angularVelocity);
    }

    public void dual_joystick_drive() {
        // Get left wheel speed from the left joystick.
        final double leftSpeed = OI.rightJoystick.getY() * kMaxSpeed;

        // Get right wheel speed from the right joystick.
        final double rightSpeed = OI.leftJoystick.getY() * kMaxSpeed;

        setSpeed(leftSpeed, rightSpeed);
    }

    /**
     * Stop the motors on the robot.
     */
    public void stopWheels() {
        m_drive.stopMotor();
    }
}
