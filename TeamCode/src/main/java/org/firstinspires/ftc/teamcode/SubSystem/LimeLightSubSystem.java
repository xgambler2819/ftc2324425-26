package org.firstinspires.ftc.teamcode.SubSystem;

import com.arcrobotics.ftclib.command.SubsystemBase;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.limelightvision.LLResult;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
public class LimeLightSubSystem extends SubsystemBase {

    private Limelight3A m_limeLight3A;

    Telemetry m_telemetry;
    public LimeLightSubSystem(final HardwareMap hmap, final Telemetry telemetry) {
        m_limeLight3A = hmap.get(Limelight3A.class, "limelight");
        m_telemetry = telemetry;
        m_limeLight3A.pipelineSwitch(0);
        m_limeLight3A.start();
    }

    @Override
    public void periodic() {
        LLResult result = m_limeLight3A.getLatestResult();
        if (result == null) {
            m_telemetry.addData("botpos", "null");
        } else if (result.isValid()) {
            Pose3D botPose = result.getBotpose();
            m_telemetry.addData("botpos", botPose);
        } else {
            m_telemetry.addData("botpos", "invalid");
        }
    }
}


