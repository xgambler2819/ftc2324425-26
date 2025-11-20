package org.firstinspires.ftc.teamcode.Auto;

import com.arcrobotics.ftclib.command.CommandBase;
import com.pedropathing.geometry.Pose;

import org.firstinspires.ftc.teamcode.SubSystem.PathFollower;

public class GoRow1EndToTarget extends CommandBase {

    private PathFollower m_follower;
    private final Pose m_startPoseRed = new Pose(56, 12, Math.toRadians(0));;
    private final Pose m_endPoseRed = new Pose(50, 52, Math.toRadians(40));
    private final Pose m_startPose;
    private final Pose m_endPose;

    public GoRow1EndToTarget(PathFollower follower) {
        m_follower = follower;
        m_startPose = m_startPoseRed;
        m_endPose = m_endPoseRed;
        addRequirements(m_follower);
    }
   
    @Override
    public void initialize () {
        m_follower.startFollowPath(m_startPose, m_endPose, 1);
    }

    @Override
    public boolean isFinished() {
        return m_follower.followFinished();
    }
}


