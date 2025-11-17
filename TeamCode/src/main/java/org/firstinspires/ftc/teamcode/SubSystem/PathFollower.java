package org.firstinspires.ftc.teamcode.SubSystem;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.pedropathing.follower.Follower;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

public class PathFollower extends SubsystemBase {
    private HardwareMap m_hardwareMap;
    private Follower m_follower;
    private Telemetry m_telemetry;
    public PathFollower(final HardwareMap hardwareMap, final Telemetry telemetry) {
        m_hardwareMap = hardwareMap;
        m_telemetry = telemetry;
    }

    public void startFollowPath(Pose startPose, Pose endPose, double maxPower){
        m_follower = Constants.createFollower(m_hardwareMap);
        Path path = new Path(new BezierLine(startPose, endPose));
        path.setLinearHeadingInterpolation(startPose.getHeading(), endPose.getHeading());
        m_follower.setStartingPose(startPose);
        m_follower.setMaxPower(maxPower);
        m_follower.followPath(path);
    }

    public boolean followFinished()
    {
        return !m_follower.isBusy();
    }

    public void periodic() {
        // These loop the movements of the robot, these must be called continuously in order to work
        m_follower.update();
        Pose pose = m_follower.getPose();
        m_telemetry.addData("X", pose.getX());
        m_telemetry.addData("Y", pose.getY());
        m_telemetry.addData("Heading", pose.getHeading());
        m_telemetry.addData("isBusy", m_follower.isBusy());
    }
}


