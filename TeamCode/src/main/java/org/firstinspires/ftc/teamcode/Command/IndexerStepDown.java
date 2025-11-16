package org.firstinspires.ftc.teamcode.Command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.SubSystem.Indexer;

public class IndexerStepDown extends CommandBase {
    private Indexer m_indexer;

    private long m_startTime;

    public IndexerStepDown(Indexer indexer) {
        m_indexer = indexer;
        addRequirements(m_indexer);
    }

    @Override
    public void initialize () {
        m_indexer.RollDown();
        m_startTime = System.currentTimeMillis();
    }

    @Override
    public boolean isFinished() {
        return System.currentTimeMillis() - m_startTime > 600;
    }
}


