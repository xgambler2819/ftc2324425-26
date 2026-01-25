package org.firstinspires.ftc.teamcode.Command;

import com.arcrobotics.ftclib.command.CommandBase;
import com.pedropathing.*;
import com.pedropathing.geometry.Pose;

import org.firstinspires.ftc.teamcode.SubSystem.PathFollower;
import org.firstinspires.ftc.teamcode.SubSystem.LimeLightSubSystem;

public class PedroDriveTurnToLL extends CommandBase {

    private PathFollower m_follower;
    private LimeLightSubSystem m_limeLight;


    public PedroDriveTurnToLL(PathFollower follower, LimeLightSubSystem limeLight) {
        m_follower = follower;
        m_limeLight = limeLight;
        addRequirements(m_limeLight);
    }

    @Override
    public void initialize () {
        Pose llPose = m_limeLight.getLasePose();
        Pose startPose = null;
        if (llPose != null)
        {
            startPose = llPose;
        }
        double angle = Math.atan2((62- startPose.getY()) , (62-startPose.getX()));
        Pose endPose = new Pose(startPose.getX(), startPose.getY(), angle);
        m_follower.startFollowPath(startPose, endPose, 0.9);
    }

    @Override
    public boolean isFinished() {
        return m_follower.followFinished();
    }
}



