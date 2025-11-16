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

    private Intake m_intake;
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

        m_intake = new Intake(hardwareMap, telemetry);
        m_indexer = new Indexer(hardwareMap, telemetry);
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

                new IntakeRollIn(m_intake),
                new IndexerMove(m_indexer, 1),
                new WaitCommand(3000),
                new ShooterStop(m_shooter),
                new IndexerMove(m_indexer, 0),

                new DriveAutoBackward(m_drivetrain),
                new DriveAutoTurn(m_drivetrain, turn),
                new DriveAutoForward(m_drivetrain)
        );
        schedule(autoSequences);

        while (!isStopRequested() && opModeIsActive())
        {
            run();
        }
        reset();
    }

 }