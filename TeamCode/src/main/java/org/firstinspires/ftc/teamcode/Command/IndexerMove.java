package org.firstinspires.ftc.teamcode.Command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.SubSystem.Indexer;

public class IndexerMove extends CommandBase {
    private Indexer m_indexer;

    final double IndexerMovePower = 0.75;
    final int m_direction;
    public IndexerMove(Indexer indexer, int direction) {
        m_indexer = indexer;
        m_direction = direction;
        addRequirements(m_indexer);
    }

    @Override
    public void initialize () {
        m_indexer.move(IndexerMovePower * m_direction);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
