package frc.robot.subsystems;

//the imports are what this class needs from other classes to function properly
//these can come from other parts of the robot or the internet
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
//import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The Shooter Class is responsible for controlling the
 * shooting mechanism of the robot
 */
public class Shooter {
    private static WPI_TalonFX shootmotorlefta = new WPI_TalonFX(6);
    private static WPI_TalonFX shootmotorhigher = new WPI_TalonFX(11);
    private static int smartCurrentLimit = 40;
    
    // Below values, 1 for normal -1 for inverted
    //private static int lowerIsInverted = -1;
    //private static int upperIsInverted = -1;


    /**
     * Constructor for the Shooter Class
     */
    public Shooter() {
        // turns off wheels
        motorPower(0);
        shootmotorhigher.setInverted(true);
        shootmotorlefta.setInverted(true);

    }

    /**
     * puts information on the @SmartDashboard
     */
    public void smartdashboard() {
        //SmartDashboard.putNumber("Shooter/Lower/Velocity", m_encoder_shoot1.getVelocity());
        //SmartDashboard.putNumber("Shooter/Upper/Velocity", m_encoder_shoot2.getVelocity());
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
        double power = 0.30;
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
        shootmotorlefta.set(ControlMode.PercentOutput, (power));
        shootmotorhigher.set(ControlMode.PercentOutput, (power));
    }

    /**
     * Activates both motors at seperate powers
     * 
     * @param motorLowerPower
     * @param motorHigherPower
     */
    public void motorPower(double motorLowerPower, double motorHigherPower) {
        shootmotorlefta.set(ControlMode.PercentOutput, motorLowerPower);
        shootmotorhigher.set(ControlMode.PercentOutput, motorHigherPower);
    }
}
