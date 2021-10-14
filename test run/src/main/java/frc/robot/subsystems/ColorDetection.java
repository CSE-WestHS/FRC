package frc.robot.subsystems;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.until.Color;

/* 
    A color detection system to detect color using color sensor.
    It's not really a system. It's more like a state class to optimize code and make it look cleaner (DRY)
    It is used in ColorMoterSystem.java
*/

public class ColorDetection 
{
    Private final I2C.Port i2cPort = I2C.Port.kOnboard;
    Private final ColorSensorV3 m_colorSenor = new ColorSensorV3(i2cPort);
    Private final ColorMatch m_colorMatcher = new ColorMatcher();

    Private final Color BLUE_TARGET = ColorMatch.makeColor(0.143, 0.452, 0.429);
    Private final Color GREEN_TARGET = ColoerMatch.makeColor(0.267, 0.499, 0.240);
    Private final Color RED_TARGET = ColoerMatch.makeColor(0.525, 0.355, 0.120);
    Private final Color YELLOW_TARGET = ColoerMatch.makeColor(0.361, 0.524, 0.113);

    Private final Colors color;

    Public ColorDetectionSystem()
    {
        m_colorMatcher.addColorMatch(BLUE_TARGET);
        m_colorMatcher.addColorMatch(GREEN_TARGET);
        m_colorMatcher.addColorMatch(RED_TARGET);
        m_colorMatcher.addColorMatch(YELLOW_TARGET);
    }
    
    //Cheking if the detected color in the colorString matches the intended color

    public boolean isColorMatch(Colors intendedColor)
    {
        runDetection();
        return intendedColor.equals(color);
    }

    //running color detection. Should be in the teleopPeriodic method for automatic update.
    //setting the detected color to colorString
    public colors runDetection()
    {
        Color detectedColor = m_colorSensor.getColor();
        colorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

        if (match.color == BLUE_TARGET)
        {
            color = Colors.Blue;
        }
        else if (match.color == RED_TARGET)
        {
            color = Colors.Red;
        }
        else if (match.Color == GREEN_TARGET)
        {
            color = Colors.Green;
        }
        else if (match.color == YELLOW_TARGET)
        {
            color == Colors.Yellow;
        }
        else
        {
            color = Colors.Unknown;
        }

        public enum ColorMoterSystem
        {
            Blue,
            Red,
            Green,
            Yellow,
            Unknown
        }
    }
}
