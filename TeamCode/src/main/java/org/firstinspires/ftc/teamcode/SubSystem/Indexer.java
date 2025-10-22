package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Indexer extends SubsystemBase {
    private CRServo m_servo1;
    private int m_servo1Direction = -1;
    private CRServo m_servo2;
    private int m_servo2Direction = 1;
    private DcMotor m_intake;

    final int ForwardPower = 1;
    final int BackwardPower = -1;
    final int StopPower = 0;

    public IndexerSubsystem(final HardwareMap hmap) {
        m_servo1 = hmap.get(CRServo.class, "bottomroller");
        m_servo2 = hmap.get(CRServo.class, "upperroller");
        m_intake = hmap.get(DcMotor.class, "intake");
        Stop();
    }

    public void setPower(double power) {
        m_servo1.setPower(power * m_servo1Direction);
        m_servo2.setPower(power * m_servo2Direction);
        m_intake.setPower(power);
    }

    public void Rollbackward() {
        setPower(BackwardPower);
    }

    public void Rollfoward() {
        setPower(ForwardPower);

    }

    public void Stop() {
        setPower(StopPower);
    }

}


