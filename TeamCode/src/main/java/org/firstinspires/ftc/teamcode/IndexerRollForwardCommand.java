package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class IndexerRollForwardCommand extends CommandBase {
    private IndexerSubsystem m_Indexer;
    private Telemetry m_telemetry;

    public IndexerRollForwardCommand(IndexerSubsystem indexer, Telemetry telemetry) {
        m_Indexer = indexer;
        m_telemetry = telemetry;
    }

    @Override
    public void initialize () {
    }
    @Override
    public void execute() {
        m_Indexer.Rollfoward();
    }


}
