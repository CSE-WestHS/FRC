package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Solenoid;

/**
 * Turret
 */
public class Turret {


    private CANSparkMax turretYawAdjuster = new CANSparkMax(2, CANSparkMax.MotorType.kBrushless);

//Solenoid popUp = new Solenoid(0);

    public Turret() {
        final CANSparkMax m_turret = new CANSparkMax(2, MotorType.kBrushless);
        //final CANSparkMax(2, CANSparkMaxLowLevel.MotorType.kBrushless);
        shooterSlave.setInverted(true);
        shooterSlave.follow(shooterMaster, FollowerType.AuxOutput1);

        shooterMaster.config_kP(0, Const.SHOOTING_Kp, 30);
        shooterMaster.config_kI(0, Const.SHOOTING_Ki, 30);
        shooterMaster.config_kD(0, Const.SHOOTING_Kd, 30);
        shooterMaster.config_kF(0, Const.SHOOTING_Kf, 30);
        shooterMaster.config_IntegralZone(0, 50);
        shooterSlave.config_kP(0, Const.SHOOTING_Kp, 30);
        shooterSlave.config_kI(0, Const.SHOOTING_Ki, 30);
        shooterSlave.config_kD(0, Const.SHOOTING_Kd, 30);
        shooterSlave.config_kF(0, Const.SHOOTING_Kf, 30);
        shooterSlave.config_IntegralZon5e(0, 0);
    }

    private void CANSparkMax(int i, MotorType kbrushless) {
    }

    public void shoot() {
        shooterMaster.set(ControlMode.Velocity, Const.SHOOTING_UNITS_PER_REV * Const.SHOOTING_TARGET_RPM / 600);
        shooterSlave.set(ControlMode.Velocity, Const.SHOOTING_UNITS_PER_REV * Const.SHOOTING_TARGET_RPM / 600);
        // System.out.println("Turret Error (ticks): " + shooterMaster.getClosedLoopError());
    }

    public void setPower(double power) {
        // shooterSlave.follow(shooterMaster);
        shooterMaster.set(ControlMode.PercentOutput, power);
    }

    public void stop() {
        shooterMaster.set(0);
        shooterSlave.set(0);
    }

    public void setPopUpPosition(boolean position) {
        popUp.set(position);
    }

    public void setTurretYawPosition(double position) {
        // TODO: create ratio between degrees and potentiometer values
    }

    public void setTurretYawPower(double power) {
        turretYawAdjuster.set(power);
    }

    public void resetShooterTicks() {
        shooterMaster.setSelectedSensorPosition(0, 0, 10);
        shooterSlave.setSelectedSensorPosition(0, 0, 10);
    }

    public double getShooterError() {
        return shooterMaster.getClosedLoopError();
    }
}