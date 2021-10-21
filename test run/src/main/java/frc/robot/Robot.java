// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

//import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.PWMSparkMax;
import edu.wpi.first.wpilibj.TimedRobot;
//import edu.wpi.first.wpilibj.Timer;
import frc.robot.controls.OI;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.ShooterSystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.ConveyorSystem;
import frc.robot.subsystem.SolenoidSystem;
/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
DriveSystem driveSystem;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {}

  /** This function is run once each time the robot enters autonomous mode. */
  // @Override
  // public void autonomousInit() {
  //  m_timer.reset();
  //  m_timer.start();
  // }

  /** This function is called periodically during autonomous. */
 // @Override
 // public void autonomousPeriodic() {
    // Drive for 2 seconds
   // if (m_timer.get() < 2.0) {
     // m_robotDrive.arcadeDrive(0.5, 0.0); // drive forwards half speed
    //} else {
      //m_robotDrive.stopMotor(); // stop robot
   // }
  // }

  /** This function is called once each time the robot enters teleoperated mode. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during teleoperated mode. */
  @Override
  public void teleopPeriodic() 
  {
    OI.update();
    DriveSystem.update();
    ShooterSystem.update();
    Intake.update();
    ConveyorSystem.update();
    SolenoidSystem.update();
   // m_robotDrive.arcadeDrive(m_stick.getY(), m_stick.getX());
  }

  /** This function is called once each time the robot enters test mode. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}