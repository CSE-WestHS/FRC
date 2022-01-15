package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

/**
 * The DriveSystem class is responsible for controlling the drivetrain of the
 * robot.
 */
public class DriveSystem {
    public static final double kMaxSpeed = 3.0; // meters per second
    public static final double kMaxAngularSpeed = 2 * Math.PI; // one rotation per second

    private static int smartCurrentLimit = 40; // amps, current limit for the smart motor controllers
    private static final double kTrackWidth = 0.381 * 2; // meters

    private final CANSparkMax m_frontLeft = new CANSparkMax(2, MotorType.kBrushless);
    private final CANSparkMax m_rearLeft = new CANSparkMax(1, MotorType.kBrushless);
    private final MotorControllerGroup m_leftGroup = new MotorControllerGroup(m_frontLeft, m_rearLeft);

    private final CANSparkMax m_frontRight = new CANSparkMax(4, MotorType.kBrushless);
    private final CANSparkMax m_rearRight = new CANSparkMax(3, MotorType.kBrushless);
    private final MotorControllerGroup m_rightGroup = new MotorControllerGroup(m_frontRight, m_rearRight);

    private final DifferentialDrive m_drive = new DifferentialDrive(m_leftGroup, m_rightGroup);
    private final DifferentialDriveKinematics m_kinematics = new DifferentialDriveKinematics(kTrackWidth);

    /**
     * Constructor for the drive system.
     */
    public DriveSystem() {
        m_frontLeft.clearFaults();

        m_frontLeft.setSmartCurrentLimit(smartCurrentLimit);
        m_rearLeft.setSmartCurrentLimit(smartCurrentLimit);
        m_frontRight.setSmartCurrentLimit(smartCurrentLimit);
        m_rearRight.setSmartCurrentLimit(smartCurrentLimit);

        m_leftGroup.setInverted(true); // invert the left side motors
        m_rightGroup.setInverted(true); // invert the right side motors
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
        setSpeed(wheelSpeeds.leftMetersPerSecond, wheelSpeeds.rightMetersPerSecond);
    }

    /**
     * Stop the robot.
     */
    public void stopWheels() {
        m_drive.stopMotor();
    }
}
