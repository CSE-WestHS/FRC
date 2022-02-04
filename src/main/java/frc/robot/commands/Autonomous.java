package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.*;
public class Autonomous extends SequentialCommandGroup {
    public Autonomous(DriveSystem m_drive, Intake m_intake, Shooter m_shooter, LIDARSensor m_LIDAR) {
        addCommands(
            //Uses DriveSystem to Drive Until It Reaches the Ball
            new DriveToBall(m_drive),
            //Picks up that ball and puts it in intake
            new PickUpBall(m_intake),
            //Turns the Robot Around(may not need due to the turret/Lidar placement)
            new TurnAround(m_drive),
            //Moves until it reaches the optimal shooting distanc
            new MoveToGoal(m_drive, m_LIDAR),
            //Shoots both balls in the robot, one after the other
            new  ShootTwoBalls(m_shooter, m_intake)
        );
    }
}
