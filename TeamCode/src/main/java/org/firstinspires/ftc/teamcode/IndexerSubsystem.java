package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class IndexerSubsystem extends SubsystemBase {
    private CRServo servo1;
    private CRServo servo2;

    private int m_ForwardPower = 1;
    private int m_BackwardPower = -1;
    private int m_StopPower = 0;

    public IndexerSubsystem(final HardwareMap hmap, final String name1, String name2) {
        servo1 = hmap.get(CRServo.class, name1);
        servo2 = hmap.get(CRServo.class, name2);

        Stop();
    }

    public void Rollbackward() {

        servo1.setPower(m_BackwardPower);
        servo2.setPower(m_BackwardPower);
    }

    public void Rollfoward() {
        servo1.setPower(m_ForwardPower);
        servo2.setPower(m_ForwardPower);

    }
public void Stop() {
    servo1.setPower(m_StopPower);
    servo2.setPower(m_StopPower);
}

  /*  @Override
    public void periodic() {
       // servo.setPosition();
    }*/

}


