package frc.robot.commands;

import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ShootTwoBalls extends CommandBase {
    // requires intake and shooter
    private final Shooter m_Shooter;
    private final Intake m_Intake;
    private double power;

    public ShootTwoBalls(Shooter subsystem, Intake intake_system) {
        m_Shooter = subsystem;
        m_Intake = intake_system;

        addRequirements(m_Shooter, m_Intake);
    }

    @Override
    // command runs when class first starts
    // turns off motors and resets encoders
    public void initialize() {
        m_Intake.stopMotors();
        m_Shooter.setPower(0);
        Intake.sideFeed.getEncoder().setPosition(0);
        Intake.upFeed.getEncoder().setPosition(0);
    }

    // command runs while class is running, goes until done
    // main bulk of command that will be edited
    // TODO finetune speeds for intake. Possibly change what motors run when.
    // TODO finetune encoder distances.
    public void execute() {
        // while the intake distance is less than 5 ticks (360* per tick)
        // run all intake motors
        // if distance is less than 10
        // run just upFeed Motors
        // else, turn off all motors (used for end condition)
        if (m_Intake.m_sideEncoder.getDistance() < 5)
            m_Intake.runMotors(0.25, 0.25);
        else if (m_Intake.m_upEncoder.getDistance() < 10) {
            m_Intake.runMotors(0, 0.25);
        } else {
            m_Intake.runMotors(0, 0);
        }
        power = m_Shooter.getPower();
        m_Shooter.setPower(power);
    }

    // command runs when class is finished, or interrupted
    public void end() {
        m_Intake.stopMotors();
        m_Shooter.setPower(0);
    }

    // sets condition to end command
    // ends if intake motors are off
    public boolean isFinished() {
        return m_Intake.m_upEncoder.getStopped();
    }
}
