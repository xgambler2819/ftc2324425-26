package org.firstinspires.ftc.teamcode.SubSystem;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Indexer extends SubsystemBase {
    private CRServo m_servo1;
    private int m_servo1Direction = -1;
    private CRServo m_servo2;
    private int m_servo2Direction = 1;
    private DcMotor m_intake;

    final int ForwardPower = 1;
    final int BackwardPower = -1;
    final int StopPower = 0;

    Telemetry m_telemetry;

    double m_servo1Power = 0;
    double m_servo2Power = 0;

    double m_intakePower = 0;

    public Indexer(final HardwareMap hmap, final Telemetry telemetry) {
        m_servo1 = hmap.get(CRServo.class, "bottomroller");
        m_servo1.setDirection(DcMotorSimple.Direction.REVERSE);
        m_servo2 = hmap.get(CRServo.class, "upperroller");
        m_servo2.setDirection(DcMotorSimple.Direction.REVERSE);
        m_intake = hmap.get(DcMotor.class, "intake");
        m_telemetry =telemetry;
        Stop();
    }

    public void RollBack() {
        m_servo1Power = BackwardPower * m_servo1Direction;
        m_servo1.setPower(m_servo1Power);
        m_servo2Power = BackwardPower * m_servo2Direction;
        m_servo2.setPower(m_servo2Power);
        m_intakePower = ForwardPower;
        m_intake.setPower(m_intakePower);
    }

    public void RollOut() {
        m_servo1Power = BackwardPower * m_servo1Direction;
        m_servo1.setPower(m_servo1Power);
        m_servo2Power = BackwardPower * m_servo2Direction;
        m_servo2.setPower(m_servo2Power);
        m_intakePower = BackwardPower;
        m_intake.setPower(m_intakePower);
    }

    public void RollForward() {
        m_servo1Power = ForwardPower * m_servo1Direction;
        m_servo1.setPower(m_servo1Power);
        m_servo2Power = ForwardPower * m_servo2Direction;
        m_servo2.setPower(m_servo2Power);
        m_intakePower = ForwardPower;
        m_intake.setPower(m_intakePower);
    }

    public void Stop() {
        m_servo1Power = StopPower;
        m_servo1.setPower(m_servo1Power);
        m_servo2Power = StopPower;
        m_servo2.setPower(StopPower);
        m_intakePower = StopPower;
        m_intake.setPower(m_intakePower);
    }

    @Override
    public void periodic() {
        m_telemetry.addData("Servo1:", "%.2f", m_servo1Power);
        m_telemetry.addData("Servo2:", "%.2f", m_servo2Power);
        m_telemetry.addData("Intake:", "%.2f", m_intakePower);
    }
}


