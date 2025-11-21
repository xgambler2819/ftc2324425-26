package org.firstinspires.ftc.teamcode.Auto;

import com.arcrobotics.ftclib.command.*;
import org.firstinspires.ftc.teamcode.Command.*;
import org.firstinspires.ftc.teamcode.SubSystem.*;
import com.pedropathing.geometry.Pose;

public class AutoBase extends CommandOpMode {

    private PathFollower m_follower;
    private Intake m_intake;
    private Indexer m_indexer;
    private Shooter m_shooter;

    private final boolean m_isRed;

    public AutoBase(boolean isRed)
    {
        m_isRed = isRed;
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
                new IndexerStepDown(m_indexer),
                new ShooterTargetLow(m_shooter),
                new ShooterReachTarget(m_shooter, telemetry),
                new IntakeRollIn(m_intake),
                new IndexerMove(m_indexer, 1),
                new WaitCommand(3000),
                new ShooterStop(m_shooter),
                new IndexerMove(m_indexer, 0),
                new IntakeStop(m_intake));

        ParallelCommandGroup pickUpBalls = new ParallelCommandGroup(
                new FollowPath(m_follower, getRow1StartPose(), getRow1EndPose(), 0.3),
                new SequentialCommandGroup(
                        new IntakeRollIn(m_intake),
                        new WaitCommand(1000),
                        new IndexerStepUp(m_indexer),
                        new WaitCommand(200)));

        SequentialCommandGroup autoSequences = new SequentialCommandGroup(
                shoot,
                new FollowPath(m_follower, getTargetPose(), getRow1StartPose(), 1),
                pickUpBalls,
                new FollowPath(m_follower, getRow1EndPose(), getTargetPose(), 1),
                shoot,
                new FollowPath(m_follower, getTargetPose(), getRow2StartPose(), 1),
                pickUpBalls
        );
        schedule(autoSequences);

        while (!isStopRequested() && opModeIsActive())
        {
            run();
        }
        reset();
    }

    Pose getTargetPose()
    {
        final Pose poseRed = new Pose(50, 52, Math.toRadians(40));
        final Pose poseBlue = new Pose(-50, 52, Math.toRadians(180-40));
        return m_isRed ? poseRed : poseBlue;
    }

    Pose getRow1StartPose()
    {
        final Pose poseRed = new Pose(24, 12, Math.toRadians(0));
        final Pose poseBlue = new Pose(-24, 12, Math.toRadians(180));
        return m_isRed ? poseRed : poseBlue;
    }

    Pose getRow1EndPose()
    {
        final Pose poseRed = new Pose(56, 12, Math.toRadians(0));
        final Pose poseBlue = new Pose(-56, 12, Math.toRadians(180));
        return m_isRed ? poseRed : poseBlue;
    }

    Pose getRow2StartPose()
    {
        final Pose poseRed = new Pose(24, -12, Math.toRadians(0));
        final Pose poseBlue = new Pose(-24, -12, Math.toRadians(180));
        return m_isRed ? poseRed : poseBlue;
    }
 }