package org.firstinspires.ftc.teamcode.SubSystem;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake extends SubsystemBase {
    private CRServo m_servo;
    private DcMotor m_intake;

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
        m_servoPower = 1;
        m_servo.setPower(m_servoPower);
        m_intakePower = -1;
        m_intake.setPower(m_intakePower);
    }

    public void RollIn() {
        m_servoPower = -1;
        m_servo.setPower(m_servoPower);
        m_intakePower = 1;
        m_intake.setPower(m_intakePower);
    }

    public void Stop() {
        m_servoPower = 0;
        m_servo.setPower(m_servoPower);
        m_intakePower = 0;
        m_intake.setPower(m_intakePower);
    }

    @Override
    public void periodic() {
        m_telemetry.addData("Intake:", "%.2f", m_intakePower);
        m_telemetry.addData("Servo:", "%.2f", m_servoPower);

    }
}


