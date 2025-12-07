package org.firstinspires.ftc.teamcode.Command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.SubSystem.Indexer;

public class IndexerStepUp extends CommandBase {
    private Indexer m_indexer;

    private long m_startTime;

    public IndexerStepUp(Indexer indexer) {
        m_indexer = indexer;
        addRequirements(m_indexer);
    }

    @Override
    public void initialize () {
        m_indexer.RollUp();
        m_startTime = System.currentTimeMillis();
    }

    @Override
    public boolean isFinished() {
        return System.currentTimeMillis() - m_startTime > 700;
    }

    @Override
    public void end(boolean interrupted) {
        m_indexer.Stop();
    }
}


