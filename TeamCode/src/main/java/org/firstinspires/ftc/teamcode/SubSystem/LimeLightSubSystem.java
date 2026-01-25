package org.firstinspires.ftc.teamcode.SubSystem;

import com.arcrobotics.ftclib.command.SubsystemBase;


import com.pedropathing.geometry.PedroCoordinates;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import com.pedropathing.geometry.Pose;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.limelightvision.LLResult;
import org.firstinspires.ftc.robotcore.external.navigation.*;
public class LimeLightSubSystem extends SubsystemBase {

    private Limelight3A m_limeLight3A;

    private Pose m_lastPose = null;
    private Pose m_pedroPose =  null;
    private double m_tx;
    private long m_lastTime = 0;

    Telemetry m_telemetry;
    public LimeLightSubSystem(final HardwareMap hmap, final Telemetry telemetry) {
        m_limeLight3A = hmap.get(Limelight3A.class, "limelight");
        m_telemetry = telemetry;
        m_limeLight3A.pipelineSwitch(0);
        m_limeLight3A.start();
    }

    public Pose getPedroPose(){return m_pedroPose;}

    public Pose getLasePose() { return m_lastPose; }
    public double getTx(){
        return m_tx;
    }
    public long getLastTime() { return m_lastTime; }

    @Override
    public void periodic() {
        LLResult result = m_limeLight3A.getLatestResult();
        if (result == null) {
            m_telemetry.addData("botpos", "null");
        } else if (result.isValid()) {
            m_tx = result.getTx();
            Pose3D botPose = result.getBotpose();
            Position position = botPose.getPosition().toUnit(DistanceUnit.INCH);
            double llx = position.y;
            double lly = -position.x;
            YawPitchRollAngles angles = botPose.getOrientation();
            double llangle = angles.getYaw() - 90;
            m_lastPose = new Pose(llx, lly, (llangle)/180*3.1416);
            m_lastTime = System.currentTimeMillis();

            m_pedroPose = new Pose(llx+72, lly+72, (llangle)/180*Math.PI);
            m_telemetry.addData("botpos", botPose);
            m_telemetry.addData("pedropos", m_pedroPose);
            m_telemetry.addData("tx", m_tx);
        } else {
            m_telemetry.addData("botpos", "invalid");
        }
        if (m_pedroPose != null){
            m_telemetry.addData("lastpedrox", m_pedroPose.getX());
            m_telemetry.addData("lastpedroy", m_pedroPose.getY());
            m_telemetry.addData("lastpedroangle", m_pedroPose.getHeading());
        } else {
            m_telemetry.addData("m_pedroPose", "null");
        }

        if (m_lastPose != null)
        {
            m_telemetry.addData("lastX", m_lastPose.getX());
            m_telemetry.addData("lastY", m_lastPose.getY());
            m_telemetry.addData("lastAngle", m_lastPose.getHeading() * 180 / 3.1416);
        }
        else 
        {
            m_telemetry.addData("lastPose", "null");
        }
        long delay = System.currentTimeMillis() - m_lastTime;
        m_telemetry.addData("lastDelay", delay);
        if (delay > 3000)
        {
            m_lastPose = null;
            m_pedroPose = null;
        }
    }

}


