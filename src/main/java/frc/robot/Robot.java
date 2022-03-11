// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TimedRobot;

import frc.robot.commands.DriveCommands;
import frc.robot.commands.ShootCommand;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.LIDARSensor;
import frc.robot.subsystems.LimeLightSystem;
import frc.robot.subsystems.Shooter;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {

  private DigitalInput lidarDIO = new DigitalInput(0);
  //creates an instance of a DriveSystem, Elevator, LIDARSensor, LimeLightSystem, Intake, and Shooter
  private final DriveSystem m_driveSystem = new DriveSystem();
  private final Elevator m_elevator = new Elevator();
  private final LIDARSensor m_lidarSensor = new LIDARSensor(lidarDIO);
  private final LimeLightSystem m_limelight = new LimeLightSystem();
  private final Intake m_intake = new Intake();
  private final Shooter m_shooter = new Shooter();
//creates an instance of the DriveCommands and ShootCommand
  private final DriveCommands m_driveCommands = new DriveCommands(m_driveSystem, m_limelight, m_intake);
  private final ShootCommand m_shootCommand = new ShootCommand(m_elevator, m_shooter, m_intake);

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    //starts up the camera on the robot
    CameraServer.startAutomaticCapture();
    //runs all the smartdashboard commands from the subsystems
    m_driveSystem.smartdashboard();
    m_elevator.smartdashboard();
    m_intake.smartdashboard();
    m_lidarSensor.smartdashboard();
    m_limelight.smartdashboard();
    m_shooter.smartdashboard();
  }

  /**
   * This function is run once each time the robot enters autonomous mode.
   */
  @Override
  public void autonomousInit() {
    //sets the position value on the drive encoders to 0
    m_driveSystem.m_frontLeft.getEncoder().setPosition(0);
    m_driveSystem.m_frontRight.getEncoder().setPosition(0);
    m_driveCommands.autonomousDrive();
    m_shootCommand.autonomousShoot();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    // whatever we want to do/check during autonomous
  }

  /**
   * This function is called once each time the robot enters teleoperated mode.
   */
  @Override
  public void teleopInit() {
    //sets the position value on the drive encoders to 0
    m_driveSystem.m_frontLeft.getEncoder().setPosition(0);
    m_driveSystem.m_frontRight.getEncoder().setPosition(0);
    //turns off all the motors of subsystems
    //this is done to stop any motors that may have been running when autonomous ended
    m_intake.stopMotors();
    m_driveSystem.stopWheels();
    m_elevator.motorPower(0);
    m_shooter.motorPower(0);
  }

  /**
   * This function is called periodically during teleoperated mode.
   */
  @Override
  public void teleopPeriodic() {
    //runs all the commands that use buttons from the subsystems
    m_driveSystem.dual_joystick_drive();
    m_elevator.elevatorButtonControl();
    m_intake.intakeButtonControl();
    m_shootCommand.shootButtonControl();
    m_driveCommands.buttonLineUp();
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
