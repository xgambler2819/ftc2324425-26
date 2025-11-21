package org.firstinspires.ftc.teamcode.SubSystem;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Indexer extends SubsystemBase {
    private DcMotor m_indexer;
    Telemetry m_telemetry;
    double m_indexerPower = 0;
    final double IndexerPower = 1;

    public Indexer(final HardwareMap hmap, final Telemetry telemetry) {
        m_indexer = hmap.get(DcMotor.class, "indexer");
        m_indexer.setDirection(DcMotorSimple.Direction.REVERSE);
        m_telemetry = telemetry;
        Stop();
    }
    public void move(double power) {
        m_indexerPower = power;
        m_indexer.setPower(m_indexerPower);
    }

    public void Stop() {
        m_indexerPower = 0;
        m_indexer.setPower(m_indexerPower);
    }
    public void RollUp() {
        move(IndexerPower);
    }
    public void RollDown() {
        move(-IndexerPower);
    }

    @Override
    public void periodic() {
        m_telemetry.addData("Indexer:", "%.2f", m_indexerPower);
    }
}


