package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.controls.OI;
import frc.robot.util.Debug;
import edu.wpi.first.wpilibj.Timer;


public class Winch {
    public CANSparkMax winchMotor = new CANSparkMax(8, MotorType.kBrushless);
    private int smartCurrentLimit = 40;
    public Timer m_Timer = new Timer();
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

    public void smartdashboard() {
        SmartDashboard.putNumber("Elevator/Voltage", winchMotor.getBusVoltage());
        SmartDashboard.putNumber("Elevator/Temperature", winchMotor.getMotorTemperature());
        SmartDashboard.putNumber("Elevator/Output", winchMotor.getAppliedOutput());
    }

    public void buttonWinch() {

        if (OI.liftButton.isPressed()) {
            m_Timer.reset();
            m_Timer.start();
           // winchMotor.getEncoder().setPosition(0);
             boolean winchUp = winchMotor.getEncoder().getPosition() >= 3;
            Debug.printOnce(String.valueOf(winchMotor.getEncoder().getPosition()));
             if(!winchUp) {
            while (winchMotor.getEncoder().getPosition() < 14 && m_Timer.get() < 2) {
                winchMotor.set(0.4);
                Debug.printOnce(String.valueOf(winchMotor.getEncoder().getPosition()));
                 }
          //   winchMotor.getEncoder().setPosition(0);
                winchMotor.set(0);
                m_Timer.stop();
            }
        }
            else if (OI.lowerButton.isPressed()){
                m_Timer.reset();
                m_Timer.start();
                Debug.printOnce(String.valueOf(winchMotor.getEncoder().getPosition()));
                winchMotor.getEncoder().setPosition(0);
                // boolean winchUp = winchMotor.getEncoder().getPosition() >= 3;
                // if(!winchUp) {
                while (winchMotor.getEncoder().getPosition() > -14 && m_Timer.get() < 2) {
                    winchMotor.set(-0.4);
                    Debug.printOnce(String.valueOf(winchMotor.getEncoder().getPosition()));
                    // }
                    // winchMotor.getEncoder().setPosition(0);
                }
                winchMotor.set(0);
                winchMotor.getEncoder().setPosition(0);
                m_Timer.stop();
                Debug.printOnce(String.valueOf(winchMotor.getEncoder().getPosition()));
            } else {
            winchMotor.set(0);
        }
    }
}