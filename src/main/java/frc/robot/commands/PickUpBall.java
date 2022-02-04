package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class PickUpBall extends CommandBase {
    private Intake m_Intake;

    // requires intake system
    public PickUpBall(Intake subsystem) {
        m_Intake = subsystem;
        addRequirements(m_Intake);
    }

    @Override
    // command runs when class first starts
    // when it starts, reset encoder and turn off motors
    public void initialize() {
        m_Intake.stopMotors();
        m_Intake.enc_sideFeed.reset();

    }

    // command runs while class is running, goes until done
    // main bulk of command that will be edited
    // TODO update values once we know how fast we need the intake motors to run
    // TODO finetune encoder distances.
    public void execute() {
        // if the motor han't spun for 12 ticks (360*)
        // run the intake and side motor at 45% power
        // if else, turn motors off(used to check if command is done)
        if (m_Intake.enc_sideFeed.getDistance() < 12) {
            m_Intake.runMotors(0.45, 0);
        } else {
            m_Intake.stopMotors();
        }
    }

    // command runs when class is finished, or interrupted
    public void end() {
        m_Intake.stopMotors();
    }

    // sets condition for command to be done
    // if the mots are stopped
    public boolean isFinished() {
        return m_Intake.enc_sideFeed.getStopped();
    }
}