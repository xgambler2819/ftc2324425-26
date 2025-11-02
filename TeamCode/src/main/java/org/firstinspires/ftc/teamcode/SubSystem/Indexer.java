package org.firstinspires.ftc.teamcode.SubSystem;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Indexer extends SubsystemBase {
    private CRServo m_servo;
    Telemetry m_telemetry;
    double m_power = 0;

    public Indexer(final HardwareMap hmap, final Telemetry telemetry) {
        m_servo = hmap.get(CRServo.class, "upperroller");
        m_telemetry =telemetry;
        Stop();
    }

    public void RollDown() {
        m_power = 1;
        m_servo.setPower(m_power);
    }


    public void RollUp() {
        m_power = -1;
        m_servo.setPower(m_power);
    }

    public void Stop() {
        final int StopPower = 0;
        m_power = StopPower;
        m_servo.setPower(m_power);
    }

    @Override
    public void periodic() {
        m_telemetry.addData("Indexer:", "%.2f", m_power);
    }
}


