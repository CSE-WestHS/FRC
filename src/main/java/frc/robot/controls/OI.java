package frc.robot.controls;

import edu.wpi.first.wpilibj.Joystick;

/**
 * The OI (Operator Input) class is responsible defining basic operator controls
 * used throughout the program.
 */
public class OI {
    public static final Joystick leftJoystick = new Joystick(1);
    public static final Joystick rightJoystick = new Joystick(0);

    public static final Button elevatorButton = new Button(rightJoystick, 4);
    public static final Button elevatorSpitoutbutton = new Button(leftJoystick, 3);
    public static final Button intakeButton = new Button(rightJoystick, 3);
    public static final Button shootButton = new Button(rightJoystick, 1);
    public static final Button shootButton2 = new Button(leftJoystick, 1);
    public static final Button turnButton = new Button(leftJoystick, 2);
    public static final Button spitoutButton = new Button(rightJoystick, 4);
    public static final Button adjustButton = new Button(rightJoystick, 2);
}
