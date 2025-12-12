package org.firstinspires.ftc.teamcode.SubSystem;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.pedropathing.ftc.drivetrains.Mecanum;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.BezierPoint;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.pedropathing.follower.Follower;
import com.pedropathing.ftc.drivetrains.Mecanum;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

import java.util.List;

public class PathFollower extends SubsystemBase {
    private HardwareMap m_hardwareMap;
    private Follower m_follower;
    private Telemetry m_telemetry;
    public PathFollower(final HardwareMap hardwareMap, final Telemetry telemetry) {
        m_hardwareMap = hardwareMap;
        m_telemetry = telemetry;
        m_follower = Constants.createFollower(m_hardwareMap);
    }
    public void RecalibrateIMU()
    {
        GoBildaPinpointDriver odo = m_hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");
        odo.recalibrateIMU();
    }
    public void startFollowPath(Pose startPose, Pose endPose, double maxPower){
        m_follower = Constants.createFollower(m_hardwareMap);
        Path path = new Path(new BezierLine(startPose, endPose));
        path.setLinearHeadingInterpolation(startPose.getHeading(), endPose.getHeading());
        m_follower.setPose(startPose); //TODO startingpose or pose
        m_follower.setMaxPower(maxPower);
        m_follower.followPath(path);
    }

    public void startFollowPoints(List<Pose> points, double maxPower){
        m_follower = Constants.createFollower(m_hardwareMap);
        Path path = new Path(new BezierCurve(points));
        Pose startPose = points.get(0);
        Pose endPose = points.get(points.size()-1);
        path.setLinearHeadingInterpolation(startPose.getHeading(), endPose.getHeading());
        m_follower.setPose(startPose);
        m_follower.setMaxPower(maxPower);
        m_follower.followPath(path);
    }
    public void startFollowBezierCurve(Pose startPose, Pose controlPoint, Pose endPose, double maxPower){
        m_follower = Constants.createFollower(m_hardwareMap);
        Path path = new Path(new BezierCurve(startPose, controlPoint, endPose));
        path.setLinearHeadingInterpolation(startPose.getHeading(), endPose.getHeading());
        m_follower.setPose(startPose);
        m_follower.setMaxPower(maxPower);
        m_follower.followPath(path);
    }

    public void Stop()
    {
        Mecanum drive = (Mecanum)m_follower.drivetrain;
        double[] zeros = {0,0,0,0};
        drive.runDrive(zeros);
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
        m_telemetry.addData("Heading", Math.toDegrees(pose.getHeading()));
        m_telemetry.addData("isBusy", m_follower.isBusy());
    }
}


