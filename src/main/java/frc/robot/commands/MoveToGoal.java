package frc.robot.commands;

import frc.robot.subsystems.LIDARSensor;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSystem;

public class MoveToGoal extends CommandBase {
  // requires Lidar and Drive System
  private final DriveSystem m_DriveSystem;
  private final LIDARSensor m_LIDARSensor;

  public MoveToGoal(DriveSystem subsystem, LIDARSensor lidar) {
    m_DriveSystem = subsystem;
    m_LIDARSensor = lidar;
    addRequirements(m_DriveSystem, m_LIDARSensor);
  }

  @Override
  // command runs when class first starts
  // When it starts, turn motors off
  public void initialize() {
    m_DriveSystem.setSpeed(0, 0);
  }

  // command runs while class is running, goes until done
  // main bulk of command that will be edited
  // Turns the motors on at 75% power
  // Runs motors backwards if less than a meter from goal
  public void execute() {
    if (m_LIDARSensor.getDistance() < 100.0) {
      m_DriveSystem.setSpeed(-0.75, -0.75);
    } else {
      m_DriveSystem.setSpeed(0.75, 0.75);
    }
  }

  // command runs when class is finished, or interrupted
  public void end() {
    m_DriveSystem.setSpeed(0, 0);
  }

  // sets the condition for the command to be done
  // if you are between a 1 and 2.5 meters, end command
  public boolean isFinished() {
    double setDistance = m_LIDARSensor.getDistance();
    boolean rightDistance = setDistance >= 100.0 && setDistance <= 250.0;
    return rightDistance;
  }
}