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

    /*
     * public static final Button BLUE_BUTTON = new Button(COLOR_STICK, 1);
     * public static final Button RED_BUTTON = new Button(COLOR_STICK, 2);
     * public static final Button YELLOW_BUTTON = new Button(COLOR_STICK, 3);
     * public static final Button BOTTOM_RIGHT = new Trigger(COLOR_STICK, 3);
     * Controls what the pneumatic control buttons control...
     * public static final Button NEXT_BUTTON = new Button(COLORSTICK, 5);
     * public static final Trigger PREVIOUS_BUTTON = new Trigger(COLORSTICK, 2);
     */public static final Button PNEU_FORWARD_BUTTON = new Button(RIGHT_STICK, 7);
    public static final Button PNEU_BACKWARD_BUTTON = new Button(RIGHT_STICK, 8);

    public static final Button INTAKE_BUTTON = new Button(LEFT_STICK, 2);
    public static final Button SHOOT_BUTTON_LOW = new Button(LEFT_STICK, 3);
    public static final Button SHOOT_BUTTON_HIGH = new Button(LEFT_STICK, 4);
    public static final Button BALL_BUTTON = new Button(RIGHT_STICK, 4);
    public static final Button BALLREVERSE_BUTTON = new Button(RIGHT_STICK, 3);

    // Since index starts at 0, using the codes above as index needs to subtract 1
    private static Button[] buttons = new Button[] {
            // BLUE_BUTTON,
            // RED_BUTTON,
            // YELLOW_BUTTON,
            // BOTTOM_BUTTON,
            // NEXT_BUTTON,
            // PREVIOUS_BUTTON,
            // PNEU_FORWARD_BUTTON,
            // PNEU_BACKWARD_BUTTON,
            // SHOOT_BUTTON_LOW,
            // INTAKE_BUTTON,
            // BALL_BUTTON,
            // BALLREVERSE_BUTTON,
            // SHOOT_BUTTON_HIGH,
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