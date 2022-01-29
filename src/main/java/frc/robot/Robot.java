// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.LIDARSensor;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.LimeLightSystem;
import edu.wpi.first.wpilibj.Timer;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the manifest
 * file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
  private Timer timedRobot = new Timer();
  private DigitalInput m_digitalInput = new DigitalInput(0); // LIDAR sensor
  private final LIDARSensor m_lidarSensor = new LIDARSensor(m_digitalInput);
  private final DriveSystem m_driveSystem = new DriveSystem();
  private final LimeLightSystem m_light = new LimeLightSystem();

  /**
   * This function is run when the robot is first started up and should be used
   * for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Place smartdashboard items here
  }

  /**
   * This function is run once each time the robot enters autonomous mode.
   */
  @Override
  public void autonomousInit() {
    timedRobot.reset();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    teleopPeriodic();
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
    m_light.start();

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
