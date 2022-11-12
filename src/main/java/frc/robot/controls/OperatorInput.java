package frc.robot.controls;

import edu.wpi.first.wpilibj.XboxController;
//import frc.robot.controls.Button;
//imported all of the things above so i have access to the commands for those systems;


public class OperatorInput {
    public static final XboxController controller = new XboxController(1);
    // There is no Button 0, any buttons assigned to 0 are in fact unbound.
    public static final Button elevatorButton = new Button(controller, 1);//Elavator up? Unassigned
    public static final Button elevatorSpitoutbutton = new Button(controller, 6);//Elavator down?
   // public static final Button intakeButton = new Button(controller, 1);//Reassign to RT
    //public static final Button shootLowGoalButton = new Button(controller, 1);//reassigned to POV
    //public static final Button shootHighGoalButton = new Button(controller, 1);//Reassigned to POV
    public static final Button spitoutButton = new Button(controller, 7);
    public static final Button liftButton = new Button(controller, 3);
    public static final Button lowerButton = new Button(controller, 2);
    public static final Button adjustButton = new Button(controller, 9);
    
    
}


