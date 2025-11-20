package org.firstinspires.ftc.teamcode.Auto;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.SubSystem.PathFollower;
import org.firstinspires.ftc.teamcode.SubSystem.Indexer;
import org.firstinspires.ftc.teamcode.SubSystem.Shooter;
import org.firstinspires.ftc.teamcode.SubSystem.Intake;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.Command.*;
@Autonomous(name = "AutoPathRed")
public class AutoPathRed extends CommandOpMode {
    public AutoPathRed()
    {
    }

    private PathFollower m_follower;
    private Intake m_intake;
    private Indexer m_indexer;
    private Shooter m_shooter;


    @Override
    public void initialize() {
        m_follower = new PathFollower(hardwareMap, telemetry);

        m_intake = new Intake(hardwareMap, telemetry);
        m_indexer = new Indexer(hardwareMap, telemetry);
        m_shooter = new Shooter(hardwareMap, telemetry);
        m_shooter.setStop();
        register(m_follower, m_intake, m_indexer, m_shooter);
    }

    @Override
    public void runOpMode() throws InterruptedException{
        initialize();
        waitForStart();

        SequentialCommandGroup shoot = new SequentialCommandGroup(
                new ShooterTargetLow(m_shooter),
                new ShooterReachTarget(m_shooter, telemetry),
                new IntakeRollIn(m_intake),
                new IndexerMove(m_indexer, 1),
                new WaitCommand(3000),
                new ShooterStop(m_shooter),
                new IndexerMove(m_indexer, 0),
                new IntakeStop(m_intake));

        ParallelCommandGroup pickUpBalls = new ParallelCommandGroup(
                new GoForwardPickup(m_follower),
                new SequentialCommandGroup(
                        new IntakeRollIn(m_intake),
                        new WaitCommand(1000),
                        new IndexerStepUp(m_indexer),
                        new WaitCommand(200)));

        SequentialCommandGroup autoSequences = new SequentialCommandGroup(
                shoot,
                new GoTargetToRow1Start(m_follower),
                pickUpBalls,
                new GoRow1EndToTarget(m_follower),
                new IndexerStepDown(m_indexer),
                shoot,
                new GoTargetToRow2Start(m_follower),
                pickUpBalls
        );
        schedule(autoSequences);

        while (!isStopRequested() && opModeIsActive())
        {
            run();
        }
        reset();
    }
 }