package frc.robot.controls;

import edu.wpi.first.wpilibj.Joystick;

/**
 * The Button class provides convenience methods to act on named buttons.
 */
public class Button {
    int code;
    Joystick joystick;

    public Button(Joystick stick, int buttonCode) {
        joystick = stick;
        code = buttonCode;
    }

    /**
     * Returns true if the button is currently pressed.
     */
    public boolean isPressed() {
        return joystick.getRawButton(code);
    }
}
