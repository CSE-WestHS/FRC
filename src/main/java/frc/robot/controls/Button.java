package frc.robot.controls;

import edu.wpi.first.wpilibj.Joystick;

/**
 * The Button class provides convenience methods to act on named buttons.
 */
public class Button {
    int code;
    Joystick joystick;
    boolean pushed;

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

    public boolean isPressedEvent() {
        if (this.pushed != this.isPressed() && this.isPressed()) {
            this.pushed = true;
            return true;
        }
        return false;
    }

    public boolean isReleasedEvent() {
        if (this.pushed != isPressed() && !this.isPressed()) {
            this.pushed = false;
            return true;
        }
        return false;
    }
}
