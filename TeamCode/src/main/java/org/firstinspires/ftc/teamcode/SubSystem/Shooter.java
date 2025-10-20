package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Shooter extends SubsystemBase {
    private MotorEx motor;
    private Telemetry m_telemetry;

    private int targetVelocity = 0;
    boolean keepVelocity = false;

    public Shooter(final HardwareMap hmap, final String name, final Telemetry telemetry) {
        m_telemetry = telemetry;
        motor = new MotorEx(hmap, "ballroller", Motor.GoBILDA.BARE);
        motor.setRunMode(MotorEx.RunMode.VelocityControl);
        motor.setVeloCoefficients(20, 0 , 0);
    }
    public void setVelocity(int velocity) {
       motor.setVelocity(velocity);
    }

    public double getVelocity(){
        double velocity = motor.getVelocity();
        return velocity;
    }

    public void setState(int targetVelocity, boolean keepVelocity){
        this.targetVelocity = targetVelocity;
        this.keepVelocity = keepVelocity;
        motor.setVelocity(targetVelocity);
    }
    public int getTargetVelocity(){
        return targetVelocity;
    }
    public boolean getKeepVelocity(){
        return keepVelocity;
    }

    public boolean reachTargetVelocity(){
        double currentVelocity = getVelocity();
        if((Math.abs(currentVelocity-targetVelocity)/targetVelocity)<0.03 ){
            return true;
        }

        return false;
    }       

    public void periodic() {
        m_telemetry.addData("Shooter Velocity", motor.getVelocity());
        m_telemetry.addData("Target", getTargetVelocity());
        m_telemetry.addData("Keep", getKeepVelocity());
        m_telemetry.addData("At Target", reachTargetVelocity());
    }

}

