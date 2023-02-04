package frc.robot.commands;

//the imports are what this class needs from other classes to function properly
//these can come from other parts of the robot or the internet
import frc.robot.controls.OperatorInput;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Shooter;
import frc.robot.util.Debug;
import frc.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.Timer;

/**
 * This class uses the Elevator, Shooter, and Intake subsystems
 * to perform autonomous commands along with a command
 * that shoots moves balls from the elevator to the shooter after it warms up
 */
public class ShootCommands {
    // this class needs an Elevator, Shooter, and Intake to run properly
    private static Elevator m_elevator;
    private static Shooter m_shooter;
    private static Intake m_intake;
    // speed at which the elevator will be running
    private double elevatorPower = 0.65;

    private static Timer m_Timer = new Timer();

    // Constructor for the ShootCommand Class
    public ShootCommands(Elevator elevator, Shooter shooter, Intake intake) {
        m_elevator = elevator;
        m_shooter = shooter;
        m_intake = intake;
    }

    /**
     * This method moves one ball from the elvator
     * to the shooter to shoot it out after it warms up
     */
    public void shootOneBall() {
        m_Timer.reset();
        m_Timer.start();

        /*
         * the shooter motor is run first without the elevator
         * to make it so when the balls reach the shooter from the elevator
         * they are shot out at the right speed
         */

        while (m_Timer.get() < 1.5 ) {
            m_shooter.motorPower(m_shooter.getPower(), 0);
        }
        /*
         * runs the elevator motors with the shooter
         * after 1 second for 3 seconds
         */
        m_Timer.reset();
        while (m_Timer.get() < 2.5) {
            m_shooter.motorPower(0.4, 0.4);
            m_elevator.motorPower(elevatorPower);
        }
        m_Timer.stop();
        m_Timer.reset();
        // without these lines, the motors would run forever
        m_shooter.motorPower(0);
        m_elevator.motorPower(0);
    }

    /**
     * This method shoots the 2 balls it collects during autonomous, one at a time
     */
    public void autonomousShoot() {
        shootOneBall();
        // runs the intake motors to get the
        // collected ball into the elevator to be shot
        m_intake.runMotors(0.65);
        shootOneBall();
        m_intake.stopMotors();
    }

    /**
     * Trigger shoot command sequence via joystick buttons.
     */
    public void shootButtonControl() {
        /*
         * while either button is pressed
         * runs the shooter motor to warm up,
         * then the elevator to bring the balls up to be shot
         */
        if (OperatorInput.shootLowGoalButton.isPressed() ||  (OperatorInput.controller.getPOV() == 180)) {

            m_Timer.start();
       
           // if (m_shooter.m_encoder_shoot1.getVelocity() < 2500) {
                m_shooter.motorPower(0.2, 0.17);
            //} else {
              //  m_shooter.motorPower(m_shooter.getPower(), 0);
               m_elevator.motorPower(elevatorPower);
           // }

        } else if (OperatorInput.shootHighGoalButton.isPressed() || (OperatorInput.controller.getPOV() == 0)) {
            m_Timer.start();
           // if (m_shooter.m_encoder_shoot1.getVelocity() < 2500) {
                m_shooter.motorPower(0.4,0.5);
                // 12.5 feet for optimal shot
                Debug.printOnce("elevator not fireing");
          //  } else {Debug.printOnce("elevator is fireing"+ Shooter.shootMotorLower.getEncoder().getVelocity());

               // m_shooter.motorPower(m_shooter.getPower());
                m_elevator.motorPower(elevatorPower);
          //  }

        } else {
            // while no buttons are pressed, the shoot motors don't move
            m_Timer.reset();
            m_shooter.motorPower(0);
        } 
    }
}
