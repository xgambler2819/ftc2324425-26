package org.firstinspires.ftc.teamcode;

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

    public Shooter(final HardwareMap hmap, final String name, final Telemetry telemetry) {
        m_telemetry = telemetry;
        m_motor = new MotorEx(hmap, "ballroller", Motor.GoBILDA.BARE);
        m_motor.setRunMode(MotorEx.RunMode.VelocityControl);
        motor.setVeloCoefficients(20, 0 , 0);
    }
    public void setVelocity(int velocity) {
       m_motor.setVelocity(velocity);
    }

    public double getVelocity(){
        double velocity = m_motor.getVelocity();
        return velocity;
    }

    public void setState(int targetVelocity, boolean keepVelocity){
        this.m_targetVelocity = targetVelocity;
        this.m_keepVelocity = keepVelocity;
        m_motor.setVelocity(targetVelocity);
    }
    public int getTargetVelocity(){
        return m_targetVelocity;
    }
    public boolean getKeepVelocity(){
        return m_keepVelocity;
    }

    public boolean reachTargetVelocity(){
        double currentVelocity = getVelocity();
        if((Math.abs(currentVelocity-m_targetVelocity)/m_   targetVelocity)<0.03 ){
            return true;
        }

        return false;
    }       

    public void periodic() {
        if(m_keepVelocity){
            m_motor.setVelocity(m_targetVelocity);
        }
        m_telemetry.addData("Shooter Velocity", m_motor.getVelocity());
        m_telemetry.addData("Target", m_targetVelocity);
        m_telemetry.addData("Keep", m_keepVelocity);
        m_telemetry.addData("At Target", reachTargetVelocity());
    }

}

