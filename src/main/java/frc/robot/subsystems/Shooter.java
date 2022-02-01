package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.controls.OI;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
/*
This is a skeleton of the shooter system
We plan to update this once we get the limelight
to work. We will put in calculations for distance
 to power for the shooting. We will also add and
 rename motors as needed
*/
public class Shooter extends SubsystemBase{
    private DigitalInput m_digitalInput = new DigitalInput(0); // LIDAR sensor
    private final LIDARSensor m_lidarSensor = new LIDARSensor(m_digitalInput);
    public static CANSparkMax shootMotor1 = new CANSparkMax(9, MotorType.kBrushless);;
    public static CANSparkMax shootMotor2 = new CANSparkMax(10, MotorType.kBrushless);;

    public Shooter() {
        shootMotor1.setInverted(true);
        shootMotor1.set(0);
        shootMotor2.set(0);
    }

    /**
     * Activates motors at a set power
     * 
     * @param power = -1 to 1
     */
    public void setPower(double power) {
        // The mins and maxes will not be -1 and 1, this is too much power on the motors
        if (OI.SHOOT_BUTTON.isHold()) {

            shootMotor1.set(power);
            shootMotor2.set(power);

        } else {
            shootMotor1.set(0);
            shootMotor2.set(0);
        }
    }

    // This method's main use is in the shooting mechanism
    /*
     * This method takes in a distance reading from the LIDAR
     * Sensor and converts that into a power based on the reading
     */
    public double getPower() {
        double distanceCM;
        double power;

        // sets distance equal to the reading of the Lidar sensor in cm
        // distances may be updated to feet
        distanceCM = m_lidarSensor.getDistance();
        // if the reading is between 0 and 1 meter
        if (distanceCM >= 0 && distanceCM <= 100) {
            power = 0.2;
        }
        // if reading is between 1 and 2 meters
        else if (distanceCM > 100 && distanceCM <= 200) {
            power = 0.3;
        }
        // if reading is between 2 and 3 meters
        else if (distanceCM > 200 && distanceCM <= 300) {
            power = 0.4;
        }
        // if reading is between 3 and 4 meters
        else if (distanceCM > 300 && distanceCM <= 400) {
            power = 0.5;
        }
        // if reading is greater than 4 meters
        else {
            power = 0.6;
        }
        return power;
    }
}
