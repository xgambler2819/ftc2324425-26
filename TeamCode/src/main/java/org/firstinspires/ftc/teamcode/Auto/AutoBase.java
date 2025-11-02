package org.firstinspires.ftc.teamcode.Auto;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.Command.*;
import org.firstinspires.ftc.teamcode.Command.ShooterReachTarget;
import org.firstinspires.ftc.teamcode.Command.ShooterStop;
import org.firstinspires.ftc.teamcode.Command.ShooterTargetLow;
import org.firstinspires.ftc.teamcode.SubSystem.DriveTrain;
import org.firstinspires.ftc.teamcode.SubSystem.Indexer;
import org.firstinspires.ftc.teamcode.SubSystem.Shooter;
import org.firstinspires.ftc.teamcode.SubSystem.Intake;

public class AutoBase extends CommandOpMode {
    private Indexer m_indexer;
    private Intake m_intake;
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
        m_intake = new Intake(hardwareMap, telemetry);
        m_shooter = new Shooter(hardwareMap, telemetry);
        m_shooter.setStop();
        register(m_drivetrain, m_intake, m_indexer, m_shooter);


    }

    @Override
    public void runOpMode() throws InterruptedException{
        initialize();
        waitForStart();

        double turn = m_isRed ? -.45 : 0.45;
        double turnAngle = m_isRed ? 50 : -50;

        SequentialCommandGroup autoSequences = new SequentialCommandGroup(
                new ShooterTargetLow(m_shooter),
                new ShooterReachTarget(m_shooter, telemetry),
                new IndexerUp(m_indexer),
                new IntakeRollIn(m_intake),
                new WaitCommand(8000),
                new ShooterStop(m_shooter),
                new IndexerStop(m_indexer),
                new DriveAutoBackward(m_drivetrain),
                new DriveAutoTurn(m_drivetrain, turn),
                //new DriveTurnTo(m_drivetrain, telemetry, turn, 1200),
                new ParallelCommandGroup(
                    new DriveAutoForward(m_drivetrain),
                    new SequentialCommandGroup(
                        new WaitCommand(2000),
                        new IndexerStepUp(m_indexer))
                )
        );
        schedule(autoSequences);

        while (!isStopRequested() && opModeIsActive())
        {
            run();
        }
        reset();
    }

 }