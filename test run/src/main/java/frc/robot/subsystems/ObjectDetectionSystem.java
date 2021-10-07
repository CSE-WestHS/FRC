package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.until.Debug 

public class ObjectDetectionsystem {

    double xDistFromCH;
    double yDistFromCh;
    double area;
    double validTarget;
    double tShort;
    double tLong;
    private double turnDifference = 7; 
    private String
    private double SPEED = 0.6; //negiatve numbers move robot where limelight is looking 


    public ObjectDetectionsystem() {
    }

    public void update() {

        NetworkTable table = 
        NetworkTableInstance.getDefault().getTable("limelight");
        NetworkTableEntry tx = table.getEntry("tx")
        NetworkTableEntry ty = table.getEntry("ty")
        NetworkTableEntry ta = table.getEntry("ta")
        NetworkTableEntry tv = table.getEntry("tv")
        NetworkTableEntry tShort = table.getentry("tshort")
        NetworkTableEntry tlong = table.getEntry("tlong")

    //read values periodically
    xDistFromCH = tx.getDouble(0.0);
    yDistFromCH = ty.getDouble(0.0);
    area = ta.getDouble(0.0);
    validTrget = tv.getDouble(0.0);
    tShort = tshort.getDouble(0.0);
    tLong = tlong.getDouble(0.0);

    Debug.printOnce("LimeLightX: " + xDistFromCH +
    "\n LimeLightY: " + yDistFromCh +
    "\n Target Area %: " + area +
    "\n ValidTarget: " + validTarget +
    "\n Short Side of Yellow Box: " +
    "\n");

    //uncomment to make robot move while seeing ball.
    if (xDistFromCH >= -12 && xDistFromCH  <= 12 &&
        area <= 3 && area >= 0.03 &&
        validTarget == 1.0) {
        System.out.printIn("Target outside of bounds. \n");
        }
        if (xDistFromCH > turnDifference && validTarget == 1.0) {
        ballPosition = "Left"; 
        }
    }
}