package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The LimeLightSystem class is responsible for controlling the limelight camera
 * used to detect objects.
 */
public class LimeLightSystem {
    private static NetworkTable table;
    private static NetworkTableEntry tx;
    private static NetworkTableEntry ty;
    private static NetworkTableEntry ta;

    public LimeLightSystem() {
        table = NetworkTableInstance.getDefault().getTable("limelight");
    }

    public void start() {
        tx = table.getEntry("tx");
        ty = table.getEntry("ty");
        ta = table.getEntry("ta");

        SmartDashboard.putNumber("LimelightX", tx.getDouble(0.0));
        SmartDashboard.putNumber("LimelightY", ty.getDouble(0.0));
        SmartDashboard.putNumber("LimelightArea", ta.getDouble(0.0));
 Shoot-Skeleton
    }
    public double getX()
    {
        return table.getEntry("tx").getDouble(0.0);
    }
    public double getY()
    {
        return table.getEntry("ty").getDouble(0.0);
    }
    public double getArea()
    {
        return table.getEntry("ta").getDouble(0.0);
    }
 main
}
