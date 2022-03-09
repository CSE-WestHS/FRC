package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The LimeLightSystem class is responsible for controlling the limelight camera
 * used to detect objects.
 */
public class LimeLightSystem {
    private static NetworkTable table;

    public LimeLightSystem() {
        table = NetworkTableInstance.getDefault().getTable("limelight");
    }

    public void smartdashboard() {
        SmartDashboard.putNumber("Limelight/x-offset", getX());
        SmartDashboard.putNumber("Limelight/y-offset", getY());
        SmartDashboard.putNumber("Limelight/area", getArea());
    }

    /**
     * @return The x-offset of the target.
     */
    public double getX()
    {
        return table.getEntry("tx").getDouble(0.0);
    }

    /**
     * @return The y-offset of the target.
     */
    public double getY()
    {
        return table.getEntry("ty").getDouble(0.0);
    }

    /**
     * @return The area of the target occupied by the camera.
     */
    public double getArea()
    {
        return table.getEntry("ta").getDouble(0.0);
    }
    public double calculateDistanceFromGoal() {
        //height of the frc 2022 reflective tape off floor
        double goalHeightInches = 104.0;
        //Distance in inches from Limelight to floor
        double limeLightDistOffFloor = 38.0;
        double targetOffsetAngle_Vertical = getY();
        //angle the Limelight is mounted from being vertical
        double limeLightAngleMount = 33;
        //gets the angle LimeLight is to the goal
        double angleToGoalDegrees = limeLightAngleMount + targetOffsetAngle_Vertical;
        //changes angles to radians
        double angleToGoalRadians = angleToGoalDegrees * (3.14159 / 180.0);
        //calculates the distance the LimeLight is to the target
        double distToGoal = (goalHeightInches - limeLightDistOffFloor)/Math.tan(angleToGoalRadians);
        return distToGoal;
    }
}
