package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSystem;

public class DriveToBall extends CommandBase {
    // The subsystem the command runs on
    private final DriveSystem m_driveSystem;

    public DriveToBall(DriveSystem subsystem) {
        m_driveSystem = subsystem;
        /*
         * requires this subsystem to be used
         * //if the required subsystem isn't available,
         * the command it is running on will be interrupted
         */
        addRequirements(m_driveSystem);
    }

    @Override
    // command runs when class first starts, initializes motors
    // It resets both encoders for the motors
    // It sets the motors to 0
    public void initialize() {
        m_driveSystem.setSpeed(0, 0);
        m_driveSystem.m_frontLeft.getEncoder().setPosition(0);
        m_driveSystem.m_frontRight.getEncoder().setPosition(0);
    }

    @Override
    // command runs while class is running, goes until done
    // main bulk of command that will be edited
    // TODO motor values will need to be edited
    // TODO possibly implement pathfinder to help with movement toward balls
    // TODO finetune encoder distances.
    public void execute() {
        // If the robot han't driven X feet, keep driving
        // at 45% power
        if (m_driveSystem.m_frontLeft.getEncoder().getPosition() < 7) {
            m_driveSystem.setSpeed(0.45, 0.45);
        } else // if not, stop the wheels
        // This is used to check if the command is done running
        {
            m_driveSystem.stopWheels();
        }
    }

    // command runs when class is finished, or interrupted
    // used in case something goes wrong to keep wheels from running on end
    public void end() {
        m_driveSystem.stopWheels();
    }

    // sets the condition for the command to end
    // it stops when the wheels stop
    public boolean isFinished() {
        return m_driveSystem.m_leftEncoder.getStopped();
    }
}
