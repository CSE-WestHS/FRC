/*
 * package frc.robot.subsystems;
 * 
 * import edu.wpi.first.wpilibj.TimedRobot;
 * import com.revrobotics.ColorMatch;
 * import com.revrobotics.ColorMatchResult;
 * import com.revrobotics.ColorSensorV3;
 * import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
 * import edu.wpi.first.wpilibj.I2C;
 * import edu.wpi.first.wpilibj.until.Color;
 * 
 * 
 * // A color detection system to detect color using color sensor.
 * // It's not really a system. It's more like a state class to optimize code
 * and make it look cleaner (DRY)
 * // It is used in ColorMoterSystem.java
 * 
 * 
 * public class ColorDetection
 * 
 * Private final I2C.Port i2cPort = I2C.Port.kOnboard;
 * Private final ColorSensorV3 m_colorSenor = new ColorSensorV3(i2cPort);
 * Private final ColorMatch m_colorMatcher = new ColorMatch();
 * 
 * Private final Color BLUE_TARGET = ColorMatch.makeColor(0.143, 0.452, 0.429);
 * Private final Color GREEN_TARGET = ColorMatch.makeColor(0.267, 0.499, 0.240);
 * Private final Color RED_TARGET = ColorMatch.makeColor(0.525, 0.355, 0.120);
 * Private final Color YELLOW_TARGET = ColorMatch.makeColor(0.361, 0.524,
 * 0.113);
 * 
 * Private final Colors color;
 * 
 * Public ColorDetectionSystem()
 * {
 * m_colorMatcher.addColorMatch(BLUE_TARGET);
 * m_colorMatcher.addColorMatch(GREEN_TARGET);
 * m_colorMatcher.addColorMatch(RED_TARGET);
 * m_colorMatcher.addColorMatch(YELLOW_TARGET);
 * }
 * 
 * //Cheking if the detected color in the colorString matches the intended color
 * //GetColor returns normalized color value from the sensor
 * public boolean isColorMatch(Colors intendedColor)
 * {
 * Color detectedColor = m_colorSenor.getColor();
 * string colorString;
 * ColorMatchResult = m_colorMatcher.matchClosestColor(detectedColor);
 * 
 * }
 * 
 * //running color detection. Should be in the teleopPeriodic method for
 * automatic update.
 * //setting the detected color to colorString
 * public colors runDetection()
 * {
 * Color detectedColor = m_colorSensor.getColor();
 * colorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
 * 
 * if (match.color == BLUE_TARGET)
 * {
 * colorString = "Blue";
 * }
 * else if (match.color == RED_TARGET)
 * {
 * colorString = "Red";
 * }
 * else if (match.Color == GREEN_TARGET)
 * {
 * colorString = "Green";
 * }
 * else if (match.color == YELLOW_TARGET)
 * {
 * colorString = "Yellow";
 * }
 * else
 * {
 * colorString = "Unknown";
 * }
 * 
 * SmartDashboard.putNumber("Red", detectedColor.red);
 * SmartDashboard.putNumber("Green", detectedColor.green);
 * SmartDashboard.putNumber("Blue", detectedColor.blue);
 * SmartDashboard.putNumber("yellow", detectedColor.yellow);
 * SmartDashboard.putNumber("Confidence", match.confidence);
 * SmartDashboard.putNumber("Detected Color", colorString);
 * 
 * public enum ColorMoterSystem
 * {
 * Blue,
 * Red,
 * Green,
 * Yellow,
 * Unknown
 * }
 * }
 * }
 */
