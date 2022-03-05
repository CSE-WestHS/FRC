// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.LIDARSensor;
import frc.robot.subsystems.LimeLightSystem;
import edu.wpi.first.cameraserver.CameraServer;
import frc.robot.subsystems.Shooter;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
  private DigitalInput m_digitalInput = new DigitalInput(0); // LIDAR sensor
  private final DriveSystem m_driveSystem = new DriveSystem();
  private final LIDARSensor m_lidarSensor = new LIDARSensor(m_digitalInput);
  private final LimeLightSystem m_light = new LimeLightSystem();
  private final Intake m_intake = new Intake();
  private final Shooter m_shooter = new Shooter();

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    // Place smartdashboard items here
    CameraServer.startAutomaticCapture();
  }

  /**
   * This function is run once each time the robot enters autonomous mode.
   */
  @Override
  public void autonomousInit() {
    m_driveSystem.m_frontLeft.getEncoder().setPosition(0);
    m_driveSystem.m_frontRight.getEncoder().setPosition(0);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {

    // If the robot han't driven X feet, keep driving
    // at 45% power
    while (m_driveSystem.m_frontLeft.getEncoder().getPosition() < 1) {
      m_driveSystem.setSpeed(-0.75, -0.75);
    }
    m_driveSystem.m_frontLeft.getEncoder().setPosition(0);
    while (m_driveSystem.m_frontLeft.getEncoder().getPosition() < 0.5) {
      m_driveSystem.setSpeed(0.75, 0.75);
    }
    m_driveSystem.m_frontLeft.getEncoder().setPosition(0);
    // if(Intake.intake1.getEncoder().getPosition() < 10)

    while (m_driveSystem.m_frontLeft.getEncoder().getPosition() < 8/(6*Math.PI)) {
      m_driveSystem.setSpeed(-0.75, -0.75);
    }
    m_driveSystem.m_frontLeft.getEncoder().setPosition(0);

    while (m_driveSystem.m_frontLeft.getEncoder().getPosition() < 15) {
      m_driveSystem.setSpeed(0.5, -0.5);
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

  /**
   * This function is called once each time the robot enters teleoperated mode.
   */
  @Override
  public void teleopInit() {
    m_driveSystem.m_frontLeft.getEncoder().setPosition(0);
    m_driveSystem.m_frontRight.getEncoder().setPosition(0);
  }

  /**
   * This function is called periodically during teleoperated mode.
   */
  @Override
  public void teleopPeriodic() {
    m_driveSystem.dual_joystick_drive();
    m_light.start();
    m_intake.buttonIntake();
    m_shooter.shootBall(.9, .75);

    SmartDashboard.putNumber("LIDAR Distance CM", m_lidarSensor.getDistance());
  }

  /**
   * This function is called once each time the robot enters test mode.
   */
  @Override
  public void testInit() {
    teleopInit();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    teleopPeriodic();
  }
}
