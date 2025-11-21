package org.firstinspires.ftc.teamcode.Auto;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Command.DriveTimedMove;
import org.firstinspires.ftc.teamcode.SubSystem.DriveTrain;
import org.firstinspires.ftc.teamcode.SubSystem.Shooter;
import org.firstinspires.ftc.teamcode.SubSystem.Intake;
@Autonomous(name = "AutoForward")
public class AutoForward extends CommandOpMode {

    private Intake m_intake;
    private Shooter m_shooter;
    private DriveTrain m_drivetrain;

    public AutoForward()
    {
    }
    @Override
    public void initialize() {
        m_drivetrain = new DriveTrain(hardwareMap, telemetry);

        m_intake = new Intake(hardwareMap, telemetry);
        m_shooter = new Shooter(hardwareMap, telemetry);
        m_shooter.setStop();
        register(m_drivetrain, m_intake, m_shooter);
    }

    @Override
    public void runOpMode() throws InterruptedException{
        initialize();
        waitForStart();
        SequentialCommandGroup autoSequences = new SequentialCommandGroup(
                new DriveTimedMove(m_drivetrain, 0, -0.5, 0,1000)
        );
        schedule(autoSequences);

        while (!isStopRequested() && opModeIsActive())
        {
            run();
        }
        reset();
    }

}
