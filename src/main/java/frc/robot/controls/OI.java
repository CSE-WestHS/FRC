package frc.robot.controls;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.JoystickButton;
//import frc.robot.subsystems.ControlModule;

//OI class: A control system class
//OI class checks control inputs such as joysticks and buttons and update the variables accordingly.
// You will find this class being used essentially everywhere (unless it is not).

public class OI {
    // Joysticks takes in the port number

    public static final Joystick leftJoystick = new Joystick(0);
    public static final Joystick rightJoystick = new Joystick(1);

    public static final Button intakeButton = new Button(leftJoystick, 5);
    public static final Button spitoutButton = new Button(leftJoystick, 6);
}
