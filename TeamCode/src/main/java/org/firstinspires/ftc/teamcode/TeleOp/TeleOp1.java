package org.firstinspires.ftc.teamcode.TeleOp;

import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.command.CommandOpMode;

import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Command.*;
import org.firstinspires.ftc.teamcode.SubSystem.*;


@TeleOp(name = "TeleOp1")
public class TeleOp1 extends CommandOpMode {

    private Indexer m_indexer;
    private Shooter m_shooter;
    private DriveTrain m_drivetrain;
    private GamepadEx m_gamepad1;
    private GamepadEx m_gamepad2;
    private DriveDefault m_driveDefault;    

    @Override
    public void initialize() {
        m_gamepad1 = new GamepadEx(gamepad1);
        m_gamepad2 = new GamepadEx(gamepad2);

        m_drivetrain = new DriveTrain(hardwareMap, telemetry);
        m_driveDefault = new DriveDefault(m_drivetrain, m_gamepad1);

        m_indexer = new Indexer(hardwareMap,telemetry);

        Button pad2_start = new GamepadButton(m_gamepad2, GamepadKeys.Button.START);
        pad2_start.whenPressed(new IndexerRollForward(m_indexer));
        Button pad2_back = new GamepadButton(m_gamepad2, GamepadKeys.Button.BACK);
        pad2_back.whenPressed(new IndexerRollBack(m_indexer));
        Button pad2_x = new GamepadButton(m_gamepad2, GamepadKeys.Button.X);
        pad2_x.whenPressed(new IndexerStop(m_indexer));
        Button pad2_lb = new GamepadButton(m_gamepad2, GamepadKeys.Button.LEFT_BUMPER);
        pad2_lb.whenPressed(new IndexerRollOut(m_indexer));

        m_shooter = new Shooter(hardwareMap, telemetry);

        Button pad2_y = new GamepadButton(m_gamepad2, GamepadKeys.Button.Y);
        pad2_y.whenPressed(new ShooterTargetHigh(m_shooter));
        Button pad2_a = new GamepadButton(m_gamepad2, GamepadKeys.Button.A);
        pad2_a.whenPressed(new ShooterTargetLow(m_shooter));
        Button pad2_b = new GamepadButton(m_gamepad2, GamepadKeys.Button.B);
        pad2_b.whenPressed(new ShooterStop(m_shooter));
        Button pad2_rb = new GamepadButton(m_gamepad2, GamepadKeys.Button.RIGHT_BUMPER);
        pad2_rb.whenPressed(new ShooterBack(m_shooter));

        register(m_drivetrain, m_indexer, m_shooter);
        m_drivetrain.setDefaultCommand(m_driveDefault);
    }
 }