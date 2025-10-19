package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class BigBallShooter extends SubsystemBase {
    private MotorEx motor;
    private Telemetry m_telemetry;
    public BigBallShooter(final HardwareMap hmap, final String name, final Telemetry telemetry) {
        m_telemetry = telemetry;
        motor = new MotorEx(hmap, "ballroller", Motor.GoBILDA.BARE);
        motor.setRunMode(MotorEx.RunMode.VelocityControl);
        motor.setVeloCoefficients(20, 0 , 0);
    }
    public void setVelcotiy(int velcotiy) {
       motor.setVelocity(velcotiy);
    }
    public void stop( ){
        motor.setVelocity(0);
    }
    public double getvelocity(){
        double velocity = motor.getVelocity();
        m_telemetry.addData("BigShooter,getvelocity", velocity);
        return velocity;
    }





}

