package frc.robot.subsystems;

//import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.controls.OI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

//A drive system.
//this is what drives the robot with the controlers. 

public class DriveSystem 
{
    /*
    Remember, the PWNVictorSPX is the actual motor. 
    PWMVictorSPX takes in the port number of the motor.
    DifferentalDrive is a class that makes running the 2 motors together easier.
    If you want to know more, consider reading the frc wpilib documentation to know more.
    */ 
    static CANSparkMax m_frontLeft = new CANSparkMax(1, MotorType.kBrushless);
    static CANSparkMax m_rearLeft = new CANSparkMax(2, MotorType.kBrushless);
    static CANSparkMax m_frontRight = new CANSparkMax(3, MotorType.kBrushless);
    static CANSparkMax m_rearRight = new CANSparkMax(4, MotorType.kBrushless);
    static SpeedControllerGroup m_left = new SpeedControllerGroup(m_frontLeft, m_rearLeft);
    static SpeedControllerGroup m_right = new SpeedControllerGroup(m_frontRight, m_rearRight);
    static DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);
     /*private static final DifferentialDrive wheelsMotor1 = new
     DifferentialDrive(new PWMVictorSPX(0), new PWMVictorSPX(15));
     private static final DifferentialDrive wheelsMotor2 = new
     DifferentialDrive(new PWMVictorSPX(1), new PWMVictorSPX(14));*/
     private static double leftSpeed = 0;
     private static double rightSpeed = 0;
    private static double SPEED_MULTIPLIER = 1;

    // Pretty obvious what this function does. Run this in the Robot.java
    // teleopPeriodic() function to run the robot.
    public static void update() {
        // if (-OI.LEFT_STICK.getY() >= 500)
        // {
        moveWheels(OI.RIGHT_STICK.getY() * SPEED_MULTIPLIER, OI.LEFT_STICK.getY() * SPEED_MULTIPLIER);
        /*
         * } else { stopWheels();
         * 
         * }
         */

    }
   
    public static void moveWheels(double leftSpeed, double rightSpeed) {
        //double _rightSpeed = -rightSpeed; // Set it to negative to match the motor set up
        m_drive.tankDrive(leftSpeed, rightSpeed);
     
    }

    public static  void stopWheels()
    {
        m_drive.stopMotor();
       
    }
}
