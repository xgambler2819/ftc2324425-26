package org.firstinspires.ftc.teamcode.Command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.SubSystem.Indexer;

public class IndexerMove extends CommandBase {
    private Indexer m_indexer;

    final double m_power;
    public IndexerMove(Indexer indexer, double power) {
        m_indexer = indexer;
        m_power = power;
        addRequirements(m_indexer);
    }

    @Override
    public void initialize () {
        m_indexer.move(m_power);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
