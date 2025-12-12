package org.firstinspires.ftc.teamcode.SubSystem;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake extends SubsystemBase {

    private DcMotor m_intake;

    Telemetry m_telemetry;

    double m_intakePower = 0;
    final double IntakePower = -1;

    public Intake(final HardwareMap hmap, final Telemetry telemetry) {
        m_intake = hmap.get(DcMotor.class, "intake");
        m_telemetry = telemetry;
        Stop();
    }
    public void move(double power) {
        m_intakePower = power;
        m_intake.setPower(m_intakePower);
    }

    public void Stop() {
        m_intakePower = 0;
        m_intake.setPower(m_intakePower);
    }
    public void RollIn() {
        move(IntakePower);
    }
    public void RollOut() {
        move(-IntakePower);
    }


    @Override
    public void periodic() {
        m_telemetry.addData("Intake:", "%.2f", m_intakePower);


    }
}


