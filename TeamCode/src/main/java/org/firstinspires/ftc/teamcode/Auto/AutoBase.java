package org.firstinspires.ftc.teamcode.Auto;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Command.IndexerRollForward;
import org.firstinspires.ftc.teamcode.Command.IndexerStop;
import org.firstinspires.ftc.teamcode.Command.ShooterReachTarget;
import org.firstinspires.ftc.teamcode.Command.ShooterStop;
import org.firstinspires.ftc.teamcode.Command.ShooterTargetLow;
import org.firstinspires.ftc.teamcode.SubSystem.DriveTrain;
import org.firstinspires.ftc.teamcode.SubSystem.Indexer;
import org.firstinspires.ftc.teamcode.SubSystem.Shooter;

public class AutoBase extends CommandOpMode {
    private Indexer m_indexer;
    private Shooter m_shooter;
    private DriveTrain m_drivetrain;

    private final boolean m_isRed;
    public AutoBase(boolean isRed)
    {
        m_isRed = isRed;
    }
    @Override
    public void initialize() {
        m_drivetrain = new DriveTrain(hardwareMap, telemetry);
        m_indexer = new Indexer(hardwareMap,telemetry);
        m_shooter = new Shooter(hardwareMap, telemetry);
        register(m_drivetrain, m_indexer, m_shooter);

        double turn = 0.5;
        if (m_isRed) { turn = -0.5;}

        SequentialCommandGroup autoSequences = new SequentialCommandGroup(
                new ShooterTargetLow(m_shooter),
                new ShooterReachTarget(m_shooter, telemetry),
                new IndexerRollForward(m_indexer),
                new WaitCommand(8000),
                new ShooterStop(m_shooter),
                new DriveAutoBackward(m_drivetrain),
                new DriveAutoTurn(m_drivetrain, turn),
                new IndexerRollForward(m_indexer),
                new DriveAutoForward(m_drivetrain),
                new IndexerStop(m_indexer)
        );
        schedule(autoSequences);
    }
 }