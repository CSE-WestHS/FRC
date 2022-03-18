package frc.robot.commands;

//the imports are what this class needs from other classes to function properly
//these can come from other parts of the robot or the internet
import frc.robot.controls.OI;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.Timer;

public class ShootCommand {
    // this class needs an Elevator, Shooter, and Intake to run properly
    private static Elevator m_elevator;
    private static Shooter m_shooter;
    private static Intake m_intake;
    // speed at which the elevator will be running
    private double elevatorPower = 0.65;

    private static Timer m_Timer = new Timer();

    // Constructor for the ShootCommand Class
    public ShootCommand(Elevator elevator, Shooter shooter, Intake intake) {
        m_elevator = elevator;
        m_shooter = shooter;
        m_intake = intake;
    }

    // This will turn on the shooter motor, wait one second,
    // then turn on the elevator motor as well, wait 2 seconds
    // then turn off motors and reset timer
    public void shootOneBall() {
        m_Timer.reset();
        m_Timer.start();
        while (m_Timer.get() < 1) {
            m_shooter.motorPower(m_shooter.getPower());
        }
        m_Timer.reset();
        while (m_Timer.get() < 3) {
            m_shooter.motorPower(m_shooter.getPower());
            m_elevator.motorPower(elevatorPower);
        }
        m_Timer.stop();
        m_Timer.reset();
        m_shooter.motorPower(0);
        m_elevator.motorPower(0);
    }

    // this shoots one ball, turns on intake,
    // then shoots another ball, then turns off intake
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
        // if either shoot button is pressed
        // turn on timer, turn on the shoot motors
        // wait 1 second, turn on elevator motors as well as shoot motors
        // if the button is not pressed,
        // turn off the motors and reset the timer
        if (OI.shootButton.isPressed() || OI.shootButton2.isPressed()) {
            m_Timer.start();
            if (m_Timer.get() < 1) {
                m_shooter.motorPower(m_shooter.getPower());
            } else {
                m_shooter.motorPower(m_shooter.getPower());
                m_elevator.motorPower(elevatorPower);
            }
        } else {
            m_Timer.reset();
            m_shooter.motorPower(0);
        }
    }
}
