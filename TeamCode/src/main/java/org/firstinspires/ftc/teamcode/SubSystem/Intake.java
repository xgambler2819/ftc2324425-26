package org.firstinspires.ftc.teamcode.SubSystem;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake extends SubsystemBase {
    private CRServo m_servo;
    private int m_servoDirection = -1;
    private DcMotor m_intake;

    final int ForwardPower = 1;
    final int BackwardPower = -1;
    final int StopPower = 0;

    Telemetry m_telemetry;

    double m_servoPower = 0;
    double m_intakePower = 0;

    public Intake(final HardwareMap hmap, final Telemetry telemetry) {
        m_servo = hmap.get(CRServo.class, "bottomroller");
        m_servo.setDirection(DcMotorSimple.Direction.REVERSE);
        m_intake = hmap.get(DcMotor.class, "intake");
        m_telemetry = telemetry;
        Stop();
    }

    public void RollOut() {
        m_servoPower = BackwardPower * m_servoDirection;
        m_servo.setPower(m_servoPower);
        m_intakePower = BackwardPower;
        m_intake.setPower(m_intakePower);
    }

    public void RollIn() {
        m_servoPower = ForwardPower * m_servoDirection;
        m_servo.setPower(m_servoPower);
        m_intakePower = ForwardPower;
        m_intake.setPower(m_intakePower);
    }

    public void Stop() {
        m_servoPower = StopPower;
        m_servo.setPower(m_servoPower);
        m_intakePower = StopPower;
        m_intake.setPower(m_intakePower);
    }

    @Override
    public void periodic() {
        m_telemetry.addData("Intake:", "%.2f", m_intakePower);
        m_telemetry.addData("Servo:", "%.2f", m_servoPower);

    }
}


