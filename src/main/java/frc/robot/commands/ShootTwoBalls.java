package frc.robot.commands;

import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ShootTwoBalls extends CommandBase {

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
    public void initialize() {
        m_Intake.stopMotors();
        m_Shooter.setPower(0);
        m_Intake.enc_upFeed.reset();
        m_Intake.enc_sideFeed.reset();
        m_Shooter.enc_Shoot.reset();
    }

    // command runs while class is running, goes until done
    // main bulk of command that will be edited
    // TODO finetune speeds for intake. Possibly change what motors run when.
    //TODO finetune encoder distances.
    public void execute() {
        if(m_Intake.enc_sideFeed.getDistance() < 5)
        m_Intake.runMotors(0.25, 0.25);
        else if(m_Intake.enc_upFeed.getDistance() < 10){
            m_Intake.runMotors(0, 0.25);
        }
        else
        {
            m_Intake.runMotors(0,0);
        }
        power = m_Shooter.getPower();
        m_Shooter.setPower(power);
    }

    // command runs when class is finished, or interrupted
    public void end() {
        m_Intake.stopMotors();
        m_Shooter.setPower(0);
    }

    // makes sure the command actually ends
    public boolean isFinished() {
        //TODO change return value when encoders are implemented;
        return m_Intake.enc_upFeed.getStopped();
    }
}
