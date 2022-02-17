package frc.robot.subsystems;

import frc.robot.controls.OI;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class TurretSystem {
    private final CANSparkMax m_Turret = new CANSparkMax(11, MotorType.kBrushless);
    private final LimeLightSystem m_LimeLight = new LimeLightSystem();
    private double leftMax = -90.0;
    private double rightMax = 90.0;

    public TurretSystem() {
        m_Turret.set(0);
    }

    public void courseCorrection()
    {
        if(m_LimeLight.getX() > 0 && m_LimeLight.getX() < leftMax)
        {
            m_Turret.set(-0.3);
        }
        else if(m_LimeLight.getX() < 0 && m_LimeLight.getX() > rightMax)
        {
            m_Turret.set(0.3);
        }
        else{
            m_Turret.set(0);
        }

    }
    public void manualRotation()
    {
        if(OI.TURRET_BUTTON.isHold())
        {
        m_Turret.set(0.3);
        }
        else if (OI.TREVERSE_BUTTON.isHold())
        {
            m_Turret.set(-0.3);
        }
        else
        {
            m_Turret.set(0);
        }
    }
    }
