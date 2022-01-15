// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard; // TODO: uncomment when SmartDashboard has been added to the project
import frc.robot.subsystems.DriveSystem;

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
  private final XboxController m_controller = new XboxController(0);
  private final DriveSystem m_driveSystem = new DriveSystem();

  // Slew rate limiters to make joystick inputs more gentle; 1/3 sec from 0 to 1.
  private final SlewRateLimiter m_linearVelocityLimiter = new SlewRateLimiter(1 / 3.0);
  private final SlewRateLimiter m_angularVelocityLimiter = new SlewRateLimiter(1 / 3.0);

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
    // Get the x speed from the left analog stick.
    // This is negative because Xbox controllers return negative values when pushed
    // forward.
    final var xSpeed = -m_linearVelocityLimiter.calculate(m_controller.getLeftY()) * DriveSystem.kMaxSpeed;

    // Get the rate of angular velocity.
    // We are inverting because we want a positive value when we pull to the left.
    // (CCW rotation is positive in mathematics, but Xbox controllers return
    // positive when you pull right.)
    final var angularVelocity = -m_angularVelocityLimiter.calculate(m_controller.getRightX())
        * DriveSystem.kMaxAngularSpeed;

    m_driveSystem.drive(xSpeed, angularVelocity);
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
