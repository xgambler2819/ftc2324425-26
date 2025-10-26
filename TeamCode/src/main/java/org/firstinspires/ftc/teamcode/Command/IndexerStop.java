package org.firstinspires.ftc.teamcode.Command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.SubSystem.Indexer;

public class IndexerStop extends CommandBase {
    private Indexer m_indexer;
    private Telemetry m_telemetry;

    public IndexerStop(Indexer indexer) {
        m_indexer = indexer;
        addRequirements(indexer);
    }

    @Override
    public void initialize () {
        m_indexer.Stop();
    }

    @Override
    public boolean isFinished() { return true;}
}
