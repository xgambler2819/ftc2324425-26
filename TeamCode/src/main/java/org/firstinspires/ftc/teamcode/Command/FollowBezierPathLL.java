package org.firstinspires.ftc.teamcode.Command;

import com.arcrobotics.ftclib.command.CommandBase;
import com.pedropathing.geometry.Pose;

import org.firstinspires.ftc.teamcode.SubSystem.PathFollower;
import org.firstinspires.ftc.teamcode.SubSystem.LimeLightSubSystem;

public class FollowBezierPathLL extends CommandBase {

    private PathFollower m_follower;
    private LimeLightSubSystem m_limeLight;
    private final Pose m_startPose;
    private final Pose m_endPose;
    private final Pose m_controlPoint;
    private final double m_power;

    public FollowBezierPathLL(PathFollower follower, LimeLightSubSystem limeLight, Pose startPose, Pose controlPoint, Pose endPose, double power) {
        m_follower = follower;
        m_limeLight = limeLight;
        m_startPose = startPose;
        m_controlPoint=controlPoint;
        m_endPose = endPose;
        m_power = power;
        addRequirements(m_follower);
        addRequirements(m_limeLight);
    }

    @Override
    public void initialize () {
        Pose llPose = m_limeLight.getLasePose();
        Pose startPose = m_startPose;
        if (llPose != null)
        {
            llPose = llPose;
        }
        m_follower.startFollowBezierCurve(startPose, m_controlPoint, m_endPose, m_power);
    }

    @Override
    public boolean isFinished() {
        return m_follower.followFinished();
    }
}



