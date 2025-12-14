package org.firstinspires.ftc.teamcode.Auto;

import com.arcrobotics.ftclib.command.*;
import org.firstinspires.ftc.teamcode.Command.*;
import org.firstinspires.ftc.teamcode.SubSystem.*;
import com.pedropathing.geometry.Pose;

public class AutoBaseSquare extends CommandOpMode {

    private PathFollower m_follower;
    private LimeLightSubSystem m_limelight;
    private Intake m_intake;
    private Indexer m_indexer;
    private Shooter m_shooter;

    private final boolean m_isRed;

    public AutoBaseSquare(boolean isRed)
    {
        m_isRed = isRed;
    }
    @Override
    public void initialize() {
        m_follower = new PathFollower(hardwareMap, telemetry);
        m_follower.RecalibrateIMU();
        m_limelight = new LimeLightSubSystem(hardwareMap, telemetry);
        m_intake = new Intake(hardwareMap, telemetry);
        m_indexer = new Indexer(hardwareMap, telemetry);
        m_shooter = new Shooter(hardwareMap, telemetry);
        m_shooter.setStop();
        register(m_follower, m_limelight, m_intake, m_indexer, m_shooter);
    }

    private SequentialCommandGroup CreateAutoSequence()
    {
        Pose poseOrigin = new Pose(0, 0, Math.toRadians(0));
        Pose poseX = new Pose(48, 0, Math.toRadians(90));
        Pose poseXY = new Pose(48, 48, Math.toRadians(180));
        Pose poseY = new Pose(0, 48, Math.toRadians(270));
      
        SequentialCommandGroup autoSequence = new SequentialCommandGroup(
            //new FollowerStop(m_follower),
            new WaitCommand(3000),
            new FollowPath(m_follower, poseOrigin, poseX, 1),
            //new FollowerStop(m_follower),
            new WaitCommand(3000),
            new FollowPath(m_follower, poseX, poseXY, 1),
            //new FollowerStop(m_follower),
            new WaitCommand(3000),
            new FollowPath(m_follower, poseXY, poseY, 1),
            //new FollowerStop(m_follower),
            new WaitCommand(3000),
            new FollowPath(m_follower, poseY, poseOrigin, 1)
            //new FollowerStop(m_follower)
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
 }