package frc.robot.controls;

import edu.wpi.first.wpilibj.Joystick;

public class Button 
{
    boolean isPressed;
    boolean isReleased;

    //sience joysick.getrowbuttonpressed(int code) only gets the button press on a single tick, 
    // isHold checks wether the button is still being held or not 
    //as far as i know, the wpilib does not provide a function to check wether A Button is still being held.
    //if thy do, delete this class and end my life 
    boolean isHold; 
    
    int code; 
    Joystick stick; 

    public Button(Joystick stick , int buttonCode)
    {
        isPressed = false; 
        isReleased = false; 
        isHold = false; 
        
        this.stick = stick; 
        code = buttonCode;

    }    

    public int getcode()
    {
        return code; 
    }

    public void setStates()
    {
        isPressed = stick.getRawButtonPressed(code); 
        isReleased = stick.getRawButtonReleased(code);

        if (isPressed)
        {
            isHold = true;
        }
        else if (isReleased)
        {
            isHold = false;
        }
    }

    public boolean isPressed()
    {
        return this.isPressed;
    }

    public boolean isReleased()
    {
        return this.isReleased;

    }

    public boolean isHold()
    {
        return this.isHold;
    }
}