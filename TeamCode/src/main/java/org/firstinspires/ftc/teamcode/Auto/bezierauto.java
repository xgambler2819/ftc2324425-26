package org.firstinspires.ftc.teamcode.Auto;

import com.arcrobotics.ftclib.command.*;
import org.firstinspires.ftc.teamcode.Command.*;
import org.firstinspires.ftc.teamcode.SubSystem.*;
import com.pedropathing.geometry.Pose;

public class bezierauto extends CommandOpMode {

    private PathFollower m_follower;
    private Intake m_intake;
    private Indexer m_indexer;
    private Shooter m_shooter;

    private final boolean m_isRed;

    public bezierauto(boolean isRed)
    {
        m_isRed = isRed; //TODO watch out for this
    }
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
                new IndexerMove(m_indexer, 1),
                new IntakeRollIn(m_intake),
                new WaitCommand(3000),
                new ParallelCommandGroup(
                        new ShooterStop(m_shooter),
                        new IndexerMove(m_indexer, 0),
                        new IntakeStop(m_intake)
                )
        );

        ParallelCommandGroup pickrow1 = new ParallelCommandGroup(
                new FollowPath(m_follower, row1(), row1end(), 1),
                new SequentialCommandGroup(
                        new IntakeRollIn(m_intake),
                        new WaitCommand(1500),
                        new IndexerStepUp(m_indexer)
                )
        );
        ParallelCommandGroup pickrow2 = new ParallelCommandGroup(
                new FollowPath(m_follower, row2(), row2end(), 1),
                new SequentialCommandGroup(
                        new IntakeRollIn(m_intake),
                        new WaitCommand(1500),
                        new IndexerStepUp(m_indexer)
                )
        );
        ParallelCommandGroup pickrow3 = new ParallelCommandGroup(
                new FollowPath(m_follower, row3(), row3end(), 1),
                new SequentialCommandGroup(
                        new IntakeRollIn(m_intake),
                        new WaitCommand(1500),
                        new IndexerStepUp(m_indexer)
                )
        );


        SequentialCommandGroup autoSequences = new SequentialCommandGroup(
                new ShooterTargetLow(m_shooter),
                new ShooterReachTarget(m_shooter, telemetry),
                shoot,
                new FollowBezierPath(m_follower, startpos(),controlrow1(), row1(), 1),
                pickrow1,
                new FollowBezierPath(m_follower, row1end(), controlrelease(), release(), 1),
                new ParallelCommandGroup(
                        new FollowPath(m_follower, release(), shootpos(), 1),
                        new SequentialCommandGroup(
                        new IndexerStepDown(m_indexer),
                        new ShooterTargetLow(m_shooter),
                        new ShooterReachTarget(m_shooter, telemetry)
                        )
                ),
                shoot,
                new FollowPath(m_follower, shootpos(), row2(), 1),
                pickrow2,
                new ParallelCommandGroup(
                        new FollowBezierPath(m_follower, row2end(), controlshoot2(), shootpos(), 1),
                        new SequentialCommandGroup(
                                new IndexerStepDown(m_indexer),
                                new ShooterTargetLow(m_shooter),
                                new ShooterReachTarget(m_shooter, telemetry)
                        )
                        ),
                shoot,
                new FollowBezierPath(m_follower, shootpos(), controlrow3(), row3(), 1),
                pickrow3,
                new ParallelCommandGroup(
                        new FollowPath(m_follower, row3end(), shootpos(), 1),
                        new SequentialCommandGroup(
                                new IndexerStepDown(m_indexer),
                                new ShooterTargetLow(m_shooter),
                                new ShooterReachTarget(m_shooter, telemetry)
                        )
                ),
                shoot,
                new FollowPath(m_follower, shootpos(), endingpos(), 1)

        );
        schedule(autoSequences);

        while (!isStopRequested() && opModeIsActive())
        {
            run();
        }
        reset();
    }

    Pose startpos()
    {
        final Pose poseRed = new Pose(122.53726169844022, 124.03466204506067, Math.toRadians(36));
        final Pose poseBlue = new Pose(-50, 52, Math.toRadians(180-40));
        return m_isRed ? poseRed : poseBlue;
    }
    Pose shootpos()
    {
        final Pose poseRed = new Pose(105.90025359256128, 109.9171597633136, Math.toRadians(36));
        final Pose poseBlue = new Pose(-50, 52, Math.toRadians(180-40));
        return m_isRed ? poseRed : poseBlue;
    }

    Pose row1()
    {
        final Pose poseRed = new Pose(103.22231614539307, 84, Math.toRadians(0));
        final Pose poseBlue = new Pose(-24, 12, Math.toRadians(180));
        return m_isRed ? poseRed : poseBlue;
    }

    Pose row1end()
    {
        final Pose poseRed = new Pose(128, 84, Math.toRadians(0));
        final Pose poseBlue = new Pose(-56, 12, Math.toRadians(180));
        return m_isRed ? poseRed : poseBlue;
    }
    Pose release()
    {
        final Pose poseRed = new Pose(127.77816291161177, 74.62045060658579, Math.toRadians(180));
        final Pose poseBlue = new Pose(-56, 12, Math.toRadians(180));
        return m_isRed ? poseRed : poseBlue;
    }

    Pose row2()
    {
        final Pose poseRed = new Pose(102.97886728655959, 60, Math.toRadians(0));
        final Pose poseBlue = new Pose(-24, -12, Math.toRadians(180));
        return m_isRed ? poseRed : poseBlue;
    }
    Pose row2end()
    {
        final Pose poseRed = new Pose(126, 60, Math.toRadians(0));
        final Pose poseBlue = new Pose(-24, -12, Math.toRadians(180));
        return m_isRed ? poseRed : poseBlue;
    }
    Pose row3()
    {
        final Pose poseRed = new Pose(103.22231614539307, 36, Math.toRadians(0));
        final Pose poseBlue = new Pose(-24, -12, Math.toRadians(180));
        return m_isRed ? poseRed : poseBlue;
    }
    Pose row3end()
    {
        final Pose poseRed = new Pose(126, 36, Math.toRadians(0));
        final Pose poseBlue = new Pose(-24, -12, Math.toRadians(180));
        return m_isRed ? poseRed : poseBlue;
    }
    Pose endingpos()
    {
        final Pose poseRed = new Pose(118.07269653423499, 69.13947590870667, Math.toRadians(180));
        final Pose poseBlue = new Pose(-24, -12, Math.toRadians(180));
        return m_isRed ? poseRed : poseBlue;
    }
    Pose controlrow1()
    {
        final Pose poseRed = new Pose(92.5105663567202, 85.69399830938292);
        final Pose poseBlue = new Pose(-24, -12, Math.toRadians(180));
        return m_isRed ? poseRed : poseBlue;
    }
    Pose controlrelease()
    {
        final Pose poseRed = new Pose(77.36568457538995, 74.37088388214906);
        final Pose poseBlue = new Pose(-24, -12, Math.toRadians(180));
        return m_isRed ? poseRed : poseBlue;
    }
    Pose controlrow2()
    {
        final Pose poseRed = new Pose(95.4319526627219, 56.84530853761623);
        final Pose poseBlue = new Pose(-24, -12, Math.toRadians(180));
        return m_isRed ? poseRed : poseBlue;
    }
    Pose controlshoot2()
    {
        final Pose poseRed = new Pose(115.63820794590026, 55.62806424344887);
        final Pose poseBlue = new Pose(-24, -12, Math.toRadians(180));
        return m_isRed ? poseRed : poseBlue;
    }
    Pose controlrow3()
    {
        final Pose poseRed = new Pose(93.60608622147083, 32.37869822485207);
        final Pose poseBlue = new Pose(-24, -12, Math.toRadians(180));
        return m_isRed ? poseRed : poseBlue;
    }
}
