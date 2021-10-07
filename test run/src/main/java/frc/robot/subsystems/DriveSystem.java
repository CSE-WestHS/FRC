package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import frc.robot.controls.OI;

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
    private static final DifferentialDrive wheelsMotor1 = new DifferentialDrive(new PWMVictorSPX(2), new PWMVictorSPX(1));
    private static final DifferentialDrive wheelsMotor2 = new DifferentialDrive(new PWMVictorSPX(3),new PWMVictorSPX(0));
    private static double leftSpeed = 0;
    private static double rightSpeed = 0;
    private static final double SPEED_MULTIPLIER = 1;

    //Pretty obvious what this function does. Run this in the Robot.java teleopPeriodic() function to run the robot.
    public static void update()
    {
        if (-OI.LEFT_STICK.getY() >= 1500) 
        {
             moveWheels(-OI.RIGHT_STICK.getY() *SPEED_MULTIPLIER, -OI.LEFT_STICK.getY() *SPEED_MULTIPLIER);
        }
        else 
        {
            stopWheels();
        }
        
    }
    public static void moveWheels(double leftSpeed, double rightSpeed)
    {
        double _rightSpeed = -rightSpeed; //Set it to negative to match the motor set up 
        wheelsMotor2.tankDrive(leftSpeed, _rightSpeed);
        wheelsMotor1.tankDrive(leftSpeed, _rightSpeed);
    }

    public static void stopWheels()
    {
        wheelsMotor2.stopMotor();
        wheelsMotor1.stopMotor();
    }
}
