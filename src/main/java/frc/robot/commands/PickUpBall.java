package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class PickUpBall extends CommandBase{
 private Intake m_Intake;
 public void PickupBall(Intake subsystem) {
 m_Intake = subsystem;
 addRequirements(m_Intake);
 }
 
 @Override
 // command runs when class first starts
 public void initialize() {
 m_Intake.stopMotors();
 }
 // command runs while class is running, goes until done
 // main bulk of command that will be edited
 //TODO update values once we know how fast we need the intake motors to run
 public void execute() {
 m_Intake.runMotors(0.45 , 0);
 }
 
 // command runs when class is finished, or interrupted
     public void end() {
     m_Intake.stopMotors();
     }
     
     // makes sure the command actually ends
     public boolean isFinished() {
     return true;
    }
}