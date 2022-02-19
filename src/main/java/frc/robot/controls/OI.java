package frc.robot.controls;

import edu.wpi.first.wpilibj.Joystick;
//import frc.robot.subsystems.ControlModule;

//OI class: A control system class
//OI class checks control inputs such as joysticks and buttons and update the variables accordingly.
// You will find this class being used essentially everywhere (unless it is not).

public class OI {
    // Joysticks takes in the port number

    public static final Joystick LEFT_STICK = new Joystick(0);
    public static final Joystick RIGHT_STICK = new Joystick(1);

    public static final Button INTAKE_BUTTON = new Button(LEFT_STICK, 5);
    public static final Button SPITOUT_BUTTON = new Button(LEFT_STICK, 6);
    public static final Button SHOOT_BUTTON = new Button(RIGHT_STICK, 7);
    public static final Button UPFEED_BUTTON = new Button(LEFT_STICK, 4);
    // Since index starts at 0, using the codes above as index needs to subtract 1
    private static Button[] buttons = new Button[] {
            INTAKE_BUTTON,
            SPITOUT_BUTTON,
            SHOOT_BUTTON,
            UPFEED_BUTTON,
    };

    // Getting the inputs of the joystick and update the variables
    public static void update() {
        // if (NEXT_BUTTON.isPressed)
        // {
        // ControlModule.changeMode(false);
        // }
        // else if(PREVIOUS_BUTTON.isPressed)
        // {
        // ControlModule.changeMode(true);
        // }
        // System.out.println(COLOR_STICK.getTrigger());
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setStates();
        }
    }
}
