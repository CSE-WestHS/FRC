package frc.robot.controls;

import edu.wpi.first.wpilibj.Joystick;

/**
 * The OI (Operator Input) class is responsible defining basic operator controls
 * used throughout the program.
 */
public class OI {
    // Joysticks takes in the port number

    public static final Joystick leftJoystick = new Joystick(0);
    public static final Joystick rightJoystick = new Joystick(1);

    public static final Button INTAKE_BUTTON = new Button(leftJoystick, 5);
    public static final Button SPITOUT_BUTTON = new Button(leftJoystick, 6);
    public static final Button SHOOT_BUTTON = new Button(rightJoystick, 7);

    // Since index starts at 0, using the codes above as index needs to subtract 1
    private static Button[] buttons = new Button[] {
            INTAKE_BUTTON,
            SPITOUT_BUTTON,
            SHOOT_BUTTON,

    };



    public static final Button intakeButton = new Button(rightJoystick, 3);
    public static final Button ElevatorButton = new Button(rightJoystick, 4);
    public static final Button spitoutButton = new Button(leftJoystick, 6);
}
