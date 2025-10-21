package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class IndexerStop extends CommandBase {
    private IndexerSubsystem m_Indexer;
    private Telemetry m_telemetry;

    public IndexerStop(IndexerSubsystem indexer, Telemetry telemetry) {
        m_Indexer = indexer;
        m_telemetry = telemetry;
        addRequirements(indexer);
    }

    @Override
    public void initialize () {
        m_Indexer.Stop();
    }
}
