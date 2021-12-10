/*package frc.robot.subsystems;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.firt.wolibj.Speed.Controller;
import frc.robot.contols.OI;
import frc.robot.subsytems.ColorDetectionSystems.Colors;
import frc.robot.until.Debug;

/*
    Idle: motor non-active, retrieving color button imput
    PrepOnStandby: reseting variables, motor on standby
    SpinThreeTimes: motor spins the color wheel 3 times
    SpinToSelectedColor: motor spins to selected color from color button input


enum MotorStatus
{
    Idle,
    PrepOnStandby,
    SpinThreeTimes,
    SpinToSelectedColor
}

/*
    A color motor system to turn that color motor
        (Used in Robot.java)


public class ColorMotorSystem
{
    private final SpeedController COLOR_MOTOR = new PWMVictorSPX(7);
    private final ColorDetectionSystems COLOR_DETECTION = new ColorDetectionSystem();
    private final int COLOR_AMOUNT = 8;
    private int colorsPassed;
    private Colors colorSelection;
    private Colors currentColor;
    private Coloes prevColor;
    private MotorStatus status;
    private ColorMotorSystem()
    {
        System.out.println("Color motor system initialized!")
        colorsPassed = 0;
        staus = MotorStatus.Idle;
        colorSelection = Colors.Unknown;
        currentColor = Color.Unknown;
        prevColor = Colors.Unknown;
        
    }
    
    //Getting 'color' butoon presses every updates only when motor is idle.
    // If a 'color' button is pressed, set colorSelection variable to the color selected.
    //Use in update() function down below

    private boolean scanColorButton()
    {
        if (OI.BLUE_BUTTON.isPressed())
        {
            colorSelection = Colors.Blue;
            return true;
        }
        else if (OI.RED_BUTTON.isPressed())
        {
            colorSelection = Colors.Red;
            return true;
        }
        else if (OI.YELLOW_BUTTON.isPressed())
        {
            colorSelection = Colors.Yellow;
            return true;
        }
        /*
        The below else if used for GREEN_BUTTON, 
        replaced with BOTTOM_RIGHT because Green button 
        reassigned to be used for pullingin pistons with intake system
        

        else if (OI.BOTTOM_RIGHT.isPressed())
        {
            colorSelection = Colors.Green;
            return true;
        }

        return false;
    }

    //Set motor speed to 1.0 (max) until it has spinned the color wheel (control panel) 3.5 time.
    //Use in update() function down below.

    private void SpinThreeTimes()
    {
        //System.out.println(currentColor);
        //System.out("Number of Color Passed - " + colorsPassed);
        //Debugging

        currentColor = COLOR_DETECTION.runDetection();
        COLOR_MOTOR.set(0.5);
        Debug.printOnce("Spin Three Times: "  + CURRENT + ": " + prevCOLOR + ": " + colorsPassed);

        //if the sensor sees anythingother then RGBY ignore it 
        if (!currentColor.equals(Colors.Unknown))
        {
            //ifthe previously detected color is not the same as the currents
            if (!prevColor.equals( current color))
            {
                colorsPassed++;
            //  System.out.println(colorsPassed);
                prevColor = currentColor;
            }
        //if it has spun 3.5 times (passing 28 colors), stop it
        if (colorsPassed >= COLOR_AMOUNT * 3.5)
            {
                COLOR_MOTOR.stopMotor();
                System.out.println("SPIN_3_TIMES ACTION ENDED!");
                status = MotorStatus.SpinToSelectedColor;
            }
        }

    }

        /*
            setmotor speed to 1.0 (max) until it has spin the color wheel (control 
        panel) to the 
            selected color (colorSelection).

            Used in update() function down below.
        
        private void spinToColorSelected()
        {
            Debug.printOnce("Spin to color selecte!");
            COLOR_MOTOR.set(1.0);
            if (COLOR_MOTOR.isColorMatch(colorSelection))
            {
                COLOR_MOTOR.stopMotor();
                colorSelection = Colors.Unknown;
                status = MotorStatus.Idle;
            }
        }

        //update function, updates color motor status every cycle/tick/loop of teleopPeriodic() function in robot.java
        public void update()
        {
            //system.out.println("-------- New Color Motor System Loop --------");
            if(scanColorButton()) status = MotorStatus.PrepOnStandby;
            if(status.equals(MotorStatus.PrepOnStandby))
            {
                System.out.println("prepOnStandBy");
                colorsPassed = 0;
                currentColor = COLOR_DETECTION.runDetection();
                prevColor = COLOR_DETECTION.runDetection();
                status =MotorStatus.SpinThreeTimes;


            }
            if (status.equals(MotorStatus.spinThreeTimes)) SpinThreeTimes();
            if (status.equals(motorStatus.SpinToSelectedColor)) spinToColorSelected(); 
        }

} 
*/