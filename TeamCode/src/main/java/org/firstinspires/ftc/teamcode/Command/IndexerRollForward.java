package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class IndexerRollForward extends CommandBase {
    private IndexerSubsystem m_Indexer;
    private Telemetry m_telemetry;

    public IndexerRollForward(IndexerSubsystem indexer, Telemetry telemetry) {
        m_Indexer = indexer;
        m_telemetry = telemetry;
    }

    @Override
    public void initialize () {
        m_Indexer.RollForward();
    }
}
