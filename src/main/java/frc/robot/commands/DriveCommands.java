package frc.robot.commands;

//the imports are what this class needs from other classes to function properly
//these can come from other parts of the robot or the internet
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.LimeLightSystem;
import frc.robot.subsystems.Intake;
import frc.robot.controls.OperatorInput;
import frc.robot.subsystems.Winch;
import edu.wpi.first.wpilibj.Timer;

/**
 * This class uses the DriveSystem, Intake, limeLight, and Winch
 * to prform various autonomous commands
 */
public class DriveCommands {
    // this class requires a DriveSystem, LimeLight, Intake, and Winch to run
    DriveSystem m_driveSystem;
    LimeLightSystem m_LimeLightSystem;
    Intake m_intake;
    Winch m_winch;
    Elevator m_elevator;
    private static Timer m_Timer = new Timer();
    ShootCommands m_ShootCommands;

    // Constructor for DriveCommands Class
    public DriveCommands(DriveSystem driveSystem, LimeLightSystem m_LimeLightSystem, Intake m_intake, Winch m_winch,
            ShootCommands m_ShootCommands) {
        this.m_driveSystem = driveSystem;
        this.m_LimeLightSystem = m_LimeLightSystem;
        this.m_intake = m_intake;
        this.m_winch = m_winch;
        this.m_ShootCommands = m_ShootCommands;
        m_Timer.reset();
    }

    /**
     * this method drives the robot foreward 10 rotations
     * or until the timer reaches 3 seconds.
     * This provides a jerking motion used to drop the intake arm
     * from its starting position.
     * 
     * @param speed can be between -1.0 and 1.0.
     *              0 is no speed,
     *              1 is full speed forewards,
     *              -1 is full speed backwards.
     */
    public void dropIntake(double speed) {
        /*
         * reseting the encoders is needed when using them to go a set distance
         * so that they give the proper readings
         */
        m_driveSystem.m_frontLeft.getEncoder().setPosition(0);
        m_Timer.start();

        while (m_driveSystem.m_frontLeft.getEncoder().getPosition() < 10 && m_Timer.get() < 3) {
            m_driveSystem.setSpeed(speed, speed);
        }

        /*
         * the timer usage is good practice incase a sensor stops working
         * to prevent a program from running indefinitely
         */

        // timer needs to be stopped and reset after every use
        m_Timer.reset();
        m_Timer.stop();
        // without stopWheels, the motors would run forever
        m_driveSystem.stopWheels();
        m_driveSystem.m_frontLeft.getEncoder().setPosition(0);
        // runs the intake motors until they are stopped
        m_intake.runMotors(0.65);

    }

    /**
     * This method drives at a set speed until you go a set distance
     * 
     * @param rotations is number of wheel turns
     * @param speed     can be between -1 and 1
     *                  0 is no speed,
     *                  1 is full speed forewards,
     *                  -1 is full speed backwards.
     */
    public void driveSetDistance(double rotations, double speed) {

        m_driveSystem.m_frontLeft.getEncoder().setPosition(0);
        m_Timer.reset();
        m_Timer.start();
        /*
         * because the encoder position can go negative to go backwards,
         * we check the value of rotations to determine weather we need to
         * see if the condition for ending the program should
         * be for increasing rotation values
         * or decreasing rotation values
         */
        if (rotations > 0) {
            while (m_driveSystem.m_frontLeft.getEncoder().getPosition() < rotations && m_Timer.get() < 5) {
                m_driveSystem.setSpeed(speed, speed);
            }
        } else {
            while (m_driveSystem.m_frontLeft.getEncoder().getPosition() > rotations && m_Timer.get() < 5) {
                m_driveSystem.setSpeed(speed, speed);
            }

            m_Timer.reset();
            m_Timer.stop();

            m_driveSystem.stopWheels();
            m_driveSystem.m_frontLeft.getEncoder().setPosition(0);
        }
    }

    /**
     * this method is used for turning the robot right around 180 degrees
     */
    public void turnAround() {

        m_driveSystem.m_frontLeft.getEncoder().setPosition(0);
        m_Timer.reset();
        m_Timer.start();
        while (m_driveSystem.m_frontLeft.getEncoder().getPosition() > -50 && m_Timer.get() < 4) {
            m_driveSystem.setSpeed(0.35, -0.35);
        }

        m_Timer.reset();
        m_Timer.stop();
        m_driveSystem.stopWheels();
        // stops the intake motors after running from the drop intake command
        m_intake.stopMotors();
        m_driveSystem.m_frontLeft.getEncoder().setPosition(0);
        // Debug.printOnce(String.valueOf(m_driveSystem.m_frontLeft.getEncoder().getPosition()));
    }

    /**
     * this method turns the winch motor
     * to lift or lower the intake arm
     * 
     * @param power can be between -1.0 and 1.0.
     */
    public void winchMove(double power) {
        m_winch.winchMotor.getEncoder().setPosition(0);
        /*
         * like with the driveSetDistance() command,
         * we need to check the power to know if we need to check for forward rotations
         * or backwards rotations to keep the winch from winding the wrong direction
         * and not being able to stop
         */
        m_Timer.reset();
        m_Timer.start();
        if (power > 0) {

            while (m_winch.winchMotor.getEncoder().getPosition() < 14 && m_Timer.get() < 2) {
                m_winch.winchMotor.set(power);
            }
        } else {
            while (m_winch.winchMotor.getEncoder().getPosition() > -14 && m_Timer.get() < 2) {
                m_winch.winchMotor.set(power);
            }
        }
        m_Timer.stop();
        m_Timer.reset();
        m_winch.winchMotor.getEncoder().setPosition(0);
        m_winch.winchMotor.set(0);
    }

    /**
     * this method takes in all the previously made methods in this class
     * and runs them for use in the autonomous portion of the competition
     */
    public void autonomousDrive() {
        m_Timer.stop();
        m_Timer.reset();
        winchMove(-0.4);
        dropIntake(-0.5);
        m_Timer.start();
        // this part does nothing for 0.5 seconds
        /*
         * this is used to give the intake some time to fall
         * after the jerking motion produced
         */
        while (m_Timer.get() < 0.5) {
        }
        m_Timer.stop();
        m_Timer.reset();
        driveSetDistance(27, -0.6);
        driveSetDistance(-7, 0.6);

        turnAround();
        winchMove(0.4);
        // while the limelight system detects that it is not in the correct range
        while ((m_LimeLightSystem.getX() > 1.0 || m_LimeLightSystem.getX() < -1.0)
                && (m_LimeLightSystem.getY() > 1.0 || m_LimeLightSystem.getY() < -1.0)) {
            aim();
        }

    }

    public void buttonAim() {
        if (OperatorInput.adjustButton.isPressed()) {
            aim();
        }
    }

    public void aim() {
        float KpAim = -0.075f;
        float KpDistance = -0.08f;
        float min_aim_command = 0.05f;

        double heading_error = -m_LimeLightSystem.getX();
        double distance_error = -m_LimeLightSystem.getY();
        double steering_adjust = 0.0f;

        if (m_LimeLightSystem.getX() > 1.0) {
            steering_adjust = KpAim * heading_error - min_aim_command;
        } else if (m_LimeLightSystem.getX() < -1.0) {
            steering_adjust = KpAim * heading_error + min_aim_command;
        }

        double distance_adjust = KpDistance * distance_error;
        double leftspeed = -steering_adjust + distance_adjust;
        double rightspeed = steering_adjust + distance_adjust;

        m_driveSystem.setSpeed(leftspeed, rightspeed);
    }
}