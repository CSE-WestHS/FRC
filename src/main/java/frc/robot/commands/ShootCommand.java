package frc.robot.commands;

import frc.robot.controls.OI;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.Timer;

public class ShootCommand {
    private static Elevator m_elevator;
    private static Shooter m_shooter;
    private static Intake m_intake;

    private double elevatorPower = 0.65;

    private static Timer m_tTimer = new Timer();

    public ShootCommand(Elevator elevator, Shooter shooter, Intake intake) {
        m_elevator = elevator;
        m_shooter = shooter;
        m_intake = intake;
    }

    public void shootOneBall() {
        m_tTimer.start();
        while (m_tTimer.get() < 1) {
            m_shooter.motorPower(m_shooter.getPower());
        }
        while (m_tTimer.get() < 3) {
            m_shooter.motorPower(m_shooter.getPower());
            m_elevator.motorPower(elevatorPower);
        }
        m_tTimer.stop();
        m_tTimer.reset();
        m_shooter.motorPower(0);
        m_elevator.motorPower(0);
    }

    public void autonomousShoot() {
        shootOneBall();
        m_intake.runLowerMotors(0.65);
        shootOneBall();
        m_intake.stopMotors();
    }

    /**
     * Trigger shoot command sequence via joystick button.
     */
    public void shootButtonControl() {
        if (OI.shootButton.isPressed() || OI.shootButton2.isPressed()) {
            m_tTimer.start();
            if (m_tTimer.get() < 1) {
                m_shooter.motorPower(m_shooter.getPower());
            } else {
                m_shooter.motorPower(m_shooter.getPower());
                m_elevator.motorPower(elevatorPower);
            }
        } else {
            m_tTimer.reset();
            m_shooter.motorPower(0);
            m_elevator.motorPower(0);
        }
    }
}
