 package frc.robot.commands;
  
  import edu.wpi.first.wpilibj2.command.CommandBase;
  import frc.robot.subsystems.DriveSystem;
  public class TurnAround extends CommandBase {
  private final DriveSystem m_DriveSystem;
  
  public TurnAround(DriveSystem subsystem) {
  m_DriveSystem = subsystem;
  addRequirements(m_DriveSystem);
  }
  
  @Override
  // command runs when class first starts
  public void initialize() {
   m_DriveSystem.stopWheels();
   m_DriveSystem.enc_left.reset();
   m_DriveSystem.enc_right.reset();
  }
  // command runs while class is running, goes until done
  // main bulk of command that will be edited
  //TODO fine tune encoder distance and motor speeds
  public void execute() {
      if(m_DriveSystem.enc_left.getDistance() < 4)
      {
        m_DriveSystem.setSpeed(0.35, -0.35); 
      }
else{
    m_DriveSystem.stopWheels();
}
  }
  
  // command runs when class is finished, or interrupted
  public void end() {
    m_DriveSystem.stopWheels();
  }
  
  // makes sure the command actually ends
  public boolean isFinished() {
  return m_DriveSystem.enc_left.getStopped();
  }
  }