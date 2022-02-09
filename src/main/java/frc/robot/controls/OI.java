package frc.robot.controls;

import edu.wpi.first.wpilibj.Joystick;
//import frc.robot.subsystems.ControlModule;

//OI class: A control system class
//OI class checks control inputs such as joysticks and buttons and update the variables accordingly.
// You will find this class being used essentially everywhere that you control with joysticks 
//(exg. what joysticks are used for driving).

public class OI {
    // Joysticks takes in the port number

    public static final Joystick LEFT_STICK = new Joystick(0);
    public static final Joystick RIGHT_STICK = new Joystick(1);

    public static final Button INTAKE_BUTTON = new Button(LEFT_STICK, 2);
    public static final Button SPITOUT_BUTTON = new Button(LEFT_STICK, 3);
    public static final Button SHOOT_BUTTON = new Button(LEFT_STICK, 4);
    // Since index starts at 0, using the codes above as index needs to subtract 1
    private static Button[] buttons = new Button[] {
            INTAKE_BUTTON,
            SPITOUT_BUTTON,
            SHOOT_BUTTON,

    };
    public static void update() {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setStates();
        }
    }
}
