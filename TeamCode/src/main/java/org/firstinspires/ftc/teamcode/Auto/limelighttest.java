package org.firstinspires.ftc.teamcode.Auto;

import com.arcrobotics.ftclib.command.*;
import org.firstinspires.ftc.teamcode.Command.*;
import org.firstinspires.ftc.teamcode.SubSystem.*;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "limelighttest")
public class limelighttest extends CommandOpMode {

    private PathFollower m_follower;
    private Shooter m_shooter;
    private LimeLightSubSystem m_limeLight;

    @Override
    public void initialize() {
        m_follower = new PathFollower(hardwareMap, telemetry);
        m_follower.RecalibrateIMU();

        m_limeLight = new LimeLightSubSystem(hardwareMap, telemetry);

        m_shooter = new Shooter(hardwareMap, telemetry);
        m_shooter.setStop();
        register( m_limeLight,m_follower, m_shooter);
    }

    private SequentialCommandGroup CreateAutoSequence()
    {

        SequentialCommandGroup autoSequence = new SequentialCommandGroup(
                new LimeLightWaitPose(m_limeLight),
                new WaitCommand(5000),
                new FollowPathLL(m_follower, m_limeLight, new Pose(80, 80), target, 1)
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

Pose target = new Pose(103,107, Math.toRadians(36));
}
