package frc.robot.commands;

//the imports are what this class needs from other classes to function properly
//these can come from other parts of the robot or the internet
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.LimeLightSystem;
import frc.robot.subsystems.Intake;
import frc.robot.controls.OI;
import edu.wpi.first.wpilibj.Timer;

public class DriveCommands {
    //this class requires a DriveSystem, LimeLight, and Intake to run
    DriveSystem m_driveSystem;
    LimeLightSystem m_LimeLightSystem;
    Intake m_intake;
    private static Timer m_Timer = new Timer();
//Constructor for DriveCommands Class
    public DriveCommands(DriveSystem driveSystem, LimeLightSystem m_LimeLightSystem, Intake m_intake) {
        this.m_driveSystem = driveSystem;
        this.m_LimeLightSystem = m_LimeLightSystem;
        this.m_intake = m_intake;
        m_Timer.reset();
    }
    //This command moves the robot forewards a small bit
    //then backwards for a small bit
    //then forewards for a small bit
    //then stops motors
    //the timer is 
    public void dropIntake(double speed) {
        m_Timer.start();
        while (m_driveSystem.m_frontLeft.getEncoder().getPosition() < 1 && m_Timer.get() < 3) {
            m_driveSystem.setSpeed(speed, speed);
        }
        m_Timer.reset();
        m_driveSystem.m_frontLeft.getEncoder().setPosition(0);
        while (m_driveSystem.m_frontLeft.getEncoder().getPosition() < 0.5 && m_Timer.get() < 3) {
            m_driveSystem.setSpeed(-speed, -speed);
        }
        m_Timer.reset();
        m_driveSystem.m_frontLeft.getEncoder().setPosition(0);

        while (m_driveSystem.m_frontLeft.getEncoder().getPosition() < 1 && m_Timer.get() < 3) {
            m_driveSystem.setSpeed(speed, speed);
        }
        m_Timer.reset();
        m_Timer.stop();
        m_driveSystem.stopWheels();
        m_driveSystem.m_frontLeft.getEncoder().setPosition(0);
        m_intake.runLowerMotors(0.65);
    }

    public void driveSetDistance(double rotations, double speed) {
        m_Timer.reset();
        m_Timer.start();
        while (m_driveSystem.m_frontLeft.getEncoder().getPosition() < rotations && m_Timer.get() < 15) {
            m_driveSystem.setSpeed(speed, speed);
        }
        m_Timer.reset();
        m_Timer.stop();
        m_driveSystem.stopWheels();
        m_driveSystem.m_frontLeft.getEncoder().setPosition(0);
    }

    public void turnAround() {
        m_Timer.reset();
        m_Timer.start();
        while (m_driveSystem.m_frontLeft.getEncoder().getPosition() < 10 && m_Timer.get() < 10) {
            m_driveSystem.setSpeed(0.5, -0.5);
        }
        m_Timer.reset();
        m_Timer.stop();
        m_driveSystem.stopWheels();
        m_intake.stopMotors();
        m_driveSystem.m_frontLeft.getEncoder().setPosition(0);
    }

    public void autonomousDrive() {

        dropIntake(0.5);
        driveSetDistance(25, 0.6);
        turnAround();
        double desiredDistance = 120.0;
        double currentDistance = m_LimeLightSystem.calculateDistanceFromGoal();
        double distanceError = desiredDistance - currentDistance;
        double errorRange = 6;
        m_Timer.reset();
        m_Timer.start();
        while (distanceError > errorRange || distanceError < -errorRange || m_Timer.get() < 15) {
            lineUp();
        }
        m_Timer.reset();
        m_Timer.stop();
    }

    /**
     * Drives from starting position to the target position.
     * 
     * @param speed
     */

    public void turnToGoal() {
        // speed the robot will turn
        double speed = 0.3;
        // margin of error the robot has in turning
        double range = 3;
        // if the robot is too far to the left
        // turn right
        if (m_LimeLightSystem.getX() < -range) {
            m_driveSystem.setSpeed(speed, -speed);
        }
        // if the robot is too far right
        // turn left
        else if (m_LimeLightSystem.getX() > range) {
            m_driveSystem.setSpeed(-speed, speed);
        }
        // if robot is at the desired angle
        // stop moving
        else {
            m_driveSystem.stopWheels();
        }

    }

    public void adjustDistance() {
        // the optimal distance is 120 inches or 12 feet
        double desiredDistance = 120.0;
        // our current distance is calculated by the limeLight calculaton function
        double currentDistance = m_LimeLightSystem.calculateDistanceFromGoal();
        // the distance error is the desired distance - the current distance
        // we want the distance error to b as close to 0 as possible
        double distanceError = desiredDistance - currentDistance;
        // speed our robot will go
        double speed = 0.4;
        // margin of error in inches
        double errorRange = 6;

        // if the error is greater than 2 inches too far
        // go forewards at 40% speed
        if (distanceError > errorRange) {
            m_driveSystem.setSpeed(speed, speed);
        }
        // if the distance error is greater than 2 inches too close
        // go backwards at 40% speed
        else if (distanceError < -errorRange) {
            m_driveSystem.setSpeed(-speed, -speed);
        }
        // if the distance is good
        // stop the wheels
        else {
            m_driveSystem.stopWheels();
        }
    }

    // adjusts the distance of the robot if the button is pressed
    public void lineUp() {
        m_Timer.reset();
        m_Timer.start();
        // margin of error the robot has in turning
        double range = 3;
        // if(m_LimeLightSystem.getX() < -range || m_LimeLightSystem.getX() > range)
        while (m_LimeLightSystem.getX() < -range || m_LimeLightSystem.getX() > range || m_Timer.get() < 5) {
            turnToGoal();
        }
        // else {
        m_Timer.reset();
        m_Timer.stop();
        adjustDistance();
        // }
    }

    public void buttonLineUp() {
        if (OI.adjustButton.isPressed()) {
            this.m_driveSystem.autonomousFlag = true;
            lineUp();
        } else {
            this.m_driveSystem.autonomousFlag = false;
        }
    }
}
