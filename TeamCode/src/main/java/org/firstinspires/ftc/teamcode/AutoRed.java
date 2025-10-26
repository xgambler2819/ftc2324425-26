package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Command.DriveAutoBackward;
import org.firstinspires.ftc.teamcode.Command.DriveDefault;
import org.firstinspires.ftc.teamcode.Command.IndexerRollForward;
import org.firstinspires.ftc.teamcode.Command.IndexerStop;
import org.firstinspires.ftc.teamcode.Command.ShooterReachTarget;
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
                new WaitCommand(3000),
                new IndexerRollForward(m_indexer),
                new WaitCommand(9000),
                new IndexerStop(m_indexer),
                new DriveAutoBackward(m_drivetrain)
        );
        schedule(autoSequences);
        while (!isStopRequested() && opModeIsActive())
        {
            run();
        }
        reset();
    }

 }