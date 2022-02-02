// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.LIDARSensor;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.LIDARSensor;
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
  private final LIDARSensor m_lidarSensor = new LIDARSensor(m_digitalInput);
  private final LimeLightSystem m_light = new LimeLightSystem();
  private final RobotContainer m_robotContainer = new RobotContainer();
  private Command m_autonomousCommand;
  /**
   * This function is run when the robot is first started up and should be used
   * for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Place smartdashboard items here
  }
  @Override
public void robotPeriodic()
{
  CommandScheduler.getInstance().run();
}
  /**
   * This function is run once each time the robot enters autonomous mode.
   */
  @Override
  public void autonomousInit() {
    timedRobot.reset();
    //Recieves the command we want to run from the RobotContainer
    Command m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command if a command isn't already running
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
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
    // This makes sure that the autonomous stops running when
    // teleop starts running. 
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
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
