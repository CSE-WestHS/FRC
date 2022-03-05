package frc.robot.commands;

import frc.robot.subsystems.DriveSystem;

public class DriveCommands {
    DriveSystem m_driveSystem;

    public DriveCommands(DriveSystem driveSystem) {
        this.m_driveSystem = driveSystem;
    }

    /**
     * Drives from starting position to the target position.
     * This function is called periodically during autonomous mode.
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
}
