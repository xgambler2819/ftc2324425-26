package org.firstinspires.ftc.teamcode.SubSystem;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Shooter extends SubsystemBase {
    private MotorEx m_motor;
    private Telemetry m_telemetry;

    private int m_targetVelocity = 0;
    boolean m_keepVelocity = false;

    public Shooter(final HardwareMap hmap, final Telemetry telemetry) {
        m_telemetry = telemetry;
        m_motor = new MotorEx(hmap, "ballroller", Motor.GoBILDA.BARE);
        m_motor.setRunMode(MotorEx.RunMode.VelocityControl);
        m_motor.setVeloCoefficients(20, 0 , 0);
        m_motor.setFeedforwardCoefficients(0, 0.7, 0);
        m_motor.setInverted(true);

        m_targetVelocity = 0;
        m_keepVelocity = false;
        setState(0, true);
    }

    public void setState(int targetVelocity, boolean keepVelocity){
        this.m_targetVelocity = targetVelocity;
        this.m_keepVelocity = keepVelocity;
        m_motor.setVelocity(targetVelocity);
    }

    public boolean reachTargetVelocity(){
        double currentVelocity = getVelocity();
        if (Math.abs(m_targetVelocity) < 0.01)
        {
            return Math.abs(currentVelocity) < 0.1;
        }
        return (Math.abs(currentVelocity - m_targetVelocity) / m_targetVelocity) < 0.12;
    }       

    public void periodic() {
        if(m_keepVelocity){
            m_motor.setVelocity(m_targetVelocity);
        }
        m_telemetry.addData("Velocity", "%.2f", getVelocity());
        m_telemetry.addData("Target", m_targetVelocity);
        m_telemetry.addData("Keep", m_keepVelocity);
        m_telemetry.addData("Reach", reachTargetVelocity());

        m_telemetry.update();
    }
    private double getVelocity(){
        double velocity = m_motor.getVelocity();
        return velocity;
    }
}

