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

    }
}