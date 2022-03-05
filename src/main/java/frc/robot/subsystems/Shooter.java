package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.controls.OI;

public class Shooter {
    //private DigitalInput m_digitalInput = new DigitalInput(0); // LIDAR sensor
    //private final LIDARSensor m_lidarSensor = new LIDARSensor(m_digitalInput);
    private static CANSparkMax shootMotor1 = new CANSparkMax(6, MotorType.kBrushless);
    private static CANSparkMax shootMotor2 = new CANSparkMax(10, MotorType.kBrushless);
    private final MotorControllerGroup shootGroup = new MotorControllerGroup(shootMotor1, shootMotor2);

    private final RelativeEncoder m_encoder_shoot1 = shootMotor1.getEncoder();
    private final RelativeEncoder m_encoder_shoot2 = shootMotor2.getEncoder();

    private static int smartCurrentLimit = 40;

    public Shooter() {
        shootMotor1.clearFaults();
        shootMotor2.clearFaults();

        shootMotor1.setSmartCurrentLimit(smartCurrentLimit);
        shootMotor2.setSmartCurrentLimit(smartCurrentLimit);

        shootGroup.setInverted(true);
    }

    public void smartdashboard() {
        SmartDashboard.putNumber("Shooter/Lower/Velocity", m_encoder_shoot1.getVelocity());
        SmartDashboard.putNumber("Shooter/Upper/Velocity", m_encoder_shoot2.getVelocity());
    }

    /*
     * This method takes in a distance reading from the LIDAR
     * Sensor and converts that into a power based on the reading
     * 
     * Tested powers are between 0.75 and 0.9
     */
    public double getPower() {
        // Constant to be changed when lidar is tested
        double power = 0.825;

        // sets distance equal to the reading of the Lidar sensor in cm
        // distances may be updated to feet
        /*
         * distanceCM = m_lidarSensor.getDistance();
         * // if the reading is between 0 and 1 meter
         * if (distanceCM >= 0 && distanceCM <= 100) {
         * power = 0.2;
         * }
         * // if reading is between 1 and 2 meters
         * else if (distanceCM > 100 && distanceCM <= 200) {
         * power = 0.3;
         * }
         * // if reading is between 2 and 3 meters
         * else if (distanceCM > 200 && distanceCM <= 300) {
         * power = 0.4;
         * }
         * // if reading is between 3 and 4 meters
         * else if (distanceCM > 300 && distanceCM <= 400) {
         * power = 0.5;
         * }
         * // if reading is greater than 4 meters
         * else {
         * power = 0.6;
         * }
         */
        return power;
    }

    /**
     * Activates motors at a set power
     * 
     * @param power = -1.0 to 1.0
     */
    public void shootButtonControl() {
        if (OI.shootButton.isPressed()) {
            shootGroup.set(getPower());
        } else {
            shootGroup.set(0);
        }
    }
}
