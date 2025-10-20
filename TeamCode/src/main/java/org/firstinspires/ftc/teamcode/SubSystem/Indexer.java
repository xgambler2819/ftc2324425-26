package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Indexer extends SubsystemBase {
    private CRServo servo1;
    private int servo1Direction = -1;
    private CRServo servo2;
    private int servo2Direction = 1;

    final int ForwardPower = 1;
    final int BackwardPower = -1;
    final int StopPower = 0;

    public IndexerSubsystem(final HardwareMap hmap) {
        servo1 = hmap.get(CRServo.class, "bottomroller");
        servo2 = hmap.get(CRServo.class, "upperroller");

        Stop();
    }

    public void setPower(double power) {
        servo1.setPower(power * servo1Direction);
        servo2.setPower(power * servo2Direction);
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


