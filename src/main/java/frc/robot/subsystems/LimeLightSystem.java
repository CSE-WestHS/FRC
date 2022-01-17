package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class LimeLightSystem {
    private static NetworkTable table;
    private static NetworkTableEntry tx;
    private static NetworkTableEntry ty;
    private static NetworkTableEntry ta;

    public LimeLightSystem(){
       table = NetworkTableInstance.getDefault().getTable("limelight");
    }
    public void start(){
        tx = table.getEntry("tx");
        ty = table.getEntry("ty");
        ta = table.getEntry("ta");

        SmartDashboard.putNumber("LimelightX", tx.getDouble(0.0));
        SmartDashboard.putNumber("LimelightY", ty.getDouble(0.0));
        SmartDashboard.putNumber("LimelightArea", ta.getDouble(0.0));

        System.out.println(tx + " " + ty + " " + ta);
    }
    
}