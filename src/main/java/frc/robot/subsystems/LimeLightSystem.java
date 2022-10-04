package frc.robot.subsystems;

//the imports are what this class needs from other classes to function properly
//these can come from other parts of the robot or the internet
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The LimeLightSystem class is responsible for controlling the limelight camera
 * used to detect objects.
 */
public class LimeLightSystem {
    private static NetworkTable table;

    /**
     * constructor for the LimeLightSystem
     */
    public LimeLightSystem() {
        table = NetworkTableInstance.getDefault().getTable("limelight");
    }

    /**
     * @return The x-offset of the target.
     */
    public double getX() {
        return table.getEntry("tx").getDouble(0.0);
    }

    /**
     * @return The y-offset of the target.
     */
    public double getY() {
        return table.getEntry("ty").getDouble(0.0);
    }

    /**
     * @return The area of the target occupied by the camera.
     */
    public double getArea() {
        return table.getEntry("ta").getDouble(0.0);
    }

    /**
     * Uses the Limelight camera's position and the reflective tape on the frc 2022
     * goal
     * to calculate the position of the robot from the goal
     * 
     * @return the distance in inches of the robot from the goal
     */
    public double calculateDistanceFromGoal() {
        // height of the frc 2022 reflective tape off floor
        double goalHeightInches = 104.0;
        // Distance in inches from Limelight to floor
        double limeLightDistOffFloor = 39.25;
        double targetOffsetAngle_Vertical = getY();
        // angle the Limelight is mounted from being vertical
        double limeLightAngleMount = 40;
        // gets the angle LimeLight is to the goal
        double angleToGoalDegrees = limeLightAngleMount + targetOffsetAngle_Vertical;
        // changes angles to radians
        double angleToGoalRadians = angleToGoalDegrees * (3.14159 / 180.0);
        // calculates the distance the LimeLight is to the target
        double distToGoal = (goalHeightInches - limeLightDistOffFloor) / Math.tan(angleToGoalRadians);
        return distToGoal;
    }

    /**
     * puts information on the @SmartDashboard
     */
    public void smartdashboard() {
        SmartDashboard.putNumber("Limelight/x-offset", getX());
        SmartDashboard.putNumber("Limelight/y-offset", getY());
        SmartDashboard.putNumber("Limelight/area", getArea());
        SmartDashboard.putNumber("Limelight/distance from goal", calculateDistanceFromGoal());
    }
}
