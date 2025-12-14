package org.firstinspires.ftc.teamcode.Command;

import com.arcrobotics.ftclib.command.CommandBase;
import com.pedropathing.geometry.Pose;

import org.firstinspires.ftc.teamcode.SubSystem.PathFollower;

public class FollowBezierPath extends CommandBase {

    private PathFollower m_follower;
    private final Pose m_startPose;
    private final Pose m_endPose;
    private final Pose m_controlPoint;
    private final double m_power;

    public FollowBezierPath(PathFollower follower, Pose startPose, Pose controlPoint, Pose endPose, double power) {
        m_follower = follower;
        m_startPose = startPose;
        m_controlPoint=controlPoint;
        m_endPose = endPose;
        m_power = power;
        addRequirements(m_follower);
    }

    @Override
    public void initialize () {
        m_follower.startFollowBezierCurve(m_startPose, m_controlPoint, m_endPose, m_power);
    }

    @Override
    public boolean isFinished() {
        return m_follower.followFinished();
    }
}



