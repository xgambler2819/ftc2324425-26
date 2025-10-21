package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class IndexerRollBack extends CommandBase {
    private Indexer m_Indexer;
    private Telemetry m_telemetry;
    private long startTime;
    private static final long DURATION = 1000; // 1 second

    public IndexerRollBack(Indexer indexer, Telemetry telemetry) {
        m_Indexer = indexer;
        m_telemetry = telemetry;
        addRequirements(indexer);
    }

    @Override
    public void initialize () {
        startTime = System.currentTimeMillis();
    @Override
    public void execute() {
        m_Indexer.Rollbackward();
    }

    @Override
    public boolean isFinished() {
        return System.currentTimeMillis() - startTime > DURATION;
    }

    @Override
    public void end(boolean interrupted) {
        m_Indexer.Stop();
    }

}
