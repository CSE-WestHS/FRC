package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSystem;

public class DriveToBall extends CommandBase {
    // The subsystem the command runs on
    private final DriveSystem m_driveSystem;

    public DriveToBall(DriveSystem subsystem) {
        m_driveSystem = subsystem;
        addRequirements(m_driveSystem);
    }

    @Override
    // command runs when class first starts, initializes motors
    public void initialize() {
        m_driveSystem.setSpeed(0, 0);
    }

    @Override
    // command runs while class is running, goes until done
    // main bulk of command that will be edited
    //TODO motor values will need to be edited
    //TODO possibly implement pathfinder to help with movement toward balls

    public void execute() {
        m_driveSystem.setSpeed(0.45, 0.45);
    }

    // command runs when class is finished, or interrupted
    // used in case something goes wrong to keep wheels from running on end
    public void end() {
        m_driveSystem.stopWheels();
    }
// makes sure the command actually ends
    public boolean isFinished() {
        return true;
    }
}
