package frc.robot;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.*;
import frc.robot.commands.Autonomous;
public class RobotContainer {
    //creates instance of @DriveSystem to be used in the @Autonomous command
    private final DriveSystem m_driveSystem = new DriveSystem();
    private final Shooter m_shooter = new Shooter();
    private final Intake m_intake = new Intake();
    private final LIDARSensor m_LIDAR = new LIDARSensor(null);
    //creates an instance of the Autonomous command
    private final Autonomous m_Autonomous = new Autonomous(m_driveSystem, m_intake, m_shooter, m_LIDAR);

    public RobotContainer() {}
    //returns the command we want to use in autonomous
    public Command getAutonomousCommand() {
        return m_Autonomous;
    }
}
