package frc.robot.commands;

import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.LimeLightSystem;
import frc.robot.controls.OI;
public class DriveCommands {
    DriveSystem m_driveSystem;
    LimeLightSystem m_limeLight;
    public DriveCommands(DriveSystem driveSystem, LimeLightSystem m_limeLight) {
        this.m_driveSystem = driveSystem;
        this.m_limeLight = m_limeLight;
    }

    /**
     * Drives from starting position to the target position.
     * 
     * @param speed
     */
    public void driveStartToBall(double speed) {
        // If the robot han't driven X feet, keep driving
        // at 45% power
        while (m_driveSystem.m_frontLeft.getEncoder().getPosition() < 1) {
            m_driveSystem.setSpeed(-speed, -speed);
        }
        m_driveSystem.m_frontLeft.getEncoder().setPosition(0);
        while (m_driveSystem.m_frontLeft.getEncoder().getPosition() < 0.5) {
            m_driveSystem.setSpeed(speed, speed);
        }
        m_driveSystem.m_frontLeft.getEncoder().setPosition(0);
        // if(Intake.intake1.getEncoder().getPosition() < 10)

        while (m_driveSystem.m_frontLeft.getEncoder().getPosition() < 25) {
            m_driveSystem.setSpeed(-speed, -speed);
        }
        m_driveSystem.m_frontLeft.getEncoder().setPosition(0);

        while (m_driveSystem.m_frontLeft.getEncoder().getPosition() < 15) {
            m_driveSystem.setSpeed(2 / 3 * speed, -2 / 3 * speed);
        }
        // else {

        /*
         * m_Intake.m_sideEncoder.restartEncoder();
         * if (m_Intake.m_sideEncoder.getDistance() < 12) {
         * m_Intake.runMotors(0.45, 0);
         * } else {
         * m_driveSystem.m_frontLeft.getEncoder().setPosition(0);
         * m_driveSystem.m_frontRight.getEncoder().setPosition(0);
         * if (m_driveSystem.m_leftEncoder.getDistance() < 4) {
         * m_driveSystem.setSpeed(0.35, -0.35);
         * } else {
         * Intake.sideFeed.getEncoder().setPosition(0);
         * Intake.upFeed.getEncoder().setPosition(0);
         * if (m_Intake.m_sideEncoder.getDistance() < 5) {
         * m_Intake.runMotors(0.25, 0.25);
         * double power = m_shooter.getPower();
         * m_shooter.setPower(power);
         * } else if (m_Intake.m_upEncoder.getDistance() < 10) {
         * m_Intake.runMotors(0, 0.25);
         * double power = m_shooter.getPower();
         * m_shooter.setPower(power);
         * } else {
         * m_Intake.runMotors(0, 0);
         * double power = m_shooter.getPower();
         * m_shooter.setPower(power);
         * }
         * double power = m_shooter.getPower();
         * m_shooter.setPower(power);
         * }
         */
        // }
    }
    public void adjustDistance()
    {
        //the optimal distance is 120 inches or 12 feet
        double desiredDistance = 120.0;
        //our current distance is calculated by the limeLight calculaton function
        double currentDistance = m_limeLight.calculateDistanceFromGoal();
        //the distance error is the desired distance - the current distance
        //we want the distance error to b as close to 0 as possible
        double distanceError = desiredDistance - currentDistance;
        //speed our robot will go
        double speed = 0.4;
        //margin of error in inches
        double errorRange = 2;

        //if the error is greater than 2 inches too far
        //go forewards at 40% speed
        if(distanceError > errorRange)
        {
            m_driveSystem.setSpeed(speed, speed);
        }
        //if the distance error is greater than 2 inches too close
        //go backwards at 40% speed
        else if(distanceError < -errorRange)
        {
            m_driveSystem.setSpeed(-speed, -speed);
        }
    }
    //adjusts the distance of the robot if the button is pressed
    public void buttonAdjustDist(){
        if(OI.adjustButton.isPressed())
        {
            adjustDistance();
        }
    }
}
