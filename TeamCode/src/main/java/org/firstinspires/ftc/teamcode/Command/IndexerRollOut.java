package org.firstinspires.ftc.teamcode.Command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.SubSystem.Indexer;

public class IndexerRollOut extends CommandBase {
    private Indexer m_indexer;
    private long startTime;
    private static final long DURATION = 1000;

    public IndexerRollOut(Indexer indexer) {
        m_indexer = indexer;
        addRequirements(indexer);
    }

    @Override
    public void initialize () {
        startTime = System.currentTimeMillis();
        m_indexer.RollOut();
    }

    @Override
    public boolean isFinished() {
        return System.currentTimeMillis() - startTime > DURATION;
    }

    @Override
    public void end(boolean interrupted) {
        m_indexer.Stop();
    }
}
