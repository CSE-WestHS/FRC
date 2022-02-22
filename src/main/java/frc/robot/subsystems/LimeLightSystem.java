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

    public double getX() {
        return table.getEntry("tx").getDouble(0.0);
    }

    public double getY() {
        return table.getEntry("ty").getDouble(0.0);
    }

    public double getArea() {
        return table.getEntry("ta").getDouble(0.0);
    }
}
