package org.firstinspires.ftc.teamcode.SubSystem;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Shooter extends SubsystemBase {
    private MotorEx m_motor;

    private Servo m_led;
    final double Color_Red = 0.279;
    final double Color_Green = 0.500;
    final double Color_Yellow = 0.388;

    final double Color_Blue = 0.611;
    final double Color_Azure = 0.555;
    final double Color_White = 1;
    boolean m_reachTarget = false;
    /* Color Guide:
        Black – 0
        Red – 0.279
        Orange – 0.333
        Gold – 0.357
        Yellow – 0.388
        Sage – 0.444
        Green – 0.500
        Azure – 0.555
        Blue – 0.611
        Indigo – 0.666
        Violet – 0.722
        White – 1
     */
    private Telemetry m_telemetry;

    private int m_targetVelocity = 0;
    boolean m_keepVelocity = false;

    final int HighVelocity = 1200;
    final int LowVelocity = 1050;
    public Shooter(final HardwareMap hmap, final Telemetry telemetry) {
        m_telemetry = telemetry;
        m_motor = new MotorEx(hmap, "ballroller", Motor.GoBILDA.BARE);
        m_led = hmap.get(Servo.class, "led");
        m_led.setPosition(0.279);
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

    public void setHigh()
    {
        setState(HighVelocity, true);
    }
    public void setLow()
    {
        setState(LowVelocity, true);
    }

    public void setStop()
    {
        setState(0, true);
    }

    public void setBack()
    {
        setState(-1*LowVelocity, true);
    }

    public boolean getReachTarget() {
        return m_reachTarget;
    }

    private boolean checkReachTarget(){
        double currentVelocity = getVelocity();
        m_reachTarget = false;
        if (Math.abs(m_targetVelocity) < 0.01)
        {
            m_reachTarget = Math.abs(currentVelocity) < 0.1;
        }
        else 
        {
            m_reachTarget = Math.abs((currentVelocity - m_targetVelocity) / m_targetVelocity) < 0.125;
        }
        return m_reachTarget;
    }     


    public void periodic() {
        if(m_keepVelocity){
            m_motor.setVelocity(m_targetVelocity);
        }
        checkReachTarget();
        m_telemetry.addData("Velocity", "%.2f", getVelocity());
        m_telemetry.addData("Target", m_targetVelocity);
        m_telemetry.addData("Keep", m_keepVelocity);
        m_telemetry.addData("Reach", getReachTarget());

        m_telemetry.update();

        if (m_reachTarget)
        {
            if (m_targetVelocity == HighVelocity) {
                m_led.setPosition(Color_Blue);
            }
            else if (m_targetVelocity == LowVelocity) {
                m_led.setPosition(Color_Green);
            }
            else {
                m_led.setPosition(Color_White);
            }
        } else {
            m_led.setPosition(Color_Red);
        }
    }

    private double getVelocity(){
        double velocity = m_motor.getVelocity();
        return velocity;
    }
}

