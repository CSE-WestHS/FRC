package frc.robot.controls;
import com.revrobotics.CANSparkMax;
public class Encoder {
    public final CANSparkMax m_encoder;
    public final double conversion_factor;
    public Encoder(CANSparkMax encoder, double conversion) {
        m_encoder = encoder;
        conversion_factor = conversion;
        //this is used to make the getPosition command convert to feet, meters, etc
        //put in the circumference of the circle (2*pi*r) 
        //divided by the unit of measurement (feet in our case)
        //drive wheels conversion factor is 1.57
        //shooter wheels are 1.04
        //conveyor wheels are 0.785
        m_encoder.getEncoder().setPositionConversionFactor(conversion);
 }
    //checks if motor is not running
    public boolean getStopped()
    {
        return m_encoder.getEncoder().getVelocity() == 0;
    }
    //resets the encoder position
    public void restartEncoder()
    {
        m_encoder.getEncoder().setPosition(0);
    }
    //gets the distance traveled by motor
    //default is rotations, changed to unit of measurement (feet) when conversion factor is used
    public double getDistance()
    {
        return m_encoder.getEncoder().getPosition();
    }
    //gets how fast motor is going
    public double getSpeed()
    {
      return m_encoder.getEncoder().getVelocity();
    }
}
