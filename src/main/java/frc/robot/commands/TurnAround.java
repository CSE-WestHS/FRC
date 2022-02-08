package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSystem;

public class TurnAround extends CommandBase {
  private final DriveSystem m_DriveSystem;

  // requires Drive System
  public TurnAround(DriveSystem subsystem) {
    m_DriveSystem = subsystem;
    addRequirements(m_DriveSystem);
  }

  @Override
  // command runs when class first starts
  // stops wheels and resets encoders
  public void initialize() {
    m_DriveSystem.stopWheels();
    m_DriveSystem.m_frontLeft.getEncoder().setPosition(0);
    m_DriveSystem.m_frontRight.getEncoder().setPosition(0);
  }

  // command runs while class is running, goes until done
  // main bulk of command that will be edited
  // TODO fine tune encoder distance and motor speeds
  // if the distance traveled is less than 4 feet
  // set motors at opisite speeds at 35% power
  // else, turn off motrs
  public void execute() {
    if (m_DriveSystem.m_frontLeft.getEncoder().getPosition() < 4) {
      m_DriveSystem.setSpeed(0.35, -0.35);
    } else {
      m_DriveSystem.stopWheels();
    }
  }

  // command runs when class is finished, or interrupted
  // turns off wheels
  public void end() {
    m_DriveSystem.stopWheels();
  }

  // sets end condition for command
  // finishes command if wheels are not running
  public boolean isFinished() {
    return     m_DriveSystem.m_frontLeft.getEncoder().getVelocity() == 0;
  }
}