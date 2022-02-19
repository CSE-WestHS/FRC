package frc.robot.subsystems;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.controls.OI;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Intake {
    /*
     * this is a proof of concept. we will most likely
     * change speeds, the number of motors, types of motors, etc
     * when the build is done enough to where we can know what
     * we need to change, we will update the code to represent
     * the motors used on the conveyor system
     */
    private static CANSparkMax intake1 = new CANSparkMax(7, MotorType.kBrushless);
    private static CANSparkMax intake2 = new CANSparkMax(9, MotorType.kBrushless);
    private static int smartCurrentLimit = 40;

    // Slew rate limiters to make inputs more gentle; 1/3 sec from 0 to 1.
    private final SlewRateLimiter m_linearVelocityLimiter = new SlewRateLimiter(1 / 3.0);

    public Intake() {
        intake1.clearFaults();
        intake2.clearFaults();

        intake1.setSmartCurrentLimit(smartCurrentLimit);
        intake2.setSmartCurrentLimit(smartCurrentLimit);

        intake2.setInverted(true);
    }

    public void stopMotors() {
        intake1.set(0);
        intake2.set(0);
    }

    public void runMotors(double motorSpeed) {
        final double xSpeed = -m_linearVelocityLimiter.calculate(motorSpeed) * 2;

        intake1.set(motorSpeed);
        intake2.set(xSpeed);
    }

    public void buttonIntake() {
        SmartDashboard.putNumber("Intake1/Voltage", intake1.getBusVoltage());
        SmartDashboard.putNumber("Intake1/Temperature", intake1.getMotorTemperature());
        SmartDashboard.putNumber("Intake1/Output", intake1.getAppliedOutput());

        SmartDashboard.putNumber("Intake2/Voltage", intake2.getBusVoltage());
        SmartDashboard.putNumber("Intake2/Temperature", intake2.getMotorTemperature());
        SmartDashboard.putNumber("Intake2/Output", intake2.getAppliedOutput());

        if (OI.intakeButton.isPressed()) {
            runMotors(0.45);
        } else if (OI.spitoutButton.isPressed()) {
            runMotors(-0.45);
        } else {
            stopMotors();
        }
    }
}
