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

    private SequentialCommandGroup CreateAutoSequence()
    {
        FollowPath startToTarget = new FollowPath(m_follower, getStartPose(), getTargetPose(), 1);

        SequentialCommandGroup shoot = new SequentialCommandGroup(
                new IndexerMove(m_indexer, 1),
                new IntakeRollIn(m_intake),
                new WaitCommand(2000),
                new ParallelCommandGroup(
                        new ShooterStop(m_shooter),
                        new IndexerMove(m_indexer, 0),
                        new IntakeStop(m_intake)
                )
        );

        FollowBezierPath targetToRow1Start = new FollowBezierPath(m_follower, getTargetPose(), getTargetRow1Control(), getRow1StartPose(),1);

        FollowPath row1StartToEnd = new FollowPath(m_follower, getRow1StartPose(), getRow1EndPose(), 0.5);
        ParallelCommandGroup pickUpBalls = new ParallelCommandGroup(
                new SequentialCommandGroup(row1StartToEnd, new WaitCommand(100)),
                new SequentialCommandGroup(
                        new WaitCommand(500),
                        new IndexerStepUp(m_indexer)
                )
        );
        
        FollowPath row1EndToTarget = new FollowPath(m_follower, getRow1EndPose(), getTargetPose(), 1);
       
        FollowBezierPath targetToRow2Start = new FollowBezierPath(m_follower, getTargetPose(), getTargetRow2Control(), getRow2StartPose(),1);
        
        FollowBezierPath row2EndToTarget = new FollowBezierPath(m_follower, getRow2EndPose(), getRow2EndToTargetControl(), getTargetPose(),1);

        SequentialCommandGroup autoSequence = new SequentialCommandGroup(
                new ParallelCommandGroup(
                    new SequentialCommandGroup(startToTarget, new WaitCommand(100)),
                    new SequentialCommandGroup(
                            new ShooterTargetLow(m_shooter),
                            new ShooterReachTarget(m_shooter, telemetry)
                    )),
                shoot,
                new IntakeRollIn(m_intake),
                targetToRow1Start, new WaitCommand(100),
                pickUpBalls,
                new ParallelCommandGroup(
                    new SequentialCommandGroup(row1EndToTarget,new WaitCommand(100)),
                    new SequentialCommandGroup(
                            new ShooterTargetLow(m_shooter),
                            new ShooterReachTarget(m_shooter, telemetry)
                    )),
                shoot,
                new IntakeRollIn(m_intake),
                targetToRow2Start, new WaitCommand(100),
                pickUpBalls,
                new ParallelCommandGroup(
                    new SequentialCommandGroup(row2EndToTarget,new WaitCommand(100)),
                    new SequentialCommandGroup(
                            new ShooterTargetLow(m_shooter),
                            new ShooterReachTarget(m_shooter, telemetry)
                    )),
                shoot
                /*,
                new FollowPath(m_follower, getTargetPose(), getRow2StartPose(), 1),
                pickUpBalls,
                new IntakeStop(m_intake)
                */
        );
        return autoSequence;
    }

    
    @Override
    public void runOpMode() throws InterruptedException{
        initialize();
        waitForStart();

        SequentialCommandGroup autoSequence = CreateAutoSequence();
        schedule(autoSequence);
        while (!isStopRequested() && opModeIsActive())
        {
            run();
        }
        reset();
    }

    Pose getStartPose()
    {
        final Pose poseRed = new Pose(44, 56, Math.toRadians(36));
        final Pose poseBlue = new Pose(-44, 56, Math.toRadians(180-36));
        return m_isRed ? poseRed : poseBlue;
    }

    Pose getTargetPose()
    {
        final Pose poseRed = new Pose(26, 42, Math.toRadians(36));
        final Pose poseBlue = new Pose(-26, 42, Math.toRadians(180-36));
        return m_isRed ? poseRed : poseBlue;
    }

    Pose getTargetRow1Control()
    {
        final Pose poseRed = new Pose(-5, 12, Math.toRadians(0));
        final Pose poseBlue = new Pose(5, 12, Math.toRadians(180));
        return m_isRed ? poseRed : poseBlue;
    }

    Pose getRow1StartPose()
    {
        final Pose poseRed = new Pose(25, 12, Math.toRadians(0));
        final Pose poseBlue = new Pose(-25, 12, Math.toRadians(180));
        return m_isRed ? poseRed : poseBlue;
    }

    Pose getRow1EndPose()
    {
        final Pose poseRed = new Pose(45, 12, Math.toRadians(0));
        final Pose poseBlue = new Pose(-45, 12, Math.toRadians(180));
        return m_isRed ? poseRed : poseBlue;
    }

    Pose getTargetRow2Control()
    {
        final Pose poseRed = new Pose(-5, -12, Math.toRadians(0));
        final Pose poseBlue = new Pose(5, -12, Math.toRadians(180));
        return m_isRed ? poseRed : poseBlue;
    }

    Pose getRow2StartPose()
    {
        final Pose poseRed = new Pose(25, -12, Math.toRadians(0));
        final Pose poseBlue = new Pose(-25, -12, Math.toRadians(180));
        return m_isRed ? poseRed : poseBlue;
    }

    Pose getRow2EndPose()
    {
        final Pose poseRed = new Pose(45, -12, Math.toRadians(0));
        final Pose poseBlue = new Pose(-45, -12, Math.toRadians(180));
        return m_isRed ? poseRed : poseBlue;
    }

    Pose getRow2EndToTargetControl()
    {
        final Pose poseRed = new Pose(-20, -12, Math.toRadians(30));
        final Pose poseBlue = new Pose(20, 12, Math.toRadians(180-30));
        return m_isRed ? poseRed : poseBlue;
    }
 }