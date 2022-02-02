 package frc.robot.commands;
  import frc.robot.subsystems.LIDARSensor;
  import edu.wpi.first.wpilibj2.command.CommandBase;
  import frc.robot.subsystems.DriveSystem;

  public class MoveToGoal extends CommandBase {
  private final DriveSystem m_DriveSystem;
  private final LIDARSensor m_LIDARSensor;
  public MoveToGoal(DriveSystem subsystem, LIDARSensor lidar) {
  m_DriveSystem = subsystem;
  m_LIDARSensor = lidar;
  addRequirements(m_DriveSystem, m_LIDARSensor);
  }
  
  @Override
  // command runs when class first starts
  public void initialize() {
  m_DriveSystem.setSpeed(0, 0);
  }
  // command runs while class is running, goes until done
  // main bulk of command that will be edited
  
  public void execute() {
    m_DriveSystem.setSpeed(0.75, 0.75);
  }
  
  // command runs when class is finished, or interrupted
  public void end() {
    m_DriveSystem.setSpeed(0, 0);
  }
  
  // makes sure the command actually ends
  public boolean isFinished() {
  double setDistance = m_LIDARSensor.getDistance();
  boolean rightDistance = setDistance >= 100.0 && setDistance <= 250.0 ;
  return rightDistance;
  }
  }