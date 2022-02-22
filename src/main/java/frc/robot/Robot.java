// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TimedRobot;

import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.LIDARSensor;
import frc.robot.subsystems.LimeLightSystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
  private DigitalInput lidarDIO = new DigitalInput(0);

  private final DriveSystem m_driveSystem = new DriveSystem();
  private final LIDARSensor m_lidarSensor = new LIDARSensor(lidarDIO);
  private final LimeLightSystem m_limelight = new LimeLightSystem();
  private final Intake m_intake = new Intake();

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    m_driveSystem.smartdashboard();
    m_lidarSensor.smartdashboard();
    m_limelight.smartdashboard();
    m_intake.smartdashboard();
  }

  /**
   * This function is run once each time the robot enters autonomous mode.
   */
  @Override
  public void autonomousInit() {
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
  }

  /**
   * This function is called once each time the robot enters teleoperated mode.
   */
  @Override
  public void teleopInit() {
  }

  /**
   * This function is called periodically during teleoperated mode.
   */
  @Override
  public void teleopPeriodic() {
    m_driveSystem.dual_joystick_drive();
    m_intake.buttonIntake();
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
