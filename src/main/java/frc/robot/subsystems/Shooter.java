package frc.robot.subsystems;

//the imports are what this class needs from other classes to function properly
//these can come from other parts of the robot or the internet
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
//import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The Shooter Class is responsible for controlling the
 * shooting mechanism of the robot
 */
public class Shooter {
    // private DigitalInput m_digitalInput = new DigitalInput(0); // LIDAR sensor
    // private final LIDARSensor m_lidarSensor = new LIDARSensor(m_digitalInput);
    public static CANSparkMax shootMotorLower = new CANSparkMax(6, MotorType.kBrushless);
    private static CANSparkMax shootMotorHigher = new CANSparkMax(10, MotorType.kBrushless);
    private final MotorControllerGroup shootGroup = new MotorControllerGroup(shootMotorLower, shootMotorHigher);

    private final RelativeEncoder m_encoder_shoot1 = shootMotorLower.getEncoder();
    private final RelativeEncoder m_encoder_shoot2 = shootMotorHigher.getEncoder();
    // limit to the amount of electricity the motors can use
    private static int smartCurrentLimit = 40;

    /**
     * Constructor for the Shooter Class
     */
    public Shooter() {
        shootMotorLower.clearFaults();
        shootMotorHigher.clearFaults();

        shootMotorLower.setSmartCurrentLimit(smartCurrentLimit);
        shootMotorHigher.setSmartCurrentLimit(smartCurrentLimit);

        shootGroup.setInverted(false);
        // turns off wheels
        motorPower(0);
    }

    /**
     * puts information on the @SmartDashboard
     */
    public void smartdashboard() {
        SmartDashboard.putNumber("Shooter/Lower/Velocity", m_encoder_shoot1.getVelocity());
        SmartDashboard.putNumber("Shooter/Upper/Velocity", m_encoder_shoot2.getVelocity());
    }

    /**
     * This method takes in a distance reading from the LIDAR
     * Sensor and converts that into a power based on the reading
     * 
     * Tested powers are between 0.75 and 0.9
     * TODO update method once we get LIDAR working
     */
    public double getPower() {
        // Constant to be changed when lidar is tested
        double power = 0.6;
        return power;
    }

    /**
     * Activates both motors at a same set power
     * 
     * @param power = -1.0 to 1.0,
     *              0 is no speed,
     *              1 is full speed forewards,
     *              -1 is full speed backwards.
     */
    public void motorPower(double power) {
        shootGroup.set(power);
    }

    /**
     * Activates both motors at seperate powers
     * 
     * @param motorLowerPower
     * @param motorHigherPower
     */
    public void motorPower(double motorLowerPower, double motorHigherPower) {
        shootMotorLower.set(motorLowerPower);
        shootMotorHigher.set(motorHigherPower);
    }
}
