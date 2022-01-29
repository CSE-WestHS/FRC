package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

public class RobotContainer {
    //creates instance of @DriveSystem to be used in the @DriveToBall command
    private final DriveSystem m_driveSystem = new DriveSystem();
    //creates an instance of the driveToBall command
    private final DriveToBall m_driveToBall = new DriveToBall(m_driveSystem);

    public RobotContainer() {}
    //returns the command we want to use in autonomous
    //TODO once we get the command group set up with all of the other commands, change the return value to that command
    public Command getAutonomousCommand() {
        return m_driveToBall;
    }
}
