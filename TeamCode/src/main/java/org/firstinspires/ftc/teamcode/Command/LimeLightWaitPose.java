package org.firstinspires.ftc.teamcode.Command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.SubSystem.LimeLightSubSystem;

public class LimeLightWaitPose extends CommandBase {
    private LimeLightSubSystem m_limeLight;
    private long m_startTime;

    public LimeLightWaitPose(LimeLightSubSystem limeLight) {
        m_limeLight = limeLight;
        addRequirements(m_limeLight);
    }

    @Override
    public void initialize () {
        m_startTime = System.currentTimeMillis();
    }

    @Override
    public boolean isFinished() {
        return m_limeLight.getLastTime() > m_startTime && m_limeLight.getLasePose() != null;
    }
}


