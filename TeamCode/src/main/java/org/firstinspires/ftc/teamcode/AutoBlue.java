package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Command.DriveAutoBackward;
import org.firstinspires.ftc.teamcode.Command.IndexerRollForward;
import org.firstinspires.ftc.teamcode.Command.IndexerStop;
import org.firstinspires.ftc.teamcode.Command.ShooterTargetLow;
import org.firstinspires.ftc.teamcode.SubSystem.DriveTrain;
import org.firstinspires.ftc.teamcode.SubSystem.Indexer;
import org.firstinspires.ftc.teamcode.SubSystem.Shooter;


@Autonomous(name = "AutoBlue")
public class AutoBlue extends CommandOpMode {

    private Indexer m_indexer;
    private Shooter m_shooter;
    private DriveTrain m_drivetrain;


    @Override
    public void initialize() {

        m_drivetrain = new DriveTrain(hardwareMap, telemetry);

        m_indexer = new Indexer(hardwareMap,telemetry);

        m_shooter = new Shooter(hardwareMap, telemetry);

        register(m_drivetrain, m_indexer, m_shooter);

    }
    @Override
    public void runOpMode() throws InterruptedException{
        initialize();

        waitForStart();

        SequentialCommandGroup autoSequences = new SequentialCommandGroup(
                new ShooterTargetLow(m_shooter),
                new WaitCommand(2000),
                new IndexerRollForward(m_indexer),
                new WaitCommand(8000),
                new IndexerStop(m_indexer),
                new DriveAutoBackward(m_drivetrain, 0.5)
        );
        schedule(autoSequences);
        while (!isStopRequested() && opModeIsActive())
        {
            run();
        }
        reset();
    }

 }