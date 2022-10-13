package frc.robot.subsystems;

//the imports are what this class needs from other classes to function properly
//these can come from other parts of the robot or the internet
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.controls.OperatorInput;
import frc.robot.util.Debug;
import edu.wpi.first.wpilibj.Timer;

/**
 * The Winch Class is responsible for controling the
 * mechanism that moves the intake arm up and down
 */
public class Winch {
    public CANSparkMax winchMotor = new CANSparkMax(8, MotorType.kBrushless);
    // limit for the amount of electricity the motors can use
    private int smartCurrentLimit = 40;
    public Timer m_Timer = new Timer();

    /**
     * Constructor for the Winch Class
     */
    public Winch() {
        winchMotor.clearFaults();
        // clearFaults clears any sticky faults that may occur in the CANSparkMaxes

        /*
         * sticky faults are errors in the CANSparkMax hardware
         * not updating after the error is resolved
         */
        winchMotor.setSmartCurrentLimit(smartCurrentLimit);
        // turns off wheels
        winchMotor.set(0);
        winchMotor.getEncoder().setPosition(0);
    }

    /**
     * puts information on the SmartDashboard
     */
    public void smartdashboard() {
        SmartDashboard.putNumber("Elevator/Voltage", winchMotor.getBusVoltage());
        SmartDashboard.putNumber("Elevator/Temperature", winchMotor.getMotorTemperature());
        SmartDashboard.putNumber("Elevator/Output", winchMotor.getAppliedOutput());
    }

    /**
     * Controls the winch motor through joystick input
     */
    public void buttonWinch() {
        /*
         * if this putton is pressed , raise the winch motor
         * until it reaches 14 rotation or 3 seconds on the timer
         */
        if (OperatorInput.liftButton.isPressed() || (OperatorInput.controller.getPOV() == 0)) {
            m_Timer.reset();
            m_Timer.start();

            boolean winchUp = winchMotor.getEncoder().getPosition() >= 3;
            Debug.printOnce(String.valueOf(winchMotor.getEncoder().getPosition()));
            /*
             * the timer usage is good practice incase a sensor stops working
             * to prevent a program from running indefinitely
             */
            if (!winchUp) {
                while (winchMotor.getEncoder().getPosition() < 14 && m_Timer.get() < 2) {
                    winchMotor.set(0.4);
                    Debug.printOnce(String.valueOf(winchMotor.getEncoder().getPosition()));
                }
                winchMotor.set(0);
                m_Timer.stop();
            }
            /*
             * if this putton is pressed,
             * lower the winch motor
             * until it reaches 14 rotation
             * or 3 seconds on the timer
             */
        } else if (OperatorInput.lowerButton.isPressed()|| (OperatorInput.controller.getPOV() == 180)) {
            m_Timer.reset();
            m_Timer.start();

            Debug.printOnce(String.valueOf(winchMotor.getEncoder().getPosition()));
            winchMotor.getEncoder().setPosition(0);

            while (winchMotor.getEncoder().getPosition() > -14 && m_Timer.get() < 2) {
                winchMotor.set(-0.4);
                Debug.printOnce(String.valueOf(winchMotor.getEncoder().getPosition()));

            }

            winchMotor.set(0);
            winchMotor.getEncoder().setPosition(0);
            m_Timer.stop();

            Debug.printOnce(String.valueOf(winchMotor.getEncoder().getPosition()));

            // if no buttons are pressed, turn off the winch
        } else {
            winchMotor.set(0);
        }
    }
}
