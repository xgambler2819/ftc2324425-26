package org.firstinspires.ftc.teamcode.Command;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.SubSystem.Indexer;

public class IndexerRollForward extends CommandBase {
    private Indexer m_indexer;

    public IndexerRollForward(Indexer indexer) {
        m_indexer = indexer;
        addRequirements(indexer);
    }

    @Override
    public void initialize () {
        m_indexer.RollForward();
    }

    @Override
    public boolean isFinished() { return true;}
}
