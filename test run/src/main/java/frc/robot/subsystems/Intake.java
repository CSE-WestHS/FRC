package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.util.Debug;
import frc.robot.controls.OI;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Intake {
    private static CANSparkMax inMotor1 = new CANSparkMax(2, MotorType.kBrushless);;
    private static CANSparkMax inMotor2 = new CANSparkMax(3, MotorType.kBrushless);;

    public Intake() 
    {
        inMotor1.set(0);
        inMotor2.set(0);
    
        inMotor1.restoreFactoryDefaults();
        inMotor2.restoreFactoryDefaults();
    }
   
    public static void update() 
    {
        //activates intake when pressed
        if (OI.INTAKE_BUTTON.isHold()) 
        {
            inMotor1.set(-0.15);
            inMotor2.set(.15);  
            System.out.println("intake button pressed");
        } 
        else 
        {
            inMotor1.set(0);
            inMotor2.set(0);    
        
        }   
    }   
}